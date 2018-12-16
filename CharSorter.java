/* This program take a string and returns the frequency of each character.  The first method organizes the frequencies in alphabetical order.
The second method organizes the frequencies by decreasing frequencies. The third method takes the frequencies of each character type.  */

import java.util.*;

public class CharSorter
{
    public static int[][] alphabeticalSort(String input) // method organizes frequencies into alphabetical order
    {
        int [] unordered = new int[input.length()]; // 1D array of all the characters of the string in integer form
        int length = unordered.length; // length of the array
        int [] ordered = new int[input.length()]; // int array of characters in alphabetical order
        int greater = 0; // records the times that a character is higher (ASCI)
        int uniqueChar = 0; // number of unique characters

        int prevInd = 0; //
        int charFreq = 0; //

        String repeat = ""; //String where all checked characters are inputted

        int[] freq = new int[input.length()]; // 1D array that takes the frequencies

        for (int i = 0; i < input.length(); i++ ) // this for loop puts the characters in the string into an unordered integer array
        {
            unordered[i] = (int) input.charAt(i);
        }

        for (int j = 0; j < length; j++) //This double for loop compares the value in a cell in the array to the rest of the values in that array.
        {
            for (int k = 0; k < length; k++)
            {
                if ((repeat.indexOf((char)unordered[k]) == -1)) //Checks that the character has not been checked before
                {
                    if((unordered[j] > unordered[k]) ) // Compares values in array to other values
                    {
                        greater++; // If the value is larger one is added to greater
                    }
                    repeat = repeat + ((char)unordered[k]); // keeps track of the characters that have been checked
                    if (j == 0) // THis if statement keeps track of the number of unique characters
                        uniqueChar++; // Adds one to uniqueChar every time a new character is experienced
                }
            }
            ordered[greater] = unordered[j]; // puts the int values into an array ordered in alphabetical order
            greater = 0; // resets the variable
            repeat = ""; //resets the string of used characters
        }

        for (int count = 0; count < input.length(); count++) // for statement checks for the frequency of each character of a string
        {
            while(input.indexOf((char)(ordered[count]), prevInd) != -1) // while needed to check that the character has not been checked before
            {
                charFreq++; // adds one to the frequency of that chosen character
                prevInd = 1 + input.indexOf(ordered[count], prevInd); // decides the next index after the last character has been checked
            }

            freq[count] = charFreq; // stores the frequency of the character into an array
            charFreq = 0; // resets the character frequency for that character
            prevInd = 0; // resets the index
        }

        int[][] orderedWithFreq = new int[uniqueChar][2]; // new array in alphabetical order with frequencies. Only lists a character once

        for (int z = 0; z < uniqueChar; z++) //for loop combined the alphabetized ints with their frequencies into one array named orderedWithFreq
        {
            orderedWithFreq[z][0] = ordered[z];
            orderedWithFreq[z][1] = freq[z];
        }

        return orderedWithFreq; // returns the array back to main
    }

