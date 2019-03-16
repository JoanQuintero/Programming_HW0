#include <stdio.h> 
#include <sys/types.h> 
#include <unistd.h> 
#include <math.h>
#include <sys/wait.h>
  
void CollatzConjecture() 
{ 
    
    int input;
	printf("Please input an integer value: ");
	scanf("%d", &input);

	while (input <= 0){
		printf("\nYour input is <= 0, which makes it invalid \n");
		printf("Please input a positive integer value this time: ");
		scanf("%d", &input);
	}

	int output;
	output = input;
  
    if (fork() == 0) 
    {
    	printf("\nCollatzConjecture Sequence is: \n");

    	while (output != 1)
    	{
    		printf("%d,", output);

	    	if (output % 2 == 0) { output = output / 2; }
	    	else { output = 3 * output + 1; }
		}
		printf("%d\n", output);
		fflush(stdout);
    }
    else
    {
    	wait(NULL);
    	printf("Child Complete \n");

    }
    
} 

int main() 
{ 
    CollatzConjecture(); 
    return 0; 
} 