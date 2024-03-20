import javax.swing.*;
import java.awt.*;

public class BounceMotionPanel extends ImagePanel{
    boolean checkBorderXLeft(Rectangle bounds) {
        return (this.x < (bounds.getMinX()));
    }
    boolean checkBorderXRight(Rectangle bounds){
        return (this.x > (bounds.getWidth() - width));
    }
    boolean checkBorderYTop(Rectangle bounds){
        return (this.y > (bounds.getHeight() - height));
    }
    boolean checkBorderYBottom(Rectangle bounds){
        return (this.y < (bounds.getMinY()));
    }
    boolean isRightFace = true;
    boolean isTopFace = true;

    @Override
    void update(int step, JFrame frame) {
        var borders = new Rectangle(0, 0, frame.getWidth(), frame.getHeight());
        if (checkBorderXRight(borders))
        {
            isRightFace = false;
            y += step * (isTopFace ? 1 : -1);
        }
        if (checkBorderXLeft(borders)){
            isRightFace = true;
            y += step * (isTopFace ? 1 : -1);
        }
        if(checkBorderYBottom(borders))
        {
            isTopFace = true;
            x += step * (isRightFace ? 1 : -1);
        }
        if (checkBorderYTop(borders))
        {
            isTopFace = false;
            x += step * (isRightFace ? 1 : -1);
        }
        x += step * (isRightFace ? 1 : -1);
        y += step * (isTopFace ? 1 : -1);

        image.Move(x, y);
        repaint();
    }
}
