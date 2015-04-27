package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

/**
 *
 * Splay tree implementation of the Set interface.  The contains() and
 * remove() methods of AbstractSet are overridden to search the tree without
 * using the iterator.
 *
 */
public class SplayTreeSet<E extends Comparable<? super E>> extends AbstractSet<E>
{
	//for toString
	private StringBuilder sb;
	// The root of the tree containing this set's items	
	Node<E> root;

	// The total number of elements stored in this set
	int size;

	/**
	 * Default constructor.  Creates a new, empty, SplayTreeSet
	 */
	public SplayTreeSet() {
		this(null, 0);
	}

	/**
	 * Shallow copy constructor.  This method is fully implemented and should not be
	 * modified.
	 */
	public SplayTreeSet(Node<E> root, int size) {
		this.root = root;
		this.size = size;
	}

	/**
	 * Gets the root of this tree.  Used only for testing. This method is fully implemented
	 * and should not be modified.
	 * @return the root of this tree.
	 */
	public Node<E> getRoot() 
	{
		return root; 
	}

	/**
	 * Determines whether the set contains an element.  Splays at the node that stores the 
	 * element.  If the element is not found, splays at the last node on the search path.
	 * @param  obj  element to be determined whether to exist in the tree
	 * @return true if the element is contained in the tree and false otherwise
	 */
	@Override
	public boolean contains(Object obj)
	{	  
		Node<E> cur = root;
		while(cur != null){
			if (cur.getData().compareTo((E) obj)<0){		//if cur is bigger than object
				cur=cur.getRight();
			}
			else if(cur.getData().compareTo((E) obj)>0){	//if cur is less than object
				cur=cur.getLeft();
			}
			else
				return true;
		}
		return false;
	}

	/**
	 * Inserts an element into the splay tree. In case the element was not contained, this  
	 * creates a new node and splays the tree at the new node. If the element exists in the 
	 * tree already, it splays at the node containing the element. 
	 * @param  key  element to be inserted
	 * @return true if insertion is successful and false otherwise
	 */
	@Override
	public boolean add(E key)
	{
		// TODO
		return true; 
	}

	/**
	 * Removes the node that stores an element.  Splays at its parent node after removal
	 * (No splay if the removed node was the root.) If the node was not found, the last node 
	 * encountered on the search path is splayed to the root.
	 * @param obj  element to be removed from the tree
	 * @return true if the object is removed and false if it was not contained in the tree. 
	 */  
	@Override
	public boolean remove(Object obj)
	{
		// TODO
		return true; 
	}

	/**
	 * Returns the node containing key, or null if the key is not
	 * found in the tree.  Called by contains().
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node<E> findEntry(E key)
	{
		// TODO
		return null; 
	}

	/**
	 * Returns the successor of the given node.
	 * @param n
	 * @return the successor of the given node in this tree, 
	 *   or null if there is no successor
	 */
	protected Node<E> successor(Node<E> n)
	{
		return n.getParent(); 
	}

	/**
	 * Removes the given node, preserving the binary search
	 * tree property of the tree.
	 * @param n node to be removed
	 */
	protected void unlinkNode(Node<E> n)
	{
		// TODO
	}

	@Override
	public Iterator<E> iterator()
	{
		return new SplayTreeIterator();
	}

	@Override
	public int size()
	{
		return size; 
	}

	/**
	 * Returns a representation of this tree as a multi-line string as
	 * explained in the project description.
	 */
	@Override
	public String toString()
	{
		sb = new StringBuilder();
		toStringHelper(sb, root, 0);
		return sb.toString(); 
	}
	/**
	 * to string recursive helper method
	 */
	private void toStringHelper(StringBuilder sb, Node<E> n, int d){
		spaceHelper(d); //spaces

		if (n.getLeft() ==null && n.getRight()==null){ // if leaf
			sb.append(n.toString() + "\n");
			return;
		}
		sb.append(n.toString() + "\n");

		if(n.getLeft()!=null){
			toStringHelper(sb, n.getLeft(), d+1);
		}
		if(n.getLeft()==null){
			spaceHelper(d+1);
			sb.append("null\n");
		}
		if (n.getRight()!=null){
			toStringHelper(sb, n.getRight(), d+1);
		}
		if(n.getRight()==null){
			spaceHelper(d+1);
			sb.append("null\n");
		}
		return;
	}
	/**
	 * toString helperhelper. spaces man. s p a c e s 
	 */
	private void spaceHelper(int d){
		for(int i=0; i< (4 * d); i++){
			sb.append(" ");
		}
	}

	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag 
	 * operations until the current node is moved to the root of the tree.
	 * @param current  node at which to splay.
	 */
	protected void splay(Node<E> current)
	{
		while(current.getParent() != null) 							//while not root
		{
			Node<E> Parent = current.getParent();
			Node<E> GrandParent = Parent.getParent();

			if (GrandParent == null) 								//if parent is root
			{
				zig(current);										//zig

			}
			else													//if parent is not root
			{
				if(current==Parent.getLeft())						//if current is left child
				{
					if (Parent == GrandParent.getLeft())			//if parent is left child
					{
						zigZig(current);							//zigzig
					}
					else											//if parent is right child
					{
						zigZag(current);							//zigzag
					}
				}
				else												//if if current is right child
				{
					if(Parent==GrandParent.getLeft())				//if parent is left child
					{
						zigZag(current);							//zigZag
					}
					else											//if parent is right child
					{
						zigZig(current);							//zigZig
					}
				}
			}
		}
	}	

