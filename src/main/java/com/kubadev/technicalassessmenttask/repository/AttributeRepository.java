package com.kubadev.technicalassessmenttask.repository;

import com.kubadev.technicalassessmenttask.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
