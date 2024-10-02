package org.product_delivery_backend.repository;

import org.product_delivery_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

 Optional<User> findUserByEmail(String email);
 Optional<User> findById(Long userId);



}
