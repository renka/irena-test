package com.irena.backend.repos;

import com.irena.backend.entities.BooksStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksStorageRepository extends JpaRepository<BooksStorage, Long> {
    Optional<BooksStorage> findByBook_Id(long id);
}
