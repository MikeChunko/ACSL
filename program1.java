//------------------------------------------------------------------------
//Michael Chunko
//12/13/15
//A program that chooses, from a pre-determined hand of 5 cards and a 
//pre-determined leading card, which card to play in a game of agram
//------------------------------------------------------------------------
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class program1 
{
	public static void main(String[] args)
	{	
		try 
		{
			//Reads in each line(leading card and hand) from the input file
			for(String line: Files.readAllLines(Paths.get("D:/Mike/Actual Documents/ASCL.txt")))
			{
				int[] numbers = new int[6];
				char[] originalNumbers = new char[6];
				char[] suits = new char[6];
				
				int i = 0;
				
				//Separates the line into parts representing a single card
				for(String part: line.split(","))
				{
					//Stores the number or "rank" of the card converted to an int(T = 10, J = 11, Q = 12, K = 13, A = 1)
					if(part.charAt(0) == 'T')
						numbers[i] = 10;
					else
					{
						if(part.charAt(0) == 'J')
							numbers[i] = 11;
						else
						{
							if(part.charAt(0) == 'Q')
								numbers[i] = 12;
							else
							{
								if(part.charAt(0) == 'K')
									numbers[i] = 13;
								else
								{
									if(part.charAt(0) == 'A')
										numbers[i] = 1;
									else
									{
										numbers[i] = Integer.parseInt(part.substring(0,1));
									}
								}
							}
						}
					}
					//Stores the original rank, not converted to an int
					originalNumbers[i] = part.charAt(0);
					
					//Stores the suit of the card
					suits[i] = part.charAt(1);
					i++;
				}
				
				ArrayList<Integer> possiblePlays = new ArrayList<Integer>();
				
				//Finds cards of the same suit as the lead card
				for(int j = 1; j < 6; j++)
				{
					if(suits[j] == suits[0])
					{
						possiblePlays.add(j);
					}
				}
				
				//Goes through the process of choosing the right card if there is at least one card in the hand that is the same suit as the lead card
				if(!possiblePlays.isEmpty())
				{
					int min = -1;
					int minHigherThanLead = -1;
					
					for(int k = 0; k < possiblePlays.size(); k++)
					{
						if(min == -1 || numbers[possiblePlays.get(k)] < numbers[possiblePlays.get(min)])
							min = k;
						
						if((numbers[possiblePlays.get(k)] > numbers[0] && minHigherThanLead == -1) ||
								(numbers[possiblePlays.get(k)] > numbers[0] && numbers[possiblePlays.get(k)] < numbers[possiblePlays.get(minHigherThanLead)]))
							minHigherThanLead = k;
					}
					
					if(minHigherThanLead != -1)
						System.out.println("" + originalNumbers[possiblePlays.get(minHigherThanLead)] + suits[possiblePlays.get(minHigherThanLead)]);
					else
						System.out.println("" + originalNumbers[possiblePlays.get(min)] + suits[possiblePlays.get(min)]);
				}
				else
				{
					//Searches for the lowest ranking card, adjusted to suit weight (clubs > diamonds > hearts > spades)
					int[] suitRanks = new int[6];
					
					for(int k = 1; k < 6; k++)
					{
						if(suits[k] == 'C')
							suitRanks[k] = 1;
						else
						{
							if(suits[k] == 'D')
								suitRanks[k] = 2;
							else
							{
								if(suits[k] == 'H')
									suitRanks[k] = 3;
								else
									suitRanks[k] = 4;
							}
						}
					}
					int min = -1;
					
					for(int k = 1; k < 6; k++)
					{
						if(min == -1 || numbers[k] < numbers[min] || (numbers[k] == numbers[min] && suitRanks[k] < suitRanks[min]))
							min = k;
					}
					System.out.println("" + originalNumbers[min] + suits[min]);
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}