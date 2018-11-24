package View;

import Model.Database;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SuccessUI extends JFrame {
    private Database db;
    private Game game;
    public SuccessUI(Game game, Database db, int fieldSize) {
        this.db = db;
        this.game = game;
        game.setEndDate(new Date());

        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

//        JLabel label = new JLabel("Du hast das Spiel in " + (db.getEndDate().getSecond() - db.getStartDate().getSecond()) + " Sekunden gemeistert!!!");
        JLabel label = new JLabel("Du hast das Spiel in " + ((this.game.getEndDate().getTime() - this.game.getStartDate().getTime()) / 1000) + " Sekunden gemeistert!!!");  //TODO: TIME
        add(label);

        JLabel uname = new JLabel("Wie ist dein Name?: ");  //TODO: TIME
        add(uname);

        JTextField username = new JTextField();
        add(username);

        JButton submit = new JButton("Resultat Abspeichern");
        add(submit);


        JLabel bestScore = new JLabel();
        ResultSet rs = db.select("Select distinct username, winningtime from Minesweeper where fieldsize = " + fieldSize + " order by winningtime asc;");
        try {
            bestScore.setText("Die beste Zeit mit deinen Spieldaten beträgt: " + rs.getString("winningtime") + " Sekunden (benutzer: " + rs.getString("username") + ")");
        } catch (SQLException sql) {
            bestScore.setText("Noch keine Daten gefunden...");
        }
        add(bestScore);



        try {
            submit.addActionListener(e -> {
                if (!username.getText().equals("") && !username.getText().startsWith(" ")) {
                    db.insert(username.getText(), String.valueOf((this.game.getEndDate().getTime() - this.game.getStartDate().getTime()) / 1000), fieldSize);
                    setVisible(false);
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}
