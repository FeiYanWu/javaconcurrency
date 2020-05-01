package com.wyf.concurrency.chapter25;

import com.wyf.concurrency.chapter19.WriteWorker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 多个线程监控某个共享变量，A线程监控到共享变量发生变化后即将触发某个动作，但是此时发现有另外一个线程B已经针对该变量的变化开始了行动，
 * 因此A便放弃了准备开始的动作，我们把这样的线程间交互成为Balking（犹豫）设计模式。其实这样的场景在生活中很常见，比如你去饭店吃饭，
 * 吃到途中想要再点一个小菜，于是你举起手示意服务员，其中一个服务员看到了你举手正准备走过来的时候，发现距离你
 * 比较近的服务员已经准备要受理你的请求于是中途放弃了。

 */
public class BalkingData {
    
    private final String fileName;
    
    private String content;
    
    private boolean changed;


    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }
    
    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }
    
    public synchronized void save() throws IOException{
        if(!changed){
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" calss dosave,content="+content);
        try (Writer writer=  new FileWriter(fileName,true)){
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
