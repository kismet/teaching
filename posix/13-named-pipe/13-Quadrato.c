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
  int c = 0;
  do{
    int check = read(num_file,&n,sizeof(n));
    if(check == -1 || check != sizeof(n)){
      printf("%5d: Non piu' possibile usare la PIPE\n", check);
      break;
    }
    if( n>0 && n<(1<<15) ){
      printf("%5d: Ho ricevuto (%d) ed il suo quadrato e' (%d)\n", c, n, n*n);
    } else if( n>0 && n>=(1<<15) ){
      printf("%5d: Ho ricevuto (%d) ma troppo grande per calcolare il quadrato\n", c, n, n*n);
    }
    c++;
  }while(n>=0);
  n=-1;
  close(num_file);
}
