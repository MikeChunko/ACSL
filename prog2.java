//------------------------------------------------------------------------
//Michael Chunko
//1/30/16
//A class containing various string methods
//------------------------------------------------------------------------
public class prog2 
{
	//public static void prog2()
	//{
	//	
	//}
	
	public String char_split (String a$, int n, char c)
	{
		String result = "";
		int count = 0;
		
		for (int current = 0; current < a$.length(); current++)
		{
			if (count < n)
			{
				result += a$.charAt(current);
				count++;
			}
			else
			{
				result += "" + c + a$.charAt(current);
				count = 1;
			}
		}
		
		return result;
	}
	
	/**
	 * @param a$  string1
	 * @param b$ string2
	 * @return			string3
	 */
	public String strrem (String a$, String b$)
	{
		String result = a$;
		
		result = result.replaceAll(b$, "");
		
		return result;
	}
	
	public String strchr (String a$, String b$)
	{
		String result = "";
		boolean flag = true;
		
		for (int current = 0; current < a$.length() && flag == true; current++)
		{
			if (a$.charAt(current) == b$.charAt(0))
			{
				flag = false;
				
				for (int bCurrent = 0; flag == false && (current + bCurrent) < a$.length() && bCurrent < b$.length(); bCurrent++)
				{
					if (a$.charAt(current + bCurrent) == b$.charAt(bCurrent))
						flag = false;
					else
						flag = true;
				}
			}
			
			if (flag == true)
				result += a$.charAt(current);
		}
		
		return result;
	}
	
	public String strtok (String a$, char b$)
	{
		String result = "";
		
		for (int current = 0; current < a$.length(); current++)
		{
			if (a$.charAt(current) == b$)
				result += " " + a$.charAt(current);
			else
				result += a$.charAt(current);
		}
		
		return result;
	}
	
	public String wordwrap (String a$, int n, char b$)
	{
		String result = "";
		int count = 0;
		
		for (int current = 0; current < a$.length(); current++)
		{
			if (count >= n || a$.charAt(current) == b$)
			{
				result += " " + a$.charAt(current);
				count = 0;
			}
			else
				result += a$.charAt(current);
			
			count ++;
		}
		
		return result;
	}
}
