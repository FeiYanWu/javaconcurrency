package com.wyf.concurrency.chapter17;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    
    private List<Observer> observers = new ArrayList<>();
    
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if(this.state == state){
            return;
        }
        this.state = state;
        notifyAllObserver();
    }
    
    
    public void attach(Observer observer){
        observers.add(observer);
    }
    
    private void notifyAllObserver(){
        observers.forEach(Observer::update);
    }
}
