#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>


#define PRIMES 1024
#define LOG if(1==0)


int producer2consumer[2];
int pidGenerator = -1, pidFactor = -1;
int primi[PRIMES];
int fattori[PRIMES];

int isPrime(int n){
  for(int i=1;primi[i]*primi[i]<n;i++){
    if(primi[i] == -1) return 1;
    if(n % primi[i] == 0) return 0;
  }
  return 1;
}

void findNextPrime(int last){
  LOG printf("Looking for the prime after %d\n",primi[last]);
  for(int t=primi[last]+2;t<primi[last]*2;t+=2){
    if(isPrime(t)==1) {
      primi[last+1] = t;
      primi[last+2] = -1;
      return;
    }
  }
}

int factors(int n){
  int idx = 0;
  fattori[idx] = -1;
  while(n != 1){
    if(primi[idx]==-1) findNextPrime(idx-1);
    if (n % primi[idx] == 0 ){
      LOG printf("%d e' un fattore di %d\n",primi[idx],n);
      fattori[idx]++;
      n = n / primi[idx];
    }else{
      idx++;
      fattori[idx]=-1;
    }
  }
}


int generatore_process(int sx, int dx){
  close(producer2consumer[0]);
  int me = getpid();
  if(sx<1) sx = 1;
  for(int i=sx; i<dx; i++){
    write(producer2consumer[1],&i,sizeof(i));
    printf("%5d: Inviato %d\n",me,i);
    usleep(50*1000);
  }
  sx=0;
  write(producer2consumer[1],&sx,sizeof(sx));
  close(producer2consumer[1]);
  return 0;
}

int factor_process(){
  close(producer2consumer[1]);
  int me = getpid();
  int value = -1;
  int n = 0;
  n = read(producer2consumer[0],&value,sizeof(value));
  while(n > 0 && value > 0){
    printf("%5d: Ricevuto %d\nI fattori sono:\n",me,value);
    factors(value);
    int x = 1;
    int i = 0;
    while(x != value){
      if(fattori[i]>=0) {
        printf("%d x %d = %d\n",x,primi[i],x*primi[i]);
        x = x * primi[i];
        fattori[i]--;
      }else{
        LOG printf("%d non e' un fattore di %d\n",primi[i],value);
        i++;
      }
    }
    n = read(producer2consumer[0],&value,sizeof(value));
  }
  close(producer2consumer[0]);
  return 0;
}



void readInterval(int *s,int *e){
  do{
    printf("Inserisci estremo sinistro dell'intervallo [A,B]\n -> ");
    scanf("%d",s);
    printf("Inserisci estremo destro dell'intervallo [A,B]\n -> ");
    scanf("%d",e);
    if( (*s) >= (*e) ){
      printf("Inserisci un intervallo dove A e' minore di B\n");
    }
  }while( (*s) >= (*e) );
}

void setupPipes(){
  int check = pipe(producer2consumer);
  if(check<0){
    printf("Impossibile creare la PIPE terminazione in corso (%d)\n",errno);
    perror("Errore creazione pipe\n");
    exit(errno);
  }
}

void createProcesses(int a,int b){
  int id;
  id = fork();
  if(id < 0) {
    printf("Impossibile creare processi figli (%d)\n",errno);
    perror("Errore creazione fork\n");
    close(producer2consumer[0]);
    close(producer2consumer[1]);
    exit(errno);
  }
  if(id == 0){
    int status = generatore_process(a,b);
    exit(status);
  }
  pidGenerator = id;
  id = fork();
  if(id < 0) {
    printf("Impossibile creare processi figli (%d)\n",errno);
    printf("Terminare manualmente il processo (%d)\n",pidGenerator);
    perror("Errore creazione fork, possibili processi zombie\n");
    close(producer2consumer[0]);
    close(producer2consumer[1]);
    exit(errno);
  }
  if(id == 0){
    int status = factor_process();
    exit(status);
  }
  pidFactor = id;
  close(producer2consumer[0]);
  close(producer2consumer[1]);
}

void waitProcesses(){
  if(pidFactor != -1){
    wait(NULL);
  }
  if(pidGenerator != -1){
    wait(NULL);
  }
}

int main(int argc,char* argv[]){
  int sx=20,dx=40;
  primi[0] = 2;
  primi[1] = 3;
  primi[2] = 5;
  primi[3] = 7;
  primi[4] = 11;
  primi[5] = 13;
  primi[6] = -1;

  readInterval(&sx,&dx);

  setupPipes();

  createProcesses(sx,dx);

  waitProcesses();
}
