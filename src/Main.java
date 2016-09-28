/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Jake Klovenski
 * jdk2595
 * 16455
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
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
		
		dict = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
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
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		// TODO more code
		
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
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
	//
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
