package classes;

import javax.swing.ImageIcon;

public class Card {
    private int number; 
    private String color; 
    private boolean imageCard; 
    private ImageIcon icon;

    public Card(int number, String color, boolean imageCard, ImageIcon icon) {
        this.number = number;
        this.color = color;
        this.imageCard = imageCard;
        this.icon = icon;
    }   

    public int getNumber() {
        return this.number;
    }

    public String getColor() {
        return this.color;
    }
    
    public boolean isImageCard() {
        return this.imageCard;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
   
    public void setImageCard(boolean imageCard) {
        this.imageCard = imageCard;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

}
