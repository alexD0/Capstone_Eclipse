package com.twowire.web.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.twowire.web.client.MainGUI;
import com.twowire.web.client.User;
import com.twowire.web.client.service.ServiceManager;

public class LoginPageControlSet {
	
	private TextBox userNameTextBox;
	private TextBox passwordTextBox;
	private Button loginButton;

	public LoginPageControlSet() {
		userNameTextBox = new TextBox(); 
		passwordTextBox = new PasswordTextBox();
		createLoginButton();
	}

	private void createLoginButton() {
		loginButton = new Button("Login", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final String user = getUsername();
				ServiceManager.getTwowireServiceInstance()
					.checkLogin(user, getPassword(), new AsyncCallback() {
						
						@Override
						public void onFailure(Throwable caught) {
							String trace = stackTraceToString(caught);
							Window.alert(caught.getMessage() + "\n" + trace);
						}

						private String stackTraceToString(Throwable caught) {
							String result = "Updated output: ";
							StackTraceElement elements[] = caught.getStackTrace();
							for(StackTraceElement element : elements) {
								result += element.getClassName();
								result += element.getMethodName() + " ";
								result += element.getLineNumber() + "\n";
							}
							return result;
						}

						@Override
						public void onSuccess(Object result) {
							User.setUsername(user);
							MainGUI gui = new MainGUI(user);
						}
					});
			}
		});
	}

	public TextBox getUserNameTextBox() {
		return userNameTextBox;
	}

	public TextBox getPasswordTextBox() {
		return passwordTextBox;
	}

	public Button getLoginButton() {
		return loginButton;
	}
	
	private String getUsername() {
		return userNameTextBox.getValue();
	}

	private String getPassword() {
		return passwordTextBox.getValue();
	}
}
