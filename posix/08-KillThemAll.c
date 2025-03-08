#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>


//La funzione che segue rappresenta il codice che dovra essere eseguito
//dai processi figli. E' IMPORTANTE notare che a livello di PROGETTAZIONE:
//1 - e' NECESSARIO creare per ogni ruolo/compito dei processi che creeremo
//una funzione distina (in questo scenario tutti i processi fanno la stessa
//cosa quindi basta una sola funzione)
//2 - E' consigliabile usare i parametri della funzione per configurare il
//comportamento di ogni singolo processo figlio
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
			sleep(1);
  }

  for(int i=0; i<nchildren;i++){
      pids[i] = wait(NULL);
  }

	return 0;

}


int multiply_child(int n) {
	int my = getpid();
	long value = n;
	//il codice e' scritto volutamente per non far mai terminare questa funziona
	//che rappresenta i processi figli, perche' il testo richiede la terminazione
	//dei proccessi figlie utilizzando il comando kill dalla shell
	while(1 == 1){
	    printf("%d: %ld\n",my,value);
	    value+=n;
	    int ms = 700*1000;
    	usleep(ms);
	}
}
