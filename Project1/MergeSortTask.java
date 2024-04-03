package Project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction{
    //class should fork until 2 elements exist
    static int threshold = 1;

    //beginning, end and the array itself
    private int[] array;
    private int start;
    private int finish;

    public MergeSortTask(int[] arr,int s, int f){
        array = arr;
        start = s;
        finish = f;
    }

    public void WriteToFile() throws Exception{
        String filename = "MergeSort_Result";
        BufferedWriter out = null;
        out = new BufferedWriter(new FileWriter(filename));


        for(int i = 0 ; i<array.length;i++){
            out.write(array[i]+" ");
            out.newLine();
        }

        out.flush();
        out.close();
    }

    public void compute(){
        if(finish - start == threshold){
            if(array[finish] < array[start]){
                //swap if the earlier value is larger
                int temp = array[finish];
                array[finish] = array[start];
                array[start] = temp;
                
            }
        }else if (array.length > 2){
        
            int mid = (finish - start) /2;
            
            System.out.println(mid);
            System.out.println(Arrays.toString(array));

            MergeSortTask left = new MergeSortTask(array,start,mid);
            MergeSortTask right = new MergeSortTask(array,mid+1,finish);

            left.fork();
            right.fork();

            left.join();
            right.join();

            
        }
    }
}
