package Chapter3.PhasedTasks;

import java.util.concurrent.Phaser;

/**
 * Created by centling on 2015/9/29.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("E:\\project\\concurrent_cookbook\\src\\main\\java\\Chapter3\\PhasedTasks\\sys","log",phaser);
        FileSearch apps = new FileSearch("E:\\project\\concurrent_cookbook\\src\\main\\java\\Chapter3\\PhasedTasks\\app","log",phaser);
        FileSearch document = new FileSearch("E:\\project\\concurrent_cookbook\\src\\main\\java\\Chapter3\\PhasedTasks\\document","log",phaser);

        Thread sysThread = new Thread(system,"System");
        Thread appThread = new Thread(apps,"App");
        Thread documentThread = new Thread(document,"Documents");


        sysThread.start();
        appThread.start();
        documentThread.start();


        sysThread.join();
        appThread.join();
        documentThread.join();

        System.out.println("Terminated: "+phaser.isTerminated());

    }
}
