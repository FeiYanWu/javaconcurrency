package com.wyf.concurrency.chapter24;

public class QueryFromHttpAction {

//    public void execute(Context context){
//       String name = context.getName();
//        String cardId = getCardId(name);
//        context.setCardId(cardId);
//    }

    public void execute(){
        String name = ActionContext.getActionContext().getContext().getName();
        String cardId = getCardId(name);
        ActionContext.getActionContext().getContext().setCardId(cardId);
    }
    private String getCardId(String name){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "1235431235432"+ Thread.currentThread().getId();
    }
}
