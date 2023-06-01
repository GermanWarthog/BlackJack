package classes;

public class Player {
    private String name;
    private Deck deck; 
    private int score; 

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.score = 0;
    }

    public void addCard(Card card) {
        System.out.println("Adding " + card.getNumber() + " to current score: " + this.getScore());

        this.setScore(this.getScore() + card.getNumber());
        this.deck.getCardStack().push(card);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getScore() {
        return this.score;
    }

    private void setScore(int score) {
        this.score = score;
    }
}
