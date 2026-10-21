package com.pregnancycompanion.api.exercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTests {

	@Mock
	private ExerciseRepository repository;

	@Spy
	private ExerciseMapper mapper = new ExerciseMapper();

	@InjectMocks
	private ExerciseService service;

	@Test
	void findActiveReturnsASummaryForEachActiveExercise() {
		when(repository.findByActiveTrue()).thenReturn(List.of(
				newExercise("Cat-cow stretch"),
				newExercise("Deep breathing")));

		List<ExerciseSummaryResponse> result = service.findActive();

		assertThat(result).extracting(ExerciseSummaryResponse::name)
				.containsExactly("Cat-cow stretch", "Deep breathing");
	}

	@Test
	void findActiveReturnsAnEmptyListWhenThereAreNoActiveExercises() {
		when(repository.findByActiveTrue()).thenReturn(List.of());

		assertThat(service.findActive()).isEmpty();
	}

	@Test
	void findActiveByIdReturnsTheFullExercise() {
		when(repository.findByIdAndActiveTrue(7L)).thenReturn(Optional.of(newExercise("Cat-cow stretch")));

		ExerciseResponse result = service.findActiveById(7L);

		assertThat(result.name()).isEqualTo("Cat-cow stretch");
		assertThat(result.instructions()).isEqualTo("Start on hands and knees.");
	}

	@Test
	void findActiveByIdThrowsWhenTheExerciseDoesNotExistOrIsInactive() {
		when(repository.findByIdAndActiveTrue(99L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.findActiveById(99L))
				.isInstanceOf(ExerciseNotFoundException.class)
				.hasMessage("Exercise with id 99 was not found");
	}

	private Exercise newExercise(String name) {
		return new Exercise(
				name,
				"Short description.",
				"Start on hands and knees.",
				Category.STRETCHING,
				EnumSet.of(Trimester.FIRST),
				5,
				Difficulty.BEGINNER,
				null,
				null);
	}
}
