package ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by centling on 2015/10/29.
 */
public class Sum extends RecursiveTask<Long> {

    static final int SEQUENTIAL_THRESHOLD = 1000;

    int low;
    int high;
    List<Integer> array;

    Sum(List<Integer> arr, int lo, int hi) {
        array = arr;
        low   = lo;
        high  = hi;
    }

    protected Long compute() {
        if(high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for(int i=low; i < high; ++i)
                sum += array.get(i)/3+22;
            return sum;
        } else {
            int mid = low + (high - low) / 2;
//            System.out.printf("new left low %s high %s\n", low, high);
//            System.out.printf("new right low %s high %s\n",mid,high);
            Sum left  = new Sum(array, low, mid);
            Sum right = new Sum(array, mid, high);
            left.fork();
            long rightAns = right.compute();
            long leftAns  = left.join();
            return leftAns + rightAns;
        }
    }

    static long sumArray(List<Integer> array) {
        return new ForkJoinPool().invoke(new Sum(array, 0, array.size()));
    }


    public static void main(String[] args){
        List<Integer> intArray = new ArrayList<>();
        for(int i=0;i<100000000;i++){
            int ele = (int)Math.random()*100000000;
            intArray.add(ele);
        }

        long start = System.currentTimeMillis();
        long result = sumArray(intArray);
        long end = System.currentTimeMillis();
        System.out.println(result+" cost "+(end-start));

        start = System.currentTimeMillis();
        long count = 0;
        for(int i=0;i<intArray.size();i++){
            count+=intArray.get(i)/3+22;
        }
        end = System.currentTimeMillis();
        System.out.println(count+" cost "+(end-start));

    }
}
