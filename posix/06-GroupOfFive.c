#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
 
void ms_sleep(int ms);
 
int sleepy_child();
 
 
int main() {
    int myself = getpid();
    int N;
    do{
      printf("Inserisci un valore multiplo di 5: ");
      scanf("%d", &N);
      if (N % 5 != 0) {
          printf("Errore: Il numero deve essere multiplo di 5!\n");
      }
    }while(N%5 != 0);    
 
    pid_t pids[5]; //pid_t e' un alias per il tippo int
    while(N>0){
      // Creazione del gruppo di 5 processi figli
      for(int i = 0; i<5; i++){
          pids[i] = fork();
          if (pids[i] == 0) {
              //codice del figlio con EXIT!!!!!
              exit(sleepy_child());
          }
      }
      N-=5;
      for(int i = 0; i<5; i++){
        int dead = wait(NULL);
        printf("%d: processo %d del gruppo %d terminato\n",myself,dead,(N+5)/5);
      }
    }
 
    printf("%d: Tutti i processi figli sono terminati.\n",myself);
 
    return 0;

}

int sleepy_child(){
    int my = getpid();
    
    //Generazione di un seed per generare i numeri casuali
    int seed = ( time(NULL)*my ) % 1009;
    srand(seed);
    
    int t = (rand() % 500 + 1)* 10; // Tempo casuale tra 100ms e 5s
    printf("%d: Sto aspettando %.2fs \n", my, (t/1000.0f) );
    ms_sleep(t);
    return 0;
}



void ms_sleep(int ms){
    int s = ms/1000;
    sleep(s);
    ms = ms % 1000;
    int us = ms * 1000;
    usleep(us);
}
 
