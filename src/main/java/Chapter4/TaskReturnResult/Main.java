package Chapter4.TaskReturnResult;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by centling on 2015/9/11.
 */
public class Main {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        do {
            logger.info("Main:Number of completed Tasks:{}", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                logger.info("Task {}:{}", i, result.isDone());

            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        logger.info("Main:Result");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            logger.info("Main: Task {}:{}", i, number);
        }
        executor.shutdown();

    }
}
