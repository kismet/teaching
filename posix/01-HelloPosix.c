#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>


int main(int argc,char *argv[]){
    int child;
    int myself = getpid();
    printf("Tutto nasce con %d\n",myself);
    child = fork();
    if (child == 0){
        myself = getpid();
        printf("%d: Ciao a tutti :)\n",myself);
        exit(EXIT_SUCCESS);
    }else if(child > 0){
        printf("%d: Tutto bene, la fork funziona ha creato %d\n",myself,child);
        int status;
        int dead = wait(&status);
        printf("%d: %d e' terminato con codice %d",myself,dead,status);
    }
}
