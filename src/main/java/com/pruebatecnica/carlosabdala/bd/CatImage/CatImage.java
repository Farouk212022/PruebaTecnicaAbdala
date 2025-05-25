package com.pruebatecnica.carlosabdala.bd.CatImage;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cat_images")
public class CatImage {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private Long idImage;

    @Column(name="binaryImage", columnDefinition = "LONGBLOB")
    private byte[] binaryImage;

    @Column(name="creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name="last_call_date_time")
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

}
