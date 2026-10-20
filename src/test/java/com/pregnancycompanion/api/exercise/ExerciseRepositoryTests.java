package com.pregnancycompanion.api.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumSet;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import com.pregnancycompanion.api.TestcontainersConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
class ExerciseRepositoryTests {

	@Autowired
	private ExerciseRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void savesAndReadsAnExerciseWithItsTrimesters() {
		Exercise saved = repository.save(newExercise());
		entityManager.flush();
		entityManager.clear();

		Exercise found = repository.findById(saved.getId()).orElseThrow();

		assertThat(found.getName()).isEqualTo("Cat-cow stretch");
		assertThat(found.getCategory()).isEqualTo(Category.STRETCHING);
		assertThat(found.getDifficulty()).isEqualTo(Difficulty.BEGINNER);
		assertThat(found.getDurationMinutes()).isEqualTo(5);
		assertThat(found.isActive()).isTrue();
		assertThat(found.getTrimesters()).containsExactlyInAnyOrder(Trimester.FIRST, Trimester.SECOND);
		assertThat(found.getImageUrl()).isNull();
		assertThat(found.getCreatedAt()).isNotNull();
		assertThat(found.getUpdatedAt()).isNotNull();
	}

	@Test
	void updatingAnExerciseKeepsCreatedAtAndRefreshesUpdatedAt() {
		Exercise saved = repository.save(newExercise());
		entityManager.flush();
		entityManager.clear();

		Exercise before = repository.findById(saved.getId()).orElseThrow();
		var createdAt = before.getCreatedAt();
		var updatedAtBefore = before.getUpdatedAt();

		before.setName("Cat-cow stretch (updated)");
		repository.saveAndFlush(before);
		entityManager.clear();

		Exercise after = repository.findById(saved.getId()).orElseThrow();

		assertThat(after.getName()).isEqualTo("Cat-cow stretch (updated)");
		assertThat(after.getCreatedAt()).isEqualTo(createdAt);
		assertThat(after.getUpdatedAt()).isAfter(updatedAtBefore);
	}

	private Exercise newExercise() {
		return new Exercise(
				"Cat-cow stretch",
				"Gentle spine mobility on all fours.",
				"Start on hands and knees. Inhale and arch your back, exhale and round it.",
				Category.STRETCHING,
				EnumSet.of(Trimester.FIRST, Trimester.SECOND),
				5,
				Difficulty.BEGINNER,
				null,
				null);
	}
}
