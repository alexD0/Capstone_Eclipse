package com.twowire.web.client.search;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.twowire.web.client.MainGUI;
import com.twowire.web.client.User;
import com.twowire.web.client.service.ServiceManager;

public class SearchSidebar {
	
	private VerticalPanel searchPanel = new VerticalPanel();
	private ListBox statusBox;
	private TextBox issueKey, searchBox;
	private String user, key="", temp, searchString;
	private int status;
	
	public SearchSidebar() {
		statusBox = new ListBox();
		createSearchBox();
	}

	private void createSearchBox() {
		searchBox = new TextBox();
		searchBox.setText("");
		Button searchButton = new Button("Search", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				temp = statusBox.getValue(statusBox.getSelectedIndex());
				key = issueKey.getValue();
				user = User.getUsername();
				searchString = searchBox.getValue();
				status = convertStatusToInt(temp);
				createMainGuiFromSearch();
			}

			private void createMainGuiFromSearch() {
				new MainGUI(user, key, status) {
					@Override
					public VerticalPanel setRecentTickets() {
						VerticalPanel recentTicketsPanel = new VerticalPanel();
						final VerticalPanel main = new VerticalPanel();
						main.add(new HTML("<h1>Recent Tickets</h1><br/>"));
						if(searchString.equals("")) {
							getIssuesForKeyAndStatus(main);
						}
						else {
							getIssuesForFiltersAndSearchString(main);
						}
						recentTicketsPanel.add(main);
						return recentTicketsPanel;
					}

					private void getIssuesForFiltersAndSearchString(
							final VerticalPanel main) {
						System.out.println(searchString);
						ServiceManager.getTwowireServiceInstance().retrieveIssues(user, key, status, searchString, new AsyncCallback<List<String>>() {
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Error Loading Pending Issues: " + caught.getMessage());
							}

							@Override
							public void onSuccess(List<String> result) {
								for(int i=0; i<result.size(); i++) {
									main.add(new HTML("<div>" + result.get(i) + "</div><br/>"));
								}
							}
						});
					}

					private void getIssuesForKeyAndStatus(
							final VerticalPanel main) {
						ServiceManager.getTwowireServiceInstance().retrieveIssues(user, key, status, new AsyncCallback<List<String>>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Error Loading Pending Issues: " + caught.getMessage());
							}

							@Override
							public void onSuccess(List<String> result) {
								for(int i=0; i<result.size(); i++) {
									main.add(new HTML("<div>" + result.get(i) + "</div><br/>"));
								}
							}
						});
					}
				};
			}


			
		});
		HorizontalPanel searchBoxPanel = new HorizontalPanel();
		searchBoxPanel.add(searchBox);
		searchBoxPanel.add(searchButton);
		DisclosurePanel advancedSearch = new DisclosurePanel("Advanced Search");
		advancedSearch.setContent(setAdvancedSearch());
		searchPanel.add(searchBoxPanel);
		searchPanel.add(advancedSearch);
	}
	
	private int convertStatusToInt(String status) {
		if(status.equalsIgnoreCase("open")) {
			return 1;
		}
		return 0;
	}

	public VerticalPanel getSearchPanel() {
		return searchPanel;
	}

	public VerticalPanel setAdvancedSearch() {
		VerticalPanel advancedSearchPanel = new VerticalPanel();
		advancedSearchPanel.add(createKeySearch());
		advancedSearchPanel.add(createStatusPanel());
		return advancedSearchPanel;
	}

	private VerticalPanel createKeySearch() {
		VerticalPanel keySearch = new VerticalPanel();
		HTML searchTypeHeader = new HTML();
		searchTypeHeader.setText("By Key:");
		issueKey = new TextBox();
		issueKey.setWidth("60");
		keySearch.add(searchTypeHeader);
		keySearch.add(issueKey);
		return keySearch;
	}

	private VerticalPanel createStatusPanel() {
		VerticalPanel statusPanel = new VerticalPanel();
		HTML statusHeader = new HTML();
		statusBox.addItem("Open");
		statusBox.addItem("Closed");
		statusBox.addItem("Both");
		statusHeader.setText("By Status: ");
		statusPanel.add(statusHeader);
		statusPanel.add(statusBox);
		return statusPanel;
	}

}
