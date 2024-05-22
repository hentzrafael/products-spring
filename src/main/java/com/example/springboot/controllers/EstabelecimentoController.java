package com.example.springboot.controllers;

import com.example.springboot.dtos.EstabelecimentoRecordDto;
import com.example.springboot.models.EstabelecimentoModel;
import com.example.springboot.repositories.EstabelecimentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EstabelecimentoController {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @PostMapping("/estabelecimentos")
    ResponseEntity<EstabelecimentoModel> createEstabelecimento(@RequestBody @Valid EstabelecimentoRecordDto estabelecimentoRecordDto){
        EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();
        BeanUtils.copyProperties(estabelecimentoRecordDto, estabelecimentoModel);

        String salt = BCrypt.gensalt();
        estabelecimentoModel.setPasswordSalt(salt);
        String passwordEncrypted = BCrypt.hashpw(estabelecimentoRecordDto.password(),salt);
        estabelecimentoModel.setPassword(passwordEncrypted);

        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoRepository.save(estabelecimentoModel));

    }

    @GetMapping("/estabelecimentos")
    ResponseEntity<List<EstabelecimentoModel>> getAllEstabelecimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoRepository.findAll());
    }

    @GetMapping("/estabelecimentos/{id}")
    ResponseEntity<Object> getOneEstabelecimento(@RequestParam(value = "id") UUID id){
        Optional<EstabelecimentoModel> estabelecimentoO = estabelecimentoRepository.findById(id);
        if (estabelecimentoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoO.get());

    }


}
