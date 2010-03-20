package com.twowire.web.client.submit.ticket;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubmitTicketBuilder extends Widget {
	
	VerticalPanel submitTicketForm;
	
	public SubmitTicketBuilder() {
		submitTicketForm = new VerticalPanel();
		addSubmitTicketHtml();
		FlexTable flexTable = constructFlexTable(new SubmitTicketControlSet());
		submitTicketForm.add(flexTable);
	}

	private FlexTable constructFlexTable(SubmitTicketControlSet controls) {
		FlexTable flexTable = new FlexTable();
		flexTable.setHTML(0, 0, "<l1>*Full Name: </l1>");
		flexTable.setHTML(0, 1, "<b1>contactNameFromDatabase: </b1>");
		flexTable.setHTML(1, 0, "<l1>*User ID:<l1>");
		flexTable.setHTML(1, 1, "<b1>userIDFromDatabase</b1>");
		flexTable.setHTML(2, 0, "<l1>Product:</l1>");
		flexTable.setHTML(2, 1, "<b1>associatedProductsFromDatabase</b1>");
		flexTable.setHTML(3, 0, "<l1>*Ticket Type</l1>");
		flexTable.setWidget(3, 1, controls.getTicketTypeListBox());
		flexTable.setHTML(4, 0, "<l1>Category:</l1>");
		flexTable.setWidget(4, 1, controls.getCategoryListBox());
		flexTable.setHTML(5, 0, "<l1>Subject:</l1>");
		flexTable.setWidget(5, 1, controls.getSubjectTextBox());
		flexTable.setHTML(6, 0, "<l1>Details:</l1>");
		flexTable.setWidget(6, 1, controls.getDetailsTextArea());
		flexTable.setHTML(7, 0, "<l1>Priority:</l1>");
		flexTable.setWidget(7, 1, controls.getPriorityListBox());
		flexTable.setHTML(8, 0, "<l1>Summary:</l1>");
		flexTable.setWidget(8, 1, controls.getStatusTextBox());
		flexTable.setHTML(9, 0, "<l1>Options</l1>");
		flexTable.setHTML(10, 0, "<l1>Attachment:</l1>");
		flexTable.setWidget(10, 1, controls.getAttachment());
		flexTable.setHTML(11, 0, "<l1>Email Notification:</l1>");
		flexTable.setWidget(11, 1, controls.getEmailNotify());
		flexTable.setWidget(12, 0, controls.getPreviewButton());
		flexTable.setWidget(12, 1, controls.getFinishButton());
		flexTable.setCellPadding(4);
		return flexTable;
	}

	private void addSubmitTicketHtml() {
		submitTicketForm.add(new HTML("<h1>Submit a Ticket</h1>"));
		submitTicketForm
				.add(new HTML(
						"<b1>The Ticket System enables you to directly submit a support ticket to a technical specialist whose expertise matches your inquiry. "
								+ "This online tool provides an alternative to phone and e-mail and helps expedite the resolution of your issue. "
								+ "You can track the status of your ticket in My Support.</b1></br>"));
		submitTicketForm.add(new HTML("<l1>Contact Info</l1>"));
	}
	
	public VerticalPanel getPanel() {
		return submitTicketForm;
	}
}
