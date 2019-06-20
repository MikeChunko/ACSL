//------------------------------------------------------------------------
//Michael Chunko
//4/26/16
//A program made to simulate regular expressions
//------------------------------------------------------------------------
import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Regex 
{
	public static void main(String[] args) 
	{
		String array[] = new String[10];
		String strCurrent = "";
		int lineNum = 0;
		String output = "";
		
		try 
		{
			//Reads in each line
			for (String line : Files.readAllLines(Paths.get("D:/Mike/Documents/CSL Commands.txt")))
			{
				if (lineNum == 0)
				{
					int current = 0;
					for (String part : line.split(",")) 
					{
						//if (part.equals("#"))
							//array[current] = null;
						//else
							array[current] = part;
						current++;
					}
				}
				else
				{
					
					output = "";
					strCurrent = line;
					Pattern p = Pattern.compile("^" + strCurrent + "$");
					for (int i = 0; i < array.length; i++)
					{
						Matcher m = p.matcher(array[i]);
						if (m.find()) {
							output += array[i] + ", ";
						}
						
					}
					output = output.substring(0, output.length() - 2);
					System.out.println(output);
				}
				
				lineNum++;
			}
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
