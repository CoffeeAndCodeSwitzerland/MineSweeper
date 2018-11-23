package View;

import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

public class SuccessUI extends JFrame {
    private Game db;
    public SuccessUI(Game db) {
        this.db = db;
        db.setEndDate(new Date());

        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());

//        JLabel label = new JLabel("Du hast das Spiel in " + (db.getEndDate().getSecond() - db.getStartDate().getSecond()) + " Sekunden gemeistert!!!");  //TODO: TIME
        JLabel label = new JLabel("Du hast das Spiel in " + ((db.getEndDate().getTime() - db.getStartDate().getTime()) / 1000) + " Sekunden gemeistert!!!");  //TODO: TIME
        add(label);

        setVisible(true);
    }
}
