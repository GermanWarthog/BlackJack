package classes;

public class Game {
    private GUI gameInterface;

    private Player player; 
    private Dealer dealer;

    private Deck deck;

    public Game(GUI gameInterface) {
        this.gameInterface = gameInterface;

        this.player = new Player("Spieler 1", new Deck());
        this.dealer = new Dealer();

        this.deck = new Deck();
        this.deck.init();
    }

    public void draw(Player player) {
        if (this.deck.getCardStack().isEmpty()) {
            System.out.println("Es gibt keine Karten mehr, die vergeben werden können!");
            return;
        }

        player.addCard(this.deck.getCardStack().top());
        this.deck.getCardStack().pop();
        this.gameInterface.AddCardsToUI(player, player.getDeck().getCardStack().top());
    }

    public void stand()  {

    }

    //@region Setter/Getters
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
