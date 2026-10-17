package com.pregnancycompanion.api.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiStatusController {

	@GetMapping("/status")
	public ApiStatusResponse status() {
		return new ApiStatusResponse("Pregnancy Companion API", "UP");
	}
}
