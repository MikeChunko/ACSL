//Michael Chunko

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Enclosure {
    static char[] key = {'{', '}', '[', ']', '(', ')'};

    public static void main(String[] args) {
        try {
            //Reads in each line from the input file
            for (String line : Files.readAllLines(Paths.get("D:/Mike/Documents/ACSL.txt"))) {
                int[] indexes = {-1, -1, -1, -1, -1, -1};
                int missing = 0;

                //Finds the index of each encloser
                //Remains at -1 if encloser does not exist in the string
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '{':
                            indexes[0] = i;
                            break;
                        case '}':
                            indexes[1] = i;
                            break;
                        case '[':
                            indexes[2] = i;
                            break;
                        case ']':
                            indexes[3] = i;
                            break;
                        case '(':
                            indexes[4] = i;
                            break;
                        case ')':
                            indexes[5] = i;
                            break;
                    }
                }

                //Finds which encloser is missing by checking which has an index equal to -1
                for (int i = 0; i < indexes.length; i++) {
                    if (indexes[i] == -1)
                        missing = i;
                }

                System.out.println(getLocations(line, missing, indexes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param c The character to be tested
     * @return boolean
     * <p>Returns true if the character is one of the four basic mathematical operators
     */
    public static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '/' || c == '*')
            return true;
        return false;
    }

    /**
     * @param line    The string containing the mathematical expression
     * @param missing The index of the missing encloser
     * @param indexes The array of the indexes of all enclosers
     * @return A string containing the potential locations for the missing encloser
     * <p>Returns all the potential locations for an encloser
     * @
     */
    public static String getLocations(String line, int missing, int[] indexes) {
        String result = "";
        int currentIndex = 0;

        //Finds the possible locations for if the missing encloser is an 'open' encloser ('{', '[', '(')
        if (missing % 2 == 0) {
            //Starts the search where the missing encloser's "pair" is closed
            currentIndex = indexes[missing + 1];
            boolean operatorFound = false, bracketOpened = false, paraOpened = false, bracketClosed = false, paraClosed = false;

            //Determines how far into the string should be searched
            int finalIndex = -1;
            if (key[missing] == '(')
                finalIndex = indexes[2];
            else if (key[missing] == '[')
                finalIndex = indexes[0];

            while (currentIndex > finalIndex) {
                if (isOperator(line.charAt(currentIndex))) {
                    operatorFound = true;
                    currentIndex--;
                }

                if (line.charAt(currentIndex) == '[')
                    bracketOpened = true;

                if (line.charAt(currentIndex) == '(')
                    paraOpened = true;

                if (line.charAt(currentIndex) == ']')
                    bracketClosed = true;

                if (line.charAt(currentIndex) == ')')
                    paraClosed = true;

                //Determines when an index is the appropriate location
                if (operatorFound) {
                    //Lord forgive me for these conditions but they work
                    //Checks to make sure the location isn't an illegal nest
                    if (key[missing] != '{' || (!bracketClosed && !paraClosed)
                            || (bracketClosed && bracketOpened && (!paraClosed || (paraClosed && paraOpened))
                            || (!bracketClosed && paraOpened && paraClosed)))
                        if (key[missing] != '[' || !paraClosed || (paraOpened && paraClosed))
                            //Checks to make sure the location isn't in the middle of a number
                            if ((currentIndex - 1 > -1 && !(line.charAt(currentIndex - 1) >= '0' && line.charAt(currentIndex - 1) <= '9'))
                                    || currentIndex - 1 == finalIndex) {
                                if (result != "")
                                    result = currentIndex + 1 + "," + result;
                                else
                                    result = "" + (currentIndex + 1);
                            }
                }

                currentIndex--;
            }
        }
        //Finds the possible locations for if the missing encloser is an 'closed' encloser ('}', ']', ')')
        else {
            //Starts the search where the missing encloser's "pair" is opened
            currentIndex = indexes[missing - 1] + 1;
            boolean operatorFound = false, bracketClosed = false, paraClosed = false, bracketOpened = false, paraOpened = false;

            //Determines how far into the string should be searched
            int finalIndex = line.length();
            if (key[missing] == ')')
                finalIndex = indexes[3];
            else if (key[missing] == ']')
                finalIndex = indexes[1];

            while (currentIndex < finalIndex) {
                if (isOperator(line.charAt(currentIndex))) {
                    operatorFound = true;
                    currentIndex++;
                }

                if (line.charAt(currentIndex) == '[')
                    bracketOpened = true;

                if (line.charAt(currentIndex) == '(')
                    paraOpened = true;

                if (line.charAt(currentIndex) == ']')
                    bracketClosed = true;

                if (line.charAt(currentIndex) == ')')
                    paraClosed = true;

                //Determines when an index is the appropriate location
                if (operatorFound) {
                    //Lord forgive me for these conditions but they work
                    //Checks to make sure the location isn't an illegal nest
                    if (key[missing] != '}' || (!bracketOpened && !paraOpened)
                            || (bracketClosed && bracketOpened && (!paraOpened || (paraOpened && paraClosed))
                            || (!bracketOpened && paraOpened && paraClosed)))
                        if (key[missing] != ']' || !paraOpened || (paraOpened && paraClosed))
                            //Checks to make sure the location isn't in the middle of a number
                            if ((currentIndex + 1 < line.length() && !(line.charAt(currentIndex + 1) >= '0' && line.charAt(currentIndex + 1) <= '9'))
                                    || currentIndex + 1 == finalIndex) {
                                if (result != "")
                                    result += "," + (currentIndex + 2);
                                else
                                    result = "" + (currentIndex + 2);
                            }
                }

                currentIndex++;
            }
        }

        return result;
    }
}