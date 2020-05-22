package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Form extends JPanel {

    private List<Item> questions;
    private JButton submit;
    private GridBagConstraints gbcItem;
    private Font labelFont;
    private Border labelBorder;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private Register register;
    private JPanel topPanel;

    public Form(char id) {
	if (id == 's') {
	    studentForm();

	} else {
	    teacherForm();

	}
	initComponent();
    }

    public JButton getSubmit() {
	return submit;
    }

    private void initComponent() {

	String[] columnNames = new String[] { "Descripción", "Valor" };
	Object[][] dataTable = new String[][] { { "Baja", "1" },
		{ "Baja-Media", "2" }, { "Media", "3" }, { "Media-Alta", "4" },
		{ "Alta", "5" } };

	centerPanel = new JPanel();
	this.gbcItem = new GridBagConstraints();
	// JPanel tablePanel = new JPanel(new FlowLayout());
	DefaultTableModel tableModel = new DefaultTableModel();
	JTable table = new JTable(tableModel) {
	    public boolean editCellAt(int row, int column, EventObject e) {
		return false;
	    }
	};

	table.setFocusable(false);

	tableModel.setColumnIdentifiers(columnNames);
	tableModel.addRow(dataTable[0]);
	tableModel.addRow(dataTable[1]);
	tableModel.addRow(dataTable[2]);
	tableModel.addRow(dataTable[3]);
	tableModel.addRow(dataTable[4]);

	labelFont = new Font("Verdana", Font.PLAIN, 12);
	// labelBorder = BorderFactory.crea

	table.setFont(labelFont);

	// tablePanel.add(table);

	DefaultTableModel tableFormModel = new DefaultTableModel();
	JTable tableForm = new JTable(tableFormModel);

	GridBagLayout gbl = new GridBagLayout();
	centerPanel.setLayout(gbl);
	gbcItem.insets = new Insets(10, 10, 10, 10);

	gbcItem.anchor = GridBagConstraints.CENTER;
	gbcItem.gridx = 0;
	gbcItem.gridy = 0;
	gbcItem.weightx = 1.0;

	gbcItem.gridwidth = 6;
	centerPanel.add(table, gbcItem);

	gbcItem.fill = GridBagConstraints.BOTH;
	gbcItem.gridx = 0;
	gbcItem.gridy = 1;
	gbcItem.weightx = 0.8;
	gbcItem.gridwidth = 1;
	JLabel label = new JLabel("Preguntas:", JLabel.CENTER);

	label.setFont(labelFont);
	centerPanel.add(label, gbcItem);

	gbcItem.weightx = 0.04;
	gbcItem.gridx = 1;
	JLabel jlSelector = new JLabel("1", JLabel.CENTER);
	jlSelector.setFont(labelFont);
	centerPanel.add(jlSelector, gbcItem);

	gbcItem.gridx = 2;
	jlSelector = new JLabel("2", JLabel.CENTER);
	jlSelector.setFont(labelFont);
	centerPanel.add(jlSelector, gbcItem);

	gbcItem.gridx = 3;
	jlSelector = new JLabel("3", JLabel.CENTER);
	jlSelector.setFont(labelFont);
	centerPanel.add(jlSelector, gbcItem);

	gbcItem.gridx = 4;
	jlSelector = new JLabel("4", JLabel.CENTER);
	jlSelector.setFont(labelFont);
	centerPanel.add(jlSelector, gbcItem);

	gbcItem.gridx = 5;
	jlSelector = new JLabel("5", JLabel.CENTER);
	jlSelector.setFont(labelFont);
	centerPanel.add(jlSelector, gbcItem);

	int yItem = 2;
	for (int i = 0; i < questions.size(); i++) {
	    yItem++;
	    JLabel question = questions.get(i).getQuestion();
	    question.setFont(labelFont);
	    gbcItem.weightx = 0.8;
	    gbcItem.gridy = yItem + 1;
	    gbcItem.gridx = 0;
	    centerPanel.add(question, gbcItem);

	    gbcItem.weightx = 0.04;

	    for (int j = 0; j < questions.get(i).getSelectors().size(); j++) {
		gbcItem.gridx = j + 1;
		centerPanel.add(questions.get(i).getSelectors().get(j),
			gbcItem);
	    }
	}

	submit = new JButton("Enviar");
	gbcItem.gridy = gbcItem.gridy + 1;
	gbcItem.gridx = 0;
	gbcItem.gridwidth = 6;
	gbcItem.fill = GridBagConstraints.NONE;
	gbcItem.anchor = GridBagConstraints.EAST;

	submit.setFont(labelFont);
	centerPanel.add(submit, gbcItem);

	mainPanel = new JPanel(new BorderLayout());
	mainPanel.add(centerPanel, BorderLayout.CENTER);

	JScrollPane scrollPane = new JScrollPane(mainPanel);
	setLayout(new BorderLayout(10, 10));

	initRegister();
	add(scrollPane, BorderLayout.CENTER);
    }

    private void addQuestion(String q) {
	if (this.questions == null)
	    this.questions = new ArrayList<Item>();
	this.questions.add(new Item(q));
    }

    private void initRegister() {

	topPanel = new JPanel(new FlowLayout());
	topPanel.add(register);
	mainPanel.add(topPanel, BorderLayout.NORTH);

    }

    private void studentForm() {
	register = new Register('s');
	addQuestion("Eres mayor de edad XD.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
    }

    private void teacherForm() {
	register = new Register('t');
	addQuestion(
		"Eres mayor de edad  lasjdnasjnda ldnsalndsandlsndlnski sccasc.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
	addQuestion("No se que preguntar.");
    }
}
