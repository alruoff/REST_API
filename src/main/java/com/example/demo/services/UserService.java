package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = (Optional<User>) findByUsername(username);

        if (userOpt.isPresent()) { // если ссылка не null
            return new org.springframework.security.core.userdetails.User(
                    userOpt.get().getLogin(),
                    userOpt.get().getPassword(),
                    mapRolesToAuthorities(userOpt.get().getRoles()) );
        } else return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String userName) {

        return userRepository.findByLogin(userName);
    }

    public Optional<User> findByUserId(Long userId) {

        return userRepository.findById(userId);
    }
    public void removeUser(User usr) {

        userRepository.delete(usr);
    }
}