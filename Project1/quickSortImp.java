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



//split on the last element

public class quickSortImp {

    //variable used for testing
    int[] inputData= {1,7,3,5,4,6,2};

    //pivot point of the array for the quick sort
    int pivot = inputData[inputData.length-1];

    //the results storing the split array
    int[] lowerArray;
    int[] higherArray = {};


    //iterate through each element of the input Data
    for(int x = 0; x <inputData.length-1;x++){

    }



}

//next steps:
// 1 create quick sort base without forking [] 
// 2 implement with forming and window size []
// 3 integrate Random int generator         []
