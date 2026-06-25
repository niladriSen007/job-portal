package com.niladri.userservice.security;

import com.niladri.userservice.entity.UserEntity;
import com.niladri.userservice.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Find the user in the database
        Optional<UserEntity> user = authRepository.findByEmail(username);

        //If user does not exists then throw UsernameNotFound Exception
        if (user.isEmpty()) {
            log.info("No user found with username: " + username);
            throw new UsernameNotFoundException("User with - " + username + " does not exist");
        }
        log.info("User with - " + username + " found from database");

        // Add authorities
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.get().getRole().name());
        Collection<GrantedAuthority> grantedAuthorities = Collections.singleton(grantedAuthority);

        return new UserInfoService(
                user.get(),
                grantedAuthorities
        );
    }
}
