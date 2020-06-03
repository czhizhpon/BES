package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableModel;

public class Form extends JPanel {

	private List<Item> questions;
	private JButton submit;
	private GridBagConstraints gbcItem;
	private Font labelFont;
	private Border labelBorder;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel questionPanel;
	private JPanel tableDescriptionPanel;
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

	public List<Item> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Item> questions) {
		this.questions = questions;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public JButton getSubmit() {
		return submit;
	}

	private void initComponent() {

		String[][] dataTableItem = { { "Nunca", "0" }, { "Pocas veces al año", "1" }, { "Una vez al mes o menos", "2" },
				{ "Unas pocas veces al mes", "3" }, { "Una vez a la semana", "4" },
				{ "Unas pocas veces a la semana", "5" }, { "Todos los días", "6" } };
		String[] names = { "<html><h4>Descripción</h4></html>", "<html><h3>Valor</h3></html>" };
		centerPanel = new JPanel();
		gbcItem = new GridBagConstraints();
		DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		JTable table = new JTable(tableModel);

		tableModel.setDataVector(dataTableItem, names);

		labelFont = new Font("Verdana", Font.PLAIN, 12);
		labelBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		table.setFont(labelFont);

		JScrollPane tableScroll = new JScrollPane(table);

		Dimension d = table.getPreferredSize();
		tableScroll.setPreferredSize(new Dimension(d.width + 297, table.getRowHeight() * dataTableItem.length + 48));

		questionPanel = new JPanel(new GridBagLayout());
		tableDescriptionPanel = new JPanel(new GridBagLayout());

		gbcItem.fill = GridBagConstraints.NONE;
		gbcItem.anchor = GridBagConstraints.CENTER;
		gbcItem.gridx = 0;
		gbcItem.gridy = 0;

		gbcItem.gridwidth = 8;
		gbcItem.insets = new InsetsUIResource(10, 10, 10, 10);
		tableDescriptionPanel.add(tableScroll, gbcItem);

		gbcItem.fill = GridBagConstraints.BOTH;
		gbcItem.gridx = 0;
		gbcItem.gridy = 0;
		gbcItem.gridwidth = 1;
		gbcItem.insets = new InsetsUIResource(0, 0, 0, 0);
		JLabel label = new JLabel("<html><h3>Preguntas:</h3></html>", JLabel.CENTER);

		label.setFont(labelFont);
		label.setBorder(labelBorder);
		questionPanel.add(label, gbcItem);

		gbcItem.gridx = 1;
		JLabel jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>0</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 2;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>1</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 3;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>2</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 4;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>3</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 5;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>4</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 6;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>5</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		gbcItem.gridx = 7;
		jlSelector = new JLabel("<html><h3 style='padding:0px 20px'>6</h3></html>", JLabel.CENTER);
		jlSelector.setFont(labelFont);
		jlSelector.setBorder(labelBorder);
		questionPanel.add(jlSelector, gbcItem);

		int yItem = 1;
		for (int i = 0; i < questions.size(); i++) {
			yItem++;
			JLabel question = questions.get(i).getQuestion();
			question.setFont(labelFont);
			question.setBorder(labelBorder);
			gbcItem.gridy = yItem + 1;
			gbcItem.gridx = 0;
			gbcItem.anchor = GridBagConstraints.WEST;
			questionPanel.add(question, gbcItem);

			for (int j = 0; j < questions.get(i).getSelectors().size(); j++) {
				gbcItem.gridx = j + 1;
				gbcItem.anchor = GridBagConstraints.CENTER;
				JPanel radioButtonPanel = questions.get(i).getSelectors().get(j);
				radioButtonPanel.setBorder(labelBorder);
				questionPanel.add(radioButtonPanel, gbcItem);
			}
		}

		submit = new JButton("<html><h4>Enviar</h4></html>");
		gbcItem.gridy = gbcItem.gridy + 1;
		gbcItem.gridx = 0;
		gbcItem.gridwidth = 8;
		gbcItem.fill = GridBagConstraints.NONE;
		gbcItem.anchor = GridBagConstraints.EAST;
		gbcItem.insets = new InsetsUIResource(10, 10, 10, 10);

		submit.setFont(labelFont);
		questionPanel.add(submit, gbcItem);

		mainPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new BorderLayout());

		JScrollPane questionScroll = new JScrollPane(questionPanel);
		questionScroll.getVerticalScrollBar().setUnitIncrement(16);

		centerPanel.add(questionScroll, BorderLayout.CENTER);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

//	JScrollPane scrollPane = new JScrollPane(mainPanel);
		setLayout(new BorderLayout(10, 10));

		initRegister();
		add(mainPanel, BorderLayout.CENTER);
	}

	private void addQuestion(String q) {
		if (this.questions == null)
			this.questions = new ArrayList<Item>();
		this.questions.add(new Item(q));
	}

	private void initRegister() {

		JPanel auxPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		topPanel = new JPanel(new BorderLayout());
		auxPanel.add(register, gbc);

		gbc.gridx = 1;
		auxPanel.add(tableDescriptionPanel, gbc);

		topPanel.add(new JScrollPane(auxPanel), BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);

	}

	private void studentForm() {
		register = new Register('s');
		addQuestion("<strong>1. Me siento emocionalmente agotado por mis estudios.</strong> ");
		addQuestion("<strong>2. Me siento cansado al final de la jornada de estudio.</strong> ");
		addQuestion("<strong>3. Me siento fatigado cuando me levanto por la mañana <br>"
				+ "y tengo que enfrentarme con otro día de estudio.</strong> ");
		addQuestion("<strong>4. Fácilmente comprendo cómo se sienten las personas.</strong> ");
		addQuestion(
				"<strong>5. Creo que trato a algunas personas como si fuesen objetos <br>" + "impersonales.</strong> ");
		addQuestion("<strong>6. Estar todo el día con otras personas es un esfuerzo.</strong>");
		addQuestion("<strong>7. Trato muy eficazmente los problemas de las personas.</strong>");
		addQuestion("<strong>8. Me siento “quemado” por mi trabajo académico.</strong>");
		addQuestion("<strong>9. Creo que estoy influyendo positivamente con mis estudios en las vidas de<br>"
				+ "los demás.</strong> ");
		addQuestion("<strong>10. Me he vuelto más insensible con la gente desde que estudio esta carrera.</strong>");
		addQuestion("<strong>11. Me preocupa el hecho de que estudiar esta carrera me esté endureciendo <br>"
				+ "emocionalmente.</strong>");
		addQuestion("<strong>12. Me siento muy activo.</strong> ");
		addQuestion("<strong>13. Me siento frustrado en mis estudios.</strong>");
		addQuestion("<strong>14. Creo que estoy estudiando demasiado.</strong>");
		addQuestion("<strong>15. No me preocupa realmente lo que le ocurre a algunas personas con las <br>"
				+ "que tengo que interactuar.</strong> ");
		addQuestion("<strong>16. Trabajar directamente con personas me produce estrés.</strong>");
		addQuestion("<strong>17. Fácilmente puedo crear una atmósfera relajada con las personas con<br>"
				+ "las que estudio.</strong>");
		addQuestion("<strong>18. Me siento estimulado después de estudiar en contacto con personas.</strong>");
		addQuestion("<strong>19. He conseguido muchas cosas útiles en mi carrera.</strong>");
		addQuestion("<strong>20. Me siento acabado.</strong> ");
		addQuestion("<strong>21. En mis estudios trato los problemas con mucha calma.</strong> ");
		addQuestion("<strong>22. Creo que las personas en mi entorno académico me culpan de algunos  <br>"
				+ "de sus problemas.</strong> ");
	}

	private void teacherForm() {
		register = new Register('t');
		addQuestion("<strong>1. Me siento emocionalmente agotado/a por mi trabajo.</strong> ");
		addQuestion("<strong>2. Me siento cansado/a al final de la jornada de trabajo.</strong> ");
		addQuestion("<strong>3. Cuando me levanto por la mañana y me enfrento a otra jornada de trabajo <br>"
				+ "me siento fatigado/a.</strong> ");
		addQuestion("<strong>4. Tengo facilidad para comprender como se sienten mis alumnos/as.</strong> ");
		addQuestion("<strong>5. Creo que estoy tratando a algunos alumnos/as como si fueran objetos <br>"
				+ "impersonales.</strong> ");
		addQuestion("<strong>6. Siento que trabajar todo el día con alumnos/as supone un gran esfuerzo <br>"
				+ "y me cansa.</strong>");
		addQuestion("<strong>7. Creo que trato con mucha eficacia los problemas de mis alumnos/as.</strong>");
		addQuestion("<strong>8. Siento que mi trabajo me está desgastando. Me siento quemado/a <br>"
				+ "por mi trabajo.</strong>");
		addQuestion("<strong>9. Creo que con mi trabajo estoy influyendo positivamente en la vida <br>"
				+ "de mis alumnos/as.</strong> ");
		addQuestion("<strong>10. Me he vuelto más insensible con la gente desde que ejerzo la <br>"
				+ "profesión de docente.</strong>");
		addQuestion("<strong>11. Pienso que este trabajo me está endureciendo emocionalmente.</strong>");
		addQuestion("<strong>12. Me siento con mucha energía en mi trabajo.</strong> ");
		addQuestion("<strong>13. Me siento frustrado/a en mi trabajo.</strong>");
		addQuestion("<strong>14. Creo que trabajo demasiado.</strong>");
		addQuestion("<strong>15. No me preocupa realmente lo que les ocurra a algunos <br>"
				+ "de mis alumnos/as.</strong> ");
		addQuestion("<strong>16. Trabajar directamente con alumnos/as me produce estrés.</strong>");
		addQuestion("<strong>17. Siento que puedo crear con facilidad un clima agradable <br>"
				+ "con mis alumnos/as.</strong>");
		addQuestion("<strong>18. Me siento motivado/a después de trabajar en contacto con alumnos/as.</strong>");
		addQuestion("<strong>19. Creo que consigo muchas cosas valiosas en este trabajo.</strong>");
		addQuestion("<strong>20. Me siento acabado/a en mi trabajo, al limite de mis posibilidades.</strong> ");
		addQuestion("<strong>21. En mi trabajo trato los problemas emocionalmente con mucha calma.</strong> ");
		addQuestion("<strong>22. Creo que los alumnos/as me culpan de algunos de sus problemas.</strong> ");
	}
}
