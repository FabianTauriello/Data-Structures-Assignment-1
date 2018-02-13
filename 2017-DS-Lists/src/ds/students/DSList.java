package ds.students;

import ds.interfaces.List;

/**
 * @author taufp001 Fabian Tauriello
 *
 */
public class DSList implements List {

	// ********************************* MAIN METHOD FOR TESTING ************************************

	public static void main(String[] args) {
		// create test objects
		DSList testList1 = new DSList();
		Token testToken1 = new Token(100);
		Token testToken2 = new Token(200);
		Token testToken3 = new Token(300);
		Token testToken4 = new Token(400);
		Token testToken5 = new Token(500);

		System.out.println("size (before)                                 " + testList1.size());
		testList1.add(testToken1);
		testList1.add(testToken2);
		testList1.add(testToken3);
		testList1.add(testToken4);
		testList1.add(testToken5);
		System.out.println("size (after)                                  " + testList1.size());


		// TESTING EQUALS METHOD
		DSList testList2 = new DSList();
		Token testToken10 = new Token(100);
		Token testToken11 = new Token(200);
		Token testToken12 = new Token(300);
		Token testToken13 = new Token(400);
		Token testToken14 = new Token(500);

		testList2.add(testToken10);
		testList2.add(testToken11);
		testList2.add(testToken12);
		testList2.add(testToken13);
		testList2.add(testToken14);

		System.out.println("Test List 1: " + testList1 + "\n" + "Test List 2: " + testList2);
		System.out.println("Is Test List 1 equal to Test List 2? " + testList1.equals(testList2));

		 //TESTING COPY CONSTRUCTOR
				DSList testList3 = new DSList(testList1);
				System.out.println("\nAfter copying...");
				System.out.println("original list:                                " + testList1 + " - size: " + testList1.size());
				System.out.println("new list which should be a copy of above:     " + testList3 + " - size: " + testList3.size());
				
				testList1.remove(0);
				testList1.add(new Token(999));
				testList1.add(0, new Token(5454));
				
				System.out.println("\nAfter making changesto to one list (should not affect other list)...");
				System.out.println("original list:                                " + testList1 + " - size: " + testList1.size());
				System.out.println("new list:				      " + testList3 + " - size: " + testList3.size());
				
			
				
		//		
		//		System.out.println("memory address of original list:              " + testList1);
		//		System.out.println("memory address of new list:                   " + testList2);
		//		
		//		System.out.println("----------------");
		//		testList1.remove(0);
		//		System.out.println("removed first position from list1....");
		//		System.out.println("----------------");
		//		
		//		System.out.println("original list:                                " + testList1);
		//		System.out.println("new list which should be a copy of above:     " + testList2);

		//		// TESTING ADD(INT, TOKEN) METHOD
		//		System.out.println("Before adding to list: " + testList1.toString());
		//		Token testToken7 = new Token(700);
		//		Token testToken8 = new Token(800);
		//		testList1.add(5, testToken7); //////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//		System.out.println("Before adding to list: " + testList1.toString());

		// TESTING GET(INT) METHOD
		//		System.out.println("should be 100: " + testList1.get(0));
		//		System.out.println("should be 300: " + testList1.get(2));
		//		System.out.println("should be 500: " + testList1.get(4));
		//		System.out.println("should be 500: " + testList1.get(-1));

		// TESTING CONTAINS METHOD
		//		System.out.println(testList1.contains(testToken2));

		// TESTING REMOVE(TOKEN) METHOD
		//		System.out.println(testList1.indexOf(testToken5));


	}


	// **********************************************************************************************

	public Node head;

	public DSList() {
		head = null;
	}

	public DSList(Node head_) {
		head = head_;
	}

	// Copy constructor.
	public DSList(DSList other) { 		

		// Create a reference for the list to be copied
		Node oldListNode = other.head;  
		
		while (oldListNode != null) {
			this.add(oldListNode.getToken());
			oldListNode = oldListNode.next;
		}		
	}

