package Chapter4.RejectTaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/9/11.
 */
public class Task implements Runnable {

    static final Logger logger = LoggerFactory.getLogger(Task.class);
    
    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        logger.info("Task "+name+": Starting");
        long duration = (long)(Math.random()*10);
        logger.info("Task {}: ReportGenerator: Generating a report during {} seconds", name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Task {}: Ending", name);
    }

    public String toString(){
        return name;
    }

}
