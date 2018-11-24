/*
 * Copyright (c) 2018. School project
 */

import View.StartScreen;

import java.io.FileNotFoundException;

public class MineSweeper {

    /**
     * @author Cédric Schweizer
     * @param args
     * classic minesweeper (school project)
     */

    public static void main(String[] args) throws FileNotFoundException {
        new MineSweeper();
    }

    private MineSweeper() throws FileNotFoundException {
        new StartScreen();
    }
}
