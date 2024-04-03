package Project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction{

    //class should fork until 2 elements exist
    static int threshold = 2;

    //beginning, end and the array itself
    private int[] array;

    public QuickSortTask(int[] arr){
        array = arr;
    }

    public void WriteToFile() throws Exception{
        String filename = "QuickSort_Result";
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
        if(array.length == threshold){
            if(array[1] < array[0]){
                //swap if the earlier value is larger
                int temp = array[1];
                array[1] = array[0];
                array[0] = temp;
            }
        }
        else if (array.length > 2){
            //size below threshold
            int pivot = array[array.length-1];

            List<Integer> higher = new ArrayList<Integer>(), lower = new ArrayList<Integer>();

            for(int x = 0; x <array.length;x++){
                //if higher than pivot then add to higher array else to lower
                if(array[x]<pivot){
                    lower.add(array[x]);
                }else if(array[x] > pivot){
                    higher.add(array[x]);
                }
            }

            int[] low = new int[lower.size()];

            for(int x = 0; x <lower.size();x++){
                low[x] = lower.get(x);
            }

            int[] high = new int[higher.size()];

            for(int x = 0; x <higher.size();x++){
                high[x] = higher.get(x);
            }

            QuickSortTask lowQuickSortTask = new QuickSortTask(low);
            QuickSortTask highQuickSortTask = new QuickSortTask(high);

            lowQuickSortTask.fork();
            highQuickSortTask.fork();

            lowQuickSortTask.join();
            System.out.println(lowQuickSortTask + "\nLower Result: "+Arrays.toString(lowQuickSortTask.array) + "\n\n");

            highQuickSortTask.join();
            System.out.println(highQuickSortTask + "\nHigher Result: "+Arrays.toString(highQuickSortTask.array)+ "\n");

            low = lowQuickSortTask.array;
            high = highQuickSortTask.array;

            List<Integer> res = new ArrayList<Integer>();

            for(int x = 0; x <low.length;x++){
                res.add(low[x]);
            }

            res.add(pivot);

            for(int x = 0; x <high.length;x++){
                res.add(high[x]);
            }

            array = res.stream().mapToInt(Integer::intValue).toArray();

            System.out.println("\nResult: "+res + "\n\n");
        }
    }


}