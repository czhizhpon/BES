package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Item extends JFrame {

    private JLabel question;
    private List<JRadioButton> selectors;
    private ButtonGroup radioGroup;

    public Item(String question) {
	this.question = new JLabel(question);
	radioGroup = new ButtonGroup();
	generateSelectors();
    }

    public JLabel getQuestion() {
	return question;
    }

    public void setQuestion(JLabel question) {
	this.question = question;
    }

    public List<JRadioButton> getSelectors() {
	return selectors;
    }

    public void setSelectors(List<JRadioButton> selectors) {
	this.selectors = selectors;
    }

    private void generateSelectors() {
	if (selectors == null)
	    selectors = new ArrayList<JRadioButton>();

	for (int i = 0; i < 5; i++) {
	    JRadioButton radioButton = new JRadioButton();
	    radioButton.setSelected(false);
	    radioButton.setHorizontalAlignment(SwingConstants.CENTER);
	    selectors.add(radioButton);
	    radioGroup.add(radioButton);
	}
    }
}
