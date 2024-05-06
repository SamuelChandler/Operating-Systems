#include <iostream>
#include <thread>
#include <string>
#include <vector>

#include <semaphore.h>

using namespace std;

struct Buffer{
    sem_t pcMutex;
    sem_t emptySlots;
    sem_t fullSlots;

    vector<string> contents;
};

vector<Buffer> bufferList(4);

thread stage1;
thread stage2; 
thread stage3;
thread stage4;
thread stage5;

void s1();
void s2();
void s3();
void s4();
void s5();


int main(int argc, char *argv[]){

    //getting arguments
    string s_buffsize = argv[1];
    string s_filesize = argv[2];
    string s_uid = argv[3];
    string s_gid = argv[4];
    string target = argv[5];

    int buffsize = stoi(s_buffsize);
    int filesize = stoi(s_filesize);
    int uid = stoi(s_uid);
    int gid = stoi(s_gid);

    //creating the needed buffers
    for(Buffer &buffer: bufferList){
        sem_init(&buffer.pcMutex,0,1);
        sem_init(&buffer.emptySlots,0,buffsize);
        sem_init(&buffer.fullSlots,0,0);
    }

    //create threads
    stage1 = thread(s1);
    stage2 = thread(s2);
    stage3 = thread(s3);
    stage4 = thread(s4);
    stage5 = thread(s5);

    //wait and join all threads 
    stage1.join();
    stage2.join();
    stage3.join();
    stage4.join();
    stage5.join();

    //destroy all buffers once stages are complete
    for (Buffer &buffer: bufferList){
        sem_destroy(&buffer.pcMutex);
        sem_destroy(&buffer.emptySlots);
        sem_destroy(&buffer.fullSlots);
    }

    cout << "Buffers Destroyed" << endl;
  
}

//stage 1 thread used for 
void s1(){
    cout << "Stage 1" << endl;
}

void s2(){
    cout << "Stage 2" << endl;
}

void s3(){
    cout << "Stage 3" << endl;
}

void s4(){
    cout << "Stage 4" << endl;
}

void s5(){
    cout << "Stage 5" << endl;
}