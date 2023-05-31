package classes;

public class Dealer extends Player {
    
    private Deck deck;

    public Dealer(){
        super("Dealer");
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
