package com.codegym.model.dto;

import java.util.Collection;

public class AlbumDto {
    private Long id;
    private String name;

    Collection<SongDto> songDtos;

    public AlbumDto() {
    }

    public AlbumDto(Long id, String name, Collection<SongDto> songDtos) {
        this.id = id;
        this.name = name;
        this.songDtos = songDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SongDto> getSongDtos() {
        return songDtos;
    }

    public void setSongDtos(Collection<SongDto> songDtos) {
        this.songDtos = songDtos;
    }
}
