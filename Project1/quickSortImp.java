package Project1;
/* Samuel Chandler, April 3rd 2023, CE4348
 * 
 * The Quicksort implementation will use the Quicksort algorithm for dividing
the list of elements to be sorted into a left half and a right half based on the position
of the pivot value.

when the list to be sorted
falls within some threshold value (for example, the list is size 100 or fewer, you
cannot hard code how many times a new thread is created), directly apply a
simple algorithm such as the Selection or Insertion sort.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//split on the last element

public class quickSortImp {

    public static List<Integer> SplitAndMerge(int[] args){ 
    
        //pivot point of the array for the quick sort
            int pivot = args[args.length-1];
    
            //the results storing the split array
            List<Integer> higher = new ArrayList<Integer>(), lower = new ArrayList<Integer>();
    
            //resulting list after recombination 
            List<Integer> result = new ArrayList<Integer>();
    
        //iterate through each element of the input Data
            for(int x = 0; x <args.length;x++){
                //if higher than pivot then add to higher array else to lower
                if(args[x]<pivot){
                    lower.add(args[x]);
                }else if(args[x] > pivot){
                    higher.add(args[x]);
                }
            }

            //if higher is larger than 2 perform split and merge, at 2 swap, else do nothing 
            if(higher.size() > 2){
                higher = SplitAndMerge(higher.stream().mapToInt(Integer::intValue).toArray());
            }else if(higher.size() == 2){
                if(higher.get(0) > higher.get(1)){
                    higher.add(higher.get(0));
                    higher.remove(0);
                }   
            }

            if(lower.size() > 2){
                lower = SplitAndMerge(lower.stream().mapToInt(Integer::intValue).toArray());
            }else if(lower.size() == 2){
                if(lower.get(0) > lower.get(1)){
                    lower.add(lower.get(0));
                    lower.remove(0);
                }   
            }
    
            //print values to check 

            System.out.println("\ndata: "+ Arrays.toString(args));
            System.out.println("Pivot: "+pivot);
            System.out.println("Lower: "+lower.toString());
            System.out.println("Higher: " + higher.toString());
    
            // adding the results to the split
            result.addAll(lower);
            result.add(pivot);
            result.addAll(higher);

            System.out.println("Result: " + result.toString());
    
            //print result
            return(result);
    
        }

    public static void main(String[] args){ 
        int[] inputData= {1,7,3,5,4,6,2,8,9,21,3};

        List<Integer> res = SplitAndMerge(inputData);

        System.out.println("Final Result:"+res.toString());
    }

    
}


//next steps:
// 1 create quick sort base without forking [X] 
// 2 implement with forking and window size []
// 3 integrate Random int generator         []
