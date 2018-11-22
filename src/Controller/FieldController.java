
package Controller;

import View.FieldView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldController {

    private FieldView view;
    private int fieldSize;

    public FieldController(int fieldSize) {
        this.fieldSize = fieldSize;
        view = new FieldView(this.fieldSize, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getSource());
                reveal();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void reveal() {
        System.out.println("jaaaaaaa");
    }
}
