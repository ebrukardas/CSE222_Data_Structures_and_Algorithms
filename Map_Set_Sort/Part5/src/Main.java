import java.util.Random;

public class Main {

    public static void main(String[] args){

        SortAlgorithms<Integer> obj = new SortAlgorithms<>();

        // array sizes
        int sizes[] = {100, 1000, 5000, 10000};
        // array to sort
        Integer[] numbers;
        // array to keep runtime
        long times[] = new long[10];

        // choices for sort algorithms in SortAlgorithms class
        for(int choice=0; choice<4/*5*/; ++choice) {

            // loop for different size of arrays
            for (int k = 0; k < sizes.length; ++k) {

                // Create array with random numbers
                numbers = new Integer[sizes[k]];
                for (int i = 0; i < sizes[k]; ++i) {
                    Random r = new Random();
                    numbers[i] = r.nextInt(10000);
                }

                // switch case for different sort algorithms
                long start;
                obj.reverseSort(numbers);       // for worst-case

                switch (choice) {
                    case 0:
//                        obj.reverseSort(numbers);       // for worst-case
                        start = System.nanoTime();
                        obj.insertionSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 1:
                        start = System.nanoTime();
                        obj.mergeSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 2:
                        start = System.nanoTime();
                        obj.quickSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 3:
                        start = System.nanoTime();
                        obj.heapSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                }
            }


            // reverse order array
            if(choice==0)
                System.out.println("Insertion sort");
            //
            else if(choice==1)
                System.out.println("Merge sort");
            //
            else if(choice==2)
                System.out.println("Quick sort");
            //
            else if(choice==3)
                System.out.println("Heap sort");

            for (int j = 0; j < sizes.length; ++j)
                System.out.println("Array of size:" + sizes[j] + " Run time:" + times[j]);
            System.out.println("\n\n");

        }


    }


}
