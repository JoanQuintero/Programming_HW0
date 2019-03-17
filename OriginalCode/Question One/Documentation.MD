## Programming Problem 1: Collatz Conjecture 
  
**Explanation:**  
  
The Collatz conjecture concerns what happens when we take  
any positive integer n and apply the following algorithm: 
  
>>if n is even, n = n/2  
if n is odd, n = 3 x n + 1  
  
The conjecture states that when this algorithm is continually  
applied, all positive integers will eventually reach 1.  
  
>>For example, if n = 35,  
the sequence is 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1  
  
**The Challenge:**  
  
Write a C program using the fork() system call that generates this sequence  
in the child process. The starting number will be provided from the command  
line. For example, if 8 is passed as a parameter on the command line, the  
child process will output 8, 4, 2, 1. Because the parent and child processes  
have their own copies of the data, it will be necessary for the child to output  
the sequence. Have the parent invoke the wait() call to wait for the child  
process to complete before exiting the program. Perform necessary error checking  
to ensure that a positive integer is passed on the command line.  
  
**Coding begins below this line** -- Program done in C
--------------------------------------------------------------------------
**Includes**
```c
#include <stdio.h> 
#include <sys/types.h> 
#include <unistd.h> 
#include <math.h>
#include <sys/wait.h>
```
>> used to include logical units such as library function declarations and classes. 

--------------------------------------------------------------------------
**Function CollactzConjecture**
```c
void CollatzConjecture() 
{ 
```   
>> opening CollactzConjecture

--------------------------------------------------------------------------
**inside of Function CollactzConjecture**
```c
    int input;
    printf("Please input an integer value: "); //ask 
    scanf("%d", &input);    // store
```  
>>ask for user input  
and store user inputs into declared variable input

--------------------------------------------------------------------------
**loop inside of Function CollactzConjecture**
```c
    while (input <= 0){
        printf("\nYour input is <= 0, which makes it invalid \n");
        printf("Please input a positive integer value this time: "); // keep asking user for valid input
        scanf("%d", &input); 
    }
```
>>While the user does not input a value greater than 0,  
program will tell user that his input is invalid and  
keep asking for a valid input.
 
```c

--------------------------------------------------------------------------
**in CollactzConjecture**
```c
    int output;
    output = input;
   // declaring a separate variable called output
```

--------------------------------------------------------------------------
**Critical Section of CollactzConjecture**
```c
    if (fork() == 0) //if new process is created
    {
        printf("\nCollatzConjecture Sequence is: \n"); //separate print to introduce the sequence
 
        while (output != 1) // Loop until output = 1.
        {
            printf("%d,", output); // print output next to one another separated by a comma on each loop
 
            if (output % 2 == 0)  
            {           
            output = output / 2;   // if output mod 2 == 0, divide output by 2 
            }

            else { 
            output = 3 * output + 1;  // if output mod 2 != 0, multiple output by 3 and add 1 to it
            }                        
            
        } // continue loop until output equals 1
        
        printf("%d\n", output);  // print output value after loop is over
        
    } // closing statement (if for() == 0).
    // exiting critical section

    else  // if fork() != 0, make the parent wait
    {
        wait(NULL);  // wait for child thread to be done
        printf("Child Complete \n"); // child exited
    }  
}  // end of function CollatzConjecture
``` 
--------------------------------------------------------------------------
```c
int main()  // calling main
{ 
    CollatzConjecture(); // calling CollactzConjecture function to run it
    return 0; 
}
```
