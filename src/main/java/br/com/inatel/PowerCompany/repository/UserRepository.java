package br.com.inatel.PowerCompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.PowerCompany.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
}
