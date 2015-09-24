package Concurrent;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by centling on 2015/9/22.
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue  = new ArrayBlockingQueue(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);

    }
}
