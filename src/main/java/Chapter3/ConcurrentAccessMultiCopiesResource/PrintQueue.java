package Chapter3.ConcurrentAccessMultiCopiesResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by centling on 2015/9/16.
 */
public class PrintQueue {

    static final Logger logger = LoggerFactory.getLogger(PrintQueue.class);

    private boolean freePrinters[];
    private Semaphore semaphore;
    private Lock lockPrinters;

    public PrintQueue(){
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for(int i=0;i<3;i++){
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document){
        try {
            semaphore.acquire();
            int assignedPrinter = getPrinter();
            long duration = (long)(Math.random()*10);
            logger.info("{} print queue:printing a job during {} seconds",Thread.currentThread().getName(),duration);
            TimeUnit.SECONDS.sleep(duration);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private int getPrinter() {
        int ret = -1;
        try{
            lockPrinters.lock();
            for(int i=0;i<freePrinters.length;i++){
                if(freePrinters[i]){
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }finally {
            lockPrinters.unlock();
        }
        return ret;
    }
}
