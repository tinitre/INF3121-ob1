import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5;
	private int last;
	public ArrayList<Record> records;	//records are stored here and sorted

	Ranking(){
		records = new ArrayList<Record>(); //initiates the ranking list
		last=0; //initiates last checker
	}


	public void recordName(int result) {
		last++;
		if(last > MAX_PEOPLE_LIMIT) { //will throw out your score if you don't beat the 5th score
		try {
			if(result <= records.get(MAX_PEOPLE_LIMIT-1).score && records.get(MAX_PEOPLE_LIMIT-1) != null) {
				System.out.println("Sorry, but your score was too low to enter the hiscores");
				show();
				return; //returns as there is no need to record the score
			}
		}
		catch(IndexOutOfBoundsException e) {}
		}
		System.out.print("\n Please enter your name -");
		Scanner in=new Scanner(System.in);
		String newName=in.nextLine();
		records.add(new Record(newName, result)); //records the player
		sort();
		show();
	}


	public void show() { //iterates the records list, using toString
		int i = 1;
		for(Record r : records) {
			System.out.println(i + "    " + r);
			if(i == MAX_PEOPLE_LIMIT) break; //breaks the print when the 5 best are shown
			i++;
		}
	}

	public static Comparator<Record> SortByScore = new Comparator<Record>() {
		//this statement lets us change the compare method to what we need it for
	public int compare(Record r1, Record r2) {
	   int score1 = r1.score;
	   int score2 = r2.score;
	   return score2-score1; //sorts the list descending
   }};

	private void sort(){
		Collections.sort(records, Ranking.SortByScore); //way less complex method for sorting
		if(records.size() > MAX_PEOPLE_LIMIT) records.remove(MAX_PEOPLE_LIMIT); //removes whoever has fallen out of the hiscore
		System.out.println(records.size());
	}
}

class Record { //class for storing records, as the previous methods were messy
	public String name;
	public int score;

	Record(String n, int s) {
		name = n;
		score = s;
	}
	public String toString() {
		return "name: " + name + ", score: " + score;
	}
}
