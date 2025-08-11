package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Game;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game>{
}
