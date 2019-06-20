/**
 * @author Michael Chunko
 * Date: 1/31/17
 * Desc: A program that takes in Strings composing of numbers and prints a list of numbers made from it <br>
 * under the conditions that: <br>
 * 		Each subsequent number is larger than the one before it <br>
 * 		Each number is created through reading the String left to right <br>
 * 		After each number has been made, the String is reversed
 */
import java.io.*;
import java.nio.file.*;
public class Ascending_Strings 
{
	public static void main(String[] args)
	{
		try 
		{
			//Reads in each line(String of numbers) from the input file
			for(String line: Files.readAllLines(Paths.get("D:/Mike/Actual Documents/ASCL.txt")))
			{
				//The first resulting number will always be just the first character
				int previousNum = Integer.parseInt(line.substring(0,1));
				System.out.print(line.charAt(0) + " ");
				line = line.substring(1, line.length());
				
				line = reverse(line);
				
				//Runs the algorithm for generating the next number
				while(!line.isEmpty())
				{
					int nextNum = 0;
					
					//Stops when the next number is bigger than the previous number
					while(!line.isEmpty() && nextNum < previousNum)
					{
						nextNum = Integer.parseInt(nextNum + line.substring(0,1));
						line = line.substring(1, line.length());
					}
					
					//To prevent the remaining characters of the string being printed out
					if(nextNum > previousNum)
					{
						line = reverse(line);
						System.out.print(nextNum + " ");
						previousNum = nextNum;
					}
				}
				System.out.println();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String that is the reverse of String s
	 * @param s - The String to be reversed
	 * @return result - The reversed String
	 */
	public static String reverse(String s)
	{
		String result = "";
		
		for(int i = s.length() - 1; i >= 0; i--)
		{
			result += s.charAt(i);
		}
		
		return result;
	}
}