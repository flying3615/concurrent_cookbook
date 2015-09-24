package Chapoter2.read_write_lock;

/**
 * Created by centling on 2015/9/24.
 */
public class Writer implements Runnable {

    private PriceInfo priceInfo;

    public Writer(PriceInfo priceInfo){
        this.priceInfo = priceInfo;
    }


    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.printf("Writer:Attempt to modify the prices.\n");
            priceInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("Writer: prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
