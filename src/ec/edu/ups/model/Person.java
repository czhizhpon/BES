package ec.edu.ups.model;

public class Person {
	private String dni;
	private String name1;
	private String name2;
	private String lastname1;
	private String lastname2;
	private int age;
	private int[] result;
	private char[] catResult;
	private int[] resultQuestion;

	public Person(String dni, String name1, String name2, String lastname1, String lastname2, int age, int[] result,
			char[] catResult, int[] resultQuestion) {
		super();
		this.dni = dni;
		this.name1 = name1;
		this.name2 = name2;
		this.lastname1 = lastname1;
		this.lastname2 = lastname2;
		this.age = age;
		this.result = result;
		this.catResult = catResult;
		this.resultQuestion = resultQuestion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getLastname1() {
		return lastname1;
	}

	public void setLastname1(String lastname1) {
		this.lastname1 = lastname1;
	}

	public String getLastname2() {
		return lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}

	public char[] getCatResult() {
		return catResult;
	}

	public void setCatResult(char[] catResult) {
		this.catResult = catResult;
	}

	public int[] getResultQuestion() {
		return resultQuestion;
	}

	public void setResultQuestion(int[] resultQuestion) {
		this.resultQuestion = resultQuestion;
	}

}
