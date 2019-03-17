## Programming Problem 3: Statistical Values  
  
**Challenge:**  
  
Write a multithreaded program that calculates various statistical values  
for a list of numbers. This program will be passed a series of numbers on  
the command line and will then create three separate worker threads.  
One thread will determine the average of the numbers, the second will  
determine the maximum value, and the third will determine the minimum value.   
  
**For example,**   
>>suppose your program is passed the integers 90 81 78 95 79 72 85   
  
**The program will report:**   
>>The average value is 82   
The minimum value is 72   
The maximum value is 95   
  
**Explanation:**  
  
The variables representing the average, minimum, and maximum values  
will be stored globally. The worker threads will set these values,  
and the parent thread will output the values once the workers have exited.  
(We could obviously expand this program by creating additional threads that  
determine other statistical values, such as median and standard deviation.)  
  
**Coding begins below this line** -- Program done in Java
--------------------------------------------------------------------------
```Java

import java.util.ArrayList;
 
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
 
import statisticalValues.Global;
 
class statisticalValues implements Runnable  
// parent thread
{ 
 
    public void run() 
    { 
        try
        {  // parent will print average, min and max
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
// making variables for avg, min and max 
// globally by using static
// also making a global array to hold the values
        static ArrayList<Integer> alist=new ArrayList<Integer>();
        public static int maximum;
        public static int minimum;
        public static int average;
    }
  
// Main Class 
    public static void main(String[] args) 
     
    {
        int input = 0;
        Scanner reader = new Scanner(System.in);  // Reading input
        System.out.println("Enter a number: ");
        input = reader.nextInt(); // storing inputs
        Global.alist.add(input); // storing inputs to a list
         
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
// storing maximum value globally
    }
}
 
class calculateMinimum implements Runnable
{
    @Override
    public void run() {
        // TODO Auto-generated method stub
        Global.minimum = Collections.min(Global.alist);
// storing minimum value globally
    }
}
 
class calculateAverage implements Runnable
{
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 0;
        for (int i: Global.alist) {
            count += i; // adding all inputs
        }
        Global.average = count/Global.alist.size(); 
// dividing the total sum of all inputs 
// by the amount of inputs in the array
// storing the value in the global variable average
    }
}
```
