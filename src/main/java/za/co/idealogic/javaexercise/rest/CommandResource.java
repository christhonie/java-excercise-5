package za.co.idealogic.javaexercise.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.idealogic.javaexercise.services.PersistenceService;

@RestController
@RequestMapping("command")
public class CommandResource {
	
	@Autowired
	private PersistenceService persistenceService;

    @GetMapping(path = "/findAllText", produces = "text/plain;charset=UTF-8")
    public String findAllText() {
    	final String separator = System.getProperty("line.separator");
    	final StringBuilder sb = new StringBuilder();
        persistenceService.getMessagesTheFancyWay().stream()
        	.forEach(message -> sb.append(message).append(separator));
        return sb.toString();
    }
}
