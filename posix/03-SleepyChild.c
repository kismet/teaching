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
        printf("%d: Ciao a tutti sono un figlio :)\n",myself);
        exit(EXIT_SUCCESS);
    }
    printf("%d: Tutto bene, la fork funziona ha il primo figlio  %d\n",myself,child);

    //Questo codice che segue da chi viene eseguito?
    //Dal padre, dal primo figlio o da entrambi, perchè?
    child = fork();
    if (child == 0){
        myself = getpid();
        printf("%d: Ciao a tutti sono un figlio :)\n",myself);
        exit(EXIT_SUCCESS);
    }
    //Questo codice che segue da chi viene eseguito?
    //Dal padre, dal secondo figlio, o da entrambi i figli, perchè?
    printf("%d: Tutto bene, la fork funziona ha il secondo figlio  %d\n",myself,child);
    int statusAlpha,statusBeta;
    int deadAlpha = wait(&statusAlpha);
    int deadBeta = wait(&statusBeta);
    printf("%d: %d e' terminato con codice %d\n",myself,deadAlpha,statusAlpha);
    printf("%d: %d e' terminato con codice %d\n",myself,deadBeta,statusBeta);
}
