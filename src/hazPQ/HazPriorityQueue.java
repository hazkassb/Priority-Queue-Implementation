package hazPQ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author HamzaKassomou
 *
 *	The HazPriorityQueue class implements the Queue interface by building a heap
 *	in an ArrayList. The heap is constructed so that the "smallest" item is at the top
 */

public class HazPriorityQueue<E> implements Queue<E> {
	
	//Data Fields
	/** The ArrayList to hold the data. */
	private List<E> theData;
	
	/** An optional reference to a Comparator object. */
	Comparator<E> comparator = null;
	
	//Methods
	//Constructor
	public HazPriorityQueue() {
		this.theData = new ArrayList<>();
	}
	
	
	
	//The offer method
	/** Insert an item into the priority queue. 
	 * 	pre: The ArrayList theData is in heap order.
	 * 	post: The item is in the priority queue and theData is in heap order.
	 * 	@param item The item to be inserted
	 * 	@throws NullPointerException if the item to be inserted is null.
	*/
	@Override
	public boolean offer(E item) {
		//Add the item to the heap.
		theData.add(item);
		
		//Set child as the newly inserted item
		int child = theData.size()-1;
		int parent = (child-1)/2; //child's parent in the heap
		
		//Reheap --> move child to its appropriate position inside the heap.
		while(parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0) {
			swap(parent, child);
			child = parent;
			parent = (child-1)/2;
		}
		
		return true;
	}
	
	
	
	//The poll method
	/**	Remove an item from the priority queue
	 * pre: The ArrayList theData is in heap order.
	 * post: Remove smallest item, theData is in heap order.
	 * @return The item with the smallest priority value or null if empty
	 */
	@Override
	public E poll() {
		if(this.theData.isEmpty()) {
			return null;		//return null if the heap is empty
		}
		
		//Save the top of the heap.
		E result = this.theData.get(0);
		
		
		//If only one item if present, then remove it.
		if(this.theData.size() == 1) {
			this.theData.remove(0);
			return result;
		}
		
		/* Remove the last item from the ArrayList and place it into the first position. */
		this.theData.set(0, this.theData.get(theData.size()-1));
		//The parent starts at the top.
		int parent = 0;
		while(true) {
			int leftChild = 2*parent + 1;
			if(leftChild >= theData.size()) {
				break;	//Out of heap.
			}
			
			int rightChild = leftChild+1;
			int minChild = leftChild;	//Assume leftChild is smaller;
			
			//Check whether rightChild is smaller.
			if(rightChild < theData.size() && compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
				minChild = rightChild;
			}
			
			//Assert: minChild is the index of the smaller child
			//Move smaller child up heap if necessary.
			if(compare(theData.get(parent), theData.get(minChild)) > 0) {
				swap(parent, minChild);
				parent = minChild;
			} else {
				break;	//Heap property is restored.
			}
		}
		
		return result;
	}

	
	/** Returns the number of elements in this queue. 
	 * @return The number of items in the priority queue
	 */
	@Override
	public int size() {
		
		return this.theData.size();
	}


	/** Returns true if this queue contains no elements.
	 *  @return true if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		
		return this.theData.size() == 0;
	}
	

	/**	Inserts the specified item into this priority queue
	 * 	@return true upon success
	 */
	@Override
	public boolean add(E item) {
		
		return this.offer(item);
	}

	
	/** Retrieves and removes an item from the priority queue. This method differs from 
	 * 	poll method only in that it throws an exception if this queue is empty.
	 *  @return The item with the smallest priority value
	 *  @throws NoSuchElementException if the queue is empty.
	 */
	@Override
	public E remove() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.poll();
	}


	/**	Retrieves, but does not remove, the smallest item in this queue.
	 *  @return the smallest item in the priority queue
	 *  @throws NoSuchElementException if the queue if empty
	 */
	@Override
	public E element() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.theData.get(0);
	}

	//The peek method
	/** Returns the smallest item in the heap without removing it.
	 * 	@return the smallest item in the heap, or null if the heap is empty
	 */
	@Override
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		
		return this.theData.get(0);
	}
	
	//The toString method
	/** Returns a string representation of the queue
	 * 
	 */
	
	public String toString() {
		return this.theData.toString();
	}
	
	
	//HELPER METHODS
	/**	Compare two items using either a Comparator object's compare method or their
	 * 	natural ordering using method compareTo.
	 * 	@pre: If comparator is null, left and rigth implement Comparable<E>.
	 * 	@param left One item
	 * 	@param right The other item
	 * 	@return A negative int if left < right, 0 if left equals right, a positive int if left > right.
	 * 	@throws ClassCastException if items are not Comparable
	 */
	@SuppressWarnings("unchecked")
	private int compare(E left, E right) {
		if(comparator != null) {	//A Comparator is defined.
			return comparator.compare(left, right);
		}
		
		//else, use let's compareTo method.
		return ((Comparable<E>) left).compareTo(right);
	}
	
	/**	Exchanges the object references in theData at indexes i and j
	 * 	@param i The index of the first item in theData
	 * 	@param j The index of the second item in theData
	 */
	private void swap(int i, int j) {
		E curr = theData.get(i);
		theData.set(i, theData.get(j));
		theData.set(j, curr);
	}
	
	
	
	//OPTIONAL METHODS
	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 *  THIS IS AN OPTIONAL METHOD INHERITED FROM COLLECTION INTERFACE
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
}
