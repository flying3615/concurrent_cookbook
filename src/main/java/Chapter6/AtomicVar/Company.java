package Chapter6.AtomicVar;

/**
 * Created by centling on 2015/9/25.
 */
public class Company implements Runnable{

    private Account account;

    public Company(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<11;i++){
            account.addAmount(1000);
        }

    }
}
