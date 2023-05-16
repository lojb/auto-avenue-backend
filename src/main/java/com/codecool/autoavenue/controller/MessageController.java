package com.codecool.autoavenue.controller;

import com.codecool.autoavenue.model.Message;
import com.codecool.autoavenue.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable("id") Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public ResponseEntity<Void> addMessage(@RequestBody Message message){
        messageService.addMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMessageById(@PathVariable("id") Long id, @RequestBody Message updatedMessage){
        Message messageToUpdate = messageService.getMessageById(id);

        if (messageToUpdate != null) {
            messageService.updateMessageById(id, updatedMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable("id") Long id){
        Message messageToDelete = messageService.getMessageById(id);

        if (messageToDelete != null) {
            messageService.deleteMessageById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
