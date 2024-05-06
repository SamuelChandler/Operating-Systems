#include <stdlib.h>
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char *argv[])
{
    string s_buffsize = argv[1];
    string s_filesize = argv[2];
    string s_uid = argv[3];
    string s_gid = argv[4];
    string target = argv[5];

    int buffsize = stoi(s_buffsize);
    int filesize = stoi(s_filesize);
    int uid = stoi(s_uid);
    int gid = stoi(s_gid);


}