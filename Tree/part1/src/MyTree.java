import java.util.LinkedList;

public class MyTree<E> extends BinaryTree < E >{

    private int size=0;

    /**
     * This method adds child item to as last child of the parent item.
     * @param parentItem parent of the child item to add
     * @param childItem child item to add
     * @return false if parent doesnt exist or child already exist, true if child is added.
     */
    public boolean add( E parentItem, E childItem ){
        if(root==null) {
            root = new Node<E>(childItem);
            return true;
        }
        if( postOrder(childItem)!=null )
            return false;
        Node<E> temp = levelOrder(parentItem);
        if(temp==null)
            return false;
        else{
            if(temp.left == null)
                temp.left = new Node<E>(childItem);
            else {
                temp = temp.left;
                while ( temp.right != null)
                    temp = temp.right;
                temp.right = new Node<E>(childItem);
            }
            ++size;
            return true;
        }
    }

    /**return size;
     * This method returns size of the list.
     * @return size of the list
     */
    public int getSize() {
        return size;
    }



    /** Starter method find.
     pre: The target object must implement
     the Comparable interface.
     @param target The Comparable object being sought
     @return The object, if found, otherwise null
     */
    public Node<E> postOrder(E target){
        if(root==null)
            return null;
        LinkedList<Node<E>> vec = new LinkedList<Node<E>>();
        Node<E> temp=root;
        vec.add(temp);
        for( temp = temp.left; temp != null;  temp = temp.left)
            vec.add(temp);
        while( vec.size()>0){
            temp = vec.remove(vec.size()-1);
//System.out.println(temp);
            if (temp.data.equals(target))
                return temp;
            for( temp=temp.right; temp != null;  temp = temp.left)
                vec.add(temp);
        }
        return null;
    }


    /**
     * This method searches by traverse tree level order
     * @param target item to search
     * @return found item, if not found returns null
     */
    public Node<E> levelOrder(E target){
        if(root==null)
            return null;
        LinkedList<Node<E>> vec = new LinkedList<Node<E>>();
        Node<E> temp=root;
        vec.add(temp);
        for( temp = temp.right; temp != null;  temp = temp.right)
            vec.add(temp);
        while( vec.size()>0){
            temp = vec.remove(0);
//System.out.println(temp);
            if (temp.data.equals(target))
                return temp;
            for( temp=temp.left; temp != null;  temp = temp.right)
                vec.add(temp);
        }
        return null;
    }

    /**
     * Overridden toString, traverse preorder
     * @return elements as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    /**
     * Helper method for toString
     @param node The local root
     @param depth The depth
     @param sb The StringBuilder to save the output
     */
    private void toString(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("\t");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth, sb);
        }
    }

}
