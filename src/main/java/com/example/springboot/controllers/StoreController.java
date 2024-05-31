package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.dtos.StoreRecordDto;
import com.example.springboot.models.StoreModel;
import com.example.springboot.repositories.StoreRepository;
import com.example.springboot.utils.CryptoManager;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    StoreRepository storeRepository;


    @PostMapping("/stores")
    ResponseEntity<StoreModel> createEstabelecimento(@RequestBody @Valid StoreRecordDto storeRecordDto){
        StoreModel storeModel = new StoreModel();
        BeanUtils.copyProperties(storeRecordDto, storeModel);

        String[] hashPasswordAndSalt = CryptoManager.encryptPassword(storeRecordDto.password());
        storeModel.setPasswordSalt(hashPasswordAndSalt[1]);
        storeModel.setPassword(hashPasswordAndSalt[0]);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeRepository.save(storeModel));
    }

    @GetMapping("/stores")
    ResponseEntity<List<StoreModel>> getAllEstabelecimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(storeRepository.findAll());
    }

    @GetMapping("/stores/{id}")
    ResponseEntity<Object> getOneEstabelecimento(@PathVariable(value = "id") UUID id){
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeO.get());
    }

    @PutMapping("/stores/{id}")
    ResponseEntity<Object> updateEstabelecimento(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid StoreRecordDto storeRecordDto){
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento not found!");
        }
        StoreModel store = storeO.get();
        BeanUtils.copyProperties(storeRecordDto, store);
        return ResponseEntity.status(HttpStatus.OK).body(storeRepository.save(store));

    }

    @DeleteMapping("/stores/{id}")
    ResponseEntity<Object> deleteEstabelecimento(@PathVariable(value = "id")UUID id){
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento not found!");
        }
        storeRepository.delete(storeO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Estabelecimento deleted!");

    }


}
