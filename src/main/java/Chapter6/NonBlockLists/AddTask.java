package Chapter6.NonBlockLists;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by centling on 2015/9/16.
 */
public class AddTask implements Runnable  {

    private ConcurrentLinkedDeque<String> list;


    public AddTask(ConcurrentLinkedDeque<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for(int i=0;i<1000;i++){
            list.add(name+": Element "+i);
        }
    }
}
