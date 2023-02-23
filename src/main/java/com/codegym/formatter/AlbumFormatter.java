package com.codegym.formatter;

import com.codegym.model.dto.AlbumDto;
import com.codegym.model.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class AlbumFormatter implements Formatter<AlbumDto> {
    private AlbumService albumService;

    @Autowired
    public AlbumFormatter(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public AlbumDto parse(String text, Locale locale) throws ParseException {
        Optional<AlbumDto> roleDto = albumService.findById(Long.parseLong(text));
        return roleDto.orElse(null);
    }

    @Override
    public String print(AlbumDto object, Locale locale) {
        return "[" + object.getId() + ", "
                + object.getName() + "]";

    }
}
