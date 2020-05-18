package ec.edu.ups.controller;

import ec.edu.ups.model.Student;
import net.sf.clipsrules.jni.CLIPSException;

public class StudentController {

	private ClipsController clipsController;

	public StudentController(ClipsController clipsController) {
		this.clipsController = clipsController;
	}

	public void createStudent(Student student) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}

	public Student readStudent(String id) throws CLIPSException {
		Student student = new Student();

		String query = "";

		clipsController.getClips().eval(query);

		return student;
	}

	public void updateStudent(Student student) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}

	public void deleteStudent(String id) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}
}
