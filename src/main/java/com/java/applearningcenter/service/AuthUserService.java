package com.java.applearningcenter.service;

import com.java.applearningcenter.repository.UserRepository;
import com.java.applearningcenter.dto.AuthUserDto;
import com.java.applearningcenter.entity.authuser.AuthUser;
import com.java.applearningcenter.enums.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = userRepository
                .getAuthUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        System.out.println("authUser = " + authUser);
        return new User(authUser.getUsername(),authUser.getPassword(), authUser.getAuthorities());
    }

    public void create(AuthUserDto userDto) {

        AuthUser authUser = mapper.map(userDto, AuthUser.class);
        authUser.setRole(Role.MENTOR);
        userRepository.save(authUser);
    }


    public List<AuthUser> getAllMentors() {
        List<AuthUser> allUsers = userRepository.findAll();

//        return allUsers.stream().filter(authUser -> authUser.getRole().toString().equals("MENTOR")).toList();

        return allUsers;


    }

    public void mentorDeleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public AuthUser getById(Integer id) {
        return userRepository.findById(id).get();
    }

    public void update(Integer id, AuthUserDto authUserDto) {
        AuthUser authUser = userRepository.findById(id).get();
        authUser.setName(authUserDto.getName());
        authUser.setUsername(authUserDto.getUsername());
        authUser.setEmail(authUserDto.getEmail());
        authUser.setPassword(authUserDto.getPassword());
        authUser.setPhone(authUserDto.getPhone());

        userRepository.save(authUser);
    }
}
