package classes;

import javax.swing.*;
import java.awt.Color;
// import java.awt.Image;

public class GUI extends JFrame {

    //@region: private GUI properties
    private JPanel panel;
    private JButton startButton;

    //@region: private misc attributes
    private Game game;

    public GUI() {
        super(); 
        System.out.println("GUI started");

        this.setLayout(null);
		this.setBounds(0, 0, 1200, 800);
        this.setTitle("Black Jack");

        //@region: properties & settings
        this.panel = new JPanel();
        this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 1200, 800);
		this.panel.setBackground(new Color(110, 160, 130));

         //@region: Frame management 
         this.panel.setVisible(true);
         this.add(this.panel);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setResizable(false);
         this.setVisible(true);

         StartButton();
    }

    private void StartButton() {
        //@region: start button settings
        this.startButton = new JButton("Spiel starten");
        this.startButton.setBounds(500, 550, 200, 50);
        this.panel.add(this.startButton);
        this.startButton.addActionListener(event -> {
            this.panel.remove(this.startButton);
            this.panel.revalidate();
            this.panel.repaint();
            this.game = new Game();
        });

        System.out.println("Start Button added");
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
}
