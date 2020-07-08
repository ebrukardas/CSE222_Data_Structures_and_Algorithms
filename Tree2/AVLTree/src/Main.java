

class Main{
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> obj = new BinarySearchTree<>();

		obj.add(3);
		obj.add(2);
		obj.add(1);

		obj.add(5);

		AVLTree<Integer> obj2 =new AVLTree<>(obj);

		System.out.println( obj + "\n" );
		System.out.println( obj2 + "\n" );

	}

}
