package com.pregnancycompanion.api.exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

	List<Exercise> findByActiveTrue();

	Optional<Exercise> findByIdAndActiveTrue(Long id);
}
