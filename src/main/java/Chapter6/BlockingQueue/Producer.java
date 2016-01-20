package Chapter6.BlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by centling on 2015/9/22.
 */
public class Producer implements Runnable{

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
