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
    @Email(message = "*Wprowadz pooprawny adres Email")
    @NotEmpty(message = "*Wprowadz adres Email")
    private String email;
    @Length(min = 5, message = "*Hasło powinno mieć minimum 5 znaków")
    @NotEmpty(message = "*Podaj hasło")
    private String password;
    private String name;
    private String lastName;
    private int active;
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;
}
