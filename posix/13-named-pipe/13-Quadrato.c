#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define SECONDO 1000*1000

int main(int argc,char* argv[]){
  if(argc < 2){
    printf("Usare %s <pipe>",argv[0]);
    exit(-1);
  }
  printf("Trying to open: %s\n", argv[1]);
  int num_file = open(argv[1], O_RDONLY);
  if(num_file<0){
    printf("Impossibile aprire la pipe");
    perror("Qualcosa non ha funzionato");
    exit(errno);
  }

  int n;
  do{
    read(num_file,&n,sizeof(n));
    if(n>0){
      printf("Ho ricevuto (%d) ed il suo quadrato e' (%d)",n, n*n);
    }
  }while(n>=0);
  n=-1;
  close(num_file);
}
