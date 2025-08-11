package org.acme.model;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;


@Entity
public class Game extends PanacheEntity {

    @Column(length = 50, unique = true)
    public String title;

    public Game() {

    }

    public Game(String title) {
        this.title = title;
    }
}