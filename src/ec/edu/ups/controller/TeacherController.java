package ec.edu.ups.controller;

import ec.edu.ups.model.Teacher;
import net.sf.clipsrules.jni.CLIPSException;

public class TeacherController {

	private ClipsController clipsController;

	public TeacherController(ClipsController clipsController) {
		this.clipsController = clipsController;
	}

	public void createTeacher(Teacher teacher) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}

	public Teacher readTeacher(String id) throws CLIPSException {
		Teacher teacher = null;

		String query = "";

		clipsController.getClips().eval(query);
		return teacher;
	}

	public void updateTeacher(Teacher teacher) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}

	public void deleteTeacher(String id) throws CLIPSException {
		String query = "";

		clipsController.getClips().eval(query);
	}

}
