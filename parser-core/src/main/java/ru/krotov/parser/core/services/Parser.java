package com.example.demo.services;

import com.example.demo.models.User;

public interface Parser<T> {
    T getById(long id);
}
