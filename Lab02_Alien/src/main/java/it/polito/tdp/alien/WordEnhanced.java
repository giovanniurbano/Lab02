package it.polito.tdp.alien;

import java.util.ArrayList;

public class WordEnhanced {
	private String word;
	private ArrayList<String> traduzione = new ArrayList<String>();
	
	public WordEnhanced(String word, String traduzione) {
		this.word = word;
		this.traduzione.add(traduzione);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public ArrayList<String> getTraduzione() {
		return traduzione;
	}
	public void addTraduzione(String traduzione) {
		if(!this.traduzione.contains(traduzione))
			this.traduzione.add(traduzione);
	}

	@Override
	public String toString() {
		String s = word;
		for(String t : traduzione)
			s += " " + t;
		return s;
	}
	
}
