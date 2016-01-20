package Chapter6.AtomicVar;

/**
 * Created by centling on 2015/9/25.
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account){
        this.account = account;
    }


    @Override
    public void run() {
        for(int i=0;i<10;i++){
            account.subtractAmount(1000);
        }


    }
}
