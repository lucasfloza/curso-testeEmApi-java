package br.com.testeemapi.api.services;

import br.com.testeemapi.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
