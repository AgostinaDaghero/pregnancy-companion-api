package com.pregnancycompanion.api.exercise;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.EnumSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

@WebMvcTest(ExerciseController.class)
class ExerciseControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ExerciseService service;

	@Test
	void listReturnsTheActiveExercises() throws Exception {
		when(service.findActive()).thenReturn(List.of(
				new ExerciseSummaryResponse(1L, "Cat-cow stretch", "Gentle spine mobility.",
						Category.STRETCHING, EnumSet.of(Trimester.FIRST, Trimester.SECOND), 5,
						Difficulty.BEGINNER, null)));

		mockMvc.perform(get("/api/v1/exercises"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Cat-cow stretch"))
				.andExpect(jsonPath("$[0].category").value("STRETCHING"))
				.andExpect(jsonPath("$[0].trimesters[0]").value("FIRST"))
				.andExpect(jsonPath("$[0].trimesters[1]").value("SECOND"))
				.andExpect(jsonPath("$[0].instructions").doesNotExist());
	}

	@Test
	void getReturnsTheFullExercise() throws Exception {
		when(service.findActiveById(1L)).thenReturn(
				new ExerciseResponse(1L, "Cat-cow stretch", "Gentle spine mobility.",
						"Start on hands and knees.", Category.STRETCHING, EnumSet.of(Trimester.FIRST), 5,
						Difficulty.BEGINNER, null, null,
						Instant.parse("2026-01-01T10:00:00Z"), Instant.parse("2026-01-02T10:00:00Z")));

		mockMvc.perform(get("/api/v1/exercises/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Cat-cow stretch"))
				.andExpect(jsonPath("$.instructions").value("Start on hands and knees."))
				.andExpect(jsonPath("$.createdAt").value("2026-01-01T10:00:00Z"))
				.andExpect(jsonPath("$.active").doesNotExist());
	}

	@Test
	void getReturnsNotFoundWithAnErrorBodyWhenTheExerciseDoesNotExist() throws Exception {
		when(service.findActiveById(99L)).thenThrow(new ExerciseNotFoundException(99L));

		mockMvc.perform(get("/api/v1/exercises/99"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Not Found"))
				.andExpect(jsonPath("$.message").value("Exercise with id 99 was not found"))
				.andExpect(jsonPath("$.path").value("/api/v1/exercises/99"))
				.andExpect(jsonPath("$.timestamp").exists());
	}
}
