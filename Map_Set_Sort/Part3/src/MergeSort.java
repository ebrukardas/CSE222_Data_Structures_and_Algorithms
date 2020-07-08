import java.util.LinkedList;

public class MergeSort < T extends Comparable <T> > {

    /**
     * Sorts the linked list with merge algorithm
     * @param list list to sort
     * @return sorted list
     */
    public LinkedList<T> mergeSort(LinkedList<T> list){

        if(list.size()<=1)
            return list;

        int half = list.size()/2;
        LinkedList<T> left = new LinkedList<T>();
        LinkedList<T> right = new LinkedList<T>();

        int c=0;
        for( T temp : list )
            if( c++ < half )
                left.add(temp);
            else
                right.add(temp);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);

    }

    /**
     * Merges list as sorted
     * @param leftSide left sublist to merge as sort
     * @param rightSide right sublist to merge as sort
     * @return sorted and merged list
     */
    private LinkedList<T> merge(LinkedList<T> leftSide, LinkedList<T> rightSide){

        LinkedList<T> merged = new LinkedList<>();

        while ( leftSide.size()>0 && rightSide.size()>0 ){
            if( leftSide.getFirst().compareTo(rightSide.getFirst()) > 0 ){
                merged.add( rightSide.getFirst() );
                rightSide.removeFirst();
            }else{
                merged.add( leftSide.getFirst() );
                leftSide.removeFirst();
            }
        }

        while ( leftSide.size()>0 ){
            merged.add( leftSide.getFirst() );
            leftSide.removeFirst();
        }
        while ( rightSide.size()>0 ) {
            merged.add(rightSide.getFirst());
            rightSide.removeFirst();
        }

        return merged;

    }


    /**
     * To take worst-case time complexity, reverse sort operation
     * @param list list to reverse sort
     */
    public void reverseSort(LinkedList<T> list){
        for(int i=0; i<list.size(); ++i){
            for(int j=0; j<list.size(); ++j){
                T el = list.get(i);
                if(el.compareTo(list.get(j)) > 0){
                    T temp = el;
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

    }

}
