import java.io.*;
import java.nio.file.*;
//TODO
//Make it so that when a row is filled in from a "size" being a clue, the slots in possible for the associated column are updated
//Make a method so that, when all values except one in possible are false, it fills in the appropriate value in puzzle
public class Skyscraper 
{
	static int[][] puzzle = null;
	public static void main(String[] args)
	{
		try 
		{
			//Reads in each set of clues
			for(String line: Files.readAllLines(Paths.get("D:/Mike/Actual Documents/ACSL.txt")))
			{
				
				boolean[][][] possible = null;
				int size = 0, count = 0;
				
				//Reads in each row of clues
				for (String part : line.split(",")) 
				{
					//Determines the array size
					if(count == 0)
					{
						size = part.length();
						puzzle = new int[size + 2][size + 2];
						possible = new boolean[size + 2][size + 2][size];
						
						for(int i = 0; i < possible.length; i++)
						{
							for(int j = 0; j < possible[0].length; j++)
							{
								for(int k = 0; k < possible[0][0].length; k++)
									possible[i][j][k] = true;
							}
						}
					}
					
					//If it's the first or last row of clues
					if(count == 0 || count == (size + 1))
					{
						for(int i = 0; i < size; i++)
							puzzle[count][i + 1] = Integer.parseInt(part.substring(i, i + 1));
					}
					//Else the other rows
					else
					{
						puzzle[count][0] = Integer.parseInt(part.substring(0, 1));
						puzzle[count][size + 1] = Integer.parseInt(part.substring(1, 2));
					}
					
					count++;
				}
				
				//Fills in the values that are known based on the first row of clues
				for(int i = 1; i <= size; i++)
				{
					if(puzzle[0][i] == 1)
					{
						puzzle[1][i] = size;
						for(int j = 1; j <= size; j++)
							possible[j][i][0] = false;
					}
					else
					{
						for(int j = puzzle[0][i]; j > 1; j--)
							possible[j - 1][i][size - 1] = false;
					}
					if(puzzle[0][i] == size)
					{
						for(int j = 1; j <= size; j++)
						{
							puzzle[j][i] = j;
							for(int k = 1; k <= size; k++)
								possible[j][k][j - 1] = false;
						}
					}
				}
				
				//Fills in the values that are known based on the last row of clues
				for(int i = 1; i <= size; i++)
				{
					if(puzzle[size + 1][i] == 1)
					{
						puzzle[size][i] = size;
						for(int j = 1; j <= size; j++)
							possible[j][i][0] = false;
					}
					else
					{
						for(int j = puzzle[size + 1][i]; j > 1; j--)
							possible[(size - j) + 2][i][size - 1] = false;
					}
					if(puzzle[size + 1][i] == size)
					{
						for(int j = 1; j <= size; j++)
						{
							puzzle[(size + 1) - j][i] = j;
							for(int k = 1; k <= size; k++)
								possible[(size + 1) - j][k][j - 1] = false;
						}
					}
				}
				
				//Fills in the values that are known based on the other row of clues
				for(int i = 1; i <= size; i++)
				{
					if(puzzle[i][0] == 1)
					{
						puzzle[i][1] = size;
						for(int j = 1; j <= size; j++)
							possible[i][j][0] = false;
					}
					else
					{
						for(int j = puzzle[i][0]; j > 1; j--)
							possible[i][j - 1][size - 1] = false;
					}
					if(puzzle[i][size+1] == 1)
					{
						puzzle[i][size] = size;
						for(int j = 1; j <= size; j++)
							possible[i][j][0] = false;
					}
					else
					{
						for(int j = puzzle[i][size + 1]; j > 1; j--)
							possible[i][(size - j) + 2][size - 1] = false;
					}
					if(puzzle[i][0] == size)
					{
						for(int j = 1; j <= size; j++)
						{
							puzzle[i][j] = j;
							for(int k = 1; k <= size; k++)
								possible[k][j][j - 1] = false;
						}
					}
					if(puzzle[i][size+1] == size)
					{
						for(int j = 1; j <= size; j++)
						{
							puzzle[i][(size + 1) - j] = j;
							for(int k = 1; k <= size; k++)
								possible[k][(size + 1) - j][j - 1] = false;
						}
					}
				}
				while(fillDetermined(possible, size));
				print(puzzle);
				System.out.println("------------------------");
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Pre: array is initialized
	//Post: prints out the contents of array in a 2D grid
	public static void print(int[][] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array[0].length; j++)
				System.out.print(array[i][j] + " ");
			System.out.println();
		}
	}
	
	public static boolean fillDetermined(boolean[][][] possible, int size)
	{
		boolean flag = false;
		int num = 0;
		
		for(int i = 1; i <= size; i++)
		{
			for(int j = 1; j <= size; j++)
			{
				num = 0;
				if(puzzle[i][j] == 0)
				{
					for(int k = 0; k < possible[0][0].length; k++)
					{
						if(num == 0 && possible[i][j][k] == true)
							num =  k + 1;
						else
						{
							if(num > 0 && possible[i][j][k] == true)
								num = -1;
						}
					}
					
					if(num > 0)
					{
						puzzle[i][j] = num;
						flag = true;
					}
				}
			}
		}
		
		return flag;
	}
}