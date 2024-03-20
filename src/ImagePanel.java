import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ImagePanel extends JPanel {

    protected RectangleImage image = null;
    int height;
    int width;
    protected int x, y;

    public ImagePanel() {
        try {
            var inImage = ImageIO.read(new File("resources/DVD-Video_Logo.svg.png"));
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(inImage, 1);
            try {
                mt.waitForAll();
            } catch (Exception e) {
                System.out.println("Image not found.");
            }
            ImageIcon icon = new ImageIcon(inImage);
            int scale = 5;
            width = icon.getIconWidth() / scale;
            height = icon.getIconHeight() / scale;
            image = new RectangleImage(inImage, width, height);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(image == null)
            return;
        Graphics2D g2 = (Graphics2D)g;
        image.Draw(g2, this);
        super.paintComponent(g);
    }

    abstract void update(int step, JFrame borders);
    protected void recolour(){ //to-do
        var sprite = image.getImage();
        int r =10, g=10, b=255, a = 100;
        BufferedImage tintedSprite = new BufferedImage(sprite.getWidth(), sprite.
                getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedSprite.createGraphics();
        graphics.drawImage(sprite, 0, 0, null);
        graphics.dispose();

        for (int i = 0; i < tintedSprite.getWidth(); i++)
        {
            for (int j = 0; j < tintedSprite.getHeight(); j++)
            {
                int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                ax *= a;
                tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        image.Replace(tintedSprite);
    }
}

