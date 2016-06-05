package com.lpsmuseum;

import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

public class ParserCommandLine extends DefaultParser {
	@Override
	public CommandLine parse(Options options, String[] arguments) throws ParseException {
		try {
			return super.parse(options, arguments);
		} catch (UnrecognizedOptionException exception) {
			System.out.println();
			System.out.println("<feature> <action>");
			for (Option option : options.getOptions()) {
				System.out.print("    " + option.getOpt());
				if (option instanceof ActionOption) {
					Object actions[] = ((ActionOption) option).getActionArray();
					if (actions.length > 0)
						System.out.print("\t" + actions[0]);
					for (int i = 1; i < actions.length; i++)
						System.out.print("|" + actions[i]);
				}
				System.out.println("\t" + option.getDescription());
			}
			System.out.println();
		}
		
		return null;
	}
}