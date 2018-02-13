package ds.students;

import java.net.SocketTimeoutException;
import java.util.Scanner;

import ds.students.Token.Type;

/**
 * @author taufp001 Fabian Tauriello
 *
 */
public class Calculator {
	
	//************************************************************************* MAIN METHOD***********************************************************************************
	
	public static void main(String[] args) {
		
		Calculator cal = new Calculator();
		DSQueue testQ = new DSQueue();
		testQ.offer(new Token(2));
		testQ.offer(new Token("+"));
		testQ.offer(new Token(1));
		System.out.println("Original Queue: " + testQ);

//		DSStack reversed = cal.reverseQueue(testQ);
//		System.out.println("original queue: " + testQ);
//		System.out.println("reversed queue (as a stack): " + reversed);

		DSQueue result = cal.infixToPrefix(testQ);
		System.out.println("Result of conversion: " + result);
		
		
	}
	
	//************************************************************************* MAIN METHOD***********************************************************************************

	public DSQueue infixToPrefix(DSQueue infix) {
		DSQueue outputQueue = new DSQueue();
		DSStack tokenStack = new DSStack();

		Node current = infix.theQueue.head;
		while(current.next != null) {
			current = current.next;
		}
		
		while(current != null) {
			if (current.getToken().type == Type.OPERAND) {
				outputQueue.offer(current.getToken());
			}
			else if (current.getToken().type == Type.PAREN) {
				// test for right parentheses
				if (current.getToken().equals(")")) 
					tokenStack.push(current.getToken());
				// test for left parentheses
				if (current.getToken().equals("(")) {
					while(!tokenStack.peek().equals("(")) {
						Token x = tokenStack.pop();
						outputQueue.offer(x);
					}	
				}
			}
			else if (current.getToken().type == Type.OPERATOR) {
				Token o1 = current.getToken();
				while (tokenStack.isEmpty() == false && tokenStack.peek().getPrecedence() >= o1.getPrecedence()) {
					Token poppedTop = tokenStack.pop();
					outputQueue.offer(poppedTop);
				}
				tokenStack.push(current.getToken());
				
			} 
			current = current.prev;
		}
		return outputQueue;
	}
	
	public double evaluatePrefix(DSQueue exp)
	{
//		DSStack stack = new DSStack();
//		Node current = exp.theQueue.head;
//		while(current != null) {
//			if (current.getToken().type == Type.OPERAND) 
//				stack.push(current.getToken());
//			if (current.getToken().type == Type.OPERATOR)
//				stack.
//
//	
		return 0;
	}
	
	public DSQueue infixToPostfix(DSQueue x) {
		return null;
	}
	
	
	/**
	 * Takes a DSQueue, reverses it and returns it as a DSStack 
	 * @param original queue passed in
	 * @return a reverse queue as a stack
	 */
	public DSStack reverseQueue(DSQueue original) {
		DSStack temp = new DSStack();
		while (!original.isEmpty()) 
			temp.push(original.poll());
		while (!temp.isEmpty()) 
			original.offer(temp.pop());
		return temp;
	}
}
