#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

//Questa funzione viene utilizzata per definire il comportamento
//di un processo per aumentare la leggibilita del codice
int sleepy_child(int n);

//Queste costanti mi aiutano ad evitare la worst-practise dei 'Magic Number'
const int SLEEPY_PARENT = 5;
const int SLEEPY_CHILD[] = {7,12};

int main(int argc,char *argv[]){
    int child;
    int myself = getpid();
    printf("Tutto nasce con %d\n",myself);
    child = fork();
    if (child == 0){
        exit(sleepy_child(SLEEPY_CHILD[0]));
    }
    printf("%d: Tutto bene, la fork funziona ha creato il primo figlio  %d\n",myself,child);

    child = fork();
    if (child == 0){
        exit(sleepy_child(SLEEPY_CHILD[1]));
    }
    printf("%d: Tutto bene, la fork funziona ha creato il secondo figlio  %d\n",myself,child);

    printf("%d: Dopo tutte queste fork mi è venuto sonno..\n", myself);
    sleep(SLEEPY_PARENT);
    printf("%d: %d secondi mi sono bastati, ora sono sveglio!\n",myself, SLEEPY_PARENT);

    int statusAlpha,statusBeta;
    int deadAlpha = wait(&statusAlpha);
    int deadBeta = wait(&statusBeta);
    printf("%d: %d e' terminato con codice %d\n",myself,deadAlpha,statusAlpha);
    printf("%d: %d e' terminato con codice %d\n",myself,deadBeta,statusBeta);
}

int sleepy_child(int n){
    int pid;
    pid = getpid();
    printf("%d: Ciao a tutti sono un figlio assonato, vado a letto...\n",pid);
    sleep(n);
    printf("%d: Una bella dormita di %d secondi\n",pid,n);
    return 0;
}
