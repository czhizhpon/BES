package ec.edu.ups.controller;

import ec.edu.ups.model.Teacher;
import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.InstanceAddressValue;
import net.sf.clipsrules.jni.InstanceNameValue;
import net.sf.clipsrules.jni.MultifieldValue;

public class TeacherController {

	private ClipsController clipsController;

	public TeacherController(ClipsController clipsController) {
		this.clipsController = clipsController;
	}

	public void createTeacher(Teacher teacher) throws CLIPSException {
		String query = "(make-instance ";

		query += "id_" + teacher.getDni();
		query += " of teacher";
		query += "(per_dni \"" + teacher.getDni() + "\")";
		query += "(per_name \"" + teacher.getName1() + "\" \"" + teacher.getName2() + "\")";
		query += "(per_lastname \"" + teacher.getLastname1() + "\" \"" + teacher.getLastname2() + "\")";
		query += "(per_age " + teacher.getAge() + ")";
		query += "(per_result_question ";
		String aux = "";
		for (int r : teacher.getResultQuestion()) {
			aux += r + " ";
		}
		aux = aux.substring(0, aux.length() - 1);
		query += aux + ")";
		query += "(tea_teach_time " + teacher.getTeachTime() + ")";
		query += "(tea_investigation_time " + teacher.getInvestigationTime() + ")";
		query += ")";

		System.out.println("QUERY:\n" + query);

		clipsController.getClips().eval(query);
	}

	public void readTeacher(Teacher teacher) throws CLIPSException, Exception {
		String query = "(find-instance ((?t teacher)) (eq ?t:per_dni ";
		query += "\"" + teacher.getDni() + "\"))";

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

			teacher.setResult(perResult);
			teacher.setCatResult(perResulCat);

		} else {
			throw new NullPointerException("No se instació el Docente: " + teacher.getDni());
		}
	}

}
