package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.Message;
import com.codecool.autoavenue.service.DAO.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;

    public List<Message> getAllMessages() {
        return messageDAO.findAll();
    }

    public Message getMessageById(Long id) {
        return messageDAO.findById(id).get();
    }

    public void addMessage(Message message) {
        messageDAO.save(message);
    }

    public void updateMessageById(Long id, Message message) {
        Message messageToUpdate = getMessageById(id);

        messageToUpdate.setMessage(message.getMessage());

        messageDAO.save(messageToUpdate);
    }

    public void deleteMessageById(Long id) {
        messageDAO.deleteById(id);
    }
}
