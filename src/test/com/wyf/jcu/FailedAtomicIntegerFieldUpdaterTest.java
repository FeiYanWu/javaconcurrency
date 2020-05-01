package com.wyf.jcu;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class FailedAtomicIntegerFieldUpdaterTest {

    /**
     * can't access the private field of object
     */
    @Test
    public void testPrivateFieldAccessError(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        TestMe me = new TestMe();
        updater.compareAndSet(me,0,1);
    }

    @Test
    public void testTargetObjectIsNUll(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        updater.compareAndSet(null,0,1);
    }

    @Test
    public void testFieldNameInvalid(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i1");
        updater.compareAndSet(null,0,1);
    }

    @Test
    public void testFieldTypeInvalid(){
        AtomicReferenceFieldUpdater<TestMe2,Long> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class,Long.class,"i");
        TestMe2 me = new TestMe2();
        //updater.compareAndSet(me,null,1);
        updater.compareAndSet(me,null,1L);
    }

    @Test
    public void testFieldIsNotVolatile(){
        AtomicReferenceFieldUpdater<TestMe2,Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class,Integer.class,"i");
        TestMe2 me = new TestMe2();
        //updater.compareAndSet(me,null,1);
        updater.compareAndSet(me,null,1);
        //java.lang.IllegalArgumentException: Must be volatile type
    }
    //想让类的属性具备原子性
    //  1.volatile
    //  2.非private，protected（如果是当前类，也可以是protected）
    //  3.类型必须一致
    //  4.其他
    //不想使用锁（显示锁，synchronized
    //大量使用原子类型修饰的对象，相对比较耗费内存
    static class TestMe2{
        volatile Integer i;
        //Integer i;
    }
}
