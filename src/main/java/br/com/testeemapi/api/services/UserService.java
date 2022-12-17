package br.com.testeemapi.api.services;

import br.com.testeemapi.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
