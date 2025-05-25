package com.pruebatecnica.carlosabdala.controladores;

import com.pruebatecnica.carlosabdala.logica.CatImagesLogic;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/catImages")
public class CatImagesController {

    private final CatImagesLogic catImagesLogic;
    public CatImagesController(CatImagesLogic catImagesLogic) {
        this.catImagesLogic = catImagesLogic;
    }

    @GetMapping(path="/registerImage")
    public ResponseEntity<?> registerImage(){
        return catImagesLogic.registerImage();
    }

    @GetMapping(path="/countImages")
    public ResponseEntity<?> countImages(){
        return catImagesLogic.uniqueRegistersCount();
    }

}
