package Chapter4.TaskReturnResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/9/11.
 */
public class FactorialCalculator implements Callable<Integer> {

    static Logger logger = LoggerFactory.getLogger(FactorialCalculator.class);

    private Integer number;

    public FactorialCalculator(Integer number){
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if((number==0)||(number==1)){
            result = 1;
        }else{
            for(int i=2;i<number;i++){
                result*=i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        logger.info("{}:{}",Thread.currentThread().getName(),result);
        return result;
    }
}
