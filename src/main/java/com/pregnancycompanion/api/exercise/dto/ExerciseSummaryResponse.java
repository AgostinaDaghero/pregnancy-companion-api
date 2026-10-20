package com.pregnancycompanion.api.exercise.dto;

import java.util.Set;

import com.pregnancycompanion.api.exercise.Category;
import com.pregnancycompanion.api.exercise.Difficulty;
import com.pregnancycompanion.api.exercise.Trimester;

/**
 * Compact representation of an exercise, used in list responses.
 */
public record ExerciseSummaryResponse(
		Long id,
		String name,
		String shortDescription,
		Category category,
		Set<Trimester> trimesters,
		Integer durationMinutes,
		Difficulty difficulty,
		String imageUrl) {
}
