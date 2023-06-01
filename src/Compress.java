import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Compress {
    public static void main(String[] args) {
        System.out.println("Comprimierung der Bilder wurde gestartet");

        // Pfad zum Ordner mit den Bildern
        String folderPath = "../imgs/toCompress";

        // Liste alle Dateien im Ordner auf
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        // Durchlaufe alle Dateien und komprimiere sie
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    compressImage(file);
                }
            }
        }
    }

    private static void compressImage(File file) {
        try {
            // Lade das Bild als ImageIcon
            ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        
            // Erzeuge ein BufferedImage aus dem ImageIcon
            Image originalImage = originalIcon.getImage();
            
            // Berechne die Zielgröße unter Beibehaltung des Seitenverhältnisses
            int targetWidth;
            int targetHeight;

            targetWidth = (int) (originalImage.getHeight(null) * 0.2);
            targetHeight = (int) (originalImage.getHeight(null) * 0.3);
        
            // Erzeuge ein BufferedImage mit der berechneten Größe und zeichne das Originalbild ein
            BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g2d.dispose();
        
            // Speichere das komprimierte Bild
            String outputFilePath = file.getParent() + "/compressed/" + file.getName();
            File outputFile = new File(outputFilePath);
            ImageIO.write(scaledImage, "png", outputFile);
            System.out.println("Bild " + file.getName() + " erfolgreich komprimiert und gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
