package com.pregnancycompanion.api.exercise;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

@Component
public class ExerciseMapper {

	public ExerciseSummaryResponse toSummary(Exercise exercise) {
		return new ExerciseSummaryResponse(
				exercise.getId(),
				exercise.getName(),
				exercise.getShortDescription(),
				exercise.getCategory(),
				copyTrimesters(exercise.getTrimesters()),
				exercise.getDurationMinutes(),
				exercise.getDifficulty(),
				exercise.getImageUrl());
	}

	public ExerciseResponse toResponse(Exercise exercise) {
		return new ExerciseResponse(
				exercise.getId(),
				exercise.getName(),
				exercise.getShortDescription(),
				exercise.getInstructions(),
				exercise.getCategory(),
				copyTrimesters(exercise.getTrimesters()),
				exercise.getDurationMinutes(),
				exercise.getDifficulty(),
				exercise.getImageUrl(),
				exercise.getVideoUrl(),
				exercise.getCreatedAt(),
				exercise.getUpdatedAt());
	}

	/**
	 * Copies the lazy, Hibernate-managed collection into a plain set, so the DTO does not
	 * depend on the persistence context. An EnumSet also keeps a stable FIRST, SECOND, THIRD order.
	 */
	private Set<Trimester> copyTrimesters(Set<Trimester> trimesters) {
		EnumSet<Trimester> copy = EnumSet.noneOf(Trimester.class);
		copy.addAll(trimesters);
		return copy;
	}
}
