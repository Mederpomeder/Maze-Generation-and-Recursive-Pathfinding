import java.util.*;

public class Main {
    private static final char WALL = '0';
    private static final char PATH = '.'; 
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Type the size of the maze (within 10 to 20): ");
        int size = scan.nextInt();
        System.out.println();

        // Fixing the condition for size validation
        if (size < 10 && size > 20) {
            System.out.println("Invalid size! The size is settled as 10.");
            size = 10;
        }

        char[][] maze = generateMaze(size);
        printMaze(maze);

        // Create a copy of the maze for solving, so the original remains unchanged
        char[][] mazeCopy = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(maze[i], 0, mazeCopy[i], 0, size);
        }

        if (traverse(mazeCopy, 0, 0)) {
            System.out.println("SOLVED maze");
        } else {
            System.out.println("could NOT SOLVE maze");
        }

        printMaze(mazeCopy);
    }

    public static char[][] generateMaze(int size) {
        char[][] maze = new char[size][size];

        // Fill all cells with walls (0 for wall)
        for (int i = 0; i < size; i++) {
            Arrays.fill(maze[i], WALL);
        }

        // Start and finish points
        maze[0][0] = PATH;
        maze[size-1][size-1] = PATH;

        // Create the main path
        createMainPath(maze, size);

        // Add random branches
        addBranches(maze, size);

        return maze;
    }

    private static void createMainPath(char[][] maze, int size) {
        int x = 0, y = 0;

        while (x != size-1 && y != size-1) {  // Fixing the logical operator here (&&)
            // Choose direction towards the finish with priority
            int direction = chooseDirection(x, y, size-1, size-1);

            switch (direction) {
                case 0: // right
                    if (y < size-1) {
                        maze[x][y+1] = PATH;
                        y++;
                    }
                    break;
                case 1: // down
                    if (x < size-1) {
                        maze[x+1][y] = PATH;
                        x++;
                    }
                    break;
                case 2: // left
                    if (y > 0 && (x != size-1 || y-1 != size-1)) {  // Fixed condition
                        maze[x][y-1] = PATH;
                        y--;
                    }
                    break;
                case 3: // up
                    if (x > 0 && (x-1 != size-1 || y != size-1)) {  // Fixed condition
                        maze[x-1][y] = PATH;
                        x--;
                    }
                    break;
            }
        }
    }

    private static int chooseDirection(int x, int y, int targetX, int targetY) {
        // Choose direction towards the finish with priority
        int[] directions = new int[2]; // Array for possible directions to the target
        int count = 0; // Counter for possible directions

        if (y < targetY) directions[count++] = 0; // right
        if (x < targetX) directions[count++] = 1; // down
        if (y > targetY) directions[count++] = 2; // left
        if (x > targetX) directions[count++] = 3; // up

        if (count > 0) {
            // 80% chance to choose direction towards the target
            if (random.nextInt(100) < 80) {
                return directions[random.nextInt(count)];
            }
        }

        return random.nextInt(4);  // Otherwise, pick a random direction
    }

    private static void addBranches(char[][] maze, int size) {
        // Number of branches depends on the maze size
        int branches = size * 2;

        for (int i = 0; i < branches; i++) {
            int x, y;
            // Choose a random point on the main path
            do {
                x = random.nextInt(size); 
                y = random.nextInt(size);
            } while (maze[x][y] != PATH); // Ensure the point is on the main path
            
            // Try to create a branch
            createBranch(maze, x, y, size);
        }
    }

    private static void createBranch(char[][] maze, int startX, int startY, int size) {
        // Length of the branch is between 3 and size/2 cells
        int length = 3 + random.nextInt(size/2); 
        int x = startX, y = startY;

        for (int i = 0; i < length; i++) {
            int direction = random.nextInt(4);  // Random direction
            int newX = x, newY = y;

            switch (direction) {
                case 0: newY++; break; // right
                case 1: newX++; break; // down
                case 2: newY--; break; // left
                case 3: newX--; break; // up
            }

            // Ensure the branch doesn't go out of bounds
            if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                if (maze[newX][newY] == WALL) {
                    maze[newX][newY] = PATH; // If it's a wall, make it part of the path
                    x = newX; // Update current coordinates
                    y = newY;
                }
            }
        }
    }

    public static void printMaze(char[][] maze) {
        System.out.println("-----------------------");
        for (char[] row : maze) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------");
    }
    
}
