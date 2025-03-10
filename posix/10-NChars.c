#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define PATH_LEN 1024
#define BUFFER_SIZE 4096


int main(int argc,char* argv[]){
  char percorso[PATH_LEN];
  printf("Inserisci il nome del file da analizzare\n -> ");
  scanf("%s",percorso);
  percorso[PATH_LEN-1]='\0';
  printf("Trying to open: %s\n", percorso);
  int num_file = open(percorso, O_RDONLY);
  if(num_file<0){
    printf("Impossibile aprire il file");
    exit(errno);
  }
  printf("File aperto in lettura con File Descriptor: %d\n",num_file);
  char buffer[BUFFER_SIZE];
  int nread = read(num_file,buffer, sizeof(buffer));
  long size = 0;
  while(nread != 0 ){
    size = size + nread;
    nread = read(num_file,buffer, sizeof(buffer));
  }
  printf("%s: %d\n",percorso,size );
}
