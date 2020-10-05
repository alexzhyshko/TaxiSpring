package io.github.zhyshko.application.dto.misc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationFilteringSortingDTO {

	private int page;
	private boolean sort;
	private boolean filter;
	private String sortBy;
	private String sortOrder;
	private String filterBy;
	private String value;
	
}
