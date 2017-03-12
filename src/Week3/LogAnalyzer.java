package Week3;


//import java.text.Format;
//import java.text.SimpleDateFormat;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Map.Entry;

import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
    	 FileResource fr = new FileResource();
    	 for (String line : fr.lines()){
    		 LogEntry le = WebLogParser.parseEntry(line);
    		 records.add(le);
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
    	 ArrayList<String> uniqIPAdrs = new ArrayList<String>();
    	 for (LogEntry le: records){
    		 String ipAddrr = le.getIpAddress();
    		if(!uniqIPAdrs.contains(ipAddrr)){
    			uniqIPAdrs.add(ipAddrr);
    		}
    	 }
    	 return uniqIPAdrs.size();
     }
     
     public void printAllHigherThanNum(int num){
    	 for (LogEntry le : records){
    		int status = le.getStatusCode();
    		if (status > num){
    			System.out.println(status);
    		}
    	 }
     }
     
     public int uniqueIPVisitsOnDay(String someday){
    	 ArrayList<String> uniqIPDay = new ArrayList<String>();
    	 for (LogEntry le: records){
    		 String ipAddrr = le.getIpAddress(); 
    		 String currentDate = le.getAccessTime().toString();
    		 if (!uniqIPDay.contains(ipAddrr) && currentDate.indexOf(someday)!=-1){
    			 uniqIPDay.add(ipAddrr);
    		 }
    	 }
    	 return uniqIPDay.size();
     }
     
     
     public int countUniqueIPsInRange(int low, int high){
    	 ArrayList<String> uniqIPinRange = new ArrayList<String>();
    	 for (LogEntry le : records){
    		String ipAddrr = le.getIpAddress();
     		int status = le.getStatusCode();
     		if (status >= low && status <= high && !uniqIPinRange.contains(ipAddrr)){
     			uniqIPinRange.add(ipAddrr);
     		}
     	 }
    	 return uniqIPinRange.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
    	 HashMap<String, Integer> visitListMap = new HashMap<String, Integer>();
    	 for (LogEntry le : records){
    		 String ipAddr = le.getIpAddress();
    		 if (!visitListMap.containsKey(ipAddr)){
    			 visitListMap.put(ipAddr, 1);
    		 }
    		 else {
    			 visitListMap.put(ipAddr, visitListMap.get(ipAddr)+1);
    		 }
    	 }
    	 return visitListMap;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> visitListMap){
    	 int max=0;
    	 for (int number : visitListMap.values()){
    		 if (number > max){
    			 max=number;
    		 }
    	 }
    	 return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitListMap){
    	 ArrayList<String> mostVisitedIPList = new ArrayList<String>();
    	 LogAnalyzer la = new LogAnalyzer();
    	 int max = la.mostNumberVisitsByIP(visitListMap);
    	 for (Entry<String, Integer> entry : visitListMap.entrySet()){
    		 if (entry.getValue()==max){
    			 mostVisitedIPList.add(entry.getKey());
    		 }
    	 }
    	 return mostVisitedIPList;
     }
     
     // Updating both key and values at different stages
     public HashMap<String, ArrayList<String>> iPsForDays(){
    	 HashMap<String, ArrayList<String>> ipDaysMap = new HashMap<String, ArrayList<String>>();
    	 for (LogEntry le : records){
    		 String day = le.getAccessTime().toString();
    		 day = day.substring(4,10);
    		 if (!ipDaysMap.containsKey(day)){
    			 ipDaysMap.put(day, new ArrayList<String>());
    		 }
    	 }
    	 
    	 for (String keys : ipDaysMap.keySet()){
    		 for (LogEntry le : records){
    			 String day = le.getAccessTime().toString();
    			 String ip = le.getIpAddress();
    			 if (day.contains(keys)){
    				 ipDaysMap.get(keys).add(ip);
    			 }
    		 }
    	 }
    	 
    	 return ipDaysMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipDaysMap){
    	 int max = 0;
    	 String dayMostVisited = "";
    	 for (String days : ipDaysMap.keySet()){
    		 if (ipDaysMap.get(days).size() > max){
    			 max = ipDaysMap.get(days).size();
    			 dayMostVisited = days;
    		 }
    	 }
    	 return dayMostVisited;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipDaysMap, String day){
    	 ArrayList<String> IPsOnGivenDayList = ipDaysMap.get(day);
    	 HashMap<String, Integer> mostVisitedIPOnGivenDayListMap = new HashMap<String, Integer>();
    	 for (String ip : IPsOnGivenDayList){
    		 if (!mostVisitedIPOnGivenDayListMap.containsKey(ip)){
    			 mostVisitedIPOnGivenDayListMap.put(ip, 1);
    		 }
    		 else {
    			 int temp = mostVisitedIPOnGivenDayListMap.get(ip);
    			 mostVisitedIPOnGivenDayListMap.put(ip, temp+1); 
    		 }
    	 }
    	 
    	 int max = mostNumberVisitsByIP(mostVisitedIPOnGivenDayListMap);
    	 ArrayList<String> IPsMaxOcuurList = new ArrayList<String>();
    	 for (Entry<String, Integer> entry : mostVisitedIPOnGivenDayListMap.entrySet()){
    		 if (entry.getValue() == max){
    			 IPsMaxOcuurList.add(entry.getKey()); 
    		 }
    	 }
    	 
    	 return IPsMaxOcuurList;
     }
     
     
     // This method teaches us about implementation of Iterator in Array
     /*public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipDaysMap, String day) {
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         ArrayList<String> output = new ArrayList<String>();
         //get an iterator to help with removing objects from an arraylist (newLogEntry)
         // newLogEntry represents an arraylist of type LogEntry which will be used to iterate though looking for IPs
         // visited on that given day.
         Iterator<LogEntry> newLogEntry = records.iterator();
         while(newLogEntry.hasNext()) {
             LogEntry le = newLogEntry.next();
             String logEntry = le.toString();
             int currLogIndexOfDate = logEntry.indexOf(day);
             
             if (currLogIndexOfDate == -1) {
            	 newLogEntry.remove();
             }
             else {
                 System.out.println(le);
             }
         }
         
         ipCounts = countVisitsPerIP();
         output = iPsMostVisits(ipCounts);
         return output;
     }*/
     	
}
