#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

const int BLOCK_SIZE = 5;
const int MONITOR_PERIOD = 1;

void ms_sleep(int ms);

int sleepy_child();

int cerca(int* vet, int n, int v);  

int main() {
	int myself = getpid();
	int nchildren;
	do {
		printf("Inserisci un valore multiplo di 5: ");
		scanf("%d", &nchildren);
		if (nchildren % BLOCK_SIZE != 0) {
			printf("Errore: Il numero deve essere multiplo di 5!\n");
		}
	} while(nchildren%5 != 0);

	int status[BLOCK_SIZE]; //1 -> Running , 0 -> Terminated
	pid_t pids[BLOCK_SIZE]; //pid_t e' un alias per il tippo int
	while(nchildren>0) {
		// Creazione del gruppo di 5 processi figli
		for(int i = 0; i<BLOCK_SIZE; i++) {
			pids[i] = fork();
			if (pids[i] == 0) {
				//codice del figlio con EXIT!!!!!
				exit(sleepy_child());
			}
			status[i] = 1;
		}
		nchildren-=5;
		int groupId = (nchildren+BLOCK_SIZE)/BLOCK_SIZE;
		int died = 0;
		while(died < BLOCK_SIZE ) {
		    //Aggiorna stato dei processi
			int dead = waitpid(-1,NULL,WNOHANG);
            while(dead > 0){
                died++;
                int idx = cerca(pids,BLOCK_SIZE,dead);  
                status[idx] = 0;
                dead = waitpid(-1,NULL,WNOHANG);
            }
            
            //Stampa stato dei processi
			printf("\n[STATO PROCESSI]\n");
			for(int i = 0; i<BLOCK_SIZE; i++) {
				if(status[i]==0){
					printf("PID: %d - Stato: TERMINATO\n", pids[i]);
				} else {
					printf("PID: %d - Stato: AVVIATO\n", pids[i]);
				}
			}
			
			//Aspetta il tempo richiesto tra un monitoraggio e l'altro
			sleep(MONITOR_PERIOD);
		}
	}

	printf("%d: Tutti i processi figli sono terminati.\n",myself);

	return 0;

}

int cerca(int* vet, int n, int v){
    for(int i=0;i<n;i++){
        if(vet[i]==v) return i;
    }
    return -1;
}


int sleepy_child() {
	int my = getpid();

	//Generazione di un seed per generare i numeri casuali
	int seed = ( time(NULL)*my ) % 1009;
	srand(seed);

	int t = (rand() % 50 + 1)*100; // Tempo casuale tra 100ms e 5s
	printf("%d: Aspettero' %.2fs \n", my, (t/1000.0f) );
	ms_sleep(t);
	return 0;
}



void ms_sleep(int ms) {
	int s = ms/1000;
	sleep(s);
	ms = ms % 1000;
	int us = ms * 1000;
	usleep(us);
}
