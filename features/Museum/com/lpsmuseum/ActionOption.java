package com.lpsmuseum;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.cli.Option;

public class ActionOption extends Option {
	
	private Set<String> actions = new HashSet<String>();
	
	public ActionOption(String opt, boolean hasArg, String description) throws IllegalArgumentException {
		super(opt, hasArg, description);
	}

	public ActionOption(String opt, String longOpt, boolean hasArg, String description)
			throws IllegalArgumentException {
		super(opt, longOpt, hasArg, description);
	}

	public ActionOption(String opt, String description) throws IllegalArgumentException {
		super(opt, description);
	}
	
	public void add(String action) {
		actions.add(action);
	}
	
	public boolean remove(String action) {
		return actions.remove(action);
	}
	
	public int numActions() {
		return actions.size();
	}
	
	public Object[] getActionArray() {
		return actions.toArray();
	}
	
	public boolean hasOptions() {
		return actions.size() > 0;
	}
	
	public boolean isValid(String action) {
		return actions.contains(action);
	}

}
	