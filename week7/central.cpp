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

    p->totalSlot = 50;
    p->occupied = 0;
    p->available = 50;
    p->entered = 0;
    p->left = 0;
    p->lastslot = 0;

    for (int i = 0; i < 50; i++) {
        p->slots[i] = false;
    }

    mkfifo(CENTRAL, 0666);
    mkfifo(FIFOG1, 0666);
    mkfifo(FIFOG2, 0666);
    mkfifo(FIFOG3, 0666);

    

    int central = open(CENTRAL, O_RDONLY);
    int fg1 = open(FIFOG1, O_WRONLY);
    int fg2 = open(FIFOG2, O_WRONLY);
    int fg3 = open(FIFOG3, O_WRONLY);

    if (central == -1 || fg1 == -1 || fg2 == -1) {
        perror("open");
        return 1;
    }

    char buf[32];

    while (true) {
        int bytes = read(central, buf, sizeof(buf) - 1);

        if (bytes <= 0)
            continue;

        buf[bytes] = '\0';

        int gate = buf[0] - '0';

        cout << "Received: " << buf << endl;

        int slot = -1;

        for (int i = 0; i < 50; i++) {
            if (!p->slots[i]) {
                slot = i;
                break;
            }
        }

        if (slot != -1) {
            p->slots[slot] = true;
            p->available--;
            p->occupied++;
            p->entered++;
            p->lastslot = slot + 1;

            char response[32];
            sprintf(response, "GREEN %d", slot + 1);

            if (gate == 1) {
                write(fg1, response, strlen(response) + 1);
            }
            else if (gate == 2) {
                write(fg2, response, strlen(response) + 1);
            }

            cout << "Gate " << gate
                 << " -> Slot " << slot + 1
                 << " allocated -> GREEN" << endl;
        }
        else {
            const char* response = "RED";

            if (gate == 1) {
                write(fg1, response, strlen(response) + 1);
            }
            else if (gate == 2) {
                write(fg2, response, strlen(response) + 1);
            }
            else if (gate == 3) {
                write(fg3, response, strlen(response) + 1);
            }

            cout << "No slot available -> RED" << endl;
        }
    }
}