package com.bellavista.bellavista_backend.repository;

import com.bellavista.bellavista_backend.entities.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CameraRepository extends JpaRepository <Camera, UUID> {
}
