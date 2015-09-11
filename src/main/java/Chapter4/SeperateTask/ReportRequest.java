package Chapter4.SeperateTask;

import java.util.concurrent.CompletionService;

/**
 * Created by centling on 2015/9/11.
 */
public class ReportRequest implements Runnable {

    private String name;
    private CompletionService<String> service;


    public ReportRequest(String name,CompletionService<String> service){
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,"Report");
        service.submit(reportGenerator);
    }
}
