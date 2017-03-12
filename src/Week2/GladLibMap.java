package Week2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private ArrayList<String> usedWords;
	private ArrayList<String> usedLabelList;
	
	private Random myRandom;
	
	private Map<String, List<String>> myMap;
	
	@SuppressWarnings("unused")
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
		usedWords = new ArrayList<String>();
		usedLabelList = new ArrayList<String>();
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		myMap = new HashMap<>();
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		
		for (String str : labels){
			List<String> wordList = readIt(source + "/" + str + ".txt");
			myMap.put(str, wordList);
		}
	}
	
	private String randomFrom(List<String> wordLabelList){
		int index = myRandom.nextInt(wordLabelList.size());
		return wordLabelList.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		//List<String> wordLabelList = myMap.get(label);
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		
		//usedWords = new ArrayList<String>();
		
		
		String sub = getSubstitute(w.substring(first+1,last));
		
		while (usedWords.contains(sub)){
			sub = getSubstitute(w.substring(first+1,last));
		}
		usedWords.add(sub);
		
		if (myMap.containsKey(w.substring(first+1,last)) && !usedLabelList.contains(w.substring(first+1,last))) {
			usedLabelList.add(w.substring(first+1,last));
		}
		
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap(){
		int totalNumber = 0;
		for(List<String> varList : myMap.values()){
			totalNumber += varList.size();
		}
		return totalNumber;
	}
	
	public int totalWordsConsidered(){
		int total = 0;
		for (String label : usedLabelList){
			total += myMap.get(label).size();
		}
		return total;
	}
	
	public void makeStory(){
	    System.out.println("\n");
	    usedWords.clear();
		//String story = fromTemplate("data/madtemplate.txt");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nTotal number of words replaced in the story is "+ usedWords.size());
		System.out.println("Total words in map: " + totalWordsInMap());
		System.out.println("Total words considered: " + totalWordsConsidered());
	}
	
	public static void main(String[] args) {
		GladLibMap glad = new GladLibMap();
		glad.makeStory();
	}

}

