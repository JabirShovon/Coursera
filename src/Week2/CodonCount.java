package Week2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class CodonCount {
	
    private static HashMap<String, Integer> map;
	
	//Constructor
	public CodonCount(){
		map = new HashMap<String, Integer>();
		
	}
	
	public static void buildCodonMap(int start, String dna){
		String str;
		for (int i= start; i< dna.length()-2; i=i+3){
			str = dna.substring(i, i+3).toLowerCase();
			if (map.keySet().contains(str)){
				map.put(str, map.get(str)+1);
			}
			else {
				map.put(str, 1);
			}
		}
	}
	
	public String getMostCommonCodon(){
		int maxValueInMap=(Collections.max(map.values()));
		String freqDNA = "";
		for (Entry<String, Integer> entry : map.entrySet()){
			if (entry.getValue()==maxValueInMap){
				freqDNA = entry.getKey();
			}
		}
		return freqDNA;
	}
	
	
	public static void printCodonCounts(int start, int end){
		for (Entry<String, Integer> entry : map.entrySet()){
			if (entry.getValue() >= start && entry.getValue() <= end){
				System.out.println(entry.getKey().toUpperCase()+"\t"+entry.getValue());
			}
		}
	}
	
	public void tester() {
		String dna = "ATTAATACTTTGTTTAACAGTAATTATTCAACTATTAAATATTTAAATAATTAAGTTATTAAACTATTAAGTACAGGGCCTGTATCTCTGATGCTGAA"
				+ "CTATGATGTGTGACTTAAGCCCCCAAATACATCATGTTATTTGGATCCAAGGTGCTGCACAGAACGCTGACCCTCTCTAAGAGCTGGGTATTACT";
		buildCodonMap(1, dna);
		String mostFreq = getMostCommonCodon();
		printCodonCounts(1, 7);
	}
	
	public static void main(String[] args) {
		CodonCount codon = new CodonCount();
		codon.tester();
		
	}
	
	
	
}

