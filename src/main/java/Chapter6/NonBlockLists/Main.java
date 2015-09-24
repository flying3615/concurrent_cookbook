package Chapter6.NonBlockLists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by centling on 2015/9/16.
 */
public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread threads[] = new Thread[100];

        for(int i=0;i<threads.length;i++){
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        logger.info("{} add task threads have been launched",threads.length);

        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }

        logger.info("Size of the List {}", list.size());

        for(int i=0;i<threads.length;i++){
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        logger.info("{} PollTask threads have been launched", threads.length);

        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }

        logger.info("Size of the list: {}", list.size());

    }

}
