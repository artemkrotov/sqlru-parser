package ru.akrotov.parser.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.akrotov.parser.common.models.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "User")
public class User implements Serializable, Identifiable {

    /**
     * Идентификатор
     */
    @Id
    @GenericGenerator(
        name = "triggerAssigned",
        strategy = "com.example.demo.models.generators.TriggerAssignedIdentityGenerator"
    )
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
