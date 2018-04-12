package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "User")
public class User implements Serializable{

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /**
     * Имя
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Город
     */
    @Column(name = "CITY")
    private String city;

    /**
     * Количество сообщений
     */
    @Column(name = "MESSAGE_COUNT")
    private Long messageCount;

    /**
     * Количество сообщений в разделе PHP
     */
    @Column(name = "MESSAGE_COUNT_IN_PHP")
    private Long messageCountInPhp;
}
