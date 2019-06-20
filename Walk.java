//Michael Chunko

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Walk 
{
	public static void main(String[] args)
	{
		int[][] grid = null;
		try 
		{
			//Reads in each line from the input file
			for(String line: Files.readAllLines(Paths.get("D:/Mike/Documents/ACSL.txt")))
			{
				//Runs only for the first line of input
				if(grid == null)
				{
					grid = new int[8][8];
					int count = 0;
					//Seperates the first line into the hexademical values
					for(String part: line.split(","))
					{
						//Fills the grid with the proper values
						
						//The hex number has to be split into two parts so that it can properly fit within an int
						int part1 = Integer.parseInt(part.substring(0, 3), 16);
						int part2 = Integer.parseInt(part.substring(3), 16);
						
						String octal = Integer.toOctalString(part1);
						for(int i = 0; i < 4; i++)
							grid[7 - count][i] = octal.charAt(i) - '0';
						
						octal = Integer.toOctalString(part2);
						for(int i = 0; i < 4; i++)
							grid[7 - count][4 + i] = octal.charAt(i) - '0';
						
						
						count++;
					}
					
					/*
					for(int i = 0; i < 8; i++)
					
					{
						for(int j = 0; j < 8; j++)
							System.out.print(grid[i][j] + " ");
						System.out.println();
					}
					System.out.println("-----------------------");
					*/
				}
				//The other lines of input
				else
				{
					//Reads in the variables from the input
					int row = line.charAt(0) - '0';
					int col = line.charAt(2) - '0';
					char direction = line.charAt(4);
					int movesLeft = line.charAt(6) - '0';
					
					while(movesLeft > 0)
					{
						//System.out.println("Entering " + row + ", " + col + " with a " + grid[8 - row][col - 1] + " from the " + direction);
						int rotation = 45 * grid[8 - row][col - 1];
						
						//Key for the values of direction
						//A = North, 1 = Northwest, L = West, 2 = Southwest, B = South, 3 = Southeast, R = East, 4 = Northeast
						
						//Finds the rotation
						switch(direction)
						{
						case 'A':
							rotation = 90 - rotation;
							break;
						case '1':
							rotation = 135 - rotation;
							break;
						case 'L':
							rotation = 180 - rotation;
							break;
						case '2':
							rotation = 225 - rotation;
							break;
						case 'B':
							rotation = 270 - rotation;
							break;
						case '3':
							rotation = 315 - rotation;
							break;
						case 'R':
							rotation = 360 - rotation;
							break;
						case '4':
							rotation = 405 - rotation;
							break;
						default:
							System.out.println("If this prints then something's wrong in the first switch statement");		
						}
						
						//Puts the rotation within the correct bounds for the next switch statement
						if(rotation < 0)
							rotation += 360;
						if(rotation == 360)
							rotation = 0;
						if(rotation > 360 || rotation < 0)
							System.out.println("Something went wrong while finding the rotation!");
						
						//Moves the current position based on the calculated rotation
						switch(rotation)
						{
						case 0:
							col += 1;
							direction = 'L';
							break;
						case 45:
							col += 1;
							row += 1;
							direction = '2';
							break;
						case 90:
							row += 1;
							direction = 'B';
							break;
						case 135:
							col -= 1;
							row += 1;
							direction = '3';
							break;
						case 180:
							col -= 1;
							direction = 'R';
							break;
						case 225:
							col -= 1;
							row -= 1;
							direction = '4';
							break;
						case 270:
							row -= 1;
							direction = 'A';
							break;
						case 315:
							col += 1;
							row -= 1;
							direction = '1';
							break;
						default:
							System.out.println("If this prints then something's wrong in the second switch statement");		
						}
						
						//Puts the positions back within the bounds of the array, allowing them to 'loop' over like a videogame
						if(row > 8)
							row = 1;
						if(col > 8)
							col = 1;
						if(col < 1)
							col = 8;
						if(row < 1)
							row = 8;
						
						movesLeft--;
					}
					
					//System.out.println("Entering " + row + ", " + col + " with a " + grid[8 - row][col - 1] + " from the " + direction);
					//System.out.println("-----------Done!-----------");
					System.out.println(row + " " + col);
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
