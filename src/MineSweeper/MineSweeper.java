/*
 * Copyright (c) 2018. School project
 */

package MineSweeper;import View.StartScreen;

import java.io.FileNotFoundException;

public class MineSweeper {

    /**
     * @author Cédric Schweizer
     * @param args
     * classic minesweeper (school project)
     */

    public static void main(String[] args) {
        new MineSweeper();
    }

    private MineSweeper() {
        new StartScreen(true);
    }
}
