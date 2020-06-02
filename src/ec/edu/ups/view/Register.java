package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Register extends JPanel {

    private GridBagConstraints gbc;
    private Font labelFont;
    private JPanel mainPanel;
    private JTextField dni;
    private JTextField name1;
    private JTextField name2;
    private JTextField lastname1;
    private JTextField lastname2;
    private JComboBox<Integer> age;

    // Teacher fields.
    private JComboBox<Integer> teachTime;
    private JComboBox<Integer> investigationTime;

    // Student Fields.
    private JComboBox<Integer> studyTime;
    private JComboBox<String> quantitySubjects;
    private JComboBox<String> studentType;

    public Register(char id) {
	initComponent(id);
    }

    private void initComponent(char id) {

	gbc = new GridBagConstraints();
	dni = new JTextField(10);
	name1 = new JTextField(20);
	name2 = new JTextField(20);
	lastname1 = new JTextField(20);
	lastname2 = new JTextField(20);
	age = new JComboBox<Integer>();

	mainPanel = new JPanel(new GridBagLayout());
	double wText = 0.85;
	double wLabel = 0.15;
	double wCombo = 0.1;

	labelFont = new Font("Verdana", Font.PLAIN, 12);

	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.WEST;

	gbc.gridx = 0;
	gbc.gridy = 0;
	JLabel tagLabel = new JLabel("Cédula:", 11);
	tagLabel.setFont(labelFont);
	mainPanel.add(tagLabel, gbc);

	gbc.gridx = 1;
	gbc.gridwidth = 2;
	gbc.fill = GridBagConstraints.NONE;
	mainPanel.add(dni, gbc);
	gbc.gridwidth = 1;

	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	tagLabel = new JLabel("Nombres:", 11);
	tagLabel.setFont(labelFont);
	mainPanel.add(tagLabel, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wText / 2;
	gbc.gridx = 1;
	mainPanel.add(name1, gbc);

	gbc.gridx = 2;
	mainPanel.add(name2, gbc);

	gbc.gridx = 0;
	gbc.gridy = 2;
	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	tagLabel = new JLabel("Apellidos:", 11);
	tagLabel.setFont(labelFont);
	mainPanel.add(tagLabel, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wText / 2;
	gbc.gridx = 1;
	mainPanel.add(lastname1, gbc);

	gbc.gridx = 2;
	mainPanel.add(lastname2, gbc);

	gbc.weightx = wLabel;
	gbc.gridx = 0;
	gbc.gridy = 3;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	tagLabel = new JLabel("Edad:", 11);
	tagLabel.setFont(labelFont);
	mainPanel.add(tagLabel, gbc);

	gbc.gridx = 1;
	gbc.weightx = wCombo;
	gbc.fill = GridBagConstraints.NONE;
	for (int i = 1; i <= 110; i++) {
	    age.addItem(i);
	}
	age.setSelectedItem(18);

	mainPanel.add(age, gbc);

	if (id == 's') {
	    studentRegister();
	} else {
	    teacherRegister();
	}

	setLayout(new BorderLayout());
	add(mainPanel, BorderLayout.CENTER);
    }

    private void studentRegister() {
	studyTime = new JComboBox<Integer>();
	quantitySubjects = new JComboBox<String>();
	studentType = new JComboBox<String>();

	double wText = 0.85;
	double wLabel = 0.15;
	double wCombo = 0.1;

	for (int i = 0; i < 24; i++) {
	    studyTime.addItem(i);
	}

	for (int i = 0; i < 10; i++) {
	    quantitySubjects.addItem("" + (i + 1));
	}
	quantitySubjects.addItem("Más...");

	studentType.addItem("Regular");
	studentType.addItem("Irregular");

	gbc.anchor = GridBagConstraints.WEST;

	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	JLabel label = new JLabel("Horas de Estudio:", 11);
	label.setFont(labelFont);
	gbc.gridy = gbc.gridy + 1;
	gbc.gridx = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wCombo;
	gbc.gridx = 1;
	mainPanel.add(studyTime, gbc);

	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	label = new JLabel("Cantidad de Materias:", 11);
	label.setFont(labelFont);
	gbc.gridy = gbc.gridy + 1;
	gbc.gridx = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wCombo;
	gbc.gridx = 1;
	mainPanel.add(quantitySubjects, gbc);

	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	label = new JLabel("Tipo de Estudiante:", 11);
	label.setFont(labelFont);
	gbc.gridy = gbc.gridy + 1;
	gbc.gridx = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wCombo;
	gbc.gridx = 1;
	mainPanel.add(studentType, gbc);

    }

    private void teacherRegister() {
	teachTime = new JComboBox<Integer>();
	investigationTime = new JComboBox<Integer>();

	double wLabel = 0.15;
	double wCombo = 0.1;

	for (int i = 0; i < 24; i++) {
	    teachTime.addItem(i);
	    investigationTime.addItem(i);
	}

	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	JLabel label = new JLabel("Horas de Docencia:", 11);
	label.setFont(labelFont);
	gbc.gridy = gbc.gridy + 1;
	gbc.gridx = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wCombo;
	gbc.gridx = 1;
	mainPanel.add(teachTime, gbc);

	gbc.weightx = wLabel;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	label = new JLabel("Horas en Investigación:", 11);
	label.setFont(labelFont);
	gbc.gridy = gbc.gridy + 1;
	gbc.gridx = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = wCombo;
	gbc.gridx = 1;
	mainPanel.add(investigationTime, gbc);
    }
}