	/**
	 * Performs the zig operation on a node.
	 * @param current  node at which to perform the zig operation.
	 */

	protected void zig(Node<E> current)
	{
		Node<E> parent = current.getParent();
		if (parent.getParent()!=null) throw new RuntimeException(); //parent must be the root

		if (parent.getLeft()==current){ 							//if current is the left child
			parent.setLeft(current.getRight());
			current.setRight(parent);
			parent.setParent(current);
		}
		else if (parent.getRight()==current){ 						//if right child
			parent.setRight(current.getLeft());
			current.setLeft(parent);
			parent.setParent(current);
		}

		current.setParent(null);
		root=current;
	}

	/**
	 * Performs the zig-zig operation on a node.
	 * @param current  node at which to perform the zig-zig operation.
	 */
	protected void zigZig(Node<E> current)
	{
		Node<E> parent = current.getParent();
		Node<E> grandParent = parent.getParent();
		Node<E> temp;

		if (parent==root) throw new RuntimeException();						 //parent can't be the root

		if(grandParent!=root){ 												//set grandparent's children
			if (grandParent.getParent().getLeft()==grandParent){ 			//if grandParent is left child
				grandParent.getParent().setLeft(current);					//set great grandParen'ts left child to current
			}
			else{
				grandParent.getParent().setRight(current);  				//set great grandparen'ts right child to current
			}
		}

		if (parent.getLeft()==current && grandParent.getLeft()==parent){ 	//if left zigzig
			temp = parent.getRight();
			parent.setRight(grandParent);
			parent.setLeft(current.getRight());
			grandParent.setLeft(temp);
			current.setRight(parent);
			current.setParent(grandParent.getParent());


		}
		else if (parent.getRight()==current && grandParent.getRight()==parent){//if right zigzig
			temp = parent.getLeft();
			parent.setLeft(grandParent);
			parent.setRight(current.getLeft());
			grandParent.setRight(temp);
			current.setLeft(parent);
			current.setParent(grandParent.getParent());

		}

		if(current.getParent()==null) root=current;							 //if finished, set to root



	}

	/**
	 * Performs the zig-zag operation on a node.
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node<E> current)
	{
		// TODO
		Node<E> parent = current.getParent();
		Node<E> grandParent = parent.getParent();

		if(parent==root) throw new RuntimeException(); //parent can't be root

		if(grandParent!=root){ 										//set grandparent's children if not root
			if (grandParent.getParent().getLeft()==grandParent){ //if grandParent is left child
				grandParent.getParent().setLeft(current);		//set great grandParen'ts left child to current
			}
			else{
				grandParent.getParent().setRight(current);  	//set great grandparen'ts right child to current
			}
		}

		if(parent.getRight()==current){ 						// current is right child, and parent left child
			grandParent.setLeft(current.getRight());
			parent.setRight(current.getLeft());
			current.setRight(grandParent);
			current.setLeft(parent);
			current.setParent(grandParent.getParent());

		}
		else{ 													// current is left child and parent is right child
			grandParent.setRight(current.getLeft());
			parent.setLeft(current.getRight());
			current.setLeft(grandParent);
			current.setRight(parent);
			current.setParent(grandParent.getParent());
		}

		if(current.getParent()==null) root=current; 			//if finished, set to root


	}    

	/**
	 *
	 * Iterator implementation for this splay tree.  The elements are
	 * returned in ascending order according to their natural ordering.
	 *
	 */
	private class SplayTreeIterator implements Iterator<E>
	{
		Node<E> cursor;

		public SplayTreeIterator()
		{
			// TODO a go
		}

		@Override
		public boolean hasNext()
		{
			// TODO
			return true; 
		}

		@Override
		public E next()
		{
			// TODO
			return null; 
		}

		@Override
		public void remove()
		{
			// TODO
		}
	}
}
