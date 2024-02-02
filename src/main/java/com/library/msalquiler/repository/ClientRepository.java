package com.library.msalquiler.repository;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.repository.jpa.ClientJpaRepository;
import com.library.msalquiler.repository.utils.SearchCriteria;
import com.library.msalquiler.repository.utils.SearchOperation;
import com.library.msalquiler.repository.utils.SearchStatement;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository {

    private final ClientJpaRepository repository;

    public Client save(Client client) { return repository.save(client); }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findById(Long id) { return repository.findById(id); }

    public List<Client> search(String name, String identity) {
        SearchCriteria<Client> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(identity)) {
            spec.add(new SearchStatement("identity", identity, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }

    public Optional<Client> findByIdentity(String identity) { return repository.findByIdentity(identity); }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public void deleteById(Long id) { repository.deleteById(id); }
}