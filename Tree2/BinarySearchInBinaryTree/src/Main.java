
public class Main {

	public static void main( String[] args ){

		BTree<Integer> obj = new BTree<Integer>();
		BTree<Integer> obj2 = new BTree<Integer>();

		int value = 3, value2 = 1024;

		for(int i=0; i<6; value+=7, ++i , value2/=2){
			System.out.println("\n" + obj + "\n");
			if (obj.binarySearch(value)) 
				System.out.println("Value in the tree. Try again");
			else
				obj.insert(value);
			System.out.println("\n" + obj2 + "\n");
			if (obj2.binarySearch(value)) 
				System.out.println("Value in the tree. Try again");
			else
				obj2.insert(value);
		}

		System.out.println("\n"+obj+"\n");
		System.out.println("\n"+obj2+"\n");

	}

}
