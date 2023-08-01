package com.frenzy.carrentapi.services;

import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.exceptions.BadRequestException;
import com.frenzy.carrentapi.models.users.User;
import com.frenzy.carrentapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterUserDTO data){
        boolean cpfRegistered = userRepository.existsByCpf(data.cpf());
        if(cpfRegistered){
            throw new BadRequestException(
                    "This CPF was already registered by an user."
            );
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(data.password());
        User user =new User(data);
        user.setPassword(encodedPass);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
