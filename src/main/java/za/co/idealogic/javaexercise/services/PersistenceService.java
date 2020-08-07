package za.co.idealogic.javaexercise.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.idealogic.javaexercise.domain.Message;
import za.co.idealogic.javaexercise.repository.MessageRepository;

@Service
@Transactional
public class PersistenceService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public boolean addMessage(String message, Instant datetime) {
		//Create the Message - New object
		Message msg = new Message();
		
		//Populate Message with data
		msg.setDateStamp(datetime);
		msg.setMessage(message);
		
		//Save the message
		try {
			messageRepository.save(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<String> getMessages(){
		List<String> result = new ArrayList<>();
		
		List<Message> messages = messageRepository.findAll();
		for(Message message : messages) {
			result.add("Message: " + message.getMessage() + " - " + message.getDateStamp().toString());
		}
		
		return result;
	}
	
	public List<String> getMessagesTheFancyWay(){
		return messageRepository.findAll().stream()
			.map(message -> {
				ZonedDateTime dt = message.getDateStamp().atZone(ZoneId.of("America/Los_Angeles"));
				return "Fancy message: " + message.getMessage() + " - " + dt.toString();
			})
			.collect(Collectors.toList());
	}
	
	private String anonymouse(Message message) {
		
		return "Message: " + message.getMessage() + " - " + message.getDateStamp().toString();
	}
}
