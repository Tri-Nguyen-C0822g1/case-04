package com.codegym.model.service;

import com.codegym.model.dto.AlbumDto;
import com.codegym.model.dto.RoleDto;
import com.codegym.model.dto.SongDto;
import com.codegym.model.dto.UserDto;
import com.codegym.model.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService extends GeneralService<SongDto>{
    Iterable<SongDto> findAllByAlbum(Optional<AlbumDto> albumDto);
    Page<SongDto> findAll(Pageable pageable);
    Page<SongDto> findAllByNameContaining(String name, Pageable pageable);
//    Page<SongDto> findAllByAlbumContaining(Long id, Pageable pageable);

    boolean isAuthenticated();
    Page<SongDto> findAllSongByAlbumId(Long id, Pageable pageable);
}
