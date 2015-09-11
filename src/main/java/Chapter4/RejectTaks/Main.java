package Chapter4.RejectTaks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class Main {

    public static void main(String[] args){

        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);

        System.out.printf("Main:Starting.\n");
        for(int i=0;i<3;i++){
            Task task = new Task("Task"+i);
            executor.submit(task);
        }

        System.out.printf("Main: Shutting down the Executor.\n");
        executor.shutdown();

        System.out.printf("Main: Sending another task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        System.out.printf("Main: End.\n");


    }


}