package com.wyf.concurrency.chapter21;

public interface Future<T> {
    T get() throws InterruptedException;
}
