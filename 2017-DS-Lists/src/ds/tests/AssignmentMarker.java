package ds.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AssignmentMarker {

	private static java.util.ArrayList<Failure> failures;


	private static void testrunner(String name, Class c) {

		Result test = JUnitCore.runClasses(c);
		failures.addAll(test.getFailures());

		for ( int i = 0 ; i < test.getFailures().size() ; ++i ) {
			String testID = test.getFailures().get(i).getDescription().getClassName() + ":" + 
					test.getFailures().get(i).getDescription().getMethodName();
			testID = testID.replaceAll("Test", ""). replaceAll("test", ""); // Strip the word "test" from the identifying string. 
		}
	}

	// Simple test information
	private static void runATest(String name, Class c) {
		System.out.println("\n" + name);
		for ( int i = 0 ; i < name.length() ; ++i ) 
			System.out.print("-");
		System.out.println();

		testrunner(name, c);
	}

	public static void main(String[] args) {

		boolean runList = true;
		boolean runStack = true;
		boolean runQueue = true;
		boolean runEval = true;
		boolean runInfixToPrefix = true;

		failures = new java.util.ArrayList<Failure>();

		float listPass = 0, listLost = 0, queuePass = 0, queueLost = 0, stackPass = 0 , stackLost =0, ifPass = 0, ifLost=0,
					evalPass = 0, evalLost =0;

		System.out.println("Data Structures Assignment #1:\n\tPolish Notation Calculator.\n");

		System.out.println("-----------------------------");

		if ( runList ) {
			runATest("Testing the List class...", ListTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("List:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("List::") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				listPass = gained;
				listLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}

		if ( runQueue ) {
			runATest("Testing the Queue class...", QueueTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Queue:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Queue:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				queuePass = gained;
				queueLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		} 


		if ( runStack ) {
			runATest("Testing the Stack class...", StackTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Stack:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Stack:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				stackPass = gained;
				stackLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}

		if ( runInfixToPrefix ) {

			runATest("Testing the infix-to-prefix conversion...", InfixToPostfixTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("infixToPrefix") )
						gained += Marks.getInstance().marks.get("ds.tests.InfixToPostfix:infixToPrefix");
				}

				float lost = 0.0f;

				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("infixToPrefix") )
						lost += Marks.getInstance().marks.get("ds.tests.InfixToPostfix:infixToPrefix");
				}
				ifPass = gained;
				ifLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}

		if ( runEval ) {
			runATest("Testing the evaluation of prefix expressions..", EvaluationTests.class);

			System.out.print("Summary: ");
			{

				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("ds.tests.Evaluations:postfixEvaluation") )
						gained += Marks.getInstance().marks.get("ds.tests.Evaluations:postfixEvaluation");
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("ds.tests.Evaluations:postfixEvaluation") )
						lost += Marks.getInstance().marks.get("ds.tests.Evaluations:postfixEvaluation");
				}
				evalPass = gained;
				evalLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}


			System.out.println("-----------------------------");
			System.out.println("\nFailed test details");
			System.out.println("( Test class: test name -> Error details)\n");
			for (Failure failure : failures) {
				String name = failure.getDescription().getClassName().replaceAll("Test",  "") 
						+ ": " +  failure.getDescription().getMethodName();
				System.out.print(name + " -> ");
				if ( failure.getMessage() != null )
					System.out.print(failure.getMessage());
				else
					System.out.print("No failure message present " +
							"(indicates systemic issues somewhere in the codebase)." +
							"\nTrace: " + failure.getTrace());
				System.out.print("\n");
			}

		}
		System.out.println("-----------------------------");
		System.out.println("Mark summary:");
		System.out.println("\tList:  [gained " + listPass + ", lost " + listLost + "]");
		System.out.println("\tQueue:  [gained " + queuePass + ", lost " + queueLost + "]");
		System.out.println("\tStack:  [gained " + stackPass + ", lost " + stackLost + "]");
		System.out.println("\tInfixToPostfix:  [gained " + ifPass + ", lost " + ifLost + "]");
		System.out.println("\tEvaluation:  [gained " + evalPass + ", lost " + evalLost + "]");

		System.out.println("Total: [" + (listPass + queuePass + stackPass + ifPass + evalPass) + 
				", lost " + (listLost + queueLost + stackLost + ifLost + evalLost) + 
				"] (out of: " + (listPass + queuePass + stackPass + ifPass + evalPass + 
								listLost + queueLost + stackLost + ifLost + evalLost) + ")");
	}

}  
