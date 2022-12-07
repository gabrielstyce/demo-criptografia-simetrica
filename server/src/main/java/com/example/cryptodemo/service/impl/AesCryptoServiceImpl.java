package com.example.cryptodemo.service.impl;

import com.example.cryptodemo.service.CryptoService;
import com.example.cryptodemo.utill.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesCryptoServiceImpl implements CryptoService {
    private Cipher _cipherInstance;
    private SecretKeySpec _secretKeySpec;
    private IvParameterSpec _ivParameterSpec;
    private static final String CRYPTO_INSTANCE = "AES/CBC/PKCS5Padding";
    private static final String CRYPTO_ALGORITHM = "AES";

    public CryptoService init(String secretKey) throws Exception {
        _cipherInstance = Cipher.getInstance(CRYPTO_INSTANCE);
        _secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), CRYPTO_ALGORITHM);
        _ivParameterSpec = new IvParameterSpec("5617118481967501".getBytes(StandardCharsets.UTF_8));
        return this;
    }

    @Override
    public String encrypt(String text) throws Exception {
        if (_cipherInstance == null) throw new Exception("Crypto context not initilized");
        _cipherInstance.init(Cipher.ENCRYPT_MODE, _secretKeySpec, _ivParameterSpec);
        return StringUtil.encode(_cipherInstance.doFinal(text.getBytes()));
    }

    @Override
    public String decrypt(String text) throws Exception {
        if (_cipherInstance == null) throw new Exception("Crypto context not initilized");
        _cipherInstance.init(Cipher.DECRYPT_MODE, _secretKeySpec, _ivParameterSpec);
        byte[] decodedText = StringUtil.decode(text);
        return StringUtil.toString(_cipherInstance.doFinal(decodedText));
    }
}
