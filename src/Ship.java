
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

public class Ship extends Observable {
	protected static Point position;
	protected static Map map;
	private ImageView imageView;
	private int scalingFactor = 35;
	public boolean hasTreasure = false;
	public boolean hitPirate = false;
	public boolean hitMonster = false;
	public boolean hitRum = false;

	public Ship() {
		Image image = new Image(getClass().getResource("ship.png").toExternalForm(),
				scalingFactor, scalingFactor, true, true);
		imageView = new ImageView(image);
	}

	public Ship(Map map) {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(map.getMap()[0].length);
			int y = random.nextInt(map.getMap().length);
			if(map.getMap()[y][x] == CellTypes.ocean) {
				position = new Point(x, y);
				break;
			}
		}
		this.map = map;

		Image image = new Image(getClass().getResource("ship.png").toExternalForm(),
				scalingFactor, scalingFactor, true, true);
		imageView = new ImageView(image);
		updateImageView();
	}

	public void updateImageView() {
		imageView.setX(position.getX() * scalingFactor);
		imageView.setY(position.getY() * scalingFactor);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public static Point getLocation() {
		return position;
	}

	public void move(int x, int y) {
		position = new Point(x, y);
		if(map.getMap()[y][x] == CellTypes.rum) {
			hitRum = true;
		}
		setChanged();
		notifyObservers();
		updateImageView();
	}

	public void goEast() {
		// go right
		final int[][] grid  = map.getMap();
		final int bounds = grid[0].length;

		if(position.x + 1 < bounds) {
			int cell = grid[position.y][position.x + 1];
			if(cell == CellTypes.ocean || cell == CellTypes.rum) {
				move(position.x + 1, position.y);
                map.updateCell(position.x, position.y, CellTypes.ocean);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x + 1, position.y);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate){
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}
		}
	}

	public void goWest() {

		final int[][] grid = map.getMap();

		if(position.x - 1 >= 0){
		    int cell = grid[position.y][position.x - 1];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
				move(position.x - 1, position.y);
                map.updateCell(position.x, position.y, CellTypes.ocean);
			}
			else if(cell == CellTypes.treasure) {

				move(position.x - 1, position.y);
				hasTreasure = true;
			}
			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}

	public void goNorth() {

		final int[][] grid = map.getMap();

		if(position.y - 1 >= 0) {
		    int cell = grid[position.y - 1][position.x];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
				move(position.x, position.y - 1);
                map.updateCell(position.x, position.y, CellTypes.ocean);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x, position.y - 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}

	public void goSouth() {

		final int[][]grid = map.getMap();
		final int bounds = grid.length;

		if(position.y + 1 < bounds) {
		    int cell = grid[position.y + 1][position.x];
            if(cell == CellTypes.ocean || cell == CellTypes.rum) {
				move(position.x, position.y + 1);
				map.updateCell(position.x, position.y, CellTypes.ocean);
			}
			else if(cell == CellTypes.treasure) {
				move(position.x, position.y + 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.pirate) {
				hitPirate = true;
			}

			else if(cell == CellTypes.monster){
				hitMonster = true;
			}

		}
	}
}
