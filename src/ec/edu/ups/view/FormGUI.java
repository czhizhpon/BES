package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ec.edu.ups.controller.FormController;
import ec.edu.ups.model.Student;
import ec.edu.ups.model.Teacher;
import net.sf.clipsrules.jni.CLIPSException;

public class FormGUI extends JFrame implements ActionListener {

	private FormController formController;

	private Form studentForm;
	private Form teacherForm;

	private JTabbedPane tabbedPane;
	private JPanel centerPanel;
	private JPanel topPanel;
	private JPanel mainPanel;

	private ResultBurnoutGUI resultBurnoutGUI;

	public FormGUI() {

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
			for (UIManager.LookAndFeelInfo look : looks) {
				System.out.println(look.getClassName());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		if (startConnectionClips()) {
			resultBurnoutGUI = new ResultBurnoutGUI();
			initComponents();
			setActionCommands();
		} else {
			initComponents();
			setVisible(false);
			dispose();
		}
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BES - Burnout Expert System");
		setSize(1150, 600);
		getContentPane().setLayout(new BorderLayout());

		mainPanel = new JPanel(new BorderLayout());
		initTabs();

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		// pack();
		setLocationRelativeTo(null);

	}

	private void initTabs() {
		centerPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane();
		// tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		studentForm = new Form('s');
		teacherForm = new Form('t');

		ImageIcon icon = new ImageIcon("../resources/img/save.svg");
		tabbedPane.addTab("Estudiantes", icon, studentForm, "Formulario para estudiantes");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Docentes", icon, teacherForm, "Formulario para docentes");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		centerPanel.add(tabbedPane, BorderLayout.CENTER);

	}

	private boolean startConnectionClips() {
		try {
			formController = new FormController();
			return true;
		} catch (CLIPSException cex) {
			JOptionPane.showMessageDialog(null, "Error al cargar la base de conocimientos.", "Error con Clips",
					JOptionPane.ERROR_MESSAGE);
			cex.printStackTrace();
		}

		return false;
	}

	private boolean createStudent() {
		String dni = this.studentForm.getRegister().getDni().getText();
		String name1 = this.studentForm.getRegister().getName1().getText();
		String name2 = this.studentForm.getRegister().getName2().getText();

		String lastname1 = this.studentForm.getRegister().getLastname1().getText();
		String lastname2 = this.studentForm.getRegister().getLastname2().getText();

		int age = (int) this.studentForm.getRegister().getAge().getSelectedItem();
		int[] result = null;
		char[] catResult = null;

		List<Item> questions = this.studentForm.getQuestions();
		List<Integer> answers = new ArrayList<Integer>();

		for (Item item : questions) {
			int index = 0;
			for (JRadioButton selection : item.getRadioSelection()) {
				if (selection.isSelected()) {
					answers.add(index);
				}
				index++;
			}

		}

		int[] resultQuestion = new int[answers.size()];

		for (int i = 0; i < answers.size(); i++) {
			resultQuestion[i] = answers.get(i);
		}

		double studyTimeDay = Double.parseDouble("" + this.studentForm.getRegister().getStudyTime().getSelectedItem());
		int quantitySubjects = (int) this.studentForm.getRegister().getQuantitySubjects().getSelectedItem();
		String studentTypeSelected = (String) this.studentForm.getRegister().getStudentType().getSelectedItem();
		char studentType = ' ';
		if (studentTypeSelected.equals("Regular")) {
			studentType = 'r';
		} else {
			studentType = 'i';
		}

		Student student = new Student(dni, name1, name2, lastname1, lastname2, age, result, catResult, resultQuestion,
				studyTimeDay, quantitySubjects, studentType);

		try {
			this.formController.instanceStudent(student);
			return true;
		} catch (CLIPSException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la instancia con CLIPS", "Error - CLIPS",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return false;
	}

	private boolean createTeacher() {

		String dni = this.teacherForm.getRegister().getDni().getText();
		String name1 = this.teacherForm.getRegister().getName1().getText();
		String name2 = this.teacherForm.getRegister().getName2().getText();

		String lastname1 = this.teacherForm.getRegister().getLastname1().getText();
		String lastname2 = this.teacherForm.getRegister().getLastname2().getText();

		int age = (int) this.teacherForm.getRegister().getAge().getSelectedItem();
		int[] result = null;
		char[] catResult = null;

		List<Item> questions = this.teacherForm.getQuestions();
		List<Integer> answers = new ArrayList<Integer>();

		for (Item item : questions) {
			int index = 0;
			for (JRadioButton selection : item.getRadioSelection()) {
				if (selection.isSelected()) {
					answers.add(index);
				}
				index++;
			}

		}

		int[] resultQuestion = new int[answers.size()];

		for (int i = 0; i < answers.size(); i++) {
			resultQuestion[i] = answers.get(i);
		}

		double teachTime = Double.parseDouble("" + this.teacherForm.getRegister().getTeachTime().getSelectedItem());
		double investigationTime = Double
				.parseDouble("" + this.teacherForm.getRegister().getInvestigationTime().getSelectedItem());

		Teacher teacher = new Teacher(dni, name1, name2, lastname1, lastname2, age, result, catResult, resultQuestion,
				teachTime, investigationTime);

		try {
			this.formController.instanceTeacher(teacher);
			return true;
		} catch (CLIPSException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la instancia con CLIPS", "Error - CLIPS",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return false;
	}

	private boolean runRules() {
		try {
			this.formController.runRules();
			return true;
		} catch (CLIPSException e) {
			JOptionPane.showMessageDialog(null, "No se pudo ejecutar las reglas.", "Error - CLIPS",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return false;
	}

	private void setStudent() {
		try {
			Student student = this.formController.getStudentFromClips();
			System.out.println("Nombre: " + student.getName1() + " " + student.getName2());
			System.out.println("Apellidos: " + student.getLastname1() + " " + student.getLastname2());

			System.out.println("Resultados: " + Arrays.toString(student.getResult()));
			System.out.println("Resultados Categorizados: " + Arrays.toString(student.getCatResult()));

			resultBurnoutGUI.setVisible(true);
			resultBurnoutGUI.printStudentResults(student);

		} catch (CLIPSException e) {
			JOptionPane.showMessageDialog(null, "No se pudo recuperar al estudiante.", "Error - CLIPS",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void setTeacher() {
		try {
			Teacher teacher = this.formController.getTeacherFromClips();
			System.out.println("Nombre: " + teacher.getName1() + " " + teacher.getName2());
			System.out.println("Apellidos: " + teacher.getLastname1() + " " + teacher.getLastname2());

			System.out.println("Resultados: " + Arrays.toString(teacher.getResult()));
			System.out.println("Resultados Categorizados: " + Arrays.toString(teacher.getCatResult()));

			resultBurnoutGUI.setVisible(true);
			resultBurnoutGUI.printTeacherResults(teacher);

		} catch (CLIPSException e) {
			JOptionPane.showMessageDialog(null, "No se pudo recuperar al estudiante.", "Error - CLIPS",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void setActionCommands() {
		this.studentForm.getSubmit().setActionCommand("est_submit");
		this.studentForm.getSubmit().addActionListener(this);

		this.teacherForm.getSubmit().setActionCommand("tea_submit");
		this.teacherForm.getSubmit().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "est_submit":
			createStudent();
			runRules();
			setStudent();
			break;

		case "tea_submit":
			createTeacher();
			runRules();
			setTeacher();
			break;
		default:
			break;
		}

	}
}
