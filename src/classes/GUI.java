package classes;

import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.HashMap;

import imports.List;
// import java.awt.Image;

public class GUI extends JFrame {

    //@region: private GUI properties
    private JPanel panel;
    private JButton startButton;
    private JLabel deck;
    private HashMap<String, List<JLabel>> imageLabels;

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

        this.deck = new JLabel();
        ImageIcon cardBack = new ImageIcon("../images/Herz-1.png");
        Image image = cardBack.getImage();

        System.out.println(image);

        MediaTracker tracker = new MediaTracker(new JPanel()); // Erstelle einen MediaTracker mit einem temporären JPanel
        tracker.addImage(image, 0);

        try {
            tracker.waitForAll(); // Warte, bis das Bild vollständig geladen ist
            if (tracker.isErrorAny()) {
                System.out.println("Fehler beim Laden des Bildes.");
            } else {
                System.out.println("Das Bild wurde erfolgreich geladen.");
            }
        } catch (InterruptedException e) {
            System.out.println("Fehler beim Warten auf das Bild.");
            System.out.println(e.getCause());
        }

        this.deck.setIcon(cardBack);
        this.deck.setBounds(500, 300, 200, 400);

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
            this.initGameInterface();
        });

        System.out.println("Start Button added");
    }

    private void initGameInterface() {
        this.game = new Game(this);

        this.imageLabels = new HashMap<>();

        this.imageLabels.put(this.game.getPlayer().getName(), new List<JLabel>());
        this.imageLabels.put(this.game.getDealer().getName(), new List<JLabel>());

        // for (int i = 0; i < 2; i++) {
        //     this.game.draw(this.game.getPlayer());
        // }

        // for (int i = 0; i < 2; i++) {
        //     this.game.draw(this.game.getDealer());
        // }

        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(900, 550, 200, 50);
        this.panel.add(drawButton);
        drawButton.addActionListener(event -> {
            this.game.draw(this.game.getPlayer());
        });

        JButton standButton = new JButton("Stand");
        standButton.setBounds(900, 650, 200, 50);
        this.panel.add(standButton);
        standButton.addActionListener(event -> {
            //@todo: Stand 
        });
    }


    //@region: public interface interactions
    public void AddCardsToUI(Player player, Card card) {
        System.out.println("Adding card to UI -> " + card.getIcon());

        JLabel image = new JLabel();

        image.setIcon(card.getIcon());

        //@comment: Nutzung von Ternären Operatoren dienen dazu den Code zu minimieren und die lesbarkeit zu verbessern.
        int yOffset = player.getName().toLowerCase().contains("dealer") ? 200 : 1600;
        int xOffset = this.imageLabels.get(player.getName()).size() + 1;

        image.setSize(100, 50);
        image.setLocation(50 + (xOffset * 100), yOffset);
        
        this.panel.add(image);
        image.setVisible(true);

        this.imageLabels.get(player.getName()).toLast();
        this.imageLabels.get(player.getName()).append(image);
        this.panel.revalidate();
        this.panel.repaint();
    }

    //@region Setter/Getters 
    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
}
