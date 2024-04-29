package com.project.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.StaffRepository;
import com.project.persistence.entities.Staff;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository
            .getByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("El usuario con usuario " + username + " no existe"));

        return new UserDetailsImpl(staff);
    }
}
