package question4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class fibonacciSequence implements Runnable {

	@Override
	public void run() 
	{
		try 
		{
			System.out.println ("The sequence is: ");
			System.out.println((Global.sequence)); 
		}
		catch (Exception e) {}
	}
	
	public static class Global 
	{
		static ArrayList<Integer> sequence=new ArrayList<Integer>();
		static int input = 0;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("How many fibonnaci numbers do you want?\n" + 
		"Enter an amount: ");
		int input = reader.nextInt();
		
		if (input <= 0) 
		{
			System.out.println("Sorry, not accepting inputs that are less than 1\n"+
		"...\n"+
		"...\n"+
		"why don't you try again?\n");
			main(args); 
		}
		
		else 
		{
			Global.input = input;
			Thread generateFib = new Thread(new generateFib()); 
			generateFib.start(); 
			Thread parent = new Thread(new fibonacciSequence());
			parent.start();
			parent.join();
		}
		reader.close();
		
		}
}

class generateFib implements Runnable 
{

	@Override
	public void run() 
	{
		int n1 = 0, n2 = 1, i;    
		fibonacciSequence.Global.sequence.add(n1);
		fibonacciSequence.Global.sequence.add(n2);
	  
	    for (i = 0; i <= fibonacciSequence.Global.input-3; i++) 
	    { 
	        int next = n1 + n2; 
	        n1 = n2; 
	        n2 = next; 
	        fibonacciSequence.Global.sequence.add(n2);
	    }
	}
}
