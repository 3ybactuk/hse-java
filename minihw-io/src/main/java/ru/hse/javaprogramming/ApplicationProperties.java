package ru.hse.javaprogramming;

import java.util.List;
import java.util.Objects;

public class ApplicationProperties {
	private final List<String> expletives;


	public ApplicationProperties(List<String> expletives) {
		this.expletives = Objects.requireNonNull(expletives, "expletives == null");
	}

	public List<String> getExpletives() {
		return expletives;
	}
}
