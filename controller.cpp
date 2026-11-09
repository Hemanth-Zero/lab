#include <iostream>
#include <unistd.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <string.h>
#include "shared.h"
using namespace std;

int main() {
    int shmid = shmget(KEY, sizeof(Data), 0666);
    Data* d = (Data*)shmat(shmid, 0, 0);

    int fd = open(FIFO, O_WRONLY);

    while(true){
        sleep(2);
        int current_count = d->count;
        const char* cmd = (current_count > 10) ? "GREEN" : "RED";
        write(fd, cmd, strlen(cmd) + 1);
        cout << "Read: " << d->count << " Sent: " << cmd << '\n';
        sleep(2);
    }
}