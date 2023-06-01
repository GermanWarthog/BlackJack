package classes;

public class Player {
    private String name;
    private Deck deck; 

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public void addCard(Card card) {
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
}
