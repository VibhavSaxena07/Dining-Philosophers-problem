/**
 *
/ * @author VIBHAVSAXENA

 */

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

//import java.io.IOException;


public class NPhilosphers {
 
  final static Semaphore mutex = new Semaphore(1);
  public static void main(String[] args) {
    
	  
	 int n  ;
	
	 System.out.println("\n Enter Number of Philosophers : \n");
	  Scanner sc = new Scanner(System.in);
	    try {
	         n= sc.nextInt();
	        }
	         finally {
	                 sc.close();
	                  }
	    Philosopher[] philosophers = new Philosopher[n];
		  Monitor monitor = new Monitor(n);
    for (int i = 0; i < n; i++) {
      philosophers[i] = new Philosopher(i,monitor);        //Initializing Threads
      new Thread(philosophers[i]).start();
    }
   
  
 
    
	Scanner Scanner = new Scanner(System.in);
    try {
        String input = Scanner.nextLine();
        String k = "n";
        if (input.equals(k));    
        	{
               System.exit(0);
            }
        } finally {
                 sc.close();
                  }  
  }
}
  class Philosopher implements Runnable {
		private Random rand = new Random();
		private int id;
		private Monitor monitor;
		public Philosopher (int id, Monitor monitor) {
			this.id = id;
			this.monitor = monitor;
		}
		public void run() {
			try {
				while (true) {
					think();
					monitor.pickUpChopsticks(id);
					eat();
					monitor.putDownChopsticks(id);
				}
			} catch (InterruptedException e) {
				System.out.println("Philosopher " + id + " was interrupted.\n");			
			}
		}
		private void think() throws InterruptedException {
			System.out.println( id + " is now thinking.\n");
			//System.out.flush();
			Thread.sleep (rand.nextInt(10));
		}
		private void eat() throws InterruptedException {
			Thread.sleep (rand.nextInt(10));
		}   
	
}
	class Monitor 
	{
		           private enum State {THINKING, HUNGRY, EATING};
		           private State[] state;
		           public Monitor (int numPhilosophers)
		             {
		        	   state = new State[numPhilosophers];
		        	   for (int i = 0; i < state.length; i++) 
		        	   	{
		        		   state[i] = State.THINKING;
		        	   	}
		             }
		
		
		
		
		
	  public synchronized void putDownChopsticks(int pID)
	        {
				state[pID] = State.THINKING;
				notifyAll();
			}
		 
		 
	  private boolean SideCheck(int pID) 
	        {
			    if (state[(pID + 1) % state.length] == State.EATING && state[(pID + 1) % state.length] == State.HUNGRY)
			    	{
			        	return true;
			    	}
			if (state[(pID + state.length - 1) % state.length] == State.EATING && state[(pID + state.length - 1) % state.length] == State.HUNGRY)
					{
				        return true;
			        }
			
			
			return false;
		    }
	  
	  
	  public synchronized void pickUpChopsticks(int pID) throws InterruptedException 
	  
	       {
			state[pID] = State.HUNGRY;
			System.out.println( pID + " is now hungry.\n");
			
			while (SideCheck(pID)) 
				{
				wait();
				}
			state[pID] = State.EATING;
			System.out.println(pID + " is now eating.\n");
			
	       }
	       
	}
		
	
