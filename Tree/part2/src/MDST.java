import java.util.Vector;
import javafx.util.Pair;

public class MDST<E extends  MultiDimItem > extends BinaryTree<E> implements SearchTree<E > {

	private int dim=0;

	@Override
	public boolean add(E item) {
		if(root==null) {
			root = new Node<E>(item);
			dim = item.size();
		}else {
			Node<E> node = root;
			int i=0, flag=0;

			while(node!=null ){

				int n = node.data.compare(item, i);
				i=(++i)%dim;

				if(n>0)
					if(node.left!=null)
						node = node.left;
					else {
						node.left = new Node<E>(item);
						break;
					}
				else//n<=0
					if(node.right!= null)
						node = node.right;
					else {
						node.right = new Node<E>(item);
						break;
					}
			}

		}
		return true;
	}

	@Override
	public boolean contains(E target) {
		Node<E> node = root;
		int i=0, flag=0;

		while(node!=null ){
			int n = node.data.compare(target, i);
			i=(++i)%dim;

			if(n==0)
				return true;
			else if(n>0)
				if(node.left!=null)
					node = node.left;
				else {
					return false;
				}
			else//n<=0
				if(node.right!= null)
					node = node.right;
				else {
					return false;
				}
		}
		return false;
	}

	@Override
	public E find(E target) {
		Node<E> node = root;
		int i=0;
		while(node!=null){
			i=i%dim;
			int n = node.data.compare(target, i++);
			if(n<0)
				node = node.left;
			else if(n>0)
				node = node.right;
			else
				return target;
		}
		return null;
	}


	@Override
	public E delete(E target) {
		if(!contains(target))
			return null;
		Node<E> node = root;
		int i=0, flag=0;

		while(node!=null ){
			int n = node.data.compare(target, i);
			//System.out.println(node + "////" +target+"///"+ i+"///"+n);

			i=(++i)%dim;

			if(n==0) {
				E item = node.data;
				node = null;
				return item;
			}else if(n>0)
				if(node.left!=null)
					node = node.left;
				else {
					return null;
				}
			else//n<=0
				if(node.right!= null)
					node = node.right;
				else {
					return null;
				}
		}
		return null;
	}

	@Override
	public boolean remove(E target) {
		if(delete(target)==null)
			return false;
		else
			return true;
	}
}
