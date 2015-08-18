package net.marcomichel.labor.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Java8LambdaTest {

	private final List<String> testData = Arrays.asList("Marco", "Tine", "Julia", "Daniel", "Manuel", "Rebekka");
	
	protected void showEntries(final List<String> liste, final StringFilter filter) {
		for (String entry : liste) {
			if (filter.check(entry)) {
				System.out.println(entry);
			}
		}
	}
	
	protected void showEntriesPredicate(final List<String> liste, final Predicate<String> filter) {
		for (String entry : liste) {
			if (filter.test(entry)) {
				System.out.println(entry);
			}
		}		
	}
	
	public void testClassic() {
		showEntries(testData, new StringFilter() {
			
			@Override
			public boolean check(String s) {
				return s.length() > 5;
			}
		});
	}
	
	public void testLambda() {
		showEntries(testData, s -> s.length() > 5);
	}
	
	public void testLambdaMultiLines() {
		showEntries(testData, s -> {
			System.out.println("Testing " + s);
			return s.length() > 5;
		});
	}
	
	public void testLambdaPredicate() {
		showEntriesPredicate(testData, s -> s.length() > 5);
	}
	
	public void testLambdaWithFunction() {
		showEntries(testData, s -> checker(s));
	}
	
	private boolean checker(String s) {
		return s.length() > 5;
	}
	
	public static void main(String[] args) {
		final Java8LambdaTest t = new Java8LambdaTest();
		System.out.println("##classic");
		t.testClassic();
		System.out.println("##lambda");
		t.testLambda();
		System.out.println("##lambda with function");
		t.testLambdaWithFunction();
		System.out.println("##lambda with predicate");
		t.testLambdaPredicate();
		System.out.println("##lambda with multi lines");
		t.testLambdaMultiLines();
	}

}
