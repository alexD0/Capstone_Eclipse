package com.twowire.web.client;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.twowire.create.Ticket;
import com.twowire.model.Issues;
import com.twowire.web.client.display.ticket.DisplayTicketBuilder;
import com.twowire.web.client.login.LoginPageBuilder;
import com.twowire.web.client.search.SearchSidebar;
import com.twowire.web.client.service.ServiceManager;
import com.twowire.web.client.submit.feedback.SubmitFeedbackBuilder;
import com.twowire.web.client.submit.ticket.SubmitTicketBuilder;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainGUI implements EntryPoint {

	private RootPanel root;
	private VerticalPanel loginPage;
	private String user, key; 
	private int status;
	
	public MainGUI() {}
	
	public MainGUI(String user) {
		clearPage();
		this.user = user;
		root.add(createGuiPanel());
	}
	
	public MainGUI(String user, String key, int status) {
		clearPage();
		this.user = user;
		this.key = key;
		this.status = status;
		root.add(createGuiPanel());
	}
	
	private void clearPage() {
		root = RootPanel.get();
		Iterator<Widget> it = root.iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public void onModuleLoad() {
		LoginPageBuilder loginBuilder = new LoginPageBuilder();
		loginPage = loginBuilder.getPanel();
		root = RootPanel.get();
		root.add(loginPage);
	}
	
	private Widget createGuiPanel() {
		DockPanel guiPanel = new DockPanel();
		guiPanel.setSpacing(10);
		guiPanel.add(new SearchSidebar().getSearchPanel(), DockPanel.WEST);
		guiPanel.add(createNavPanel(), DockPanel.EAST);
		return guiPanel;
	}

	private DecoratedTabPanel createNavPanel() {
		DecoratedTabPanel navPanel = new DecoratedTabPanel();
		navPanel.add(setRecentTickets(), "Recent Tickets");
		navPanel.add(setSubmitTicket(), "Submit a Ticket");
		navPanel.add(setSubmitFeedBack(), "Submit Feedback");
		navPanel.add(setContactUs(), "Contact Us");
		navPanel.selectTab(0);
		return navPanel;
	}

	public VerticalPanel setRecentTickets() {
		VerticalPanel recentTicketsPanel = new VerticalPanel();
		final VerticalPanel main = new VerticalPanel();
		main.add(new HTML("<h1>Recent Tickets</h1>"));
		ServiceManager.getTwowireServiceInstance().retrieveIssues(user, new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error Loading Pending Issues: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<String> result) {
				
				final List<String> result2=result;
				
				ServiceManager.getTwowireServiceInstance().retrieveIssues2(user, new AsyncCallback<List<String>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error Loading Pending Issues: " + caught.getMessage());
					}

					@Override
					public void onSuccess(List<String> result) {
						

	
				DisclosurePanel tickets = new DisclosurePanel();
				for(int i=0; i<result.size(); i++) {
					tickets = new DisclosurePanel(result2.get(i));
					tickets.add(setDisplayTickets(i,result));                         //              //
					main.setSpacing(5);
					main.add(tickets);
				}
				}
				});
			
			}
		});
		recentTicketsPanel.add(main);
		return recentTicketsPanel;
	}
	
	private VerticalPanel setDisplayTickets(int i,List<String>result)                               //          //
	{
		VerticalPanel displayTicketsPanel = new VerticalPanel();
		displayTicketsPanel.add(new DisplayTicketBuilder(i,result,user).getPanel());           //       //
		return displayTicketsPanel;
	}

	private VerticalPanel setSubmitTicket() {
		VerticalPanel submitTicketPanel = new VerticalPanel();
		submitTicketPanel.add(new SubmitTicketBuilder().getPanel());
		return submitTicketPanel;
	}

	private VerticalPanel setSubmitFeedBack() {
		VerticalPanel submitFeedbackPanel = new VerticalPanel();
		submitFeedbackPanel.add(new SubmitFeedbackBuilder().getPanel());
		return submitFeedbackPanel;
	}

	private VerticalPanel setContactUs() {
		VerticalPanel contactUsPanel = new VerticalPanel();
		contactUsPanel.add(new HTML("<h1>Contact Us:<br></h1>"
				+ "<b1>Australia 0011.800.424.29.473<br>"
				+ "Europe 0011.800.424.29.473<br>"
				+ "Canada +1.866.642.6045<br>" + "Mexico +1.866.642.6045<br>"
				+ "U.S. 866.642.6045<br>"
				+ "Normal hours 8AM-5PM PST/PDT Mon-Fri<br>"
				+ "Severity one incidents are 24/7</b1>"));
		return contactUsPanel;
	}



}
