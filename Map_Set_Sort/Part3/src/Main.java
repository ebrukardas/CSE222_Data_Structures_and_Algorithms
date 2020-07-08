import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        MergeSort<Integer> obj= new MergeSort<>();
        LinkedList<Integer> numbers = new LinkedList<>();

        int sizes[] = {10, 100, 500, 1000};
        long times[] = new long[sizes.length];

        for (int k = 0; k < sizes.length; ++k) {

            // Create array with random numbers
            numbers.clear();
            for (int i = 0; i < sizes[k]; ++i) {
                Random r = new Random();
                numbers.add(r.nextInt(1000));
            }
            obj.reverseSort(numbers);
/*for(Integer e: numbers)
    System.out.print(e + " ");
System.out.println("\n");
*/
            long start = System.nanoTime();
            obj.mergeSort(numbers);
            times[k] = System.nanoTime() - start;

        }

        for (int j = 0; j < sizes.length; ++j)
            System.out.println("Array of size:" + sizes[j] + " Run time:" + times[j]);
        System.out.println("\n\n");

    }

}
