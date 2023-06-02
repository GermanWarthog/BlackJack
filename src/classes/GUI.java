package classes;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import imports.List;

public class GUI extends JFrame {

    // @region: private GUI properties
    private JPanel panel;
    private JButton startButton;
    private JButton restartButton;
    private HashMap<String, List<JLabel>> imageLabels;

    // @region: Game Components
    private JButton drawButton;
    private JButton standButton;
    private JLabel playerScore;
    private JLabel dealerScore;
    private JLabel winnerLabel;

    // @region: private misc attributes
    private Game game;

    public GUI() {
        super();
        System.out.println("GUI started");

        this.setLayout(null);
        this.setBounds(0, 0, 1200, 800);
        this.setTitle("Black Jack");

        // @region: properties & settings
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 1200, 800);
        this.panel.setBackground(new Color(110, 160, 130));

        // @region: Frame management
        this.add(this.panel);
        this.panel.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.StartButton();
    }

    private void StartButton() {
        // @region: start button settings
        this.startButton = new JButton("Spiel starten");
        this.startButton.setBounds(500, 350, 200, 50);
        this.panel.add(this.startButton);
        this.startButton.addActionListener(event -> {
            this.panel.remove(this.startButton);
            this.panel.revalidate();
            this.panel.repaint();
            this.RenderGameInterface();
        });

        System.out.println("Start Button added");
    }

    private void RestartButton() {
        this.panel.remove(this.drawButton);
        this.panel.remove(this.standButton);

        this.restartButton = new JButton("Spiel neustarten");
        this.restartButton.setBounds(500, 700, 200, 50);
        this.panel.add(this.restartButton);

        this.panel.revalidate();
        this.panel.repaint();

        this.restartButton.addActionListener(event -> {
            this.panel.remove(this.playerScore);
            this.panel.remove(this.dealerScore);
            this.panel.remove(this.winnerLabel);

            for (Map.Entry<String, List<JLabel>> entry : imageLabels.entrySet()) {
                String key = entry.getKey();
                List<JLabel> value = entry.getValue();
                
                System.out.println("Key: " + key);
                System.out.println("Value: " + value);
                    
                value.toFirst();

                while (value.hasAccess()) {
                    this.panel.remove(value.getContent());
                    value.next();
                }
            }

            this.panel.remove(this.restartButton);
    
            this.panel.revalidate();
            this.panel.repaint();

            this.StartButton();
        });
    }

    private void RenderGameInterface() {
        this.game = new Game(this);

        this.imageLabels = new HashMap<>();

        this.imageLabels.put(this.game.getPlayer().getName(), new List<JLabel>());
        this.imageLabels.put(this.game.getDealer().getName(), new List<JLabel>());

        this.drawButton = new JButton("Draw");
        this.drawButton.setBounds(350, 700, 200, 50);
        this.panel.add(this.drawButton);
        this.drawButton.addActionListener(event -> {
            if (!this.game.isGameActive()) {
                return;
            }

            this.game.draw(this.game.getPlayer());
        });

        this.standButton = new JButton("Stand");
        this.standButton.setBounds(650, 700, 200, 50);
        this.panel.add(this.standButton);
        this.standButton.addActionListener(event -> {
            Player dealer = this.game.getDealer();
            
            if (!this.game.isGameActive()) {
                return;
            }

            if (this.imageLabels.get(this.game.getPlayer().getName()).size() > 2 ) {
                this.game.draw(dealer);
            }
            
            this.imageLabels.get(dealer.getName()).toFirst();
            
            JLabel firstDealerCard = this.imageLabels.get(dealer.getName()).getContent();
            Card firstCard = null; 

            while (!dealer.getDeck().getCardStack().isEmpty()) {
                firstCard = dealer.getDeck().getCardStack().top();
                dealer.getDeck().getCardStack().pop();
            }

            System.out.println("firstCard: " + firstCard.getIcon());

            firstDealerCard.setIcon((firstCard != null) ? firstCard.getIcon() : new ImageIcon("../imgs/back_of_card.png"));
            
            this.playerScore.setVisible(true);
            this.dealerScore.setVisible(true);
            
            this.game.stand();
            this.game.setGameActive(false);

            this.winnerLabel = new JLabel("Gewinner: " + ((this.game.getGameWinner() != null) ? this.game.getGameWinner().getName() : "Unendschieden"));
            this.winnerLabel.setBounds(540, 10, 400, 100);
            this.panel.add(this.winnerLabel);

            this.panel.revalidate();
            this.panel.repaint();

            this.RestartButton();
        });

        this.playerScore = new JLabel("Spieler Hand: 0");
        this.playerScore.setBounds(300, 10, 400, 100);
        this.playerScore.setVisible(false);

        this.dealerScore = new JLabel("Dealer Hand: 0");
        this.dealerScore.setBounds(800, 10, 400, 100);
        this.dealerScore.setVisible(false);


        this.panel.add(this.playerScore);
        this.panel.add(this.dealerScore);
        this.panel.revalidate();
        this.panel.repaint();

        for (int i = 0; i < 2; i++) {
            this.game.draw(this.game.getPlayer());
        }

        for (int i = 0; i < 2; i++) {
            this.game.draw(this.game.getDealer());
        }
    }

    // @region: public interface interactions
    public void AddCardsToUI(Player player, Card card) {
        System.out.println("Adding card to UI from -> " + player.getName().toLowerCase() + " : " + card.getIcon());

        JLabel cardImage = new JLabel();
        boolean isDealer = player.getName().toLowerCase().contains("dealer");
        int listSize = this.imageLabels.get(player.getName()).size();

        // @comment: Nutzung von Ternären Operatoren dienen dazu den Code zu minimieren und die lesbarkeit zu verbessern.
        int xOffset = listSize == 1 ? 10 : (listSize + 1) * 160;
        int yOffset = isDealer ? 400 : 100;

        System.out.println(yOffset);
        System.out.println(xOffset);
        System.out.println(listSize);
        System.out.println(isDealer && listSize == 1);

        cardImage.setBounds(xOffset, yOffset, 200, 300);
        cardImage.setHorizontalAlignment(SwingConstants.CENTER);
        cardImage.setVerticalAlignment(SwingConstants.CENTER);
        cardImage.setPreferredSize(new Dimension(200, 300));
        cardImage.setIcon(isDealer && listSize < 1 ? new ImageIcon("../imgs/back_of_card.png") : card.getIcon());

        this.panel.add(cardImage);
        this.panel.revalidate();
        this.panel.repaint();

        this.imageLabels.get(player.getName()).toLast();
        this.imageLabels.get(player.getName()).append(cardImage);

        if (isDealer) {
            this.dealerScore.setText("Dealer Hand: " + player.getScore());
        } else {
            this.playerScore.setText("Spieler Hand: " + player.getScore());
        }
    }
}