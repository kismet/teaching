#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

//Questa funzione viene utilizzata per definire il comportamento
//di un processo per aumentare la leggibilita del codice
int sleepy_child(int n);

int main(int argc,char *argv[]){
    int nchildren;
    int myself = getpid();
    int dead, status, child;
    printf("Tutto nasce con %d\n",myself);
    printf("Quanti processi vuoi creare\n -> ");
    scanf("%d",&nchildren);
    int nsleep[nchildren];
    
    for(int i=0;i<nchildren;i++){
        int seconds;
        printf("Quanti deve aspettare il processo %d\n -> ",(i+1));
        scanf("%d",&seconds);
        nsleep[i]=seconds;
    }
    
    
    for(int i=0;i<nchildren;i++){
        child = fork();
        if (child == 0){
            exit(sleepy_child(nsleep[i]));
        }
    }    
    
    for(int i=0;i<nchildren;i++){
        dead = wait(&status);
        printf("%d: %d e' terminato con codice %d\n",myself,dead,status);
    }     
    
    return 0;
}

int sleepy_child(int n){
    int pid;
    pid = getpid();
    printf("%d: Ciao a tutti sono un figlio assonato, vado a letto...\n",pid);
    sleep(n);
    printf("%d: Una bella dormita di %d secondi\n",pid,n);
    return 0;
}
