package com.pregnancycompanion.api.exercise;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(name = "short_description", nullable = false, length = 300)
	private String shortDescription;

	@Column(nullable = false, columnDefinition = "text")
	private String instructions;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private Category category;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "exercise_trimesters", joinColumns = @JoinColumn(name = "exercise_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "trimester", nullable = false, length = 10)
	private Set<Trimester> trimesters = EnumSet.noneOf(Trimester.class);

	@Column(name = "duration_minutes", nullable = false)
	private Integer durationMinutes;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Difficulty difficulty;

	@Column(name = "image_url", length = 500)
	private String imageUrl;

	@Column(name = "video_url", length = 500)
	private String videoUrl;

	@Column(nullable = false)
	private boolean active = true;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	protected Exercise() {
		// requerido por JPA
	}

	public Exercise(String name, String shortDescription, String instructions, Category category,
			Set<Trimester> trimesters, Integer durationMinutes, Difficulty difficulty,
			String imageUrl, String videoUrl) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.instructions = instructions;
		this.category = category;
		this.trimesters.addAll(trimesters);
		this.durationMinutes = durationMinutes;
		this.difficulty = difficulty;
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
	}

	@PrePersist
	void onCreate() {
		Instant now = Instant.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	@PreUpdate
	void onUpdate() {
		this.updatedAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Trimester> getTrimesters() {
		return trimesters;
	}

	public void setTrimesters(Set<Trimester> trimesters) {
		this.trimesters.clear();
		this.trimesters.addAll(trimesters);
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
