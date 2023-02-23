package com.codegym.model.service.impl;

import com.codegym.model.dto.AlbumDto;
import com.codegym.model.dto.SongDto;
import com.codegym.model.entity.Song;
import com.codegym.model.repository.SongRepository;
import com.codegym.model.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<SongDto> findAllByAlbum(Optional<AlbumDto> albumDto) {
        Iterable<Song> entities = songRepository.findAllByAlbum(albumDto);
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, SongDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<SongDto> findAll() {
        Iterable<Song> entities = songRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, SongDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<SongDto> findAll(Pageable pageable) {
        List<Song> entities = songRepository.findAll();
        List<SongDto> dtos = entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, SongDto.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(dtos);
    }

    @Override
    public Page<SongDto> findAllByNameContaining(String name, Pageable pageable) {
        Page<Song> entities = songRepository.findAllByNameContaining( name,pageable);
        List<SongDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, SongDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos);
    }


    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
        || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    public Page<SongDto> findAllSongByAlbumId(Long id, Pageable pageable) {
        List<Song> entities = songRepository.findAllSongByAlbumId(id);
        List<SongDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, SongDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos);
    }

    @Override
    public Optional<SongDto> findById(Long id) {
        Song song = songRepository.findById(id).orElse(new Song());
        return Optional.of(modelMapper.map(song, SongDto.class));
    }

    @Override
    public void save(SongDto songDto) {
        Song song = modelMapper.map(songDto, Song.class);
        songRepository.save(song);
    }

    @Override
    public void remove(Long id) {songRepository.deleteById(id);

    }
}
