package br.com.testeemapi.api.services.impl;

import br.com.testeemapi.api.domain.User;
import br.com.testeemapi.api.domain.dto.UserDTO;
import br.com.testeemapi.api.repositories.UserRepository;
import br.com.testeemapi.api.services.exceptions.DataIntegratyViolationException;
import br.com.testeemapi.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final String EMAIL = "antonio.jj@gmail.com";
    public static final String PASSWORD = "123456";
    public static final String NAME = "Antônio JJ";
    public static final Integer ID = 3;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";


    @InjectMocks //real instance
    private UserServiceImpl userServiceImpl;

    @Mock //false instance
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt()))
                .thenReturn(optionalUser);

        User response = userServiceImpl.findById(ID);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            userServiceImpl.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());

        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userServiceImpl.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(User.class,response.get(0).getClass());
        }

    @Test
    void whenCreateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);


        User response = userServiceImpl.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }
        catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }
    void whenUpdateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userServiceImpl.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            userServiceImpl.update(userDTO);
        }
        catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}