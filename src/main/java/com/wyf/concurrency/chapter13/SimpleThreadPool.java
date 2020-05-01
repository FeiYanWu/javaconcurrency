package com.wyf.concurrency.chapter13;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool extends Thread{
    
    private int size;
    
    private final int queueSize;
    
    private final static int DEFAULT_SIZE = 10;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    
    private volatile static int seq = 0;
    
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();
    private final DiscardPolicy discardPolicy;
    
    private volatile boolean destroy = false;
    
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY =()-> {
        throw new DiscardException("discard this task");
    };
    
    private int min;
    private int max;
    private int active;

    @Override
    public void run() {
        while(!destroy){
           System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d.QueueSize:%d\n",
                   this.min,this.active,this.max,this.size,TASK_QUEUE.size());

            try {
                Thread.sleep(5_000);

                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max");
                    size = max;
                }
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=====reduce=======");

                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SimpleThreadPool(){
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }
    
    public SimpleThreadPool(int min, int active, int max, int queueSize,DiscardPolicy discardPolicy){
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for(int i=0;i<min;i++){
            createWorkTask();
        }
        this.size = min;
        this.start();
    }
    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP,THREAD_PREFIX+(seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }
    
    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }
    public static class DiscardException extends RuntimeException{
        public DiscardException(String message){
            super(message);
        }
    }
    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }
    public void submit(Runnable runnable){
        if(destroy){
            throw new IllegalStateException("the thread pool already destroy and not allow submit task");
        }
        synchronized (TASK_QUEUE){
            if(TASK_QUEUE.size()>queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }
    private static class WorkerTask extends Thread{
        private volatile TaskState taskState = TaskState.FREE;
        
        public WorkerTask(ThreadGroup group,String name){
            super(group,name);
        }
        
        public TaskState getTaskState(){
            return this.taskState;
        }
         
        @Override
        public void run() {
            
            OUTER:
            while(this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    
                    while(TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed");
                            break OUTER;
                        }
                       
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if(runnable!=null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
        
        
    }

    public void shutdown() throws InterruptedException {
        while(!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE){
        
        int intVal = THREAD_QUEUE.size();
            while (intVal>0){
                for(WorkerTask task:THREAD_QUEUE){
                    if(task.getTaskState() == TaskState.BLOCKED){
                        task.interrupt();
                        task.close();
                        intVal--;
    
                    }else{
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;

        System.out.println(GROUP.activeCount());
        System.out.println("The thread pool disposed");
    }
    
    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy(){
        return destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public static void main(String[] args) throws InterruptedException {
        //SimpleThreadPool threadPool = new SimpleThreadPool(6,10,SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        SimpleThreadPool threadPool = new SimpleThreadPool();

//        for(int i=0;i<40;i++){
//            threadPool.submit(()->{
//                System.out.println("The runnable is be serviced by "+Thread.currentThread());
//                try {
//                    Thread.sleep(10_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("The runnable is finished that serviced by "+Thread.currentThread());
//            });
//        }
        
        
        IntStream.rangeClosed(0,100).forEach(i->{
            threadPool.submit(()->{
                System.out.println("The runnable "+i+" is be serviced by "+Thread.currentThread());
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("The runnable "+i+" is finished that serviced by "+Thread.currentThread());
            });
        });


        Thread.sleep(5_000);
        //shutdown中的interrupt可能被上面sleep捕获
        threadPool.shutdown();
        
//        threadPool.submit(()->{
//            System.out.println("------------");
//        });
        
        
//        OUTER:  //outer label
//        for(int i = 0; i < 4; i++){
//            if(i % 2 == 0){
//                System.out.println("Odd number: " + i + ", continue from OUTER label");
//                continue OUTER;
//            }
//
//            INNER:  //inter label
//            for(int j = 0; j < 4; j++){
//                System.out.println("Even number: " + i + ", break  from INNER label");
//                break INNER;
//            }
//        }
    }
}
