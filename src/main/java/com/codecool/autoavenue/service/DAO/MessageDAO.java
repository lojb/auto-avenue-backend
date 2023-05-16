package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDAO extends JpaRepository<Message, Long> {
}
