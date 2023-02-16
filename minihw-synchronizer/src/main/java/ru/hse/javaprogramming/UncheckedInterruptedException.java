package ru.hse.javaprogramming;

/**
 * Непроверяемый {@link InterruptedException}
 */
public class UncheckedInterruptedException extends RuntimeException {
	private final InterruptedException interruptedException;

	public UncheckedInterruptedException(InterruptedException interruptedException) {
		super(interruptedException);
		this.interruptedException = interruptedException;
	}

	public InterruptedException getInterruptedException() {
		return interruptedException;
	}
}
