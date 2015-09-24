package Chapter3.ConcurrentAccessAResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by centling on 2015/9/16.
 */
public class PrintQueue {


    static final Logger logger = LoggerFactory.getLogger(PrintQueue.class);
    private final Semaphore semaphore;
    private final Lock lock = new ReentrantLock();

    public PrintQueue(){
        semaphore = new Semaphore(1);
    }

    public void printJob(Object document){
        try {
//            semaphore.acquire();
            lock.lock();
            long duration = (long)(Math.random()*10);
            logger.info("{} print queue:printing a job during {} seconds",Thread.currentThread().getName(),duration);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
//            semaphore.release();
        }
    }

}
