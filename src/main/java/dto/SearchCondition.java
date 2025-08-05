package dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchCondition {
	
	//検索条件のデータ
	private Integer id;
	private String name;
	private Integer minAge;
	private Integer maxAge;
	
	//日付型はyyyy/MM/dd形式のみを許可する
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate minStartDate;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate maxStartDate;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate minEndDate;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate maxEndDate;

	
	
	//Getter・Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public LocalDate getMinStartDate() {
		return minStartDate;
	}

	public void setMinStartDate(LocalDate minStartDate) {
		this.minStartDate = minStartDate;
	}

	public LocalDate getMaxStartDate() {
		return maxStartDate;
	}

	public void setMaxStartDate(LocalDate maxStartDate) {
		this.maxStartDate = maxStartDate;
	}

	public LocalDate getMinEndDate() {
		return minEndDate;
	}

	public void setMinEndDate(LocalDate minEndDate) {
		this.minEndDate = minEndDate;
	}

	public LocalDate getMaxEndDate() {
		return maxEndDate;
	}

	public void setMaxEndDate(LocalDate maxEndDate) {
		this.maxEndDate = maxEndDate;
	}
}
