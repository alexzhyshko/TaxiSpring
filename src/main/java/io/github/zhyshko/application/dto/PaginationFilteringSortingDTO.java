package io.github.zhyshko.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationFilteringSortingDTO {

	private Boolean sort;
	private Boolean filter;
	private String sortBy;
	private String sortOrder;
	private String filterBy;
	private String value;
	
}
