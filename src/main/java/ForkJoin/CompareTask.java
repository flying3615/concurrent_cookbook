package ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by centling on 2015/10/29.
 */
public class CompareTask extends RecursiveTask<List<Person>> {

    static final int SEQUENTIAL_THRESHOLD=100;

    static final int SIZE = 100000;

    int low;
    int high;
    List<Person> persons;
    List<Person> change_persons;


    public CompareTask(int low, int high, List<Person> persons,List<Person> change_persons) {
        this.low = low;
        this.high = high;
        this.persons = persons;
        this.change_persons = change_persons;
    }

    @Override
    protected List<Person> compute() {
        List<Person> tmpList = new ArrayList<>();
        if(high-low<=SEQUENTIAL_THRESHOLD){
            //do job
            for(int i=low;i<high;i++){
                Person p = persons.get(i);
                if(!p.equals(change_persons.get(i))) {
                    tmpList.add(p);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return tmpList;
        }else{
            int mid = low+(high-low)/2;
            CompareTask left = new CompareTask(low,mid,persons,change_persons);
            CompareTask right = new CompareTask(mid,high,persons,change_persons);
            left.fork();
            List<Person> rightPersonList = right.compute();
            List<Person> leftPersonList = left.join();
            leftPersonList.addAll(rightPersonList);
            return leftPersonList;
        }
    }


    public static void main(String[] args){
        List<Person> orgPerson = new ArrayList<Person>();
        List<Person> changePerson = new ArrayList<Person>();

        for(int i=0;i<SIZE;i++){
            Person p = new Person("old"+i,i);
            orgPerson.add(p);

            if(i%10==0){
               Person new_p = new Person("changed"+i,i);
                changePerson.add(new_p);
            }else{
                changePerson.add(p);
            }
        }
        System.out.println("start");
        ForkJoinPool fjPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        List<Person> result = fjPool.invoke(new CompareTask(0, orgPerson.size(), orgPerson, changePerson));
        long end = System.currentTimeMillis();
        System.out.println("result1 "+(end-start)+" "+result.size());
        try {
            fjPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        start = System.currentTimeMillis();
        List<Person> tmpList = new ArrayList<>();
        for(int i=0;i<orgPerson.size();i++){
            Person p = orgPerson.get(i);
            if(!p.equals(changePerson.get(i))){
                tmpList.add(p);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("result2 "+(end-start)+" "+tmpList.size());

    }
}
