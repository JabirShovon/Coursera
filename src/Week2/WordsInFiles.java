package Week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
	
	private static HashMap<String, List<String>> wordMap;
	
	//Constructor
	public WordsInFiles(){
		wordMap = new HashMap<String, List<String>>();
	}
	
	private void addWordsFromFile(File f){
		FileResource file = new FileResource();
		for (String word : file.words()){
			if (wordMap.containsKey(word)){
				List<String> fileList = wordMap.get(word);
				if (!fileList.contains(f.getName())){
					fileList.add(f.getName());
				}
			}
			else {
				List<String> fileList = new ArrayList<String>();
				fileList.add(f.getName());
				wordMap.put(word, fileList);
			}
		}
	}
	
	
	public void buildWordFileMap(){
		wordMap.clear();
		DirectoryResource dr =  new DirectoryResource();
		for (File f : dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}
	
	
	public int maxNumber(){
		int max = 0;
		// Map.values() returns only the values, not keys, to iterate over.
		for (List<String> fileList : wordMap.values()){
			if (fileList.size() > max){
				max = fileList.size();
			}
		}
		return max;
	}
	

	
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> strList = new ArrayList<String>();
		// Map.values() only returns values of a map. But here we need both key and respective values. So here Entry<?,?>
		// is used to iterate over the map.
		for (Entry<String, List<String>> entry : wordMap.entrySet()){
			if (entry.getValue().size() == number){
				strList.add(entry.getKey());
			}
		}
		return strList;
		
	}
	
	public void printFilesIn(String word){
		//fileList should be List, not ArrayList as wordMap maps to a List type object not ArrayList type object.
		List<String> fileList = wordMap.get(word); 
		for (int i=0; i< fileList.size(); i++){
			System.out.println(fileList.get(i));
		}
		
		// Another way of printing contents of an ArrayList
		/*for (String file : fileList) {
			System.out.println(file);
		}*/	
	}
	
	public void tester() {
		/*buildWordFileMap();
		int number = maxNumber();
		List<String> words = wordsInNumFiles(number);
		for (String word : words){
			printFilesIn(word);
		}*/
		
		
		buildWordFileMap();
		int number = maxNumber();
		List<String> words = wordsInNumFiles(number);
		System.out.println("Max num of files: " + number);
		System.out.println("Total of " + words.size() + " words that are in " + number + " files:");
		for (String word : words) {
			System.out.println("\"" + word + "\" appears in the files:");
			for (String file : wordMap.get(word)) {
				System.out.println("\t" + file);
			}
		}
		
	}
	
	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.tester();
		
	}
	
}
