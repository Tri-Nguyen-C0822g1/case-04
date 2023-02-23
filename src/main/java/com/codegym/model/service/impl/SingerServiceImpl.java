package com.codegym.model.service.impl;

import com.codegym.model.dto.CategoryDto;
import com.codegym.model.dto.SingerDto;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.Singer;
import com.codegym.model.repository.CategoryRepositoty;
import com.codegym.model.repository.SingerRepository;
import com.codegym.model.service.CategoryService;
import com.codegym.model.service.SingerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<SingerDto> findAll() {
        Iterable<Singer> entities = singerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(),true)
                .map(entity -> modelMapper.map(entity, SingerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SingerDto> findById(Long id) {
        Singer entity = singerRepository.findById(id).orElse(new Singer());
        return Optional.of(modelMapper.map(entity, SingerDto.class));
    }

    @Override
    public void save(SingerDto singerDto) {
        Singer singer = modelMapper.map(singerDto, Singer.class);
        singerRepository.save(singer);
    }

    @Override
    public void remove(Long id) {
        singerRepository.deleteById(id);
    }
}
