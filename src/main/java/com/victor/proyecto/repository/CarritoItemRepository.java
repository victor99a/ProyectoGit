package com.victor.proyecto.repository;


import com.victor.proyecto.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> { }
