package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {


    private String username;
    private String password;
    @Id
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {

    }
}
