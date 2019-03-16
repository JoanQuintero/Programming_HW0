package question3;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import question3.statisticalValues.Global;

class statisticalValues implements Runnable  // parent thread
{ 

	public void run() 
	{ 
		try
		{ 
			System.out.println ("The average value is: "+ Global.average);
		    System.out.println ("The maximum value is: "+ Global.maximum);
		    System.out.println ("The minimum value is: "+ Global.minimum);
		} 
		catch (Exception e) 
		{                     // Throwing an exception 
			System.out.println ("Exception is caught"); 
		} 
	}
	
	public static class Global {
		static ArrayList<Integer> alist=new ArrayList<Integer>();
	    public static int maximum;
	    public static int minimum;
	    public static int average;
	}
 
// Main Class 
	public static void main(String[] args) 
	
	{
		int input = 0;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		input = reader.nextInt();
		Global.alist.add(input);
		
		if (input <= 0) // if input = 0, stop user inputs
		{ 
			Global.alist.remove(Global.alist.size() - 1);
			System.out.println(Global.alist); // only here for testing
			System.out.println(Global.alist.size()); // only here for testing
			
			Thread object = new Thread(new statisticalValues()); //parent
			Thread maximum = new Thread(new calculateMaximum()); //child maximum
			Thread minimum = new Thread(new calculateMinimum()); //child minimum
			Thread average = new Thread(new calculateAverage()); //child average
			average.start(); // start average thread
			minimum.start(); // start minimum thread
			maximum.start(); // start maximum thread
			object.start();  // restart parent thread
		}
		
		else 
		{
			main(args); // Recalling main to ask user for more inputs
		}
		reader.close(); // Done listening for inputs
	}
}

class calculateMaximum implements Runnable
{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Global.maximum = Collections.max(Global.alist);   
	}
}

class calculateMinimum implements Runnable
{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Global.minimum = Collections.min(Global.alist);
	}
}

class calculateAverage implements Runnable
{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i: Global.alist) {
			count += i;
		}
		Global.average = count/Global.alist.size();
	}
}
