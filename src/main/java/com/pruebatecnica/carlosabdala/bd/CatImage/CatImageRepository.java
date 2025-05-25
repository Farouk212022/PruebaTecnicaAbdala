package com.pruebatecnica.carlosabdala.bd.CatImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatImageRepository extends JpaRepository<CatImage, Long> {

    @Query(value = "SELECT * FROM  cat_images WHERE binary_image= :binary_image", nativeQuery = true)
    List<CatImage> findCatImageByBinary(@Param("binary_image") byte[] binaryImage);

}
