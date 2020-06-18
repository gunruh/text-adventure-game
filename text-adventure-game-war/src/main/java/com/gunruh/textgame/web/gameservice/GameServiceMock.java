package com.gunruh.textgame.web.gameservice;

import org.springframework.stereotype.Component;
import textgame.gunruh.com.*;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class GameServiceMock implements GameServiceInterface {
    private static String gameId = "";

    @PostConstruct
    public void initData() {
        gameId = UUID.randomUUID().toString();
    }

    @Override
    public StartGameResponse startGame(StartGameRequest request) {
        StartGameResponse startGameResponse = new StartGameResponse();

        Response response = new Response();
        response.setStatus(Status.SUCCESS);
        response.setStatusMessage("The game has started.");
        startGameResponse.setResponse(response);

        startGameResponse.setGameId(gameId);

        return startGameResponse;
    }

    @Override
    public GetNextStoryTextResponse getNextStoryText(GetNextStoryTextRequest request) {
        GetNextStoryTextResponse getNextStoryTextResponse = new GetNextStoryTextResponse();

        Response response = new Response();
        response.setStatus(Status.SUCCESS);
        response.setStatusMessage("Processed input.");
        response.setText("And then something else happened...");
        getNextStoryTextResponse.setResponse(response);

        return getNextStoryTextResponse;
    }

    @Override
    public StopGameResponse stopGame(StopGameRequest request) {
        StopGameResponse stopGameResponse = new StopGameResponse();

        Response response = new Response();
        response.setStatus(Status.SUCCESS);
        response.setStatusMessage("The game has stopped.");
        stopGameResponse.setResponse(response);

        stopGameResponse.setGameId(gameId);

        return stopGameResponse;
    }
}
