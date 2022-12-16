package br.com.testeemapi.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Data // gera os métodos EqualsAndHashCode, ToString, Setter, Getter
//@Getter // Lombok gera os métodos Getter de todos os atributos
//@Setter // Lombok gera os métodos Setter de todos os atributos
//@EqualsAndHashCode //Lombok gera o Equals e HashCode(De todos os atributos)
//@ToString
@AllArgsConstructor // Lombok gera um construtor com todos os parametros possiveis
@NoArgsConstructor // Lombok gera um construtor sem parametros
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)// se houver a criação de um email já salvo no banco, ele lançará uma exception
    private String email;
    private String password;


//      CÓDIGO BOILERPLATE
//
//    public User(){}
//    public User(Integer id, String name, String email, String password) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//    public Integer getId() {
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
