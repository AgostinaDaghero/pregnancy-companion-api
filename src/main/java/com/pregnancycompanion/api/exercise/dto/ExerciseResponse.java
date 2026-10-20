package com.pregnancycompanion.api.exercise.dto;

import java.time.Instant;
import java.util.Set;

import com.pregnancycompanion.api.exercise.Category;
import com.pregnancycompanion.api.exercise.Difficulty;
import com.pregnancycompanion.api.exercise.Trimester;

/**
 * Full representation of an exercise, used when fetching a single one.
 */
public record ExerciseResponse(
		Long id,
		String name,
		String shortDescription,
		String instructions,
		Category category,
		Set<Trimester> trimesters,
		Integer durationMinutes,
		Difficulty difficulty,
		String imageUrl,
		String videoUrl,
		Instant createdAt,
		Instant updatedAt) {
}
