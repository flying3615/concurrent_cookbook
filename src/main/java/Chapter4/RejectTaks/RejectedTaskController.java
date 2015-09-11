package Chapter4.RejectTaks;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class RejectedTaskController implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectTaskController: The task %s has been rejected\n",r.toString());
        System.out.printf("RejectedTaskController: Terminating:%s\n",executor.isTerminating());
        System.out.printf("RejectedTaskController: Terminated: %s\n",executor.isTerminated());

    }
}
