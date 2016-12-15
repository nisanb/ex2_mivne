public class BinaryTree {
	public static  BNode root;
	public BinaryTree(){
		this.root = null;
	}
	
	public BNode find(double id){
		BNode current = root;
		while(current!=null){
			if(current.data==id){
				return current;
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return null;
	}
	public boolean delete(double id){
		BNode parent = root;
		BNode current = root;
		boolean isLeftChild = false;
		while(current.data!=id){
			parent = current;
			if(current.data>id){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			BNode successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public BNode getSuccessor(BNode deleleBNode){
		BNode successsor =null;
		BNode successsorParent =null;
		BNode current = deleleBNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleBNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleBNode.right;
		}
		return successsor;
	}
	public void insert(double id){
		BNode newBNode = new BNode(id);
		if(root==null){
			root = newBNode;
			return;
		}
		BNode current = root;
		BNode parent = null;
		while(true){
			parent = current;
			if(id<current.data){				
				current = current.left;
				if(current==null){
					parent.left = newBNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newBNode;
					return;
				}
			}
		}
	}
	public void display(BNode root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data);
			display(root.right);
		}
	}
	public static void main(String arg[]){
		BinaryTree b = new BinaryTree();
		b.insert(3);b.insert(8);
		b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
		b.insert(20);b.insert(25);b.insert(15);b.insert(16);
		System.out.println("Original Tree : ");
		b.display(b.root);		
		System.out.println("");
		System.out.println("Check whether BNode with value 4 exists : " + b.find(4));
		System.out.println("Delete BNode with no children (2) : " + b.delete(2));		
		b.display(root);
		System.out.println("\n Delete BNode with one child (4) : " + b.delete(4));		
		b.display(root);
		System.out.println("\n Delete BNode with Two children (10) : " + b.delete(10));		
		b.display(root);
	}
}

class BNode{
	double data;
	BNode left;
	BNode right;	
	public BNode(double data){
		this.data = data;
		left = null;
		right = null;
	}
}