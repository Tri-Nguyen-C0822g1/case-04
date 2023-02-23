package com.codegym.model.service.impl;

import com.codegym.model.dto.AlbumDto;
import com.codegym.model.entity.Album;
import com.codegym.model.repository.AlbumRepository;
import com.codegym.model.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<AlbumDto> findAll() {
        Iterable<Album> entities = albumRepository.findAll();
        return StreamSupport.stream(entities.spliterator(),true)
                .map(entity -> modelMapper.map(entity, AlbumDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AlbumDto> findById(Long id) {
        Album entity = albumRepository.findById(id).orElse(new Album());
        return Optional.of(modelMapper.map(entity, AlbumDto.class));
    }

    @Override
    public void save(AlbumDto albumDto) {
    Album album = modelMapper.map(albumDto, Album.class);
    albumRepository.save(album);
    }

    @Override
    public void remove(Long id) {
        albumRepository.deleteById(id);
    }
}
