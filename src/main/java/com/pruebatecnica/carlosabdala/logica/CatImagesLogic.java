package com.pruebatecnica.carlosabdala.logica;

import com.pruebatecnica.carlosabdala.bd.CatImage.CatImage;
import com.pruebatecnica.carlosabdala.bd.CatImage.CatImageRepository;

import com.pruebatecnica.carlosabdala.utils.apiConnection.CatApiConnnection;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class CatImagesLogic {

    private static final Logger logger = LoggerFactory.getLogger(CatImagesLogic.class);

    private final CatImageRepository catImageRepository;
    private final CatApiConnnection catApiConnnection;

    public CatImagesLogic(CatImageRepository catImageRepository, CatApiConnnection catApiConnnection){
        this.catImageRepository=catImageRepository;
        this.catApiConnnection = catApiConnnection;
    }

    public ResponseEntity<?> registerImage() {
        try {
            BufferedImage apiResponse = catApiConnnection.recoverImageFromApi();
            byte[] imageBytes = convertToBytes(apiResponse);
            validateRepeatedRegister(imageBytes);
            return setResponse(imageBytes);

        } catch (Exception e) {
            logger.error("Ha ocurrido un error al traer la imagen desde la API",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al traer la imagen desde la API");
        }
    }

    public ResponseEntity<?> uniqueRegistersCount(){
        try {
            Long numberOfRegisters = obtainNumberOfRegisters();
            return ResponseEntity.status(HttpStatus.OK).body("Imágenes guardadas: "+numberOfRegisters);
        } catch (Exception e){
            logger.error("Ha ocurrido un error al traer la imagen desde la API",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al traer la imagen desde la API");
        }
    }

    private byte[] convertToBytes(BufferedImage response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        ImageIO.write(response,"jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private ResponseEntity<?> setResponse(byte[] imageBytes){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }

    private void validateRepeatedRegister(byte[] imageBytes){
        List<CatImage> queryResults = catImageRepository.findCatImageByBinary(imageBytes);
        if (queryResults.isEmpty()){
            saveDataBaseRegister(imageBytes);
        } else{
            updateDataBaseRegister(queryResults.get(0));
        }
    }

    private void saveDataBaseRegister(byte[] imageBytes){
        CatImage catImage = new CatImage();
        catImage.setBinaryImage(imageBytes);
        catImage.setCreationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        catImage.setLastCallDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        catImageRepository.save(catImage);
    }

    private void updateDataBaseRegister(CatImage register){
        register.setLastCallDateTime(LocalDateTime.now());
        catImageRepository.save(register);
    }

    private Long obtainNumberOfRegisters(){
        return catImageRepository.count();
    }
}
