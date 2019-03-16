package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import problem2.checkPrimes.User;


public class checkPrimes {
	
	public static class User 
	{
		static int input = 0;
	}
	
	public static void main(String args [])
	{
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a number: ");
		User.input = reader.nextInt();   
		
		if (User.input <= 0) 
		{ 
			System.out.println(
					"Sorry, wrorng input \n"
							+
					"because: \n"
							+ 
					"1) negative integers can not be prime.\n"
							+ 
					"2) primes are integers greater than one "
							+ 
					"with no positive divisors besides one and "
							+ 
					"itself.\n"
							+
					"...\n"
							+
					"...\n"
							+
					"why don't you try again?\n");
		}
		
		else 
		{
			Thread printPrimes = new Thread(new printPrimes());
			printPrimes.start();
		}
	};
}

class printPrimes implements Runnable
{
	@Override
	public void run() 
	{
		int i, divisiblebythisamount, j;
		ArrayList<Integer> listPrimes = new ArrayList<Integer>();
	    
		for(i = 1; i <= User.input; i++)
        {
            divisiblebythisamount = 0;

            for( j = 1; j <= User.input; j++)
            {
                if(i % j == 0)
                {
                    divisiblebythisamount++;
                }
            }
            if(divisiblebythisamount == 2)
            {
                listPrimes.add(i);
            }
        }
		System.out.println("The Prime Numbers up to your input, "
                           + 
                           	 User.input+ ", are: "+ listPrimes);
	}
}
