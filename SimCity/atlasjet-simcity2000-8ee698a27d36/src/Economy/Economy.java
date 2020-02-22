package Economy;

import Population.Population;
import Structures.*;

public final class Economy {
    private int money;
    
    public Economy(int m){
        money = m;
    }
    
    public int getMoney() {
        return money;
    }
    

    public void calcEco(int taxes){
        money = money + (Population.getPop()*2 - (PoliceDepartment.getPolnum()*3000 + 
                    
                    FireDepartment.getFirenum() * 2000 + 
                    Hospital.getHosnum() * 5000 +
                    Education.getSchnum() * 2000 +
                    Education.getHighnum() * 3000 +
                    Education.getUninum() * 6000
                
               ));
    
    }
    
    public void outEco(int value){
        money -= value;
    }

}
        
        
        
        
        
        
        
        /*implements Runnable{
    private Thread t;
    private String threadName = "Increase";
    private String pop = "2";
    
    
    BigInteger money;
    
    public Economy(){
        this.start();
        money = new BigInteger("123456789");
    }
    
    public Economy(BigInteger i){
        this.start();
        money = i;
    }
    
    
    
    public void outgoingMoney(BigInteger cost){
        money =  money.subtract(cost);
    }
    
    
    public void incomeMoney(BigInteger cost){
        money =  money.add(cost);
    }
    
    public String printMoney(){
        return "This is your money :" + money.toString();
    }

    @Override
    public void run() {
        System.out.println("Thread is started.");
        while (true){
            incomeMoney(new BigInteger("100").multiply(new BigInteger(pop))); 
            System.out.println(money);//Increase with money * population.
            try {
                Thread.sleep(10000);                                            //Wait 10 Sec
            } catch (InterruptedException ex) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
        }
    }
    
    
    public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
    
}
*/