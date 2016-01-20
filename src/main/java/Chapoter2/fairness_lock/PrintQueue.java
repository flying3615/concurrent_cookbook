package Chapoter2.fairness_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by centling on 2015/9/24.
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ " PrintQueue: " +
                    "Printing a Job during "+(duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }

        queueLock.lock();

        Long duration = (long)(Math.random()*10000);
        System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job" +
                "during "+(duration/1000)+" seconds");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
