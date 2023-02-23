package com.codegym.model.dto;

import com.codegym.model.entity.Album;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.Singer;

import java.io.Serializable;

public class SongDto implements Serializable {
    private Long id;
    private String name;
    private Singer singer;
    private Category category;
    private Album album;
    private String image;
    private String link;

    public SongDto(Long id, String name,
                   Singer singer, Category category,
                   Album album, String image, String link) {
        super();
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.album = album;
        this.image = image;
        this.link = link;
    }

    public SongDto(String name, Singer singer,
                   Category category, Album album,
                   String image, String link) {
        super();
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.album = album;
        this.image = image;
        this.link = link;
    }

    public SongDto() {
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

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
