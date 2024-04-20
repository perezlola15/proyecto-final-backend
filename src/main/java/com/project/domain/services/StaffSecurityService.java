package com.project.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.StaffRepository;
import com.project.persistence.entities.Staff;


@Service
public class StaffSecurityService implements UserDetailsService {

	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Obtener el usuario desde bbdd
		Staff staff = this.staffRepository.getByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no se ha encontrado"));
		
		String roles[] = new String[staff.getRoles().size()]; 
		
		for (int i = 0; i < roles.length; i++) {
			roles[i] = staff.getRoles().get(i).getRole();
		}
		
		return User.builder()
				.username(staff.getUsername())
				.password(staff.getPassword())
				.roles(roles)
				.disabled(staff.isDisabled())
				.accountLocked(staff.isLocked())
				.build();
	}
	
}
