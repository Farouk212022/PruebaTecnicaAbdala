package com.pruebatecnica.carlosabdala.bd.CatImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CatImageRepository extends JpaRepository<CatImage, Long> {

    @Query(value = "SELECT * FROM  cat_images WHERE binary_image= :binary_image", nativeQuery = true)
    List<CatImage> findCatImageByBinary(@Param("binary_image") byte[] binaryImage);

}
