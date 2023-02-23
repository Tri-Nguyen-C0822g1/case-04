package com.codegym.model.dto;

import com.codegym.model.entity.Album;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.Singer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.Serializable;

public class SongRequest implements Serializable {
    private Long id;
    private String name;
    private Singer singer;
    private Category category;
    private Album album;
    private String image;
    private MultipartFile file;

    public SongRequest(Long id, String name, Singer singer, Category category, Album album, String image, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.album = album;
        this.image = image;
        this.file = file;
    }

    public SongRequest() {
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

    public MultipartFile  getFile() {
        return file;
    }

    public void setFile(MultipartFile   file) {
        this.file = file;
    }
}
