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
	private static MuseologicalObjectService service = new MuseologicalObjectService();
	
	private static void verifyFeatures() {
		try {
			Class.forName("com.lpsmuseum.entity.MuseologicalObjectDO");
			features.put("object", true);
		} catch (ClassNotFoundException exception) {
			features.put("object", false);
		}
		/*try {
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
		}*/
	}
	
	private static void createOptions() {
		// options.addOption(cmd, hasArgs, decription);
		
		if (features.get("object")) {
			ActionOption object = new ActionOption("object", true, "Do an action with 'object' feature.");
			object.add("create");
			object.add("list");
			options.addOption(object);
		} 
	}
	
	private static void verifyOption(CommandLine line) throws ParseException {
		String action = null;
		
		if (line.hasOption("object")) {
			action = line.getOptionValue("object");
			ActionOption option = (ActionOption) options.getOption("object");
			
			if (!option.isValid(action))
				throw new ParseException("Invalid action for object feature");
			
			if (action.equals("list")) {
				List<MuseologicalObject> objects = service.listObjects();
				for (MuseologicalObject object : objects)
					object.toString();
			}
		}
	}

}
