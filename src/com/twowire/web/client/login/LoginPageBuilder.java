package com.twowire.web.client.login;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPageBuilder {
	private VerticalPanel loginPagePanel;
	private FlexTable layout = new FlexTable();

	public LoginPageBuilder() {
		loginPagePanel = new VerticalPanel();
		LoginPageControlSet lpcs = new LoginPageControlSet();
		addTextBoxes(lpcs);
		addButtons(lpcs);
		loginPagePanel.add(layout);
	}

	private void addTextBoxes(LoginPageControlSet controls) {
		layout.setHTML(0, 0, "<l1>Username:</l1>");
		layout.setWidget(0, 1, controls.getUserNameTextBox());
		layout.setHTML(1, 0, "<l1>Password:</l1>");
		layout.setWidget(1, 1, controls.getPasswordTextBox());
	}

	private void addButtons(LoginPageControlSet controls) {
		layout.setWidget(2, 2, controls.getLoginButton());
	}

	public VerticalPanel getPanel() {
		return loginPagePanel;
	}
}
