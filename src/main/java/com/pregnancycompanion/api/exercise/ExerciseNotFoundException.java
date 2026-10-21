package com.pregnancycompanion.api.exercise;

public class ExerciseNotFoundException extends RuntimeException {

	public ExerciseNotFoundException(Long id) {
		super("Exercise with id " + id + " was not found");
	}
}
