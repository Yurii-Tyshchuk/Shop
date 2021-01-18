package ru.skillsad.sad.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillsad.sad.repository.UserRepo;

@Service
public class UserSecurityService  implements UserDetailsService {
    private final UserRepo userRepo;

    public UserSecurityService(UserRepo userRepo) {this.userRepo = userRepo;}

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByName(s);
    }
}