    public static int[][] frequencySort(String input)
    {
        int [] unordered = new int[input.length()]; // Makes array the length of string

        int openIndex = 1; //last place to be checked
        int uniqueFreq = 0; // number of unique frequencies
        int numdiffChar = 1; // number of unique characters

        for (int i = 0; i < input.length(); i++ )
        {
            unordered[i] = (int) input.charAt(i); // Takes string and puts each individual character into 1d int array
        }

        int [] freq = new int [input.length()]; // creates a new array the length of the string to store frequencies
        int freq1 = 0; // freq of character
        String past = ""; // String where stored characters are used
        int[][] decreasingFreq = new int[input.length()][input.length() + 1]; // 2d array with characters listed by decreasing freq with frequencies
        String orderTies = ""; // Keeps track of characters that are tied

        for (int count = 0; count < input.length(); count++)
        {
            openIndex = 1; // Tells program what the next open index is for ties
            orderTies = ""; // characters that are tied for frequency
            freq1 = 0; // frequency for the character is being checked

            if (past.indexOf((char)(unordered[count])) == -1) // if statement checks that character has not already been checked
            {
                for (int count2 = 0; count2 < input.length(); count2++)
                {
                    if (unordered[count] == unordered[count2]) // compares characters in a array
                    {
                        freq1++; // adds one to frequency of character
                    }

                }
                past = past.concat(Character.toString((char)(unordered[count]))); // adds character to the string of checked characters.

                if ((decreasingFreq[input.length() - freq1][0]) ==  0) // puts frequencies into an array decreasingFreq. Only if cell is empty
                {
                    decreasingFreq[input.length() - freq1][0] = freq1; // puts in frequency
                    decreasingFreq[input.length()-freq1][1] = unordered[count]; // puts in characters underneath
                    uniqueFreq++; // adds to number of unique frequencies

                }
                else if ((decreasingFreq[input.length() - freq1][0]) == freq1) // adds characters of the same frequency
                {
                    for (int i = 1; decreasingFreq[input.length() - freq1][i] != 0; i++)
                    {
                        openIndex++; // determines the first open space that is available
                    }
                    decreasingFreq[input.length() - freq1][openIndex] = unordered[count]; // puts the character into the array
                    if (openIndex > numdiffChar) // determines the dimensions of the array.
                        numdiffChar = openIndex; // stops array from adding anymore
                    for (int i = 1; i <= openIndex; i++) // for loop takes into consideration any frequency ties
                    {
                        orderTies = orderTies.concat(Character.toString((char)decreasingFreq[input.length() - freq1][i])); // adds the character to the string containing ties
                    }
                    int[][] alpha = alphabeticalSort(orderTies); // alphabetises tied characters. returns 2d string. only worried about characters

                    for (int k = 1; k <= openIndex; k++) // for loops puts tied characters back into array in alphabetical order
                    {
                        decreasingFreq[input.length() - freq1][k] = alpha[k-1][0];
                    }
                }
            }
        }
        int count3 = 0;

        int[][] finishedArray = new int[uniqueFreq][numdiffChar +1]; // creates array with dimensions of the number of different frequencies by number of total unique characters. Gets rid of gaps and 0's
        for (int count = 0; count < input.length(); count++)
        {
            if (decreasingFreq[count][0] != 0 ) // checks that their is value in the cell
            {
                finishedArray[count3][0] = decreasingFreq[count][0]; // inputs the frequencies into finished array
                for (int count4 = 1; count4 <= numdiffChar; count4++)
                {
                    finishedArray[count3][count4] = decreasingFreq[count][count4]; // puts the characters underneath their frequencies and they are already alphabetised.

                }
                count3++; // continues iteration
            }
        }
        return finishedArray; // returns the finished array back to main
    }

    public static int[] charTypes(String input) // This method finds the frequencies of each character type
    {
        int[][] alphabeticalSort = alphabeticalSort(input); // Takes the string and puts it into first method
        // Returns a 2d string with the frequency of each character already found
        int[] freqTypes = new int[4]; // where the frequencies will be stored
        int typeFreq = 0;


        for (int count = 0; count < alphabeticalSort.length; count++) // This for loop ensures that we check every character in the string returne
        {
            if ((alphabeticalSort[count][0] >= 65) && (alphabeticalSort[count][0] <= 90) || (alphabeticalSort[count][0] >97) && ((alphabeticalSort[count][0] <= 122)) ) //This if statement uses ASCI values to check if the chosen character is a letter
            {
                freqTypes[0] = freqTypes[0] + alphabeticalSort[count][1]; // puts the frequency of this char type into index 0
            }
            else if ((alphabeticalSort[count][0] >= 48) && (alphabeticalSort[count][0] <= 57)) // checks if chosen character is a number
            {
                freqTypes[1] = freqTypes[1] + alphabeticalSort[count][1]; // puts the frequency of this char type into index 1
            }
            else if ((alphabeticalSort[count][0] == 32)) // checks if chosen character is a space
            {
                freqTypes[2] = freqTypes[2] + alphabeticalSort[count][1]; // puts the frequency of this char type into index 2
            }
            else // checks if chosen character is anything else which in this case will be symbols
            {
                freqTypes[3] = freqTypes[3] + alphabeticalSort[count][1]; // puts the frequency of this char type into index 3
            }
        }
        return freqTypes; // returns the array back to main
    }

