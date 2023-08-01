package com.frenzy.carrentapi.repositories;

import com.frenzy.carrentapi.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT EXISTS (SELECT 1 FROM User c WHERE c.cpf = :cpf)")
    Boolean existsByCpf(String cpf);

    UserDetails findByEmail(String email);
}
