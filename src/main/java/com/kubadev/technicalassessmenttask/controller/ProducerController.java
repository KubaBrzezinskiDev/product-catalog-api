package com.kubadev.technicalassessmenttask.controller;

import com.kubadev.technicalassessmenttask.dto.ProducerDto;
import com.kubadev.technicalassessmenttask.dto.ProducerRequestDto;
import com.kubadev.technicalassessmenttask.service.ProducerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerDto> getProducer(@PathVariable Long id){
        ProducerDto producer = producerService.getProducer(id);
        return ResponseEntity.ok(producer);
    }

    @GetMapping()
    public ResponseEntity<List<ProducerDto>> getAll() {
        List<ProducerDto> producers = producerService.getAllProducers();
        return ResponseEntity.ok(producers);
    }

    @PostMapping()
    public ResponseEntity<Void> addProducer(@Valid @RequestBody ProducerRequestDto producerRequestDto) {
        producerService.addProducer(producerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProducer(@PathVariable Long id,@Valid @RequestBody ProducerRequestDto producerRequestDto) {
        producerService.updateProducer(id, producerRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
        return ResponseEntity.noContent().build();
    }


}
