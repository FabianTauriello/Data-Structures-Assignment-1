package ds.students;

import java.util.EmptyStackException;

import ds.interfaces.Stack;

/**
 * @author taufp001 Fabian Tauriello
 *
 */
public class DSStack extends Stack {
	
	//**************************************************************************** MAIN METHOD FOR TESTING ***************************************************************//
	public static void main(String[] args) {
		DSStack stackTest1 = new DSStack();
		DSStack stackTest2 = new DSStack();
		
		stackTest1.push(new Token(1));
		stackTest1.push(new Token(2));
		System.out.println("Stack 1: " + stackTest1);
		
		stackTest2.push(new Token(3));
		stackTest2.push(new Token(5));
		System.out.println("Stack 2: " + stackTest2);
		
		
		// TESTING EQUALS METHOD
		System.out.println("Is stack 1 the same as stack 2? " + stackTest1.equals(stackTest2));
		
		
		// TESTING COPY CONTRUCTOR
		DSStack newStack = new DSStack(stackTest2);
		System.out.println("\nStack 2: " + stackTest2 + " - size: " + stackTest2.size());
		System.out.println("New stack (should be the same as stack 1): " + newStack + " - size: " + newStack.size());
		
		System.out.println("\nAfter changing one.....");
		newStack.pop();
		newStack.pop();
		
		System.out.println("Stack 2: " + stackTest2 + " - size: " + stackTest2.size());
		System.out.println("New stack (should be the same as stack 1): " + newStack + " - size: " + newStack.size());
		
		
		
		
	}
	//**************************************************************************** MAIN METHOD FOR TESTING ***************************************************************//
	
	// The list containing the data
	private	DSList theStack;

	// Constuct an empty stack using a LinkedList as the container
	public DSStack() {
		theStack = new DSList();
	}

	public DSStack(DSStack other) {
		// Call no argument constructor to initialize the calling object
		this();
		
		// Create a reference for the stack to be copied
		Node oldListNode = other.theStack.head;
		
		while (oldListNode != null) {
			this.push(oldListNode.getToken());
			oldListNode = oldListNode.next;
		}
	}
	
	/**
	 * Adds the given object to the top of the Stack. 
	 * @return The given object. 
	 */
	public Token push(Token obj) {		
		theStack.add(obj);
		return obj;
	}

	/**
	 * Returns, without removing, the object at the top of the Stack. 
	 * @return the object at the top of the Stack. 
	 * 
	 * @throws EmptyStackException if the stack is empty. 
	 */
	public Token peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return theStack.get(theStack.size()-1);
	}

	/**
	 * Returns and removes the object at the top of the Stack. 
	 * @return the object at the top of the Stack. 
	 * 
	 * @throws EmptyStackException if the stack is empty.
	 */
	public Token pop() {
		if(isEmpty())
			throw new EmptyStackException();
		Token topToken = theStack.remove(theStack.size()-1);
		return topToken;
	}

	/**
	 * Returns true if this collection contains no elements. 
	 * @return True if the collection is empty. 
	 */
	public boolean isEmpty() {
		if (theStack.size() == 0)
				return true;
		return false;
	}

	/**
	 * Returns the number of elements in this collection. 
	 * @return The number of elements in this collection. 
	 */
	public int size() {
		return theStack.size();
	}
	
	@Override
	public String toString() {
		// return empty string if stack is empty
		if (isEmpty())
			return "";
		Node temp = theStack.head;
		StringBuilder sb = new StringBuilder();
		// append first token to string 
		sb.append(temp.getToken());
		// add next tokens if they exist.
		while (temp.next != null) {
			temp = temp.next;
			sb.append(" " + temp.getToken());
		}
		// return stack as string
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DSStack == false)
			return false;
		
		// Cast object to a stack (DSStack)
		DSStack objStack = (DSStack)obj;
		
		// test if both lists are empty - return true if they both are
		if (theStack.head == null && objStack.theStack.head == null)
			return true;
		
		// test if stacks are different sizes
		if (this.size() != objStack.size())
			return false;
		
		// assign reference pointers to the beginning of both stacks
		Node thisCurrent = theStack.head;
		Node otherCurrent = objStack.theStack.head;
		// set up result value
		boolean result = true;
		// loop through both stacks comparing tokens as it goes
		while (thisCurrent != null) {
			// test current tokens in each stack for equality
			if (!thisCurrent.getToken().equals(otherCurrent.getToken()))
				return false;
			// Advance through both stacks
			thisCurrent = thisCurrent.next;
			otherCurrent = otherCurrent.next;
		}
		return result;
	}
}
