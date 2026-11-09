#include <iostream>
#include <unistd.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include "shared.h"
using namespace std;

int main() {
    int shmid = shmget(KEY, sizeof(Data), 0666 | IPC_CREAT);
    Data* d = (Data*)shmat(shmid, 0, 0);

    mkfifo(FIFO, 0666);
    int fd = open(FIFO, O_RDONLY);

    char buf[32];
    while(true){
        d->count = rand() % 20;
        int bytes = read(fd, buf, sizeof(buf) - 1);
        if (bytes > 0) {
            buf[bytes] = '\0';
            cout << "Count: " << d->count << " Command: " << buf << '\n';
        }
    }
}