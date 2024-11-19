package com.demo.services;

public interface JWTService<T> {
    String get(T token);
    T validate(String jwt);
}