package com.twowire.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class Filter {
	
	public List<HashMap> filterIssuesByStatus(List<HashMap> issues, final int status) {
		final Predicate<HashMap> matchingStatus =  new Predicate<HashMap>() {

			@Override
			public boolean apply(HashMap issue) {
				return issue.get("status").toString().equals(String.valueOf(status));
			}
			
		};
		return ImmutableList.copyOf(Iterables.filter(issues, matchingStatus));
	}
	
	public List<HashMap> filterIssuesByUser(List<HashMap> issues, final String user) {
		final Predicate<HashMap> matchingStatus =  new Predicate<HashMap>() {

			@Override
			public boolean apply(HashMap issue) {
				return issue.get("reporter").equals(user); 
			}
			
		};
		return ImmutableList.copyOf(Iterables.filter(issues, matchingStatus));
	}

	public List<HashMap> filterIssuesBySearchString(List<HashMap> issues, String regex) {
		final Pattern pattern = Pattern.compile(regex);
		final Predicate<HashMap> matchingStatus =  new Predicate<HashMap>() {

			@Override
			public boolean apply(HashMap issue) {
				Matcher matcher = pattern.matcher(parseValuesOnly(issue));
				return matcher.find();
			}

			
			
		};
		return ImmutableList.copyOf(Iterables.filter(issues, matchingStatus));
	}
	
	private String parseValuesOnly(HashMap issue) {
		String result = "";
		Collection c = issue.values();
		for(Iterator it = c.iterator(); it.hasNext();) {
			result += it.next();
		}
		return result;
	}
}
