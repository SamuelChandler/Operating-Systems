#include <iostream>
#include <thread>
#include <string>
#include <vector>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>


#include <cmath>
#include <cstdlib>
#include <cassert>

#include <semaphore.h>

using namespace std;

struct Buffer{
    sem_t pcMutex;
    sem_t emptySlots;
    sem_t fullSlots;

    vector<string> contents;
};

vector<Buffer> bufferList(4);

DIR *dir;
dirent *dp;
struct stat fileStat;

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

int buffsize;
int filesize;
int uid;
int gid;
string target;

int main(int argc, char *argv[]){

    //getting arguments
    string s_buffsize = argv[1];
    string s_filesize = argv[2];
    string s_uid = argv[3];
    string s_gid = argv[4];
    target = argv[5];

    buffsize = stoi(s_buffsize);
    filesize = stoi(s_filesize);
    uid = stoi(s_uid);
    gid = stoi(s_gid);

    //creating the needed buffers
    for(Buffer &buffer: bufferList){
        sem_init(&buffer.pcMutex,0,1);
        sem_init(&buffer.emptySlots,0,buffsize);
        sem_init(&buffer.fullSlots,0,0);
    }

    //create threads
    stage1 = thread(s1);
    stage2 = thread(s2);
    //stage3 = thread(s3);
    //stage4 = thread(s4);
    //stage5 = thread(s5);

    //wait and join all threads 
    stage1.join();
    stage2.join();
    //stage3.join();
    //stage4.join();
    //stage5.join();

    //destroy all buffers once stages are complete
    for (Buffer &buffer: bufferList){
        sem_destroy(&buffer.pcMutex);
        sem_destroy(&buffer.emptySlots);
        sem_destroy(&buffer.fullSlots);
    }

    cout << "Buffers Destroyed" << endl;
  
}

//Adds to specified buffer
void add(string data, Buffer &buf){

    //reserving spot in buffer
    sem_wait(&buf.emptySlots);

    //acquire lock for critical section
    sem_wait(&buf.pcMutex);

    assert(buf.contents.size() >= 0 && buf.contents.size() <= buffsize);

    //insert item
    buf.contents.insert(buf.contents.begin(),data);

    //wake up consumer
    sem_post(&buf.fullSlots);

    //release critical section lock
    sem_post(&buf.pcMutex);
}

//removes from the specified buffer returning the removed value 
string remove(Buffer &buf){
    /* Reserve a full slot */
	sem_wait(&buf.fullSlots);

	/* Acquire the lock for critical section */
	sem_wait(&buf.pcMutex);

	assert(buf.contents.size() >= 0 && buf.contents.size() <= buffsize);

	// Delete an item at the end of the buffer and store into data
	string data = buf.contents.back();
	buf.contents.pop_back();
	assert(data != "");

	// Wake up a producer 
	sem_post(&buf.emptySlots);

	// Release the lock for critical section 
	sem_post(&buf.pcMutex);

	return data;
}



//stage 1 thread used for deterining the files within the current directory
//loading the file names into the first buffer 
void s1(){
    
    dir = opendir(".");

    while((dp = readdir(dir)) != NULL){
        
        if(stat(dp->d_name,&fileStat) == 0){
            if(S_ISDIR(fileStat.st_mode)){}
            else{
                add(dp->d_name,bufferList[0]);
            }
        }
    }
    add("Done",bufferList[0]);
    cout << "Stage 1 Complete" << endl;
}

//this stage is for file filtering that is defined in the arguments of the user
void s2(){

    while (1)
    {
        string name = remove(bufferList[0]);

        if(name == "Done"){
            break;
        }

        stat(name.c_str(),&fileStat);

        int size = fileStat.st_size;
        int f_uid = fileStat.st_uid;
        int f_gid = fileStat.st_gid;

        if(uid != -1 && f_uid != uid)
            continue;
        if(gid != -1 && f_gid != gid)
            continue;
        if(filesize != -1 && size <= filesize)
            continue;

        cout << name << endl;

        add(name,bufferList[1]);
    }


    cout << "Stage 2 Complete" << endl;
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