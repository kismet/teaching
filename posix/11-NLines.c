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
  printf("Trying to open: %s\n", argv[1]);
  int num_file = open(argv[1], O_RDONLY);
  if(num_file<0){
    printf("Impossibile aprire il file");
    exit(errno);
  }
  printf("File aperto in lettura con File Descriptor: %d\n",num_file);
  char buffer[BUFFER_SIZE];
  int nread = read(num_file,buffer, sizeof(buffer));
  int lines = 1;
  while(nread != 0 ){
    for(int i=0; i<nread; i++){
      if( buffer[i]=='\n' ){
        lines++;
      }
    }
    nread = read(num_file,buffer, sizeof(buffer));
  }
  printf("%s: %d\n",argv[1],lines );
}
