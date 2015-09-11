package Chapter4.RejectTaks;

import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/9/11.
 */
public class Task implements Runnable {

    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task "+name+": Starting");
        long duration = (long)(Math.random()*10);
        System.out.printf("Task %s: ReportGnerator: Generating a report during %d seconds\n",name,duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending\n",name);
    }

    public String toString(){
        return name;
    }

}
