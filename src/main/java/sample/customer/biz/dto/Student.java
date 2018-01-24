package sample.customer.biz.dto;

public class Student {
	private Integer age;

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		System.out.println("Age : " + age);
		return age;
	}
}