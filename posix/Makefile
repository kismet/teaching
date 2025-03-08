CC=gcc
CFLAGS=

all: HelloPosix TwoChild Grandson SleepyChild SleepyChildren

HelloPosix:
	$(CC) -o HelloPosix 01-HelloPosix.c

TwoChild:
	$(CC) -o TwoChild 02a-TwoPosix.c

Grandson:
	$(CC) -o Grandson 02b-TwoPosix.c

SleepyChild:
	$(CC) -o SleepyChild 03-SleepyChild.c

SleepyChildren:
	$(CC) -o SleepyChildren 04-SleepyChildren.c

clean:
	rm -f *.o
	rm -f *.exe
