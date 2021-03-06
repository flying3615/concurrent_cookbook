package Chapter6.AtomicVar;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by centling on 2015/9/25.
 */
public class Account {

    private AtomicLong balance;

    public Account(){
        balance = new AtomicLong();
    }

    public long getBalance(){
        return balance.get();
    }

    public void setBalance(long balance){
        this.balance.set(balance);
    }

    public void addAmount(long amount){
        this.balance.getAndAdd(amount);
    }

    public void subtractAmount(long amount){
        this.balance.getAndAdd(-amount);
    }
}
