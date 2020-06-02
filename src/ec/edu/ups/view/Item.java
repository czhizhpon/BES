package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Item {

    private JLabel question;
    private List<JPanel> selectors;
    private ButtonGroup radioGroup;

    public Item(String question) {
	this.question = new JLabel(
		"<html><p style='padding:10px'>" + question + "</p></html>");
	radioGroup = new ButtonGroup();
	generateSelectors();
    }

    public JLabel getQuestion() {
	return question;
    }

    public void setQuestion(JLabel question) {
	this.question = question;
    }

    public List<JPanel> getSelectors() {
	return selectors;
    }

    public void setSelectors(List<JPanel> selectors) {
	this.selectors = selectors;
    }

    private void generateSelectors() {
	if (selectors == null)
	    selectors = new ArrayList<JPanel>();

	for (int i = 0; i < 7; i++) {
	    JRadioButton radioButton = new JRadioButton();
	    radioButton.setSelected(false);
	    JPanel panel = new JPanel(new BorderLayout());

	    radioButton.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(radioButton, BorderLayout.CENTER);
	    selectors.add(panel);
	    radioGroup.add(radioButton);
	}
    }
}
