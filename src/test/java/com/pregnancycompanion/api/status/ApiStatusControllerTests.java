package com.pregnancycompanion.api.status;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ApiStatusControllerTests {

	private final ApiStatusController controller = new ApiStatusController();

	@Test
	void reportsThatTheApiIsUp() {
		ApiStatusResponse response = controller.status();

		assertEquals("Pregnancy Companion API", response.application());
		assertEquals("UP", response.status());
	}
}
