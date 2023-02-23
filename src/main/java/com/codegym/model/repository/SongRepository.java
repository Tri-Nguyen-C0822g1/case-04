package com.codegym.model.repository;


import com.codegym.model.dto.AlbumDto;
import com.codegym.model.dto.CategoryDto;
import com.codegym.model.dto.SingerDto;
import com.codegym.model.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Iterable<Song> findAllByAlbum(Optional<AlbumDto> albumDto);
    Iterable<Song> findAllBySinger(SingerDto singerDto);
    Iterable<Song> findAllByCategory(CategoryDto categoryDto);
    Page<Song>findAllByNameContaining(String name, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from songs where album_id = :id")
    List<Song> findAllSongByAlbumId(@Param(value = "id") Long id);

    @Query(nativeQuery = true,
            value = "SELECT a.name FROM albums a " +
                    "INNER JOIN songs s ON a.id = s.album_id " +
                    "WHERE s.name = :name")
    List<String> findAlbumBySongName(@Param("name") String name);
    @Query(nativeQuery = true,
    value =  "select * from songs where search like '%' ? '%';")
    Page <Song> findAllBySearch(@Param("search") String search, Pageable pageable);

}
