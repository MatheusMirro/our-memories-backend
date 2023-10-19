package br.com.matheusmirro.ourmemories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.matheusmirro.ourmemories.repository.user.IUserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    IUserRepository respository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return respository.findByUsername(username);
    }
}
