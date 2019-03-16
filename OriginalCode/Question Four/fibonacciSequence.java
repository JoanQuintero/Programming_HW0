package question4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class fibonacciSequence implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println ("The sequence is: ");
			System.out.println((Global.sequence)); 
		    
		}catch (Exception e) {
			
		}
	}
	
	public static class Global {
		static ArrayList<Integer> sequence=new ArrayList<Integer>();
		static int input = 0;
	}
	
	
	public static void main(String[] args) throws InterruptedException
	{
		
		
		Scanner reader = new Scanner(System.in);
		System.out.println("How many fibonnaci numbers do you want?\n" + 
		"Enter an amount: ");
		int input = reader.nextInt();
		
		if (input <= 0) {
			System.out.println("Sorry, not accepting inputs that are less than 1\n"+
		"...\n"+
		"...\n"+
		"why don't you try again?\n");
			main(args); // wrong input, go again
		}
		
		else {
			Global.input = input;
			Thread generateFib = new Thread(new generateFib()); //child average
			generateFib.start(); // start average thread
			Thread parent = new Thread(new fibonacciSequence());
			parent.start();
			parent.join();
		
		}
		reader.close();
		
		}
}

class generateFib implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int f1 = 0, f2 = 1, i;    
		fibonacciSequence.Global.sequence.add(f1);
		fibonacciSequence.Global.sequence.add(f2);
	  
	    for (i = 0; i <= fibonacciSequence.Global.input-3; i++) 
	    { 
	        int next = f1 + f2; 
	        f1 = f2; 
	        f2 = next; 
	        fibonacciSequence.Global.sequence.add(f2);
	        }
	    }
	}
