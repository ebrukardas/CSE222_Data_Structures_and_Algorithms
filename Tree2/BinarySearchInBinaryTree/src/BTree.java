public class BTree<E extends Comparable<E> > {


	private Node root;


	/**
	 * WANTED BINARY SEARCH METHOD
	 */
	public boolean binarySearch(E target) {
		return binarySearch(target, root);
	}

	private boolean binarySearch(E target, Node node) {

		boolean returned = false;

		if (node == null) {
			returned = false;
		}else{ 
			int compare = target.compareTo(node.data);
			System.out.println(node.data);

			if (compare == 0) {
				returned = true;
			} else if (compare < 0) {
				returned = binarySearch(target, node.left);
			} else {
				returned = binarySearch(target, node.right);
			}
		}

		return returned;
	}

	/**
	 * Creates an empty balanced tree.
	 */
	public BTree() {
		root = null;
	}

	/**
	 * Creates a balances tree using the given node as tree root.
	 */
	public BTree(Node root) {
		this.root = root;
	}

	/**
	 * Inserts an element into the tree.
	 */
	public void insert(E info) {
		insert(info, root, null, false);
	}

	private void insert(E info, Node node, Node parent, boolean right) {

		if (node == null) {
			if (parent == null) {
				root = node = new Node(info, parent);
			} else if (right) {
				parent.right = node = new Node(info, parent);
			} else {
				parent.left = node = new Node(info, parent);
			}
			restructInsert(node, false);
		} else if (info.compareTo(node.data) == 0) {
			node.data = info;
		} else if (info.compareTo(node.data) > 0) {
			insert(info, node.right, node, true);
		} else {
			insert(info, node.left, node, false);
		}
	}



	/**
	 * Returns a text representation of the tree.
	 */
	public String toString() {
		return inOrder();
	}

	/**
	 * Returns all elements of the tree in in-order traversing.
	 */
	public String inOrder() {
		return inOrder(root);
	}

	private String inOrder(Node node) {

		String result = "";
		if (node != null) {
			result = result + inOrder(node.left) + " ";
			result = result + node.data.toString();
			result = result + inOrder(node.right);
		}
		return result;
	}


	/**
	 * Returns the height of the tree.
	 */
	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		int height = 0;

		if (node == null) {
			height = -1;
		} else {
			height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		}
		return height;
	}



/*

	/**
	 * Removes an elememt from the tree.
	 *
	public void delete(E info) {
		delete(info, root);
	}


	private void delete(E info, Node node) throws NoSuchElementException {

		if (node == null) {
			throw new NoSuchElementException();
		} else if (info.compareTo(node.data) == 0) {
			deleteNode(node);
		} else if (info.compareTo(node.data) > 0) {
			delete(info, node.right);
		} else {
			delete(info, node.left);
		}
	}

	private void deleteNode(Node node) {

		Node eNode, minMaxNode, delNode = null;
		boolean rightNode = false;

		if (node.isLeaf()) {
			if (node.parent == null) {
				root = null;
			} else if (node.isRightNode()) {
				node.parent.right = null;
				rightNode = true;
			} else if (node.isLeftNode()) {
				node.parent.left = null;
			}
			delNode = node;
		} else if (node.hasLeftNode()) {
			minMaxNode = node.left;
			for (eNode = node.left; eNode != null; eNode = eNode.right) {
				minMaxNode = eNode;
			}
			delNode = minMaxNode;
			node.data = minMaxNode.data;

			if (node.left.right != null) {
				minMaxNode.parent.right = minMaxNode.left;
				rightNode = true;
			} else {
				minMaxNode.parent.left = minMaxNode.left;
			}

			if (minMaxNode.left != null) {
				minMaxNode.left.parent = minMaxNode.parent;
			}
		} else if (node.hasRightNode()) {
			minMaxNode = node.right;
			delNode = minMaxNode;
			rightNode = true;

			node.data = minMaxNode.data;

			node.right = minMaxNode.right;
			if (node.right != null) {
				node.right.parent = node;
			}
			node.left = minMaxNode.left;
			if (node.left != null) {
				node.left.parent = node;
			}
		}
		restructDelete(delNode.parent, rightNode);
	}
*/



	private void restructInsert(Node node, boolean wasRight) {

		if (node != root) {
			if (node.parent.balance == '_') {
				if (node.isLeftNode()) {
					node.parent.balance = '/';
					restructInsert(node.parent, false);
				} else {
					node.parent.balance = '\\';
					restructInsert(node.parent, true);
				}
			} else if (node.parent.balance == '/') {
				if (node.isRightNode()) {
					node.parent.balance = '_';
				} else {
					if (!wasRight) {
						rotateRight(node.parent);
					} else {
						doubleRotateRight(node.parent);
					}
				}
			} else if (node.parent.balance == '\\') {
				if (node.isLeftNode()) {
					node.parent.balance = '_';
				} else {
					if (wasRight) {
						rotateLeft(node.parent);
					} else {
						doubleRotateLeft(node.parent);
					}
				}
			}
		}
	}
