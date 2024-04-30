/*
 Samuel Chandler 
 CE4348
 4.3.2024
 Project 1 

 This file reads in the random file created by "RandomIntegerGenerator.java" and starts sorting using merge sort and quick sort 
 saving the results to thier respective text files. 
 */

package Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws Exception{ 

        int[] inputData;
        ArrayList <Integer> inData = new ArrayList<Integer>();
        String readfile = "random_integers.txt";

        //read random file into input data
        BufferedReader bf = new BufferedReader(new FileReader(readfile));
        String line = bf.readLine();

        //read into list
        while(line != null){
            inData.add(Integer.parseInt(line));
            line = bf.readLine();
        }
        bf.close();

        System.err.println("Inputted Data Array: "+inData.toString());

        inputData = inData.stream().mapToInt(Integer::intValue).toArray();


        //QuickSortTask Quicksort = new QuickSortTask(inputData);
        //Quicksort.compute();
        //Quicksort.WriteToFile();

        MergeSortTask MergeSort = new MergeSortTask(inputData, 0, inputData.length-1);
        MergeSort.compute();
        MergeSort.WriteToFile();

    }
}
