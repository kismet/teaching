#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define PATH_LEN 1024
#define BUFFER_SIZE 4096


int main(int argc,char* argv[]){
  if(argc < 2){
    printf("Usare %s <percorso>",argv[0]);
    exit(-1);
  }

  int righe, colonne;
  printf("Quante e' alto il rettangolo\n -> ");
  scanf("%d", &righe);
  printf("Quante e' largo il rettangolo\n -> ");
  scanf("%d", &colonne);

  printf("Trying to open: %s\n", argv[1]);
  int num_file = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
  if(num_file<0){
    printf("Impossibile aprire o creare il file, potrebbe essere un problema di permessi di scrittura\n");
    exit(errno);
  }
  printf("File pronto per scrivervi File Descriptor: %d\n",num_file);
  char item = '*';
  char newline = '\n';
  for(int j=0;j<righe;j++){
    for(int i=0;i<colonne;i++){
      write(num_file,&item,sizeof(item));
    }
    write(num_file,&newline,sizeof(newline));
  }
  printf("%s: Creato Rettangolo\n", argv[1]);
}
