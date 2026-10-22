package com.pregnancycompanion.api.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

	private final ExerciseService service;

	public ExerciseController(ExerciseService service) {
		this.service = service;
	}

	@GetMapping
	public List<ExerciseSummaryResponse> list() {
		return service.findActive();
	}

	@GetMapping("/{id}")
	public ExerciseResponse get(@PathVariable Long id) {
		return service.findActiveById(id);
	}
}
