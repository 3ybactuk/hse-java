package ru.hse.javaprogramming;

public class Synchronizer {
	private volatile int id = 1;
	public synchronized void acceptFirst(Runnable runnable) {
		while (id != 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		runnable.run();
		id = 2;
		notifyAll();
	}

	public synchronized void acceptSecond(Runnable runnable) {
		while (id != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		runnable.run();
		id = 3;
		notifyAll();
	}

	public synchronized void acceptThird(Runnable runnable) {
		while (id != 3) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		runnable.run();
	}
}
