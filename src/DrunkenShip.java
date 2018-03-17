import java.awt.*;
import java.util.Random;

public class DrunkenShip extends Ship {
    public DrunkenShip() {}

    public DrunkenShip(Ship ship) {
        super();
        map = ship.map;
        position = ship.getLocation();
        updateImageView();
    }

    public void move(int x, int y) {
        Random random = new Random();
        if(random.nextInt(4) == 0) {
            return;
        }
        position = new Point(x, y);
        setChanged();
        notifyObservers();
        updateImageView();
    }
}
