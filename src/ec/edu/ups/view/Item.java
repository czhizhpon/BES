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
	private List<JRadioButton> radioSelection;
	private ButtonGroup radioGroup;

	public Item(String question) {
		this.question = new JLabel("<html><p style='padding:10px'>" + question + "</p></html>");
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

	public List<JRadioButton> getRadioSelection() {
		return radioSelection;
	}

	public void setRadioSelection(List<JRadioButton> radioSelection) {
		this.radioSelection = radioSelection;
	}

	public ButtonGroup getRadioGroup() {
		return radioGroup;
	}

	public void setRadioGroup(ButtonGroup radioGroup) {
		this.radioGroup = radioGroup;
	}

	private void generateSelectors() {
		if (selectors == null) {
			selectors = new ArrayList<JPanel>();
			radioSelection = new ArrayList<JRadioButton>();
		}

		for (int i = 0; i < 7; i++) {
			JRadioButton radioButton = new JRadioButton();
			radioButton.setSelected(false);

			if (i == 0) {
				radioButton.setSelected(true);
			}

			JPanel panel = new JPanel(new BorderLayout());

			radioButton.setHorizontalAlignment(SwingConstants.CENTER);
			radioSelection.add(radioButton);
			panel.add(radioButton, BorderLayout.CENTER);
			selectors.add(panel);
			radioGroup.add(radioButton);
		}
	}
}
