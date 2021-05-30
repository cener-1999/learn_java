package Thread;

import javax.crypto.interfaces.PBEKey;

class BankAccout {
    private int balance = 100;

    public int getBalance(){
        return balance;
    }

    public synchronized void withdraw(int amount){
        balance = balance - amount;
    }
}

public class RyanAndMonocaJob implements Runnable{

    private final BankAccout accout = new BankAccout();

    public static void main(String[] args) {
        RyanAndMonocaJob theJob = new RyanAndMonocaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }

    public void run(){
        for (int x=0;x<10;x++){
            makeWithdrawl(10);
            if(accout.getBalance()<10){
                System.out.println("Overdrawn!");
            }
        }
    }

    private void makeWithdrawl(int amount) {
        if(accout.getBalance()>= amount){
            System.out.println(Thread.currentThread().getName()+" is about to withdraw");
            try{
                System.out.println(Thread.currentThread().getName()+" is going to sleep");
            }catch(InternalError ex){
               ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" woke up");
            accout.withdraw(amount);
            System.out.println(Thread.currentThread().getName()+" completes the withdrawl");
        }
        else{
            System.out.println("Sorry,not enough for "+ Thread.currentThread().getName());
        }
    }
}