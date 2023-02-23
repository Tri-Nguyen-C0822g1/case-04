package com.codegym.controller;


import com.codegym.model.dto.AlbumDto;
import com.codegym.model.dto.CategoryDto;
import com.codegym.model.dto.SingerDto;
import com.codegym.model.dto.SongDto;
import com.codegym.model.dto.SongRequest;
import com.codegym.model.dto.UserDto;
import com.codegym.model.entity.Album;
import com.codegym.model.service.AlbumService;
import com.codegym.model.service.CategoryService;
import com.codegym.model.service.SingerService;
import com.codegym.model.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/song")
public class SongController {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    SingerService singerService;

    @ModelAttribute("singerDtos")
    public Iterable<SingerDto> singerDtos() {
        return singerService.findAll();
    }


    @ModelAttribute("categoryDtos")
    public Iterable<CategoryDto> categoryDtos() {
        return categoryService.findAll();
    }

    @ModelAttribute("albumDtos")
    public Iterable<AlbumDto> albumDtos() {
        return albumService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listSong(@RequestParam(value = "search", required = false) Optional<String> search,
                                 Pageable pageable) {
        Page<SongDto> songDtos;
        if (search.isPresent()) {
            songDtos = songService.findAllByNameContaining(search.get(), pageable);
        } else {
            songDtos = songService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/song/list");
        modelAndView.addObject("songDtos", songDtos);
        return modelAndView;
    }

@GetMapping("/list/{id}")
public ModelAndView showListByAlbum(@PathVariable(value = "id") Long id, Pageable pageable) {
    Page<SongDto> songDtos = (Page<SongDto>) songService.findAllSongByAlbumId(id, pageable);
    ModelAndView modelAndView = new ModelAndView("/song/list");
    modelAndView.addObject("songDtos", songDtos);
    return modelAndView;
}
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/song/create");

        modelAndView.addObject("songDto", new SongDto());
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView saveSong(@ModelAttribute("songDto") SongRequest songRequest) throws IOException {


// Save file to memory
        MultipartFile multipartFile = songRequest.getFile();
        String fileName = multipartFile.getOriginalFilename();
        if (fileName.toLowerCase().endsWith(".mp3") || fileName.toLowerCase().endsWith(".wav") || fileName.toLowerCase().endsWith(".m4p")) {
            try {
                FileCopyUtils.copy(songRequest.getFile().getBytes(), new File(fileUpload + songRequest.getAlbum().getName() + " - " + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }


            SongDto songDto = new SongDto(songRequest.getId(), songRequest.getName()
                    , songRequest.getSinger(), songRequest.getCategory(), songRequest.getAlbum(),
                    songRequest.getImage(), songRequest.getAlbum().getName() + " - " + fileName);

// Save song to db
            songService.save(songDto);
            ModelAndView modelAndView = new ModelAndView("/song/create");
            modelAndView.addObject("songDto", new SongDto());
            modelAndView.addObject("message", "New song created successfully");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/song/create");
            modelAndView.addObject("message", "The application only accepts music files with the extension .mp3, .wav, .ogg, .m4p!!");
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<SongDto> songDto = songService.findById(id);
        ModelAndView modelAndView;
        if (songDto.isPresent()) {
            modelAndView = new ModelAndView("/song/edit");
            modelAndView.addObject("songDto", songDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateSong(@ModelAttribute("songDto") @Valid SongDto songDto) {
        songService.save(songDto);
        ModelAndView modelAndView = new ModelAndView("/song/edit");
        modelAndView.addObject("songDto", songDto);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<SongDto> songDto = songService.findById(id);
        ModelAndView modelAndView;
        if (songDto.isPresent()) {
            modelAndView = new ModelAndView("/song/delete");
            modelAndView.addObject("songDto", songDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("song") SongDto songDto) {
        songService.remove(songDto.getId());
        return "redirect:list";
    }
}