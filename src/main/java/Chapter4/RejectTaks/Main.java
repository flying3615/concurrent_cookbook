package Chapter4.RejectTaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class Main {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){

        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);

        logger.info("Main:Starting.");
        for(int i=0;i<3;i++){
            Task task = new Task("Task"+i);
            executor.submit(task);
        }

        logger.info("Main: Shutting down the Executor.");
        executor.shutdown();

        logger.info("Main: Sending another task.");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        logger.info("Main: End.");


    }


}
