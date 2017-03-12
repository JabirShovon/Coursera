package Week3;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
    	String filename = "short-test_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	la.printAll();
    }
    
    public void testUniqueIP(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	int uniqIP = la.countUniqueIPs();
    	System.out.println("Unique IP Address is "+ uniqIP);
    }
    
    public void testStatusHigherThanNum(){
    	String filename = "weblog1_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	la.printAllHigherThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	String date1 = "Sep 24";
    	System.out.println(la.uniqueIPVisitsOnDay(date1));
    	//la.uniqueIPVisitsOnDay(date1);
    }
    
    public void testUniqueIPsInRange(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	System.out.println(la.countUniqueIPsInRange(400, 499));
    }
    
    public void testcountVisitsPerIP(){
    	String filename = "weblog-short_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	System.out.println(la.countVisitsPerIP());
    }
    
    public void testmostNumberVisitsByIP(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	HashMap<String, Integer> map = la.countVisitsPerIP();
    	System.out.println(la.mostNumberVisitsByIP(map));
    }
    
    public void testiPsMostVisits(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	HashMap<String, Integer> map = la.countVisitsPerIP();
    	System.out.println(la.iPsMostVisits(map));
    }
    
    public void testiPsForDays(){
    	String filename = "weblog3-short_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	System.out.println(la.iPsForDays());
    }
    
    public void testdayWithMostIPVisits(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	HashMap<String, ArrayList<String>> map = la.iPsForDays();
    	System.out.println(la.dayWithMostIPVisits(map));
    }
    
    public void testiPsWithMostVisitsOnDay(){
    	String filename = "weblog2_log";
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile(filename);
    	HashMap<String, ArrayList<String>> map = la.iPsForDays();
    	System.out.println(la.iPsWithMostVisitsOnDay(map, "Sep 30"));
    }
    
    public static void main(String[] args) {
		Tester ts = new Tester();
		ts.testiPsWithMostVisitsOnDay();
	}
}
