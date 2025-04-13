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

    
}
