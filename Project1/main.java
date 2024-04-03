package Project1;

public class main {
    public static void main(String[] args) throws Exception{ 
        int[] inputData= {1,7,3,5,4,6,2,8,9,21,3,345,436,234,6436,745,3,3,3,234234,235,32,54};

        QuickSortTask Quicksort = new QuickSortTask(inputData);
        Quicksort.compute();
        Quicksort.WriteToFile();

        MergeSortTask MergeSort = new MergeSortTask(inputData, 0, inputData.length-1);
        MergeSort.compute();
        MergeSort.WriteToFile();

    }
}
