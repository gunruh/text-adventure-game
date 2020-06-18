package com.gunruh.textgame.web.gameservice;

import textgame.gunruh.com.*;

public interface GameServiceInterface {
    StartGameResponse startGame(StartGameRequest request);

    GetNextStoryTextResponse getNextStoryText(GetNextStoryTextRequest request);

    StopGameResponse stopGame(StopGameRequest request);
}
