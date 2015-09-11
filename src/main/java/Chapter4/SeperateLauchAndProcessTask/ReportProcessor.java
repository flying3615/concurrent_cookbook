package Chapter4.SeperateLauchAndProcessTask;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/9/11.
 */
public class ReportProcessor implements Runnable{

    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service){
        this.service = service;
        end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                //read output of request
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if(result!=null){
                    String report = result.get();
                    System.out.printf("ReportReceiver: report received:%s\n",report);
                }
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender :end\n");
    }

    public void setEnd(boolean end){
        this.end = end;
    }

}
