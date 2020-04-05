import java.util.*; 
import java.io.*; 

class BSTNode<T> //Node class 
{
	public T data;
	public BSTNode<T> left;
	public BSTNode<T> right; 

	public BSTNode(Object data) //Constructor with parameter of type Object 
	{
		this.data = (T)data; 
		this.left = null;
		this.right = null; 
	}
}

public class BST<T extends Comparable<T>> 
{
	private BSTNode<T> root; //Private variable root of type BSTNode<T> 

	public BST() //Constructor - sets root to null 
	{
		root = null;  
	}

	//Find Functions
	//Public Find function
	public boolean find(Comparable item) //Return true if item is found in the BST
	{
		return find(root,item); //Call to private find function
	}

	//Private Find function
	private boolean find(BSTNode<T> root, Comparable item) 
	{
		if(root == null) //If tree is empty, false is returned
		{
			return false;
		}

		else if(item.compareTo(root.data) < 0) //Go left if item were looking for is less than data
		{
			return find(root.left,item); 
		}

		else if(item.compareTo(root.data) > 0) //Go right if item were looking for is more than data
		{
			return find(root.right,item);
		}

		else
		{
			return true; 
		}
	}

	//Insert Functions
	//Public Insert Function
	public void insert(Comparable item) //Insert item into BST, keeps duplicates in their own nodes
	{
		root = insert(root,item); //Call to private insert function
	}

	//Private Insert Function
	private BSTNode<T> insert(BSTNode<T> root, Comparable item)
	{
		if (root == null) //If tree is empty, create new Node and add item to the node
		{
			return new BSTNode<T>(item); 
		}

		else if (item.compareTo(root.data) < 0) //Go left if item to add is lower than data 
		{
			root.left = insert(root.left, item);
		}

		else if (item.compareTo(root.data) > 0) //Go right is item to add is more than data
		{
			root.right = insert(root.right, item);
		}

		return root; 
	}


	//Print Functions
	//Public Print Function
	public void print() //Using println, output each item in the BST, in order
	{
		print(root); //Call to private print function
		System.out.println(); 
	}

	//Private Print Function
	private void print(BSTNode<T> root)
	{
		if(root != null) //If the tree is not empty then print
		{
			print(root.left); //Print the lower side 
			System.out.print(" " + root.data); 
			print(root.right); //Print the higher side
		}

	}

	//Delete Functions
	//Public Delete Function
	public void delete(Comparable item) //Delete first instance of item from the BST
	{
		root = delete(root,item); //Calls the private delete function
	}

	//Private Delete Function
	private BSTNode<T> delete(BSTNode<T> root, Comparable item)
	{

		if(root == null) // If tree is empty , return null 
		{
			return null;
		}

		else if (item.compareTo(root.data) < 0) //Go left if item to add is lower than data 
		{
			root.left = delete(root.left, item);
		}

		else if(item.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, item); //Go right if item to add is higher than data 
		}

		else
		{
			// If node has 1 child or 0 children, promote child to parent position
			if(root.left == null) 
			{
				return root.right;
			}

			else if (root.right == null)
			{
				return root.left;
			}


			else //There are 2 children,decide who is the in order successor 
			{
				if(root.right.left == null)
				{
					root.data = root.right.data; 
					root.right = root.right.right; 
				}

				else
				{
					 root.data = (T) removeSmallest(root.right); 
				}

			}
	
		}

		return root; 
	}

	private BSTNode<T> removeSmallest(BSTNode<T> root)
	{
		if(root.left != null) //Descend into the tree until a leaf is found
		{
			return removeSmallest(root.left);
		}

		return (BSTNode<T>) root.data;
	}


}