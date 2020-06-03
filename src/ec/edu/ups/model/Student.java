package ec.edu.ups.model;

public class Student extends Person {

	private double studyTimeDay;
	private int quantitySubjects;
	private char studentType;

	public Student(String dni, String name1, String name2, String lastname1, String lastname2, int age, int[] result,
			char[] catResult, int[] resultQuestion, double studyTimeDay, int quantitySubjects, char studentType) {
		super(dni, name1, name2, lastname1, lastname2, age, result, catResult, resultQuestion);
		this.studyTimeDay = studyTimeDay;
		this.quantitySubjects = quantitySubjects;
		this.studentType = studentType;
	}

	public double getStudyTimeDay() {
		return studyTimeDay;
	}

	public void setStudyTimeDay(double studyTimeDay) {
		this.studyTimeDay = studyTimeDay;
	}

	public int getQuantitySubjects() {
		return quantitySubjects;
	}

	public void setQuantitySubjects(int quantitySubjects) {
		this.quantitySubjects = quantitySubjects;
	}

	public char getStudentType() {
		return studentType;
	}

	public void setStudentType(char studentType) {
		this.studentType = studentType;
	}

}
