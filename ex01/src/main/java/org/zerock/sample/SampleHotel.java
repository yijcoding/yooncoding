package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Getter
@RequiredArgsConstructor
public class SampleHotel {
	@NonNull
	private Chef chef;
	
	
}
