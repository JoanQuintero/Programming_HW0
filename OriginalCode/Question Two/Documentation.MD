## Programming Problem 2: Prime Numbers

**The Challenge:**  
Write a multithreaded program that outputs prime numbers.  
  
*This program should work as follows:*   
**The user** will run the program and **will enter a number** on the command line.  
**The program** will then **create a separate thread** that outputs all the prime numbers *less than or equal* to the number entered by the user. 
  
**Coding begins below this line** -- Program Done using Eclipse/Java
--------------------------------------------------------------------------
**Includes**
```Java
package problem2;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
 
import problem2.checkPrimes.User;
// for security measures checkPrimes needs to be imported
```
>> import keyword is used to import built-in and user-defined packages  
for example, in this code, we are importing "checkPrimes.User" which is a user-defined class we made.

--------------------------------------------------------------------------

```Java
public class checkPrimes implements Runnable {
// Implementing Runnable interface 
// is a way of threading
    @Override
    public void run() {
        // TODO Auto-generated method stub
    }
     
    public static class User {
        static int input = 0;
    } // storing user input globally
     
    public static void main(String args [])
    {
        Scanner reader = new Scanner(System.in);  // Reading input
        System.out.println("Enter a number: ");
        User.input = reader.nextInt();   
        // taking input from user and storing value in global variable input
         
        if (User.input <= 0) // if input = 0, explain to user
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
                    
        } // end of if statement
        else 
        {
            Thread printPrimes = new Thread(new printPrimes()); // creating new thread
            printPrimes.start(); // start printPrimes thread
        }
    };
}
 
class printPrimes implements Runnable  // defining class for the new thread
{
    @Override
    public void run() {

        int i, divisiblebythisamount, j;     
        // integer variables that will be used to check prime values
        
        ArrayList<Integer> listPrimes = new ArrayList<Integer>(); 
        // creating an array to store the values

        for(i = 1; i <= User.input; i++) // loop from 1 up to value of user input
        {
            divisiblebythisamount = 0; // initiating var
 
            for( j = 1; j <= User.input; j++) // inner loop
            
                        // inner loop will check two things:
                       // - if a number is divisible by itself
                      // - and any other number it might be divisible by as well

            {
                if (i % j == 0) 
                {
                    divisiblebythisamount++;
                }
                    // whichever number it is divisible by, 
                    // if the remainder is zero
                   // add one to the "divisiblebythisamount" variable      
            
            if (divisiblebythisamount == 2)
                listPrimes.add(i);
                
               //if a number is only divisible by 2 numbers only,
               // add it to the list of primes
               // reason: a prime number is only divisible by itself and 1.
        }
        System.out.println("The Prime Numbers up to your input, "
                           + 
                             User.input+ ", are: "+ listPrimes);   // print list of primes.
    }
}
```
