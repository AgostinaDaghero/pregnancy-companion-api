package com.pregnancycompanion.api.error;

import java.time.Instant;

/**
 * Uniform error body returned by the API.
 */
public record ErrorResponse(
		Instant timestamp,
		int status,
		String error,
		String message,
		String path) {
}
