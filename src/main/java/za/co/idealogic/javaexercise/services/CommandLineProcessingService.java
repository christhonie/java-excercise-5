package za.co.idealogic.javaexercise.services;

import java.time.Instant;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandLineProcessingService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersistenceService persistenceService;
	
	public void process(String... args) {
		final Options options = new Options();

        Option countOption = Option.builder("m")
        		.required(false)
        		.longOpt( "message" )
        		.hasArg()
        		.argName("message")
                .desc("The message to add to the database. Use quotes to enclose strings with spaces.")
                .build();
        options.addOption(countOption);

        Option displayOption = Option.builder("d")
        		.required(false)
        		.longOpt( "display" )
                .desc("Use this flag to display all messages.")
                .build();
        options.addOption(displayOption);

        //We need this for running in the IDE. Otherwise you sometimes get an exception.
        Option springOutputAnsiEnabledOption = Option.builder()
        		.required(false)
        		.longOpt("spring.output.ansi.enabled")
        		.hasArg()
        		.build();
        options.addOption(springOutputAnsiEnabledOption);
        
        final CommandLineParser parser = new DefaultParser();
        final HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            log.error(e.getMessage());
            formatter.printHelp("Message storage application", options);

            System.exit(1);
            return;
        }
        
        boolean paramProcessed = false;

        if (cmd.hasOption('m')) {
        	paramProcessed = true;
        	final String message = cmd.getOptionValue("message");
        	final Instant now = Instant.now();
        	if(persistenceService.addMessage(message, now)) {
            	log.info("Message added @ {}", now.toString());
        	} else {
        		log.error("Something went wrong. We could not add the message!");
        	}
        }

        if (cmd.hasOption('d')) {
        	paramProcessed = true;
        	List<String> messages = persistenceService.getMessagesTheFancyWay();
        	if(messages != null && messages.size() > 0) {
            	log.info("The current messages in your database are:");
        		for(String message : messages) {
        			log.info("  {}", message); //Spaces are used to indent the output
        		}
        	} else {
            	log.info("There are no messages to display. Add some first.");
        	}
        }

        if(!paramProcessed) {
        	log.info("No parameters provided. Nothing to do.");
        }
	}
}
