# Compilazione

Questi sono i passaggi necessari per compilare il sistema

```
gcc -o Gen -Os -O2 13-Generatore.c
gcc -o Pow 13-Quadrato.c -Os -O2
```

# Esecuzione

## Creazione PIPE

Aprire un terminale e creare una *named pipe* con il comando _mkfifo_

```
mkfifo canale
```

## Eseguire i due programmi

Aprire *due terminali* tutti nella stessa working directory in cui si trovano
i programmi: **Gen** , **Pow** (compilati in precedenza) e **canale** (la pipe creata nello step precedente)

### Terminale A

Eseguire:

```
./Gen canale
```

### Terminale B

Eseguire:

```
./Pow canale
```
