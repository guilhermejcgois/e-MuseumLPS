package com.lpsmuseum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

public class Main {
	
	private static Map<String, Boolean> features = new HashMap<String, Boolean>();
	private static Options options = new Options();
	
	private static void verifyFeatures() {
	}
	
	private static void createOptions() {
		// options.addOption(cmd, hasArgs, decription);
		
		options.addOption(ActionOption.builder("exit")
				.desc("Exit the program.")
				.build());
	}
	
	private static void verifyOption(CommandLine line) throws ParseException {
		if (line == null) {
			continue;
		} else if (line.hasOption("exit")) {
			goAhead = false;
		}
	}
	
	public static void main(String[] args) {
		boolean goAhead = true;
		
		verifyFeatures();
		createOptions();
		
		CommandLineParser parser = new DefaultParser();
		Scanner scanner = new Scanner(System.in);
		
		while (goAhead) {
			try {
				System.out.print(">> ");
				String cmds = scanner.nextLine();
				
				if (cmds.isEmpty())
					continue;
				
				cmds = "-".concat(cmds);
				verifyOption(parser.parse(options, cmds.split(" ")));
			} catch (ParseException exception) {
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
		}
		
		System.out.println("Good bye!!");
	}

}
