package com.bellavista.bellavista_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bellavista.bellavista_backend.entities.Ospite;
import java.util.UUID;

public interface OspiteRepository extends JpaRepository<Ospite, UUID> {
}