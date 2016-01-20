package Gpars

import groovyx.gpars.GParsExecutorsPool
import groovyx.gpars.GParsPool

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by centling on 2015/11/13.
 */
class WithPool {

    static main(args){
//        def t
//        GParsExecutorsPool.withPool {
//                t = new Thread(new Runnable() {
//                    @Override
//                    void run() {
//                        TimeUnit.SECONDS.sleep(1)
//                        println('in thread')
//                    }
//                })
//            t.start()
//            println('in inner')
//        }
//        t.join()
//        println('in outer')
//
//        GParsPool.withPool {
//            final AtomicInteger result = new AtomicInteger(0)
//            [1,2,3,4,5].eachParallel {result.addAndGet(it)}
//            assert 15 == result
//
//            final List list = [1,2,3,4,5].collectParallel {it*2}
//            assert [2,4,6,8,10].equals(list)
//        }

        GParsPool.withPool(6){
            println it
            assert [1,2,3,4,5].everyParallel {it>0}
            assert [1,2,3,4,5].everyParallel {it>1}
        }

    }



}
