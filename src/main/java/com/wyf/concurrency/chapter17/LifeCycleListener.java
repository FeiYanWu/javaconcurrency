package com.wyf.concurrency.chapter17;

public interface LifeCycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
