package com.twowire.web.client.submit.feedback;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubmitFeedbackBuilder extends Widget {

	private VerticalPanel submitFeedbackForm;

	public SubmitFeedbackBuilder() {
		submitFeedbackForm = new VerticalPanel();
		submitFeedbackForm.add(new HTML("<h1>Submit Feedback</h1>"));
		submitFeedbackForm.add(new HTML(
			"<b1>We are committed to providing you with the highest level of online support and services. "
			+ "We encourage you to share your valuable opinions and experiences with us, and will look"
			+ "forward to your feedback.</b1>"));
		submitFeedbackForm.add(createFlexTable());
		submitFeedbackForm.add(createFeedbackTextArea());
		submitFeedbackForm.add(createSubmitFeedbackButton());
	}
	
	public Widget getPanel() {
		return submitFeedbackForm;
	}

	private Button createSubmitFeedbackButton() {
		Button submitFeedback = new Button();
		submitFeedback.setText("Submit Feedback");
		return submitFeedback;
	}

	private TextArea createFeedbackTextArea() {
		TextArea feedbackText = new TextArea();
		feedbackText.setWidth("400px");
		feedbackText.setHeight("300px");
		return feedbackText;
	}

	private FlexTable createFlexTable() {
		FlexTable layout = new FlexTable();
		layout.setHTML(0, 0, "<l1>First Name:</l1>");
		layout.setHTML(0, 1, "<b1>fistNameFromDatabase</b1>");
		layout.setHTML(1, 0, "<l1>Email:</l1>");
		layout.setHTML(1, 1, "<b1>emailFromDatabase</b1>");
		layout.setHTML(2, 0, "<l1>Feedback Type:</l1>");
		layout.setWidget(2, 1, createFeedbackListBox());
		layout.setHTML(3, 0, "<l1>Your Feedback</l1>");
		return layout;
	}
	
	private ListBox createFeedbackListBox() {
		ListBox feedbackLB = new ListBox();
		feedbackLB.addItem("Select One");
		feedbackLB.addItem("Criticism");
		feedbackLB.addItem("Compliment");
		feedbackLB.addItem("Suggestion");
		feedbackLB.addItem("Mistake");
		feedbackLB.addItem("Comment");
		feedbackLB.addItem("Comment");
		feedbackLB.addItem("Other");
		return feedbackLB;
	}
}
