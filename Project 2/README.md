# Project 2 
Samuel Chandler 
CE 4348

## Description
In this file there is the make files and executable for pipe grep which can be used to scan files of multiple types in a directory to find every occurance of this file and return the results in the form of the file name and the line location of the target word. 

- This program does not recursivly traverse a directory in search of files 

## Critical Sections 
Within this program there are critical sections only when adding and removing from a buffer during the add and remove functions. This is done to protect the posibility of an entry being overwriten of changed before being added to the buffer. All other fuctions can be preformed in parallel due to the structure of the buffer.

## Buffer Size for 30 files
For a directory of 30 files I'm unsure what would result in the most efficient buffersize

## Additional threads for Improved Performance
Out of all the stages Stage 3 could probably be broken into 3 threads with a buffer in between to allow the verification to be performed faster since each verification step does not rely on the others. 

## Known Bugs
I dont know of any bugs in the code itself, however there might be errors in the make file since I dont use them very often. 

