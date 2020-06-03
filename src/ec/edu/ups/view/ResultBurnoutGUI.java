package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ec.edu.ups.model.Student;
import ec.edu.ups.model.Teacher;

public class ResultBurnoutGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtLastname;
	private JTextField txtBurnoutResult;
	private JTextField txtEE;
	private JTextField txtDP;
	private JLabel lblDP;
	private JTextField txtPA;
	private JLabel lblPA;
	private JLabel lblTypeEE;
	private JLabel lblTypeDP;
	private JLabel lblTypePA;
	private JLabel lblTypeBur;
	private JTextArea txtAreaRecom;

	/**
	 * Create the frame.
	 */
	public ResultBurnoutGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 555);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel resulPanel = new JPanel();
		contentPane.add(resulPanel, BorderLayout.CENTER);
		resulPanel.setLayout(null);

		JLabel lblName = new JLabel("Nombres:");
		lblName.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(10, 11, 112, 30);
		resulPanel.add(lblName);

		JLabel lblNewLabel = new JLabel("Apellidos:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 64, 112, 30);
		resulPanel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtName.setBounds(132, 11, 333, 30);
		resulPanel.add(txtName);
		txtName.setColumns(10);

		txtLastname = new JTextField();
		txtLastname.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtLastname.setBounds(132, 64, 333, 30);
		resulPanel.add(txtLastname);
		txtLastname.setColumns(10);

		JLabel lblBurnoutResult = new JLabel("Burnout:");
		lblBurnoutResult.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblBurnoutResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBurnoutResult.setBounds(10, 127, 112, 30);
		resulPanel.add(lblBurnoutResult);

		txtBurnoutResult = new JTextField();
		txtBurnoutResult.setHorizontalAlignment(SwingConstants.CENTER);
		txtBurnoutResult.setFont(new Font("Verdana", Font.PLAIN, 25));
		txtBurnoutResult.setBounds(132, 113, 86, 58);
		resulPanel.add(txtBurnoutResult);
		txtBurnoutResult.setColumns(10);

		JLabel lblEE = new JLabel("Cansancio Emocional");
		lblEE.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblEE.setHorizontalAlignment(SwingConstants.CENTER);
		lblEE.setBounds(10, 206, 167, 30);
		resulPanel.add(lblEE);

		txtEE = new JTextField();
		txtEE.setHorizontalAlignment(SwingConstants.CENTER);
		txtEE.setFont(new Font("Verdana", Font.PLAIN, 25));
		txtEE.setBounds(52, 246, 86, 58);
		resulPanel.add(txtEE);
		txtEE.setColumns(10);

		txtDP = new JTextField();
		txtDP.setHorizontalAlignment(SwingConstants.CENTER);
		txtDP.setFont(new Font("Verdana", Font.PLAIN, 25));
		txtDP.setColumns(10);
		txtDP.setBounds(229, 246, 86, 58);
		resulPanel.add(txtDP);

		lblDP = new JLabel("Despersonalizaci\u00F3n");
		lblDP.setHorizontalAlignment(SwingConstants.CENTER);
		lblDP.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDP.setBounds(187, 206, 167, 30);
		resulPanel.add(lblDP);

		txtPA = new JTextField();
		txtPA.setHorizontalAlignment(SwingConstants.CENTER);
		txtPA.setFont(new Font("Verdana", Font.PLAIN, 25));
		txtPA.setColumns(10);
		txtPA.setBounds(406, 246, 86, 58);
		resulPanel.add(txtPA);

		lblPA = new JLabel("Realizaci\u00F3n Personal");
		lblPA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPA.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPA.setBounds(364, 206, 167, 30);
		resulPanel.add(lblPA);

		lblTypeEE = new JLabel("");
		lblTypeEE.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeEE.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTypeEE.setBounds(52, 310, 86, 30);
		resulPanel.add(lblTypeEE);

		lblTypeDP = new JLabel("");
		lblTypeDP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeDP.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTypeDP.setBounds(229, 315, 86, 30);
		resulPanel.add(lblTypeDP);

		lblTypePA = new JLabel("");
		lblTypePA.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypePA.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTypePA.setBounds(406, 315, 86, 30);
		resulPanel.add(lblTypePA);

		txtAreaRecom = new JTextArea();
		txtAreaRecom.setEditable(false);
		txtAreaRecom.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtAreaRecom.setBounds(52, 375, 440, 120);
		txtAreaRecom.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(txtAreaRecom);
		scroll.setSize(375, 120);
		scroll.setBounds(52, 375, 440, 120);
		resulPanel.add(scroll);

		JLabel lblNewLabel_1 = new JLabel("Recomendaci\u00F3n:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 335, 128, 30);
		resulPanel.add(lblNewLabel_1);

		lblTypeBur = new JLabel("");
		lblTypeBur.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeBur.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTypeBur.setBounds(229, 127, 86, 30);
		resulPanel.add(lblTypeBur);
	}

	public void printStudentResults(Student student, String recomendation) {
		this.txtName.setText(student.getName1() + " " + student.getName2());
		this.txtName.setEditable(false);
		this.txtLastname.setText(student.getLastname1() + " " + student.getLastname2());
		this.txtLastname.setEditable(false);

		this.txtBurnoutResult.setText("" + student.getResult()[3]);
		this.txtBurnoutResult.setEditable(false);
		this.lblTypeBur.setText(getStringType(student.getCatResult()[3]));

		this.txtEE.setText("" + student.getResult()[0]);
		this.txtEE.setEditable(false);
		this.lblTypeEE.setText(getStringType(student.getCatResult()[0]));

		this.txtDP.setText("" + student.getResult()[1]);
		this.txtDP.setEditable(false);
		this.lblTypeDP.setText(getStringType(student.getCatResult()[1]));

		this.txtPA.setText("" + student.getResult()[2]);
		this.txtPA.setEditable(false);
		this.lblTypePA.setText(getStringType(student.getCatResult()[2]));

		this.txtAreaRecom.setText(recomendation);

	}

	public void printTeacherResults(Teacher teacher, String recomendation) {
		this.txtName.setText(teacher.getName1() + " " + teacher.getName2());
		this.txtName.setEditable(false);
		this.txtLastname.setText(teacher.getLastname1() + " " + teacher.getLastname2());
		this.txtLastname.setEditable(false);

		this.txtBurnoutResult.setText("" + teacher.getResult()[3]);
		this.txtBurnoutResult.setEditable(false);
		this.lblTypeBur.setText(getStringType(teacher.getCatResult()[3]));

		this.txtEE.setText("" + teacher.getResult()[0]);
		this.txtEE.setEditable(false);
		this.lblTypeEE.setText(getStringType(teacher.getCatResult()[0]));

		this.txtDP.setText("" + teacher.getResult()[1]);
		this.txtDP.setEditable(false);
		this.lblTypeDP.setText(getStringType(teacher.getCatResult()[1]));

		this.txtPA.setText("" + teacher.getResult()[2]);
		this.txtPA.setEditable(false);
		this.lblTypePA.setText(getStringType(teacher.getCatResult()[2]));

		this.txtAreaRecom.setText(recomendation);
	}

	public String getStringType(char c) {

		String type = "";

		switch (c) {
		case 'l':
			type = "Bajo";
			break;

		case 'm':
			type = "Medio";
			break;

		case 'h':
			type = "Alto";
			break;
		default:
			break;
		}

		return type;
	}
}