	/**
	 * Remove the object at the specified index from the list, if it exists. 
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *	
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	public Token remove(int index) {

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		// test if caller wants to remove the head
		if (index == 0) {
			// get object from head
			Token removedToken = head.getToken();
			head = head.next;
			//head.prev = null;
			return removedToken;
		}

		// create a reference of the start position in the list
		Node pointer = head;
		// set up index counter to compare with index given
		int indexCounter = 0;
		// set up variable to hold token which will be removed
		Token removedToken = null;
		// While there are nodes remaining in the list, advance through to reach the end.
		while(pointer != null) {
			if (index == indexCounter) {
				// test if current index is last position
				if (pointer.next == null) {
					removedToken = pointer.getToken();
					pointer.prev.next = null;
					return removedToken;
				}
				if (pointer.next != null) {
					// get object from current index position
					removedToken = pointer.getToken();
					pointer.next.prev = pointer.prev;
					pointer.prev.next = pointer.next;
					return removedToken;
				}
			}
			indexCounter++;
			pointer = pointer.next;
		}
		return removedToken;
	}

	/**
	 * Returns the first index of the specified object, or -1 if the object does not exist
	 * in the list. 
	 * @param token
	 * @return The index of the specified token, or -1 if it is not contained in the list. 
	 */
	public int indexOf(Token obj) {
		// Create another reference to head
		Node current = head;
		// test if current token in head matches the token obj given
		if (current.getToken().equals(obj)) {
			return 0;
		}
		// loop through list
		for(int i = 0; i < size(); i++) {
			// test if current token in current node matches the token obj given
			if (current.getToken().equals(obj)) {
				// return index position
				return i;
			}
			// advance through the list
			current = current.next;
		}
		// return this if the specified object wasn't found
		return -1;
	}

	/**
	 * Get the object at the specified index, if it exists. 
	 * @param index The index to retrieve 
	 * @return The object at the specified index, if it exists. ////////////////////////////////////////////////////////////////////////////// RETURNING NULL WORKS INSTEAD?!
	 * 
	 * @throws IndexOutOfBoundsException if the specified index is out of bounds
	 */
	public Token get(int index) {
		if (index < 0 || index >= size())
			return null;
		//throw new IndexOutOfBoundsException();

		if (index == 0)
			return head.getToken();

		Token returnedToken = null;
		Node current = head;
		int indexCounter = 0;
		while(current != null) {
			if (index == indexCounter)
				returnedToken = current.getToken();
			current = current.next;
			indexCounter++;
		}
		return returnedToken;
	}


