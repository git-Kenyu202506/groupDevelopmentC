package entity;

import java.time.LocalDate;

public class Employee {
	private int id;
	private String name;
	private String password;
	private int age;
	private LocalDate startDate;
	private LocalDate endDate;
	
	
	//デフォルトコンストラクタ
	public Employee() {}
	
	//コンストラクタ
	public Employee(int id, String name, String password, int age, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	
	
	
	//Getter・Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
