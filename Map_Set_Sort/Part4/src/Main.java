import java.util.Random;

public class Main {

    public static void main(String[] args){

        SortAlgorithms<Integer> obj = new SortAlgorithms<>();

        // array sizes
        int sizes[] = {5, 10, 20, 50, 100, 200, 500, 1000, 5000, 10000};
        // array to sort
        Integer[] numbers;
        // array to keep runtime
        long times[] = new long[10];

        // choices for sort algorithms in SortAlgorithms class
        for(int choice=0; choice<4; ++choice) {

            // loop for different size of arrays
            for (int k = 0; k < 10; ++k) {

                // Create array with random numbers
                numbers = new Integer[sizes[k]];
                for (int i = 0; i < sizes[k]; ++i) {
                    Random r = new Random();
                    numbers[i] = r.nextInt(10000);
                }

                // switch case for different sort algorithms
                long start = System.nanoTime();
                switch (choice) {
                    case 0:
                        obj.insertionSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 1:
                        obj.mergeSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 2:
                        obj.quickSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                    case 3:
                        obj.heapSort(numbers);
                        times[k] = System.nanoTime() - start;
                        break;
                }

            }
            //
            if(choice==0)
                System.out.println("Insertion sort");
            else if(choice==1)
                System.out.println("Merge sort");
            else if(choice==2)
                System.out.println("Quick sort");
            else if(choice==3)
                System.out.println("Heap sort");

            for (int j = 0; j < 10; ++j)
                System.out.println("Array of size:" + sizes[j] + " Run time:" + times[j]);
            System.out.println("\n\n");

        }

    }

}
