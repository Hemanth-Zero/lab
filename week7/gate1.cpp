#include <iostream>
#include <unistd.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <string.h>
#include "shared.h"

using namespace std;

int main() {
    int shmid = shmget(KEY, sizeof(Parking), 0666 | IPC_CREAT);
    if (shmid == -1) {
        perror("shmget");
        return 1;
    }

    Parking* p = (Parking*)shmat(shmid, nullptr, 0);
    if (p == (Parking*)-1) {
        perror("shmat");
        return 1;
    }

    mkfifo(FIFOG1, 0666);

    int central = open(CENTRAL, O_WRONLY);
    if (central == -1) {
        perror("open central");
        return 1;
    }

    int fg1 = open(FIFOG1, O_RDONLY);
    if (fg1 == -1) {
        perror("open g1");
        return 1;
    }

    char buf[32];
    const char* sendcmd = "1 REQUEST";

    while (true) {
        write(central, sendcmd, strlen(sendcmd) + 1);

        int bytes = read(fg1, buf, sizeof(buf) - 1);

        if (bytes > 0) {
            buf[bytes] = '\0';
            cout << "Gate 1 Command: " << buf << endl;
        }

        sleep(5);
    }
}