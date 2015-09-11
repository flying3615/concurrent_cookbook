package Chapter4.SeperateTask;

import java.util.concurrent.*;

/**
 * Created by centling on 2015/9/11.
 */
public class Main {


//    When one of these tasks is executed when the completion service finishes its execution, the
//    completion service stores the Future object used to control its execution in a queue. The
//    poll() method accesses this queue to see if there is any task that has finished its execution
//    and, if so, returns the first element of that queue which is a Future object for a task that has
//    finished its execution.


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        ReportRequest faceRequest = new ReportRequest("Face", service);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);

        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        System.out.printf("Main:Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();

        System.out.printf("Main: Waiting for the report generators.\n");
        faceThread.join();
        onlineThread.join();

        System.out.printf("Main:Shutting down the executor.\n");
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);


        processor.setEnd(true);
        System.out.println("Main:Ends");


    }
}
