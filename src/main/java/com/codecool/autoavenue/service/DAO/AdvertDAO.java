package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertDAO extends JpaRepository<Advert, Long> {
}
