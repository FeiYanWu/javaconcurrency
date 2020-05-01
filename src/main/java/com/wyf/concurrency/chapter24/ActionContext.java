package com.wyf.concurrency.chapter24;

import com.wyf.concurrency.chapter23.ThreadLocalSimulator;

public class ActionContext {
    private static ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };
    
    
    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }
    
    public static  ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }
    
    public Context getContext(){
        return threadLocal.get();
    }
}
