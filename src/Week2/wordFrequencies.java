package Week2;

import java.util.ArrayList;
import edu.duke.*;

public class wordFrequencies {
	public static void main(String [] args){
		wordFrequencies wf = new wordFrequencies();
		wf.tester();

	}
	private static ArrayList<String> myWords;
	private static ArrayList<Integer> myFreqs;
	
	//Constructor
	public wordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
		
	}
	
	public static void findUnique(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
		myWords.clear();
		myFreqs.clear();
		
		FileResource resource = new FileResource();
		
		
		for (String s : resource.words()){
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			
			if (index == -1){
				myWords.add(s);
				myFreqs.add(1);
			}
			else {
				int value = myFreqs.get(index);
				myFreqs.set(index, value+1);
			}
		}
		
	}
	
	public static void tester(){
		findUnique();
		
		System.out.println("## of unique words are "+ myWords.size());
		
		for (int i=0; i<myWords.size(); i++){
			System.out.println(myFreqs.get(i)+ "\t"+myWords.get(i));
		}
		int max = findIndexOfMax();
		//System.out.println("Largest value of myfreqs is "+ findIndexOfMax());
		//System.out.println(myWords.get(max));
		System.out.println("The word that occurs most often and its count are: " + myWords.get(max) + " " + myFreqs.get(max));
		//System.out.println(myWords.size());
	}
	
	public static int findIndexOfMax(){
		int max = 0;
		int indexOfMax = -1;
		for (int k=0; k< myFreqs.size(); k++){
			if (myFreqs.get(k) > max){
				max = myFreqs.get(k);
				indexOfMax = k;
			}
		}
		return indexOfMax;
	}
}

