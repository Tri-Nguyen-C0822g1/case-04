package com.codegym.controller;

import com.codegym.model.dto.AlbumDto;
import com.codegym.model.dto.RoleDto;
import com.codegym.model.service.AlbumService;
import com.codegym.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/list")
    public ModelAndView listAlbums() {
        ModelAndView modelAndView = new ModelAndView("/album/list");
        Iterable<AlbumDto> albumDtos = albumService.findAll();
        modelAndView.addObject("albumDtos", albumDtos);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/album/create");
        modelAndView.addObject("albumDto", new AlbumDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveAlbum(@ModelAttribute("albumDto") AlbumDto albumDto) {
        albumService.save(albumDto);
        ModelAndView modelAndView = new ModelAndView("/album/create");
        modelAndView.addObject("albumDto", new AlbumDto());
        modelAndView.addObject("message", "New album created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<AlbumDto> albumDto = albumService.findById(id);
        if (albumDto.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/album/edit");
            modelAndView.addObject("albumDto", albumDto.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateAlbum(@ModelAttribute("albumDto") AlbumDto albumDto) {
        albumService.save(albumDto);
        ModelAndView modelAndView = new ModelAndView("/album/edit");
        modelAndView.addObject("albumDto", albumDto);
        modelAndView.addObject("message", "Album updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<AlbumDto> albumDto = albumService.findById(id);
        if (albumDto.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/album/delete");
            modelAndView.addObject("albumDto", albumDto.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteRole(@ModelAttribute("albumDto") AlbumDto albumDto) {
        albumService.remove(albumDto.getId());
        return "redirect:list";
    }
}