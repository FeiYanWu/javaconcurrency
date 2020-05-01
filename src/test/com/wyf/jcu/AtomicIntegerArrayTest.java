package com.wyf.jcu;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    
    @Test
    public void testCreateAtomicIntegerArray(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        Assert.assertEquals(10,array.length());
    }

    @Test
    public void testGet(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        Assert.assertEquals(0,array.get(5));
    }

    @Test
    public void testSet(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        array.set(5,5);
        Assert.assertEquals(5,array.get(5));
    }


    @Test
    public void testGetAndSet(){
        
        int[] originArray = new int[10];
        originArray[5] = 5;
        AtomicIntegerArray array = new AtomicIntegerArray(originArray);
        
        int v = array.getAndSet(5,6);
        Assert.assertEquals(5,v);
        Assert.assertEquals(6,array.get(5));
        
    }
    
}
