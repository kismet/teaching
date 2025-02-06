#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

//Questa funzioni servono per definire il comportamento di ogni processo
//e permettono di migliorare la progettazione del codice e la sua leggibilita.
//Per ogni _categoria_ di processo definiamo una funzione

//Processo che stampa i numeri dispari minori di N
void process_odd(int n);

//Processo che stampa i numeri pari minori di N
void process_even(int n);

//Procedura per aspettare x millisecondi 
void ms_sleep(int x);

const int NCHILDREN = 2;

int main(int argc,char *argv[]){
    int child;
    int myself = getpid();
    int dead, status;
    printf("Tutto nasce con %d\n",myself);
    printf("Inserisci un valore\n -> ");
    int value;
    scanf("%d",&value);
    child = fork();
    if (child == 0){
        process_odd(value);
        //Notare che qui manca la exit
    }else if( child < 0){
        printf("%d: Impossibile creare un processo figliro. Meglio fermarsi qui\n",myself);
        exit(child);
    }

    child = fork();
    if (child == 0){
        process_even(value);
        //Notare che qui manca la exit
    }else if( child < 0){
        printf("%d: Impossibile creare un processo figlio\n",myself);
        printf("%d: Aspetto la fine del processo figlio\n",myself);
        dead = wait(&status);
        printf("%d: %d e' terminato con codice %d\n",myself,dead,status);
        exit(child);
    }

    for(int i=0;i<NCHILDREN;i++){
        dead = wait(&status);
        printf("%d: %d e' terminato con codice %d\n",myself,dead,status);
    }

    //Dove e chi definisce EXIT_SUCCESS?
    return EXIT_SUCCESS;
}

void ms_sleep(int ms){
    int s = ms/1000;
    sleep(s);
    ms = ms % 1000;
    int us = ms * 1000;
    usleep(us);
}

void process_odd(int n){
    int mypid = getpid();
    for(int i=1;i<n;i+=2){
        printf("%d: Pari    %5d\n",mypid,i);
        ms_sleep(i);
    }
    exit(EXIT_SUCCESS);
}

//Processo che stampa i numeri pari minori di N
void process_even(int n){
    int mypid = getpid();
    for(int i=0;i<n;i+=2){
        printf("%d: Dispari %5d\n",mypid,i);
        ms_sleep(i);
    }
    exit(EXIT_SUCCESS);    
}
