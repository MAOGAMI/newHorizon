package pl.mkpk.newhorizon.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Enter correct Email")
    @NotEmpty(message = "*Enter an Email")
    private String email;

    @Length(min = 8, message = "*Password length should be at least 8 characters long")
    @NotEmpty(message = "*Enter password")
    private String password;

    private String name;

    private String lastName;

    private int age;

    private int height;

    private int weight;

    private int active;

    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;
}
