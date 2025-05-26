package com.pruebatecnica.carlosabdala.bd.CatImage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cat_images")
public class CatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImage;

    @Column(name = "binaryImage", columnDefinition = "LONGBLOB")
    private byte[] binaryImage;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "last_call_date_time")
    private LocalDateTime lastCallDateTime;

    public void setBinaryImage(byte[] binaryImage) {
        this.binaryImage = binaryImage;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setLastCallDateTime(LocalDateTime lastCallDateTime) {
        this.lastCallDateTime = lastCallDateTime;
    }

    public void setIdImage(Long idImage) { this.idImage = idImage; }

    @Override
    public String toString() {
        return "CatImage{" +
                "binaryImage=" + Arrays.toString(binaryImage) +
                ", creationDateTime=" + creationDateTime +
                ", lastCallDateTime=" + lastCallDateTime +
                '}';
    }
}
