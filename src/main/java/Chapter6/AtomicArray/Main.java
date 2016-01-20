package Chapter6.AtomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by centling on 2015/9/25.
 1. You get the value of the variable, which is the old value of the variable.
 2. You change the value of the variable in a temporal variable, which is the new value
 of the variable.
 3. You substitute the old value with the new value, if the old value is equal to the actual
 value of the variable. The old value may be different from the actual value if another
 thread has changed the value of the variable.
 */
public class Main {

    public static void main(String[] args){
        final int THREADS = 100;
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(vector);
        Decrementer decrementer = new Decrementer(vector);
        Thread threadIncrementer[] = new Thread[THREADS];
        Thread threadDecrementer[] = new Thread[THREADS];

        for(int i=0;i<THREADS;i++){
            threadIncrementer[i] = new Thread(incrementer);
            threadDecrementer[i] = new Thread(decrementer);

            threadIncrementer[i].start();
            threadDecrementer[i].start();
        }

        for(int i=0;i<100;i++){
            try {
                threadDecrementer[i].join();
                threadIncrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0;i<vector.length();i++){
            if(vector.get(i)!=0){
                System.out.println("Vector["+i+"] : "+vector.get(i));
            }
        }

        System.out.println("Main: End of example");
    }
}
