package com.wyf.jcu;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.RunListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {
    @Test
    public void testCreateWithoutArguments(){
        AtomicBoolean result = new AtomicBoolean();
        Assert.assertFalse(result.get());
    }

    @Test
    public void testCreateWithArguments(){
        AtomicBoolean result = new AtomicBoolean(true);
        Assert.assertTrue(result.get());
    }

    @Test
    public void testGetAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean reuslt = bool.getAndSet(false);
        Assert.assertTrue(reuslt);
        Assert.assertFalse(bool.get());
    }
    
    @Test
    public void testCompareAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(true,false);
        Assert.assertTrue(result);
        Assert.assertFalse(bool.get());
    }

    @Test
    public void testCompareAndSetFailed(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(false,true);
        Assert.assertFalse(result);
        Assert.assertTrue(bool.get());
    }
}
