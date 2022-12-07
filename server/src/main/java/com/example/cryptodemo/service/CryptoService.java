package com.example.cryptodemo.service;

public interface CryptoService {
    public String encrypt(String text) throws Exception;
    public String decrypt(String text) throws Exception;
}
