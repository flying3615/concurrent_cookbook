package Chapter4.RejectTaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class RejectedTaskController implements RejectedExecutionHandler{

    static final Logger logger = LoggerFactory.getLogger(RejectedTaskController.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info("RejectTaskController: The task {} has been rejected",r.toString());
        logger.info("RejectedTaskController: Terminating:{}", executor.isTerminating());
        logger.info("RejectedTaskController: Terminated: {}", executor.isTerminated());

    }
}
