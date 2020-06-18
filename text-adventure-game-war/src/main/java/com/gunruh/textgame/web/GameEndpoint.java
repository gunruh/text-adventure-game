package com.gunruh.textgame.web;

import com.gunruh.textgame.web.gameservice.GameServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import textgame.gunruh.com.*;

@Endpoint
public class GameEndpoint {
    private static final String NAMESPACE_URI = "com.gunruh.textgame";

    private GameServiceInterface gameService;

    @Autowired
    public GameEndpoint(GameServiceInterface gameService) {
        this.gameService = gameService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "startGameRequest")
    @ResponsePayload
    public StartGameResponse startGame(@RequestPayload StartGameRequest request) {
        return gameService.startGame(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNextStoryTextRequest")
    @ResponsePayload
    public GetNextStoryTextResponse getNextStoryText(@RequestPayload GetNextStoryTextRequest request) {
        return gameService.getNextStoryText(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "stopGameRequest")
    @ResponsePayload
    public StopGameResponse stopGame(@RequestPayload StopGameRequest request) {
        return gameService.stopGame(request);
    }
}
