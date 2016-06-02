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

import com.lpsmuseum.dto.MuseologicalObject;
import com.lpsmuseum.service.MuseologicalObjectService;

public class Main {
	
	private static Map<String, Boolean> features = new HashMap<String, Boolean>();
	private static Options options = new Options();
	
	private static void verifyFeatures() {
		try {
			Class.forName("com.lpsmuseum.entity.MuseologicalObjectDO");
			features.put("object", true);
		} catch (ClassNotFoundException exception) {
			features.put("object", false);
		}
		try {
			Class.forName("com.lpsmuseum.entity.ImageDO");
			features.put("image", true);
		} catch (ClassNotFoundException exception) {
			features.put("image", false);
		}
		try {
			Class.forName("com.lpsmuseum.entity.TextDO");
			features.put("text", true);
		} catch (ClassNotFoundException exception) {
			features.put("text", false);
		}
	}
	
	private static void createOptions() {
		// options.addOption(cmd, hasArgs, decription);
		
		if (features.get("objetc")) {
			ActionOption object = new ActionOption("object", true, "Do an action with 'object' feature.");
			object.add("create");
			object.add("list");
			options.addOption(object);
		}
		/*options.addOption(ActionOption.builder("object")
				.hasArg().argName("create").argName("list")
				.desc("Do an action with 'object' feature.")
				.build());*/
		options.addOption(ActionOption.builder("exit")
				.desc("Exit the program.")
				.build());
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
				CommandLine line = parser.parse(options, cmds.split(" "));
				
				String action = null;
				if (line == null) {
					continue;
				} else if (line.hasOption("object")) {
					action = line.getOptionValue("object");
					ActionOption option = (ActionOption) options.getOption("object");
					
					if (!option.isValid(action))
						throw new ParseException("Invalid action for object feature");
					
					switch (action) {
					case "list":
						System.out.println();
						List<MuseologicalObject> objects = new MuseologicalObjectService().listObjects();
						System.out.println();
						for (MuseologicalObject object : objects)
							System.out.println(object.toString());
						break;
					}
				} else if (line.hasOption("exit")) {
					goAhead = false;
				}
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
