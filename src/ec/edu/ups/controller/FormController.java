package ec.edu.ups.controller;

import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.model.Student;
import net.sf.clipsrules.jni.CLIPSException;

public class FormController {

	final static String CLIPS_PATH = "src/ec/edu/ups/resources/clips/";

	private ClipsController clipsController;
	private StudentController studentController;

	private List<Student> students;

	public FormController() throws CLIPSException {
		clipsController = new ClipsController(CLIPS_PATH + "knowledge_base.clp");
		studentController = new StudentController(clipsController);

	}

	public void runRules() throws CLIPSException {
		this.clipsController.runRules();
	}

	public boolean instanceStudent(Student student) throws CLIPSException {

		this.studentController.createStudent(student);

		if (this.students == null)
			this.students = new ArrayList<Student>();
		this.students.add(student);

		return true;

	}

	public Student getStudentFromClips() throws Exception {
		this.studentController.readStudent(this.students.get(students.size() - 1));
		return this.students.get(students.size() - 1);
	}

}
