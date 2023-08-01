package com.frenzy.carrentapi.models.users;

import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.models.cars.Car;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name="User")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="plan")
    private Plan plan;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="cpf")
    private String cpf;
    @Column(name="date_of_birth")
    private LocalDateTime dateOfBirth;
    @Column(name="reputation")
    private double reputation;

    @OneToMany(mappedBy = "host",  cascade = CascadeType.ALL)
    private List<Car> carList = new ArrayList<>();

    private boolean active;

    public User(RegisterUserDTO userDTO) {
        this.active = true;
        this.reputation = 5;
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.plan = Plan.STANDART;
        this.dateOfBirth = userDTO.dateOfBirth();
        this.cpf = userDTO.cpf();
        this.email = userDTO.email();
        this.password = userDTO.password();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
