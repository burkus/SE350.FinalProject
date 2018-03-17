import java.awt.*;
import java.util.Random;

public class DrunkenShip extends Ship {
    public DrunkenShip() {}

    boolean isRandom = false;


    public DrunkenShip(Ship ship) {
        super();
        map = ship.map;
        position = ship.getLocation();
        updateImageView();
    }

    public void move(int x, int y) {
        Random a = new Random();
        // gives 20% chance to move ship at random. if a == 0, then sends ship to go method, creating random move
        if(a.nextInt(5) == 0){
            go();
            return;
        }

        position = new Point(x, y);
        setChanged();
        notifyObservers();
        updateImageView();
    }

    public void go(){
        Random rand = new Random();
        int randInt = rand.nextInt(4);
        isRandom = true;
        System.out.println("Hiccup! the rum causde you to turn tqhe kship the zwrong way.");
        if(randInt == 0){
            goEast();
        }
        else if (randInt == 1){
            goWest();
        }
        else if (randInt == 2){
            goNorth();
        }
        else if (randInt == 3){
            goSouth();
        }

    }


    @Override
    public void goEast() {
        final int[][] grid  = map.getMap();
        final int bounds = grid[0].length;

        if(position.x + 1 < bounds) {
            if(grid[position.y][position.x + 1] == CellTypes.ocean) {
                move(position.x + 1, position.y);
            }
            else if(grid[position.y][position.x + 1] == CellTypes.treasure) {
                move(position.x + 1, position.y);
                hasTreasure = true;
            }

            else if(grid[position.y][position.x + 1] == CellTypes.pirate){
                hitPirate = true;
            }

            else if(grid[position.y][position.x + 1] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;
    }

    public void goWest() {
        final int[][] grid = map.getMap();

        if(position.x - 1 >= 0){
            if(grid[position.y][position.x - 1] == CellTypes.ocean) {
                move(position.x - 1, position.y);
            }
            else if(grid[position.y][position.x - 1] == CellTypes.treasure) {

                move(position.x - 1, position.y);
                hasTreasure = true;
            }
            else if(grid[position.y][position.x - 1] == CellTypes.pirate) {
                hitPirate = true;
            }

            else if(grid[position.y][position.x - 1] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;

    }

    public void goNorth() {
        final int[][] grid = map.getMap();

        if(position.y - 1 >= 0) {
            if(grid[position.y - 1][position.x] == CellTypes.ocean) {
                move(position.x, position.y - 1);
            }
            else if(grid[position.y - 1][position.x] == CellTypes.treasure) {
                move(position.x, position.y - 1);
                hasTreasure = true;
            }

            else if(grid[position.y - 1][position.x] == CellTypes.pirate) {
                hitPirate = true;
            }

            else if(grid[position.y - 1][position.x] == CellTypes.monster){
                hitMonster = true;
            }

        }
        isRandom = false;

    }

    public void goSouth() {
        final int[][] grid = map.getMap();
        final int bounds = grid.length;

        if (position.y + 1 < bounds) {
            if (grid[position.y + 1][position.x] == CellTypes.ocean) {
                move(position.x, position.y + 1);
            } else if (grid[position.y + 1][position.x] == CellTypes.treasure) {
                move(position.x, position.y + 1);
                hasTreasure = true;
            } else if (grid[position.y + 1][position.x] == CellTypes.pirate) {
                hitPirate = true;
            } else if (grid[position.y + 1][position.x] == CellTypes.monster) {
                hitMonster = true;
            }

        }
        isRandom = false;

    }

}
