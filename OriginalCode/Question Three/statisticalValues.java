package question3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import question3.statisticalValues.Global;

class statisticalValues implements Runnable  
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
		{                   
			System.out.println ("Exception is caught"); 
		} 
	}
	
	public static class Global 
	{
	    static ArrayList<Integer> alist = new ArrayList<Integer>();
	    public static int maximum;
	    public static int minimum;
	    public static int average;
	}
 
	public static void main(String[] args) 
	
	{
		int input = 0;
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a number: ");
		input = reader.nextInt();
		Global.alist.add(input);
		
		if (input <= 0) 
		{ 
			Global.alist.remove(Global.alist.size() - 1);
			
			Thread object = new Thread(new statisticalValues()); 
			Thread maximum = new Thread(new calculateMaximum()); 
			Thread minimum = new Thread(new calculateMinimum()); 
			Thread average = new Thread(new calculateAverage()); 
			average.start(); 
			minimum.start(); 
			maximum.start(); 
			object.start();  
		}
		
		else 
		{
			main(args); 
		}
		reader.close(); 
	}
}

class calculateMaximum implements Runnable
{
	@Override
	public void run() 
	{
		Global.maximum = Collections.max(Global.alist);   
	}
}

class calculateMinimum implements Runnable
{
	@Override
	public void run() 
	{
		Global.minimum = Collections.min(Global.alist);
	}
}

class calculateAverage implements Runnable
{
	@Override
	public void run() 
	{
		int count = 0;
		for (int i: Global.alist) 
		{
			count += i;
		}
		Global.average = count/Global.alist.size();
	}
}
