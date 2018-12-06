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

    public static void main(String[] args) throws FileNotFoundException {
        new MineSweeper();
    }

    public MineSweeper() throws FileNotFoundException {
        new StartScreen(true);
    }
}
