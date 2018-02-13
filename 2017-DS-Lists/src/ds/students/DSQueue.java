package ds.students;


import ds.interfaces.Queue;

/**
 * @author taufp001 Fabian Tauriello
 *
 */
public class DSQueue extends Queue {
	
	//***************************************************************************************
	
	public static void main(String[] args) {
		DSQueue testQ = new DSQueue();
		
		System.out.println("Size of testQueue: " + testQ.theQueue.size());
		testQ.offer(new Token(100));
		testQ.offer(new Token(200));
		System.out.println("Size of testQueue after adding: " + testQ.theQueue.size());
		
		System.out.println("\ntestQueue: " + testQ);
	
		// TETING COPY CONSTRUCTOR
		DSQueue newQ = new DSQueue(testQ);
		System.out.println("New queue (should be the same as testQueue): " + newQ + " - size: " + newQ.size());
		
		System.out.println("\nAfter changing one queue...");
		testQ.list.add(new Token(1));

		System.out.println("testQ: " + testQ + " - size: " + testQ.size());
		System.out.println("New queue: " + newQ + " - size: " + newQ.size());
		
	
	
	}
	
	//***************************************************************************************
	
	// The list containing the data
	DSList theQueue;
	
	public DSQueue() {
		theQueue = new DSList();
	}
	
	public DSQueue(Queue s) {
		this();
		
		// Cast s to a DSQueue type
		DSQueue sCasted = (DSQueue)s;
		
		// Create a reference for the Queue to be copied
		Node queueToCopy = sCasted.theQueue.head;
		
		while(queueToCopy != null) {
			this.offer(queueToCopy.getToken());
			queueToCopy = queueToCopy.next;
		}
	}
	
	/**
	 * Inserts the given object into the Queue if possible. 
	 * @param t
	 * @return True if the object was inserted. 
	 * 
	 * @throws NullPointerException if the given object is null.
	 */
	@Override
	public boolean offer(Token t) {
		if (t == null) 
			throw new NullPointerException();
		// Test if new object is inserted
		if (theQueue.add(t))
			return true;
		return false;
	}

	/**
	 * Removes and returns the head of the Queue. 
	 * @return The head of the Queue. 
	 */
	@Override
	public Token poll() {
		if (size() == 0) 
			return null;
		Token x = theQueue.get(0);
		theQueue.remove(0);
		return x;
	}
	
	/**
	 * Retrieves, but does not remove, the head of this Queue. 
	 * If the Queue is empty, returns null. 
	 * @return The head of the Queue. 
	 */
	@Override
	public Token peek() {
		if (size() == 0)
			return null;
		return theQueue.get(0);
	}

	@Override
	public int size() {		
		return theQueue.size();
	}

	@Override
	public String toString() {
		// return empty string if stack is empty
		if (isEmpty())
			return "";
		Node temp = theQueue.head;
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
	public boolean isEmpty() {
		if (theQueue.size() == 0)
			return true;
		return false;
	}
}
