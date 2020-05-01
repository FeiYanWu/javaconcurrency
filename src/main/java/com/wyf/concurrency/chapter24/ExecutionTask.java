package com.wyf.concurrency.chapter24;

import java.util.function.Consumer;

public class ExecutionTask implements Runnable{

    private QueryFromDBAction queryAction = new QueryFromDBAction();
    
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();
    @Override
    public void run() {
//        final Context context = new Context();
//        queryAction.execute(context);
//        System.out.println("The name query successful");
//        httpAction.execute(context);
//        System.out.println("The cardid query successful");
//        System.out.println("The name is "+context.getName()+" the cardId is "+context.getCardId());



        Context context = ActionContext.getActionContext().getContext();
        queryAction.execute();
        System.out.println("The name query successful");
        httpAction.execute();
        System.out.println("The cardid query successful");
        System.out.println("The name is "+context.getName()+" the cardId is "+context.getCardId());
    }
}
