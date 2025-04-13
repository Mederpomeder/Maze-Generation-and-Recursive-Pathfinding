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
}
