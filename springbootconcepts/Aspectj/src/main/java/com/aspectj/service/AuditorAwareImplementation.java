package com.aspectj.service;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImplementation implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Sarran sher");
	}

}
 