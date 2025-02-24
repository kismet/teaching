#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
#include <math.h>

int isPrime(int n);

//Si usano i parametri per dare il contesto di esecuzione al processo figlio
//Abbiamo tre figli che devono fare tre cose diverse definisco tre funzioni 
//distinte
int procPrime(int n);

int procPureSquare(int n);

int procPureCubic(int n);


int main() {
	int myself = getpid();
	int n;
	printf("Inserisci un valore\n -> ");
	scanf("%d", &n);

    int child = 0;
    child = fork();
    if( child == 0 ){
        exit(procPrime(n));
    }
    child = fork();
    if( child == 0 ){
        exit(procPureCubic(n));
    }
    child = fork();
    if( child == 0 ){
        exit(procPureSquare(n));
    }
	
	child=wait(NULL);
	child=wait(NULL);
	child=wait(NULL);

	return 0;

}

int isPrime(int n){
    if(n%2 == 0) return 2;
    int e = sqrt(n)+1;
    for(int i=3; i<e; i+=2){
        if(n%i == 0 ) return i;
    }
    
    return 0;
}

int procPrime(int n){
    int my=getpid();
    for(int i=0;i<n;i++){
        if(isPrime(i)==0){
            printf("%5d: Numero primo %5d\n",my,i);
        }
        usleep(1000);
    }
    return 0;
}

int procPureSquare(int n){
    int my=getpid();
    for(int i=0;i<n;i++){
        int x = sqrt(i);
        if(x*x == i){
            printf("%5d: Quadrato %5d perfetto di %3d\n",my,i,x);
            usleep(10*1000);
        }
    }
    return 0;
}

int procPureCubic(int n){
    int my=getpid();
    for(int i=0;i<n;i++){
        long x = i*i*i;
        if( x <= n ){
            usleep(250*1000);
            printf("%5d: Cubo %5ld perfetto di %3d\n",my,x,i);
        }else{
            i = x;
        }
    }    
    return 0;
}
