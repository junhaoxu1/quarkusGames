package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.acme.model.Game;

import java.util.List;
import java.util.logging.Logger;

@Path("/games")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class GameResource {

    @Inject
    GameRepository gameRepository;

    private static final Logger logger = Logger.getLogger(GameResource.class.getName());

    @GET
    public Response getAllGames() {
        List<Game> games = Game.listAll();
        return Response.ok(games).build();
    }

    @GET
    @Path("/{id}")
    public Game getGame(@PathParam("id") Long id) {
        Game game = gameRepository.findById(id);
        if(game == null) {
            throw new WebApplicationException("Id is invalid", 422);
        }
        return game;
    }

    @POST
    @Transactional
    public Response createGame(Game game) {
        if (game.id != null) {
            throw new WebApplicationException("id is invalid", 422);
        }
        gameRepository.persist(game);
        return Response.ok(game).status(201).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteGame(@PathParam("id") Long id) {
        Game game = gameRepository.findById(id);
        if(game == null) {
            throw new WebApplicationException("Id is invalid", 422);
        }
        gameRepository.delete(game);
        return Response.ok("Game Deleted").build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateGame(@PathParam("id") Long id, Game updatedGame) {
        Game existingGame = gameRepository.findById(id);
        if(existingGame == null) {
            throw new WebApplicationException("Id is invalid", 422);
        }

        existingGame.title = updatedGame.title;

        return Response.ok(existingGame).build();
    }
}




