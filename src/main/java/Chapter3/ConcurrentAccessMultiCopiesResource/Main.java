package Chapter3.ConcurrentAccessMultiCopiesResource;


/**
 * Created by centling on 2015/9/16.
 */
public class Main {

    public static void main(String[] args){
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for(int i=0;i<10;i++){
            thread[i] = new Thread(new Job(printQueue),"Thread"+i);
        }

        for(int i=0;i<10;i++){
            thread[i].start();
        }
    }

}
