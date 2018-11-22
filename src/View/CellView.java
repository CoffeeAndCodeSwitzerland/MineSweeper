/*
 * Copyright (c) 2018. School project
 */

package View;

import javax.swing.*;
import java.awt.event.MouseListener;

public class CellView extends JButton {
    public CellView(MouseListener listener) {
        addMouseListener(listener);
    }
}