    public static void main(String[] args)
    {
        Boolean playOn = true; // Boolean for looping into the menu
        Scanner scnr = new Scanner(System.in); // Receives user input
        Boolean inputError = false; // Boolean used for while loop to check for bad input
        int option = 0; // Option that user inputs


        System.out.println("Welcome to the Character Sorter Program \nPlease input a string to be sorted"); //  Tells the user to input string

        String input = scnr.nextLine(); // String that is to be checked for frequencies

        while (playOn) // While loop that keeps bringing use back to the menu once an option is chosen
        {

            while (!inputError) // this while loop ensures that the user has given correct menu input
            {
                System.out.println("\nPlease select the option that you would like to see \n \n 1. Display character frequencies alphabetically \n 2. Display sorted frequencies \n 3. Show types of character frequencies \n 4. Exit");
                if (scnr.hasNextInt()) // Checks that the user inputted an integer
                {
                    option = scnr.nextInt(); // Stores the integer that the user chose into option
                    if (option >= 1 && option <= 4) // Ensures that the user chose an integer between 1 to 4
                    {
                        inputError = true; // Gets user out of while loop. User has put in correct input

                    }
                    else // User put an integer that is not between numbers 1- 4
                    {
                        System.out.println("Error, bad input please enter a number 1-4"); // Alerts the user of error


                    }

                }
                else // If the user has not inputted an integer
                {
                    scnr.next(); // Clears the scanner
                    System.out.println("Error, bad input please enter a number 1-4"); // Alerts the user of error



                }
            }
            int acceptedOption = option; // new int that will go into the switch once all the tests have been completed

            switch(acceptedOption) // This switch statement takes the user menu choice and sends the string into the method that will get the chosen resullt
            {
                case 1: // Lists characters with frequencies in alphabetical order
                    int[][] alphabetSort = alphabeticalSort(input); // 2d array that has in the top row has the characters in alphabetical order with their frequencies in the row below them
                        //Array above also puts string through method alphabetical Sort
                    int length = alphabetSort.length; // length of the array used for for loop
                    for (int i = 0; i < length; i++ ) // for loop prints the array with the character first in their desired freq
                    {
                        System.out.println(((char) alphabetSort[i][0]) + " freq: " + alphabetSort[i][1]);
                    }
                    inputError = false; // Ensures that user goes back to menu while loop
                    break; // exits switch

                case 2: //This case prints each character and frequency by decreasing frequency
                    int[][] freqSort = frequencySort(input); // Creates 2d array with frequencies in the first row and characters with the same frequency below them. Characters below are also listed in alphabetical order
                    System.out.println("The sorted by frequency characters are:\n");
                    for (int i = 0; i < freqSort.length; i++) // Double for loop is needed to print the char with frequencies in decreasing order
                    {
                        for  (int j = 1; (j < freqSort[0].length) && (freqSort[i][j] != 0); j++)
                        {
                            System.out.println((char)freqSort[i][j] + " freq: " + freqSort[i][0]); // Prints each char with frequency by decreasing frequencies
                        }
                    }
                    inputError = false; // ensures that user gets back to menu while loop
                    break; // exits switch
                case 3: // this case gives the frequency of each character type
                    int[] charTypes = charTypes(input); // creates 1d array and puts the frequency of each type
                    System.out.println("Textual Character count: " + charTypes[0]); // frequency of textual characters stored in index 0
                    System.out.println("Numerical Character Count: " + charTypes[1]); // frequency of numerical characters stored in index 1
                    System.out.println("WhiteSpace Character Count: " + charTypes[2]); // frequency of space characters stored in index 2
                    System.out.println("Symbol Character Count: " + charTypes[3]); // frequency of symbol characters stored in index 0
                    inputError = false; // ensures user goes back into menu while loop
                    break; // exits out of switch
                case 4:
                    System.out.println("\nCharacter Sorter Exited Successfully"); // notifies user that they have exited
                    return; // terminates program
            }
        }

    }
}

