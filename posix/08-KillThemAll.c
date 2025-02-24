#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>


//Si usano i parametri per dare il contesto di esecuzione al processo figlio
int multiply_child(int n);


int main() {
	int myself = getpid();
	int nchildren;
	printf("Inserisci un valore\n -> ");
	scanf("%d", &nchildren);
	
	int children[nchildren];
	int pids[nchildren];
    for(int i=0; i<nchildren;i++){
    	printf("Inserisci il seme per %d processo\n -> ",(i+1) );
    	scanf("%d", &(children[i]));
    }	
    
    for(int i=0; i<nchildren;i++){
        pids[i] = fork();
        if(pids[i]==0){
            exit(multiply_child(children[i]));
        }
    }
    
    for(int i=0; i<nchildren;i++){
        pids[i] = wait(NULL);
    }

	return 0;

}


int multiply_child(int n) {
	int my = getpid();
	long value = n;
	while(1 == 1){
	    printf("%d: %ld\n",my,value);
	    value+=n;
	    int ms = 700*1000;
    	usleep(ms);
	}
}


