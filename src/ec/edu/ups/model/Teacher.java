package ec.edu.ups.model;

public class Teacher extends Person {

	private double teachTime;
	private double investigationTime;

	public Teacher(String dni, String name1, String name2, String lastname1, String lastname2, int age, int[] result,
			char[] catResult, int[] resultQuestion, double teachTime, double investigationTime) {
		super(dni, name1, name2, lastname1, lastname2, age, result, catResult, resultQuestion);
		this.teachTime = teachTime;
		this.investigationTime = investigationTime;
	}

	public double getTeachTime() {
		return teachTime;
	}

	public void setTeachTime(double teachTime) {
		this.teachTime = teachTime;
	}

	public double getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(double investigationTime) {
		this.investigationTime = investigationTime;
	}

}
