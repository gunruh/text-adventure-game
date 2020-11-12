package com.gunruh.textgame.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Constants {
    public static final String CANT_GO_THAT_WAY = "Can't go that way.";
//    public static final String SPACE_DUDES_TITLE =
//            "        ______  ______  ______  ______  ______       ______  ___ ___ ______  ______  ______\n" +
//            "     **/      \\/      \\/      \\/      \\/      \\*****\\      \\/   /   |      \\/      \\/      \\*******\n" +
//            "    **|        |   |   |   |   |    __/   ____/*****|   |   |   |   |   |   |  ____/        |*****\n" +
//            "   ***|    \\__/|   ___/        |   /*|      \\*******|   |   |   |   |   |   |    \\*|    \\__/*****\n" +
//            "  *****\\__    \\|   |**|    |   |   \\*|    __/*******|   |   |   |   |   |   |  __/**\\__    \\****\n" +
//            " ******/  \\    |   |**|    |   |      \\       \\*****|   |   |       |   |   |      \\/  \\    |**\n" +
//            "*******\\______/|__/***\\___/\\__/\\______/\\______/*****/______/\\______//______/\\______/\\______/**";

    public static String SPACE_DUDES_TITLE =
                    "        ______  ______  ______  ______  ______\n" +
                    "     **/      \\/      \\/      \\/      \\/      \\*******\n" +
                    "    **|        |   |   |   |   |    __/   ____/*******\n" +
                    "   ***|    \\__/|   ___/        |   /*|      \\*********\n" +
                    "  *****\\__    \\|   |**|    |   |   \\*|    __/*********\n" +
                    " ******/  \\    |   |**|    |   |      \\       \\*******\n" +
                    "*******\\______/|__/***\\___/\\__/\\______/\\______/*******\n" +
                    "*******\\      \\/   /   |      \\/      \\/      \\*******\n" +
                    "*******|   |   |   |   |   |   |  ____/        |*****\n" +
                    "*******|   |   |   |   |   |   |    \\*|    \\__/*****\n" +
                    "*******|   |   |   |   |   |   |  __/**\\__    \\****\n" +
                    "*******|   |   |       |   |   |      \\/  \\    |**\n" +
                    "*******/______/\\______//______/\\______/\\______/**";

    public static final String INTRO_TEXT =
            "The room comes into focus as you open your eyes." +
          "\n\"Another day on the job\", you think to yourself." +
          "\nYou gather your strength and roll out of bed.\n";
}
