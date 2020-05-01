package com.wyf.concurrency.chapter21;
/*
        Future  ->  未来的凭据
        FutureTask ->  将你的调用逻辑进行隔离
        FutureService -> 桥接Future 和  task
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        /*String result = get();

        System.out.println(result);*/
        
        
//        FutureService futureService = new FutureService();
//        
//        Future<String> future = futureService.submit(()->{
//            try {
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "FINISHED";
//        });
//
//        System.out.println("=======================");
//        System.out.println("do other thing");
//        
//        Thread.sleep(1000);
//        System.out.println("=======================");
//
//        System.out.println(future.get());


        FutureService futureService = new FutureService();

        Future<String> future = futureService.submit(()->{
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISHED";
        },System.out::println);

        System.out.println("=======================");
        System.out.println("do other thing");

        Thread.sleep(1000);
        System.out.println("=======================");
    }
    
    private static String get() throws InterruptedException {
        Thread.sleep(10000);
        return "FINISH";
    }
}
