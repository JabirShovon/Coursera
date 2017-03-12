package Week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class charactersInPlay {
	
	private static ArrayList<String> strings;
	private static ArrayList<Integer> Freqs;
	
	public charactersInPlay() {
		strings = new ArrayList<String>();
		Freqs = new ArrayList<Integer>();
	}
	

	public static void update(String person) {
		int index = strings.indexOf(person);

		if (index == -1) {
			strings.add(person);
			Freqs.add(1);
		} else {
			int value = Freqs.get(index);
			Freqs.set(index, value + 1);
		}
	}

	public static void findAllCharacters(){
		FileResource resource = new FileResource();
		
		for (String line : resource.lines()){
			int index2 = line.indexOf('.');
			if (index2 != -1){
				String person = line.substring(0, index2);
				update(person);
			}
		}
	}

	public static void characterWithNumParts(int num1, int num2) {
		for (int i=0; i < strings.size(); i++){
			if (Freqs.get(i)>= num1 && Freqs.get(i)<= num2){
				System.out.println(strings.get(i));
			}
		}
	}
	
	public static int findIndexOfMax(){
		int max = 0;
		int indexOfMax = -1;
		for (int k=0; k< Freqs.size(); k++){
			if (Freqs.get(k) > max){
				max = Freqs.get(k);
				indexOfMax = k;
			}
		}
		return indexOfMax;
	}
	
	
	public void tester(int minimumCount) {
		findAllCharacters();
		for (int i = 0; i < strings.size(); i++) {
			if (Freqs.get(i) >= minimumCount) {
				System.out.println(strings.get(i) + " " + Freqs.get(i));
			}
		}
		characterWithNumParts(10,15);
	}
		
		public static void main(String[] args) {
			charactersInPlay cip = new charactersInPlay();
			cip.tester(0);
			System.out.println(strings.size());
			int max = findIndexOfMax();
			//System.out.println("Largest value of myfreqs is "+ findIndexOfMax());
			//System.out.println(strings.get(max));
			System.out.println("The word that occurs most often and its count are: " + strings.get(max) + " " + Freqs.get(max));
			
		}
	}
		

