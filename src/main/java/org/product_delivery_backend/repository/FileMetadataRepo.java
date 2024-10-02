package org.product_delivery_backend.repository;

import org.product_delivery_backend.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileMetadataRepo extends JpaRepository<FileMetadata, UUID>
{
        Optional<FileMetadata> findByFileName(String fileName);
}
