package classes;

import java.util.Random;
import imports.Stack; 
import javax.swing.ImageIcon;

public class Deck {
    private Stack<Card> cardStack;
    private ImageIcon[] images;
    private Card[] cards;
    private Random random = new Random();

    public Deck() {
        cardStack = new Stack<Card>();
        images = new ImageIcon[52];
        cards = new Card[52];
    }

    public void init(){
        this.initImages();
        this.initCards();
        this.shuffleCards();
    }

    private void initImages() {
        for (int i = 2; i < 15; i++) {
            //@comment: Diese Bilder wurden genommen, da sie eine einheitliche Größe haben.
            images[i - 2] = new ImageIcon("../imgs/" + i + "_of_diamonds.png");
            images[i + 11] = new ImageIcon("../imgs/" + i + "_of_hearts.png");
            images[i + 24] = new ImageIcon("../imgs/" + i + "_of_spades.png");
            images[i + 37] = new ImageIcon("../imgs/" + i + "_of_clubs.png");
        }
    }
    
    private void initCards() {
        for (int i = 0; i < 13; i++) {
            if (i > 10) {
                cards[i] = new Card((i + 2), "Karo", true, images[i]);
                cards[i + 13] = new Card((i + 2), "Herz", true, images[i + 13]);
                cards[i + 26] = new Card((i + 2), "Pik", true, images[i + 26]);
                cards[i + 39] = new Card((i + 2), "Kreuz", true, images[i + 39]);
            } else {
                cards[i] = new Card((i + 2), "Karo", false, images[i]);
                cards[i + 13] = new Card((i + 2), "Herz", false, images[i + 13]);
                cards[i + 26] = new Card((i + 2), "Pik", false, images[i + 26]);
                cards[i + 39] = new Card((i + 2), "Kreuz", false, images[i + 39]);
            }
        }
    }

    private void shuffleCards() {
        for (int a = 0; a < 6; a++) {
            for (int i = 0; i < 52;) {
                int randomIndex = random.nextInt(52);
                if (cards[randomIndex] != null) {
                    this.cardStack.push(cards[randomIndex]);
                    cards[randomIndex] = null;
                    i++;
                }
            }
            this.initCards();
        }
    }

    //@region: Setter/Getters 
    public ImageIcon[] getImages() {
        return images;
    }

    public void setImages(ImageIcon[] images) {
        this.images = images;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Stack<Card> getCardStack() {
        return this.cardStack;
    }

    public void setCardStack(Stack<Card> cardStack) {
        this.cardStack = cardStack;
    }
}
