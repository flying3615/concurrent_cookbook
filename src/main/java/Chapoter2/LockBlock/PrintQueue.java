package Chapoter2.LockBlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by centling on 2015/9/24.
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*1000);
            System.out.println(Thread.currentThread().getName()+ "PrintQueue: " +
                    "Printing a Job during "+(duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
