package com.library.msalquiler.repository;

import com.library.msalquiler.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {


    Optional<Client> findByIdentity(String identity);



    Optional<Client> findByfirstName(String firstName);


    List<Client> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);








}
