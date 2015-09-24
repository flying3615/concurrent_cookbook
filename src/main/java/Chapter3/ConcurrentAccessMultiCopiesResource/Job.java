package Chapter3.ConcurrentAccessMultiCopiesResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by centling on 2015/9/16.
 */
public class Job implements Runnable {

    static final Logger logger = LoggerFactory.getLogger(Job.class);
    private final PrintQueue printQueue;

    public Job(PrintQueue printQueue){
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        logger.info("{}: Going to print a job", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        logger.info("{}: the document has been printed", Thread.currentThread().getName());
    }
}
