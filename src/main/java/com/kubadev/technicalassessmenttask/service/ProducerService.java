package com.kubadev.technicalassessmenttask.service;

import com.kubadev.technicalassessmenttask.dto.ProducerDto;
import com.kubadev.technicalassessmenttask.dto.ProducerRequestDto;
import com.kubadev.technicalassessmenttask.mapper.ProducerMapper;
import com.kubadev.technicalassessmenttask.model.Producer;
import com.kubadev.technicalassessmenttask.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;


    public ProducerService(ProducerRepository producerRepository, ProducerMapper producerMapper) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
    }

    public ProducerDto getProducer(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producer not found"));
        return producerMapper.mapToDto(producer);
    }

    public List<ProducerDto> getAllProducers() {
        List<Producer> producers = producerRepository.findAll();
        return producerMapper.mapToDtos(producers);
    }

    public void addProducer(ProducerRequestDto producerRequestDto) {
        Producer producer = producerMapper.mapToCreateProducer(producerRequestDto);
        producerRepository.save(producer);
    }

    public void updateProducer(Long id, ProducerRequestDto producerRequestDto) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producer not found"));
        producerMapper.mapToUpdateProducer(producer, producerRequestDto);
        producerRepository.save(producer);
    }

    public void deleteProducer(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producer not found"));
        producerRepository.delete(producer);
    }
}