/*
	private void restructDelete(Node z, boolean wasRight) {

		Node parent;
		boolean isRight = false;
		boolean climb = false;
		boolean canClimb;

		if (z == null) {
			return;
		}

		parent = z.parent;
		canClimb = (parent != null);

		if (canClimb) {
			isRight = z.isRightNode();
		}

		if (z.balance == '_') {
			if (wasRight) {
				z.balance = '/';
			} else {
				z.balance = '\\';
			}
		} else if (z.balance == '/') {
			if (wasRight) {
				if (z.left.balance == '\\') {
					doubleRotateRight(z);
					climb = true;
				} else {
					rotateRight(z);
					if (z.balance == '_') {
						climb = true;
					}
				}
			} else {
				z.balance = '_';
				climb = true;
			}
		} else {
			if (wasRight) {
				z.balance = '_';
				climb = true;
			} else {
				if (z.right.balance == '/') {
					doubleRotateLeft(z);
					climb = true;
				} else {
					rotateLeft(z);
					if (z.balance == '_') {
						climb = true;
					}
				}
			}
		}

		if (canClimb && climb) {
			restructDelete(parent, isRight);
		}
	}
*/
	private void rotateLeft(Node a) {

		Node b = a.right;

		if (a.parent == null) {
			root = b;
		} else {
			if (a.isLeftNode()) {
				a.parent.left = b;
			} else {
				a.parent.right = b;
			}
		}

		a.right = b.left;
		if (a.right != null) {
			a.right.parent = a;
		}

		b.parent = a.parent;
		a.parent = b;
		b.left = a;

		if (b.balance == '_') {
			a.balance = '\\';
			b.balance = '/';
		} else {
			a.balance = '_';
			b.balance = '_';
		}
	}

	private void rotateRight(Node a) {

		Node b = a.left;

		if (a.parent == null) {
			root = b;
		} else {
			if (a.isLeftNode()) {
				a.parent.left = b;
			} else {
				a.parent.right = b;
			}
		}

		a.left = b.right;
		if (a.left != null) {
			a.left.parent = a;
		}

		b.parent = a.parent;
		a.parent = b;
		b.right = a;

		if (b.balance == '_') {
			a.balance = '/';
			b.balance = '\\';
		} else {
			a.balance = '_';
			b.balance = '_';
		}
	}

	private void doubleRotateLeft(Node a) {

		Node b = a.right;
		Node c = b.left;

		if (a.parent == null) {
			root = c;
		} else {
			if (a.isLeftNode()) {
				a.parent.left = c;
			} else {
				a.parent.right = c;
			}
		}

		c.parent = a.parent;

		a.right = c.left;
		if (a.right != null) {
			a.right.parent = a;
		}
		b.left = c.right;
		if (b.left != null) {
			b.left.parent = b;
		}

		c.left = a;
		c.right = b;

		a.parent = c;
		b.parent = c;

		if (c.balance == '/') {
			a.balance = '_';
			b.balance = '\\';
		} else if (c.balance == '\\') {
			a.balance = '/';
			b.balance = '_';
		} else {
			a.balance = '_';
			b.balance = '_';
		}

		c.balance = '_';
	}

	private void doubleRotateRight(Node a) {

		Node b = a.left;
		Node c = b.right;

		if (a.parent == null) {
			root = c;
		} else {
			if (a.isLeftNode()) {
				a.parent.left = c;
			} else {
				a.parent.right = c;
			}
		}

		c.parent = a.parent;

		a.left = c.right;
		if (a.left != null) {
			a.left.parent = a;
		}
		b.right = c.left;
		if (b.right != null) {
			b.right.parent = b;
		}

		c.right = a;
		c.left = b;

		a.parent = c;
		b.parent = c;

		if (c.balance == '/') {
			b.balance = '_';
			a.balance = '\\';
		} else if (c.balance == '\\') {
			b.balance = '/';
			a.balance = '_';
		} else {
			b.balance = '_';
			a.balance = '_';
		}
		c.balance = '_';
	}

	class Node {

		E data;

		Node parent;

		Node left;

		Node right;

		char balance;

		public Node(E data, Node parent) {
			this.data = data;
			this.parent = parent;
			this.left = null;
			this.right = null;
			this.balance = '_';
		}

		boolean isLeaf() {
			return ((left == null) && (right == null));
		}

		boolean isNode() {
			return !isLeaf();
		}

		boolean hasLeftNode() {
			return (null != left);
		}

		boolean hasRightNode() {
			return (right != null);
		}

		boolean isLeftNode() {
			return (parent.left == this);
		}

		boolean isRightNode() {
			return (parent.right == this);
		}
	}
}

