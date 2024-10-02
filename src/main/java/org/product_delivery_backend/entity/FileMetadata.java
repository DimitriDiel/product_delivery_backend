package org.product_delivery_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "file_metadata")
public class FileMetadata {
        @Id
        //@GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private LocalDateTime ctime;
        private long size;

        @Column(unique = true, name = "file_name")
        @NotNull
        private String fileName;

        private String mimeType;
        private String keyword;
        }
