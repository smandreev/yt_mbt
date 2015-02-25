package ru.smandreev.ytmbt;

import org.apache.commons.cli.*;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey.Andreev on 25.02.2015.
 * e-mail: smandreev@gmail.com
 */

public class Ripper {
    public static void main(String[] args) throws ParseException, IOException {
        CommandLine line = getCommandLine(args);

        Map<String, Object> config = new HashMap<String, Object>();
        config.put("baseURL", line.getOptionValue("baseURL", "http://localhost"));
        config.put("login", line.getOptionValue("login", "root"));
        config.put("password", line.getOptionValue("pwd", "root"));


        if (config.keySet().size()>0) {
            for (String key : config.keySet()) {
                String value = config.get(key).toString();
                System.out.printf(key + ":\t" + value + "\n");
            }
            System.out.printf("\n");
        }

    }

    private static CommandLine getCommandLine(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(OptionBuilder.hasArg().withDescription("User Login").create("login"));
        options.addOption(OptionBuilder.hasArg().withDescription("User Password to login").create("pwd"));
        options.addOption(OptionBuilder.hasArg().withDescription("baseURL of youtrack").create("baseURL"));

        if (args.length <= 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(Console.class.getCanonicalName(), options);
        }

        // create the parser
        CommandLineParser parser = new BasicParser();
        return parser.parse(options, args);
    }


}
