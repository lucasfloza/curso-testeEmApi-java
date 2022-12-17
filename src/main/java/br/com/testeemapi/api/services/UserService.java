package br.com.testeemapi.api.services;

import br.com.testeemapi.api.domain.User;
import br.com.testeemapi.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO obj);
}
