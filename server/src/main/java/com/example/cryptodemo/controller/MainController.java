package com.example.cryptodemo.controller;

import com.example.cryptodemo.model.MainRequestDTO;
import com.example.cryptodemo.model.MainResponseDTO;
import com.example.cryptodemo.service.CryptoService;
import com.example.cryptodemo.service.impl.AesCryptoServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/AES")
public class MainController {

    @PostMapping("/encrypt")
    public ResponseEntity<MainRequestDTO> encrypt(
            @RequestHeader("x-app-private-key") String privateKey,
            @RequestBody MainRequestDTO requestBody) throws Exception {

        String encryptedText = new AesCryptoServiceImpl()
                .init(privateKey)
                .encrypt(requestBody.getText());

        final MainRequestDTO mainResponse = MainRequestDTO
                .builder()
                .text(encryptedText)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(mainResponse);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<MainResponseDTO> decrypt(
            @RequestHeader("x-app-private-key") String privateKey,
            @RequestBody MainRequestDTO requestBody) throws Exception {

        CryptoService cryptoService = new AesCryptoServiceImpl().init(privateKey);

        String decryptedText = cryptoService.decrypt(requestBody.getText());
        String encryptedText1 = cryptoService.encrypt("encryptedText1");
        String encryptedText2 = cryptoService.encrypt("encryptedText2");

        final MainResponseDTO mainResponse = MainResponseDTO
                .builder()
                .text(decryptedText)
                .text1(encryptedText1)
                .text2(encryptedText2)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(mainResponse);
    }
}
