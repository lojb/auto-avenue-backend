package com.codecool.autoavenue;

import com.codecool.autoavenue.model.Message;
import com.codecool.autoavenue.service.DAO.MessageDAO;
import com.codecool.autoavenue.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Mock
    private MessageDAO messageDAO;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMessages() {
        // Arrange
        List<Message> messages = Arrays.asList(
                mock(Message.class),
                mock(Message.class)
        );
        when(messageDAO.findAll()).thenReturn(messages);

        // Act
        List<Message> result = messageService.getAllMessages();

        // Assert
        assertEquals(messages, result);
        verify(messageDAO, times(1)).findAll();
    }

    @Test
    public void testGetMessageById() {
        // Arrange
        Message message = mock(Message.class);
        when(messageDAO.findById(1L)).thenReturn(Optional.of(message));

        // Act
        Message result = messageService.getMessageById(1L);

        // Assert
        assertEquals(message, result);
        verify(messageDAO, times(1)).findById(1L);
    }

    @Test
    public void testAddMessage() {
        // Arrange
        Message message = mock(Message.class);

        // Act
        messageService.addMessage(message);

        // Assert
        verify(messageDAO, times(1)).save(message);
    }

    @Test
    public void testUpdateMessageById() {
        // Arrange
        Message existingMessage = mock(Message.class);
        Message updatedMessage = mock(Message.class);
        when(messageDAO.findById(1L)).thenReturn(Optional.of(existingMessage));

        // Act
        messageService.updateMessageById(1L, updatedMessage);

        // Assert
        verify(existingMessage, times(1)).setMessage(updatedMessage.getMessage());
        verify(messageDAO, times(1)).save(existingMessage);
    }

    @Test
    public void testDeleteMessageById() {
        // Arrange

        // Act
        messageService.deleteMessageById(1L);

        // Assert
        verify(messageDAO, times(1)).deleteById(1L);
    }
}



