package net.marcomichel.labor.lambda;

@FunctionalInterface
public interface StringFilter {
	public boolean check(String s);
}
