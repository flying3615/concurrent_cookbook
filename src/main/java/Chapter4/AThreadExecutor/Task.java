package Chapter4.AThreadExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/9/11.
 */
public class Task implements Runnable {

    static final Logger logger = LoggerFactory.getLogger(Task.class);

    private Date initDate;
    private String name;


    public Task(String name){
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        logger.info("{}: Task {}: Created on {}",Thread.currentThread().getName(),name,initDate);
        logger.info("{}: Task {}: Started on {}",Thread.currentThread().getName(),name,new Date());

        Long duration = (long)(Math.random()*10);
        logger.info("{}: Task {}: Doing a task during {} seconds",Thread.currentThread().getName(),name,duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("{}: Task {}: Finished on:{}",Thread.currentThread().getName(),name,new Date());
    }
}
