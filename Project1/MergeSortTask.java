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
        String filename = "MergeSort_Result.txt";
        BufferedWriter out = null;
        out = new BufferedWriter(new FileWriter(filename));

        System.out.println("\nFinal Result: "+Arrays.toString(array));
        for(int i = 0 ; i<array.length;i++){
            out.write(array[i]+" ");
            out.newLine();
        }

        out.flush();
        out.close();
    }

    public void compute(){
        if(start<finish){
            int mid =  (start+finish)/2;
            

            MergeSortTask left = new MergeSortTask(array,start,mid);
            MergeSortTask right = new MergeSortTask(array,mid+1,finish);

            left.fork();
            left.join();

            right.fork();
            right.join();
           

            merge(start, mid, finish);
            return;
        }
        return;
    }

    public void merge(int left, int mid, int right){
        
        //define the lengths of each portion of the array
        int lenLeft = mid - left + 1;
        int lenRight = right - mid;

        //create and populate temp arrays of left and right portions
        int L[] = new int[lenLeft];
        int R[] = new int[lenRight];

        for(int i =0;i < lenLeft; ++i){
            L[i] = array[left +i];
        }

        for(int i =0;i < lenRight; ++i){
            R[i] = array[mid+1+i];
        }

        //start the merge
        int x =0,y = 0,k=left;

        while(x < lenLeft && y < lenRight){
            if(L[x] < R[y]){
                array[k] = L[x];
                x++;
            }else {
                array[k] = R[y];
                y++;
            }
            k++;
        }

        while (x < lenLeft) {
            array[k] = L[x];
            x++;
            k++;
        }

        while (y < lenRight) {
            array[k] = R[y];
            y++;
            k++;
        }
        
    }
}
