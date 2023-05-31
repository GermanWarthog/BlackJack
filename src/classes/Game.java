package classes;

public class Game {

    private Player player; 
    private Dealer dealer;

    public Game(){
        System.out.println("Started Game");
        
    }

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
