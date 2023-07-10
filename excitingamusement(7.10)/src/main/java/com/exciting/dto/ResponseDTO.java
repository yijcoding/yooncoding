package com.exciting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
// TodoDTO 뿐만 아니라 이후 다른 모델의 DTO도 ResponseDTO를 이용해 리턴할 수
//있도록 자바 Generic을 이용했다.
	private String error;
	private List<T> data;
}
