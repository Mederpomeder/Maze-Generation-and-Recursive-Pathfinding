Maze Generator & Solver in Java
This Java program generates a random square maze and then solves it using a recursive depth-first search (DFS) algorithm.

✨ Features
Generates a random maze with:

Start at the top-left corner (0,0)

End at the bottom-right corner (n-1,n-1)

A guaranteed path between start and end

Random branches for complexity

Solves the maze and shows the solution path

Works for maze sizes from 10 to 20

🧠 How It Works
Maze Generation

Maze is filled with walls ('0')

A main path from start to end is carved

Additional random branches are added to make the maze more complex

Maze Solving

The program uses recursive DFS to find a path from start to end

Visited cells are marked as '*'

The solution path is marked as ' ' (space)

🛠️ How to Use
Run the program.

Enter a maze size between 10 and 20.

The program:

Generates and prints the maze

Tries to solve it

Prints the result (solved or not)

Shows the maze with the solution path

🖼️ Example Output
markdown
Копировать
Редактировать
Type the size of the maze (within 10 to 20): 12

-----------------------
| . 0 0 0 0 0 0 0 0 0 0 0 |
| * * 0 0 0 0 0 0 0 0 0 0 |
| 0   0 0 0 0 0 0 0 0 0 0 |
...
| 0 0 0 0 0 0 0 0 0 0 0   |
-----------------------
✅ If a path is found, you'll see SOLVED maze.

❌ If not, you'll see could NOT SOLVE maze.

📌 Notes
If you enter a number outside the 10–20 range, the maze size defaults to 10

The original maze is preserved, and solving is done on a copy

