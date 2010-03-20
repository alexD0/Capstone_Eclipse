package com.twowire.web.client.submit.ticket;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.twowire.web.client.service.ServiceManager;

public class SubmitTicketControlSet {

	private ListBox severityListBox;
	private ListBox ticketTypeListBox;
	private ListBox priorityListBox;
	private ListBox categoryListBox;
	private FileUpload attachment;
	private CheckBox emailNotify;
	private Button previewButton;
	private Button finishButton;
	private TextBox summaryTextBox;
	private TextBox subjectTextBox;
	private TextArea detailsTextArea;

	public SubmitTicketControlSet() {
		createListBoxes();
		createText();
		attachment = new FileUpload();
		emailNotify = new CheckBox();
		createButtons();
	}

	private void createText() {
		createTextBox();
		createSubjectTextBox();
		detailsTextArea = createDetailsTextArea();
	}

	private void createSubjectTextBox() {
		subjectTextBox = new TextBox();
	}

	private void createTextBox() {
		summaryTextBox = new TextBox();
	}

	private void createButtons() {
		previewButton = createButton("Preview");
		finishButton = new Button("Finish", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ServiceManager.getTwowireServiceInstance().submitIssue(
						getIntForType(), "Open", getIntFromPriority(),
						"reporter", getSummary(), false, "ASU",
						new AsyncCallback() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Issue create failed!"
										+ caught.getMessage());
							}

							@Override
							public void onSuccess(Object result) {

							}
						});
			}

		});
	}

	private String getSummary() {
		return summaryTextBox.getValue();
	}

	private TextArea createDetailsTextArea() {
		TextArea detailsTA = new TextArea();
		detailsTA.setWidth("400px");
		detailsTA.setHeight("300px");
		return detailsTA;
	}

	private int getIntFromPriority() {
		String temp = priorityListBox.getValue(priorityListBox
				.getSelectedIndex());
		if (temp.equals("Blocker")) {
			return 1;
		} else if (temp.equals("Critical")) {
			return 2;
		} else if (temp.equals("Major")) {
			return 3;
		} else if (temp.equals("Minor")) {
			return 4;
		} else {
			return 5;
		}
	}

	private int getIntForType() {
		String temp = ticketTypeListBox.getValue(ticketTypeListBox
				.getSelectedIndex());
		if (temp.equals("Bug")) {
			return 1;
		} else if (temp.equals("Improvement")) {
			return 2;
		} else if (temp.equals("New Feature")) {
			return 3;
		} else if (temp.equals("Task")) {
			return 4;
		} else {
			return 5;
		}
	}

	private void createListBoxes() {
		ticketTypeListBox = createTicketTypeListBox();
		priorityListBox = createPriorityListBox();
		categoryListBox = createCategoryListBox();
	}

	private ListBox createCategoryListBox() {
		ListBox categoryLB = new ListBox();
		categoryLB.addItem("Account Issue");
		categoryLB.addItem("CMS Application");
		categoryLB.addItem("Database");
		categoryLB.addItem("Gateway/CPE");
		categoryLB.addItem("HowTo");
		categoryLB.addItem("Installation");
		categoryLB.addItem("Troubleshooting");
		return categoryLB;
	}

	private Button createButton(String text) {
		Button previewButton = new Button();
		previewButton.setText(text);
		return previewButton;
	}

	private ListBox createPriorityListBox() {
		ListBox priorityLB = new ListBox();
		priorityLB.addItem("Blocker");
		priorityLB.addItem("Critical");
		priorityLB.addItem("Major");
		priorityLB.addItem("Minor");
		priorityLB.addItem("Trivial");
		return priorityLB;

	}

	private ListBox createTicketTypeListBox() {
		ListBox ticketTypeLB = new ListBox();
		ticketTypeLB.addItem("Select One");
		ticketTypeLB.addItem("Bug");
		ticketTypeLB.addItem("New Feature");
		ticketTypeLB.addItem("Improvement");
		ticketTypeLB.addItem("Task");
		ticketTypeLB.addItem("Custom Issue");
		return ticketTypeLB;
	}

	public ListBox getCategoryListBox() {
		return categoryListBox;
	}

	public ListBox getSeverityListBox() {
		return severityListBox;
	}

	public ListBox getTicketTypeListBox() {
		return ticketTypeListBox;
	}

	public ListBox getPriorityListBox() {
		return priorityListBox;
	}

	public FileUpload getAttachment() {
		return attachment;
	}

	public CheckBox getEmailNotify() {
		return emailNotify;
	}

	public Button getPreviewButton() {
		return previewButton;
	}

	public Button getFinishButton() {
		return finishButton;
	}

	public TextBox getStatusTextBox() {
		return summaryTextBox;
	}

	public TextArea getDetailsTextArea() {
		return detailsTextArea;
	}

	public TextBox getSubjectTextBox() {
		return subjectTextBox;
	}
}
