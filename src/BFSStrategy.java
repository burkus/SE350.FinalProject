import java.awt.Point;
import java.util.*;

public class BFSStrategy implements PursuitStrategy {
    private Map oceanMap;


    public BFSStrategy() {
        oceanMap = Map.getInstance();
    }

    @Override
    public Point getNextPosition(Point currentPosition, Point targetPosition) {
        final int[][] map = oceanMap.getMap();
        List<Point> shortestPath = performBFS(map, currentPosition, targetPosition);
        printPointList(shortestPath);
        if (shortestPath != null && !shortestPath.isEmpty()) {
            return shortestPath.remove(0);
        }
        else return currentPosition;
    }

    //Breadth first search pursuit strategy
    //Returns a list of points that represents the shortest path
    //from start to target
    //Adapted from the BFS algorithm found at https://en.wikipedia.org/wiki/Breadth-first_search
    private List<Point> performBFS(int[][] map, Point start, Point target) {

        HashMap<Point, Point> searchPathMap = new HashMap<>(); //contains the search path info, a map from child to parent nodes
        Set<Point> visited = new HashSet<>(); //a set of nodes that have been visited
        Queue<Point> marked = new LinkedList<>(); //a FIFO queue of nodes to visit

        searchPathMap.put(start, null);
        marked.add(start);
        while (!marked.isEmpty()) {
            Point parent = marked.remove();

            if (parent.equals(target)) {
                return constructPath(searchPathMap, start, parent);
            }

            List<Point> neighbors = getSurroundingPoints(parent, map);
            for (Point child : neighbors) {
                if (visited.contains(child))
                    continue;

                if (!marked.contains(child)) {
                    searchPathMap.put(child, parent);
                    marked.add(child);
                }
            }

            visited.add(parent);
        }
        return null;
    }

    private List<Point> constructPath(HashMap<Point, Point> searchPathMap, Point start, Point target) {
        Stack<Point> shortestPath = new Stack<>();

        Point parent = searchPathMap.get(target);
        while(parent != null) {
            shortestPath.push(parent);
            parent = searchPathMap.get(parent);
        }
        return shortestPath;
    }

    private List<Point> getSurroundingPoints(Point p, int[][] array) {
        int x = p.x;
        int y = p.y;
        int width = array[0].length;
        int height = array.length;
        LinkedList<Point> neighbors = new LinkedList<>();

        //Add all the neighbors that are valid array indices
        if (y - 1 >= 0) {
            neighbors.add(new Point(x, y - 1));
        }
        if (x + 1 < width) {
            neighbors.add(new Point(x + 1, y));
        }
        if (y + 1 < height) {
            neighbors.add(new Point(x, y + 1));
        }
        if (x - 1 >= 0) {
            neighbors.add(new Point(x - 1, y));
        }
        return neighbors;
    }

    private void printPointList(List<Point> ps) {
        String str = "The path is: ";
        for (Point p : ps) {
            str += "(" + p.x + ", " + p.y + ") ";
        }
        System.out.println(str);
    }
}
