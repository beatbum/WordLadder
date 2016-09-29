/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Jake Klovenski
 * jdk2595
 * 16450
 * Chris Oligschlaeger
 * cgo328
 * 16450
 * Slip days used: <0>
 * Git URL:https://github.com/beatbum/WordLadder.git
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	static Set<String> dict;
	
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
		dict = makeDictionary();	// Not sure this is necessary
						// Either call in BFS/DFS OR call it here and pass as parameter
	}
	
	/**
	 * If command is /quit, return empty ArrayList. 
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.nextLine();
		if(input.equals("/quit"))
		{
			return new ArrayList<String>();
		}
		int space_index = input.indexOf(" ");
		String start = input.substring(0,space_index-1);
		String end = input.substring(space_index+1);
		ArrayList<String> parsed = new ArrayList<String>(2);
		parsed.add(start);
		parsed.add(end);
		return parsed;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO more code
		boolean discovered[] = new boolean[dict.size()];
		ArrayList<String> dictionary = new ArrayList<String>(dict);
		Arrays.fill(discovered, false);
		discovered[dictionary.indexOf(start)] = true;
		ArrayList<String> ladder = recurseDFSLadder(start, end, discovered, dictionary);
		return ladder; // replace this line later with real return
	}

	private static ArrayList<String> recurseDFSLadder(String start, String end, boolean discovered[], ArrayList<String> dictionary)
	{
		boolean full_false[] = new boolean[dict.size()];
		Arrays.fill(full_false, false);
		if(distance(start,end) == 0)
		{
			ArrayList<String> val = new ArrayList<String>();
			val.add(start);
			return val;
		}	
		else if(!(discovered.equals(full_false)))
		{
			return new ArrayList<String>();
		}
		else
		{
			int min_diff = 1000;
			int place = 0;
			ArrayList<String> result = new ArrayList<String>();
			while(result.isEmpty())
			{
				for(int i = 0; i < dict.size(); i++)
				{
					if(distance(dictionary.get(i), start) == 1 && !discovered[i] && distance(dictionary.get(i), end) < min_diff)
					{
						place = i;
						min_diff = distance(dictionary.get(i), end);
					}
				}
				if(min_diff == 1000)
				{
					return new ArrayList<String>();
				}
				else
				{
					discovered[place] = true;
					result = recurseDFSLadder(dictionary.get(place), end, discovered,dictionary);
				}
				result.add(0,start);
				return result;
			}
		}
	
		return null;
	}
	
	/**
	 * Uses BFS to find the shortest word ladder between two word
	 * @param start is the String representing the first word of the ladder
	 * @param end is the String representing the last word of the ladder
	 * @return the ArrayList containing the ladder, or empty array if no ladder was found
	 */
   	public static ArrayList<String> getWordLadderBFS(String start, String end) {
    		HashSet<String> copy = new HashSet<String>(dict);				// Makes a copy of dictionary
    		String word = start;											// The current word being searched for
    		Node parent = new Node(start);
    		Node child;
    		int i = 0;
    		char j = 'a';
    	
		while ((!copy.isEmpty()) && (!word.equals(end))){
			while ((i <= word.length()) && (!word.equals(end))){		// Go through every possible word one letter away
				j = 'a';
				String temp = word;										// String that will be changed one letter at a time
			
				while ((j <= 'z') && (!temp.equals(end))){
					temp = temp.substring(0, i) + j + temp.substring(i + 1);
					if (copy.contains(temp)){							// Check if word is in dictionary
						child = new Node(temp, parent);
						parent.children.add(child);
						copy.remove(temp);
					} 
					j++;
				}
				i++;
			}
			
		}
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/**
	 * Print out each word of word ladder in order
	 * @param ladder is the ArrayList of words to be printed
	 **/
	public static void printLadder(ArrayList<String> ladder) {
		String word;
		for (int i = 0; i < ladder.size(); i++) {
			word = ladder.get(i);
			System.out.println(word);
		}
	}
	// TODO
	// Other private static methods here
	//

	/**
	 * Method returns the number of letters that Strings a and b do not have in common
	 * @param a is the first String
	 * @param b is the second String
	 * @return number of letters differing between a and b
	 **/
	private static int distance(String a, String b)
	{
		int delta = 0;
		for(int i = 0; i < a.length(); i++)
		{
			if(a.charAt(i) != b.charAt(i))
			{	
				delta+=1;
			}
		}	
		return delta;	
	}
	
}
