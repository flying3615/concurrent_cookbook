package Chapoter2.read_write_lock;

/**
 * Created by centling on 2015/9/24.
 */
public class Reader implements Runnable {

    private PriceInfo priceInfo;

    public Reader(PriceInfo priceInfo){
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("%s:price1 = %f\n",Thread.currentThread().getName(),priceInfo.getPrice1());
            System.out.printf("%s:price2 = %f\n",Thread.currentThread().getName(),priceInfo.getPrice2());

        }
    }
}
