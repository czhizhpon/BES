package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FormGUI extends JFrame {

    private Form studentForm;
    private Form teacherForm;

    private JTabbedPane tabbedPane;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel mainPanel;

    public FormGUI() {

	try {

	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	    UIManager.LookAndFeelInfo[] looks = UIManager
		    .getInstalledLookAndFeels();
	    for (UIManager.LookAndFeelInfo look : looks) {
		System.out.println(look.getClassName());
	    }
	} catch (ClassNotFoundException | InstantiationException
		| IllegalAccessException | UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	}

	initComponents();
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
//	pack();
	setLocationRelativeTo(null);

    }

    private void initTabs() {
	centerPanel = new JPanel(new BorderLayout());
	tabbedPane = new JTabbedPane();
//	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	tabbedPane.setTabPlacement(JTabbedPane.LEFT);
	studentForm = new Form('s');
	teacherForm = new Form('t');

	ImageIcon icon = new ImageIcon("../resources/img/save.svg");
	tabbedPane.addTab("Estudiantes", icon, studentForm,
		"Formulario para estudiantes");
	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	tabbedPane.addTab("Docentes", icon, teacherForm,
		"Formulario para docentes");
	tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	centerPanel.add(tabbedPane, BorderLayout.CENTER);

    }
}
