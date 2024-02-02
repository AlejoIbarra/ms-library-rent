package com.library.msalquiler.repository;

import com.library.msalquiler.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Client entities.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Retrieves a Client by its identity.
     *
     * @param identity The identity of the Client to retrieve.
     * @return An Optional containing the Client, or empty if not found.
     */
    Optional<Client> findByIdentity(String identity);

    /**
     * Retrieves a Client by its first name.
     *
     * @param firstName The first name of the Client to retrieve.
     * @return An Optional containing the Client, or empty if not found.
     */
    Optional<Client> findByFirstName(String firstName);

    /**
     * Retrieves a list of Clients by matching either the first name or last name (case-insensitive).
     *
     * @param firstName The first name to search for (case-insensitive).
     * @param lastName  The last name to search for (case-insensitive).
     * @return A list of Clients matching the search criteria.
     */
    List<Client> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);
}
