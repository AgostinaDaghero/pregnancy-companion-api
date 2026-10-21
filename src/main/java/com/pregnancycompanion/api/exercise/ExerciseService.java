package com.pregnancycompanion.api.exercise;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pregnancycompanion.api.exercise.dto.ExerciseResponse;
import com.pregnancycompanion.api.exercise.dto.ExerciseSummaryResponse;

@Service
@Transactional(readOnly = true)
public class ExerciseService {

	private final ExerciseRepository repository;
	private final ExerciseMapper mapper;

	public ExerciseService(ExerciseRepository repository, ExerciseMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ExerciseSummaryResponse> findActive() {
		return repository.findByActiveTrue().stream()
				.map(mapper::toSummary)
				.toList();
	}

	public ExerciseResponse findActiveById(Long id) {
		return repository.findByIdAndActiveTrue(id)
				.map(mapper::toResponse)
				.orElseThrow(() -> new ExerciseNotFoundException(id));
	}
}
