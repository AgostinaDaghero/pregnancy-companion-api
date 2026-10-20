package com.pregnancycompanion.api.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

class ExerciseMapperTests {

	private final ExerciseMapper mapper = new ExerciseMapper();

	@Test
	void mapsAnExerciseToItsSummary() {
		Exercise exercise = newExercise();

		ExerciseSummaryResponse summary = mapper.toSummary(exercise);

		assertThat(summary.id()).isEqualTo(7L);
		assertThat(summary.name()).isEqualTo("Cat-cow stretch");
		assertThat(summary.shortDescription()).isEqualTo("Gentle spine mobility on all fours.");
		assertThat(summary.category()).isEqualTo(Category.STRETCHING);
		assertThat(summary.trimesters()).containsExactly(Trimester.FIRST, Trimester.SECOND);
		assertThat(summary.durationMinutes()).isEqualTo(5);
		assertThat(summary.difficulty()).isEqualTo(Difficulty.BEGINNER);
		assertThat(summary.imageUrl()).isEqualTo("https://example.com/cat-cow.jpg");
	}

	@Test
	void mapsAnExerciseToItsFullResponse() {
		Exercise exercise = newExercise();

		ExerciseResponse response = mapper.toResponse(exercise);

		assertThat(response.id()).isEqualTo(7L);
		assertThat(response.instructions()).isEqualTo("Start on hands and knees.");
		assertThat(response.videoUrl()).isNull();
		assertThat(response.trimesters()).containsExactly(Trimester.FIRST, Trimester.SECOND);
		assertThat(response.createdAt()).isEqualTo(Instant.parse("2026-01-01T10:00:00Z"));
		assertThat(response.updatedAt()).isEqualTo(Instant.parse("2026-01-02T10:00:00Z"));
	}

	@Test
	void returnedTrimestersAreACopyOfTheEntityCollection() {
		Exercise exercise = newExercise();

		ExerciseSummaryResponse summary = mapper.toSummary(exercise);
		exercise.getTrimesters().add(Trimester.THIRD);

		assertThat(summary.trimesters()).containsExactly(Trimester.FIRST, Trimester.SECOND);
	}

	private Exercise newExercise() {
		Exercise exercise = new Exercise(
				"Cat-cow stretch",
				"Gentle spine mobility on all fours.",
				"Start on hands and knees.",
				Category.STRETCHING,
				EnumSet.of(Trimester.SECOND, Trimester.FIRST),
				5,
				Difficulty.BEGINNER,
				"https://example.com/cat-cow.jpg",
				null);
		// id and timestamps are normally set by the database and JPA callbacks
		ReflectionTestUtils.setField(exercise, "id", 7L);
		ReflectionTestUtils.setField(exercise, "createdAt", Instant.parse("2026-01-01T10:00:00Z"));
		ReflectionTestUtils.setField(exercise, "updatedAt", Instant.parse("2026-01-02T10:00:00Z"));
		return exercise;
	}
}
