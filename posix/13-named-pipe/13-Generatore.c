#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define SECONDO 1000*1000

void generatore(int fd, int n, int us);

int main(int argc,char* argv[]){
  if(argc < 2){
    printf("Usare %s <pipe>",argv[0]);
    exit(-1);
  }
  printf("Trying to open: %s\n", argv[1]);
  int num_file = open(argv[1], O_WRONLY);
  if(num_file<0){
    printf("Impossibile aprire la pipe");
    perror("Qualcosa non ha funzionato");
    exit(errno);
  }

  int genera, delta;

  printf("Quanti valori devo generare al secondo?\n -> ");
  scanf("%d",&genera);
  delta = SECONDO / genera;

  int n;
  printf("Quanti valori devo generare?\n -> ");
  scanf("%d",&n);
  do{
    generatore(num_file,n,delta);
    printf("Quanti valori devo generare?\n(0 per terminare il programma)\n -> ");
    scanf("%d",&n);
  }while(n!=0);
  n=-1;
  write(num_file,&n,sizeof(n));
  close(num_file);
}


void generatore(int file, int n, int us){
  //Generazione di un seed per generare i numeri casuali
  int my = getpid();
	int seed = ( time(NULL)*my ) % 1009;
	srand(seed);
  for(int i=0;i<n;i++){
    int value = rand() % (1<<17);
    int check;
    check = write(file,&value,sizeof(value));
    if(check == -1){
      printf("Non piu' possibile usare la PIPE\n");
      return;
    }
    usleep(us);
  }
}
