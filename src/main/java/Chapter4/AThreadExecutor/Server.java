package Chapter4.AThreadExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class Server {

    static final Logger logger = LoggerFactory.getLogger(Server.class);

    private ThreadPoolExecutor executor;

    public Server(){
//        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task){
        logger.info("Server: A new task has arrived");
        executor.execute(task);
        logger.info("Server:Pool size {}",executor.getPoolSize());
        logger.info("Server:Active Count {}",executor.getActiveCount());
        logger.info("Server:Completed tasks {}",executor.getCompletedTaskCount());
    }

    public void endServer(){
        executor.shutdown();
    }

}
