package com.library.msalquiler.repository.jpa;

import com.library.msalquiler.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Optional<Client> findByIdentity(String identity);

    Optional<Client> findByName(String firstName);

}
