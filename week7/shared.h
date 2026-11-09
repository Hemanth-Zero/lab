#ifndef SHARED_H
#define SHARED_H

#define KEY 12345

#define FIFOG1 "/tmp/g1"
#define FIFOG2 "/tmp/g2"
#define FIFOG3 "/tmp/g3"
#define CENTRAL "/tmp/c"

struct Parking {
    int totalSlot;
    int occupied;
    int available;
    int entered;
    int left;
    bool slots[50];
    int lastslot;
};

#endif