	public void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.getToken());
			current = current.next;
		}
	}

	/**
	 * Returns true if this list contains no elements. 
	 * @return True if the list is empty. 
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of elements in this list. 
	 * @return The number of elements in this list. 
	 */
	public int size() {
		if (head == null) {
			return 0;
		} else {
			int count = 0;
			Node nodeRef = head;
			while (nodeRef != null) {
				// advance down the list
				nodeRef = nodeRef.next; 
				count++;
			}
			return count;	
		}		
	}

	/**
	 * Returns a string containing the toString() 
	 * 	for each object in this list. 
	 * @return The concatenated toString() for each element in this list
	 */
	@Override
	public String toString() {
		// If list is empty return an empty string.
		if (isEmpty())
			return "";
		Node current = head;
		StringBuilder sb = new StringBuilder();
		sb.append(current.getToken().toString());
		while (current.next != null) {
			current = current.next;
			sb.append(" " + current.getToken().toString());

		}
		return sb.toString();
	}

	/**
	 * Appends the specified element to the end of this list.
	 * @param obj The object to add. 
	 * @return True if the object has been added to the list. 
	 * 
	 * @throws NullPointerException if the specified object is null.
	 */
	public boolean add(Token obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		// If list is empty, add new node to front.
		if(isEmpty()) {  
			// Create a new Node. Add Token obj as data
			Node newNode = new Node(null, null, obj);  
			// point head to new Node, newNode
			head = newNode;
			return true;
		} else { 
			// create a reference of the start position in the list
			Node current = head;
			// While there are nodes remaining in the list, advance through to reach the end.
			while (current.next != null) 
				current = current.next;
			// Create a new node and append it to the end of the list, with 'prev' pointing to current (2nd to last node)
			Node newNode = new Node(null, current, obj);
			// Point old last element to the newest, last node in the list (in next variable)
			current.next = newNode;
			// Return true if successful addition of node.
			return true;
		}
	}

	/**
	 * Inserts the specified element at the specified position in this list. 
	 * Shifts the element currently at that position (if any) and any subsequent 
	 * elements to the right (adds one to their indices).
	 * @param index Index at which to add
	 * @param obj The object to add
	 * @return True if insertion was successful
	 * 
	 * @throws NullPointerException if the given object is null
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public boolean add(int index, Token obj) { 

		if (obj == null) 
			throw new NullPointerException();
		if (index < 0 || index > size())  
			throw new IndexOutOfBoundsException();

		Node temp = head;
		Node newNode = new Node(null, null, obj);
		int indexCounter = 0;

		// insert new element into tail position
		if (index == size()) {
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			newNode.prev = temp;
			return true;
		}

		// insert new element into head position
		if (indexCounter == index) {				
			newNode.next = head; 
			head.prev = newNode;
			head = newNode; 	
			return true;
		} else {
			// iterate through array to find correct index position for insertion
			while(temp != null) {					
				if (index == indexCounter) {
					newNode.next = temp;
					newNode.prev = temp.prev;
					temp.prev.next = newNode;
					temp.prev = newNode;
					return true;
				}	
				temp = temp.next;
				indexCounter++;
			}
		}
		return false;
	}

	public void addAfter(Node newNode, Token newToken) {
		Node temp = new Node (newNode.next, null, newToken);
		newNode.next = temp;
	}

	/**
	 * Returns true if the given object is contained in the list. 
	 * @param obj The object whose presence is to be tested
	 * @return True if the list contains the given object
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean contains(Token obj) {
		// if the object is null, throw an exception
		if (obj == null)
			throw new NullPointerException();
		// create a reference of the start position in the list
		Node current = head;
		// While there are nodes remaining in the list, advance through to reach the end.
		while (current != null) {
			if (current.getToken().equals(obj)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Remove the first instance of the given object from the list, if it exists
	 * @param obj The object to remove
	 * @return True if the object was removed 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean remove(Token obj) {				
		// If the object doesn't exist, throw an exception
		if (obj == null)
			throw new NullPointerException();
		// test if list is empty
		if(isEmpty())
			return false;

		// create a reference of the start position in the list
		Node current = head;
		// While there are nodes remaining in the list, advance through to reach the end.
		while (current != null) {
			// if the current node contains the same object as the one given, remove it by rearranging links/pointers
			if (current.getToken().equals(obj)) {
				// test if object is at first position. remove from there if true
				if(current==head) {
					current.next.prev = null;
					head = current.next;
					return true;
				}
				// test if object is at last position. remove from there if true
				if (current.next == null) {
					current.prev.next = null;
					current.prev = null;
					return true;
				}
				current.prev.next = current.next;
				current.next.prev = current.prev;
				// return true on successful removal
				return true;
			}
			current = current.next;
		}
		// return false if object wasn't found in list
		return false;
	}

	/**
	 * Returns the hashCode for this list. 
	 * (This method must satisfy the constraint that if List l1.equals(List l2), 
	 * 	then l1.hashCode() == l2.hashCode() must also be true. 
	 * @return The hashCode of this list. 
	 */
	@Override
	public int hashCode() {
		return (int) System.nanoTime();
	}

	/**
	 * Compares this list with the specified object for equality. 
	 * The equality comparison must be value-based rather than the default 
	 * (reference based). 
	 * 
	 * @param obj The object to compare against. 
	 * @return True if the specified object is value-comparatively equal to this list
	 */
	@Override
	public boolean equals(Object other) {			

		if (other instanceof DSList == false) {
			return false;
		}
		
		// Cast object to a linked list (DSList)
		DSList otherList = (DSList)other;
		
		// test if lists are empty
		if (this.head == null && otherList.head == null)
			return true;
		
		// test if lists are different sizes
		if (this.size() != otherList.size())
			return false;
		
		// assign reference pointers to the beginning of both lists
		Node thisCurrent = head;
		Node otherCurrent = otherList.head;
		// set up result value
		boolean result = true;
		// loop through both lists comparing tokens as it goes
		while (thisCurrent != null) {
			// test current tokens in each list for equality
			if (!thisCurrent.getToken().equals(otherCurrent.getToken()))
				result = false;
			// Advance through both lists
			thisCurrent = thisCurrent.next;
			otherCurrent = otherCurrent.next;
		}
		return result;
	}
}