//------------------------------------------------------------------------
//Michael Chunko
//1/30/16
//The driver for a class containing various string methods
//------------------------------------------------------------------------
	import java.io.*;
import java.nio.file.*;
	
public class prog2driver 
{
	
	public static void main(String[] args) 
	{
		String method1str = "", method2str = "", method2str2 = "", method3str = "", method3str2 = "", method4str = "", method5str = "";
		int method1int = 0, method5int = 0;
		char method1char = '0', method4char = '0', method5char = '0';
		int current = 0, lineNum = 0;
		try 
		{
			for (String line : Files.readAllLines(Paths.get("D:/Mike/Documents/ACSLinput.txt"))) 
			{
				current = 0;
				for (String part : line.split(",")) 
				{
					if (lineNum == 0 && current == 0)
						method1str = part;
					else
					{
						if (lineNum == 0 && current == 1)
							method1int = Integer.parseInt(part);
						else
						{
							if (lineNum == 0 && current == 2)
								method1char = part.charAt(0);
							else
							{
								if (lineNum == 1 && current == 0)
									method2str = part;
								else
								{
									if (lineNum == 1 && current == 1)
										method2str2 = part;
									else
									{
										if (lineNum == 2 && current == 0)
											method3str = part;
										else
										{
											if (lineNum == 2 && current == 1)
												method3str2 = part;
											else
											{
												if (lineNum == 3 && current == 0)
													method4str = part;
												else
												{
													if (lineNum == 3 && current == 1)
														method4char = part.charAt(0);
													else
													{
														if (lineNum == 4 && current == 0)
															method5str = part;
														else
														{
															if (lineNum == 4 && current == 1)
																method5int = Integer.parseInt(part);
															else
															{
																if (lineNum == 4 && current == 2)
																	method5char = part.charAt(0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
					current++;
				}
				lineNum++;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		prog2 str = new prog2();
		System.out.println(str.char_split(method1str, method1int, method1char));
		System.out.println(str.strrem(method2str, method2str2));
		System.out.println(str.strchr(method3str, method3str2));
		System.out.println(str.strtok(method4str, method4char));
		System.out.println(str.wordwrap(method5str, method5int, method5char));

	}

}
