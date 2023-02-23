package com.codegym.model.service;

import com.codegym.model.dto.RoleDto;
import com.codegym.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends GeneralService<UserDto> {
    Iterable<UserDto> findAllByRole(RoleDto roleDto);
    Iterable<UserDto> findAll();
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findAllByFullNameContaining(String fullName, Pageable pageable);

    boolean isAuthenticated();
}
