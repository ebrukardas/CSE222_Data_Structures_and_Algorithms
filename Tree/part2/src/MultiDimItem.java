import java.util.Vector;

public class MultiDimItem < E extends Comparable<E> >  {

    /**
     * Member that takes item in dimensions
     */
    Vector<E> vec;

    /**
     * Constrcutor that takes vector as argument
     * @param vec
     */
    public MultiDimItem(Vector<E> vec){
        this.vec = vec;
    }

    /**
     * Default constructor which create 3 dim vec
     */
    public MultiDimItem(){
        this.vec = new Vector<E>(3);
    }

    /**
     * This method compares two MultiDimItem according to dimension
     * @param other MultiDimItem object to compare
     * @param dimension dimension to compare for objects
     * @return negative if item of dim of the vector is less than other object's
     *          positive if greater than,
     *          0(zero) if equal
     */
    public int compare(MultiDimItem other, int dimension){
        if(dimension<0 || dimension>=vec.size() || other==null)
            return -1;
        return (vec.get(dimension)).compareTo( (E)other.vec.get(dimension) );
    }


    /**
     * This method returns item in the given dim.
     * @param dim can be thought as index for vector
     * @return item in that dimension
     */
    public E get(int dim){
        return vec.get(dim);
    }

    /**
     * Returns dimension
     * @return number of dimension
     */
    public int size(){
        return vec.size();
    }

    /**
     * This method returns toString of vector that class hold
     * @return class vector included information
     */
    @Override
    public String toString() {
        return vec.toString();
    }

}
