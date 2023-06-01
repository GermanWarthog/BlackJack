package classes;

public class Game {
    //@region: interface
    private GUI gameInterface;

    //@region: Players 
    private Player player; 
    private Player dealer;

    //@region: Game states
    private boolean isGameActive; 
    private Player gameWinner; 

    //@region: Misc atributes
    private Deck deck;

    public Game(GUI gameInterface) {
        this.gameInterface = gameInterface;
        this.isGameActive = true;

        this.player = new Player("Spieler 1", new Deck());
        this.dealer = new Player("Dealer", new Deck());

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

        //@todo: if player score is > 21 then loose the game.
    }

    public void stand()  {

        int dealerScore = this.dealer.getScore();
        int playerScore = this.player.getScore();

        if (dealerScore == playerScore) {
            this.setGameWinner(null);
        } else if (dealerScore < 22 && (playerScore >= 22 || dealerScore > playerScore)) {
            this.setGameWinner(this.dealer);
        } else if (playerScore < 22 && (dealerScore >= 22 || playerScore > dealerScore)) {
            this.setGameWinner(this.player);
        }

        System.out.println(this.getGameWinner());
    }

    //@region Setter/Getters
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public void setGameActive(boolean isGameActive) {
        this.isGameActive = isGameActive;
    }

    public Player getGameWinner() {
        return gameWinner;
    }

    private void setGameWinner(Player gameWinner) {
        this.gameWinner = gameWinner;
    }
}
