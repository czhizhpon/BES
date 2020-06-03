package ec.edu.ups.controller;

import ec.edu.ups.model.Student;
import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.InstanceAddressValue;
import net.sf.clipsrules.jni.InstanceNameValue;
import net.sf.clipsrules.jni.MultifieldValue;

public class StudentController {

	private ClipsController clipsController;

	public StudentController(ClipsController clipsController) {
		this.clipsController = clipsController;
	}

	public void createStudent(Student student) throws CLIPSException {
		String query = "(make-instance ";

		query += "id_" + student.getDni();
		query += " of student";
		query += "(per_dni \"" + student.getDni() + "\")";
		query += "(per_name \"" + student.getName1() + "\" \"" + student.getName2() + "\")";
		query += "(per_lastname \"" + student.getLastname1() + "\" \"" + student.getLastname2() + "\")";
		query += "(per_age " + student.getAge() + ")";
		query += "(per_result_question ";
		String aux = "";
		for (int r : student.getResultQuestion()) {
			aux += r + " ";
		}
		aux = aux.substring(0, aux.length() - 1);
		query += aux + ")";
		query += "(stu_study_time_day " + student.getStudyTimeDay() + ")";
		query += "(stu_quantity_subjects " + student.getQuantitySubjects() + ")";
		query += "(stu_student_type " + student.getStudentType() + ")";
		query += ")";

		System.out.println("QUERY:\n" + query);

		clipsController.getClips().eval(query);
	}

	public void readStudent(Student student) throws CLIPSException, Exception {
		String query = "(find-instance ((?s student)) (eq ?s:per_dni ";
		query += "\"" + student.getDni() + "\"))";

		MultifieldValue mv = this.clipsController.getAResult(query);

		if (mv.size() > 0) {
			InstanceNameValue inv = (InstanceNameValue) mv.get(0);
			InstanceAddressValue iav = inv.getInstance(this.clipsController.getClips());
			String perResultIns = iav.getSlotValue("per_result").toString();
			String perResulCatIns = iav.getSlotValue("per_cat_result").toString();

			perResultIns = perResultIns.substring(1, perResultIns.length() - 1);
			perResulCatIns = perResulCatIns.substring(1, perResulCatIns.length() - 1);

			String[] perInsArr = perResultIns.split(" ");
			String[] perResulCatArr = perResulCatIns.split(" ");
			int[] perResult = new int[perInsArr.length];
			char[] perResulCat = new char[perResulCatArr.length];
			for (int i = 0; i < perInsArr.length; i++) {
				perResult[i] = Integer.parseInt(perInsArr[i]);
				perResulCat[i] = perResulCatArr[i].charAt(0);
			}

			student.setResult(perResult);
			student.setCatResult(perResulCat);

		} else {
			throw new NullPointerException();
		}
	}
}
