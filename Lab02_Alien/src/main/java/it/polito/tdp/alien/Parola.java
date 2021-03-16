package it.polito.tdp.alien;

public class Parola {
	private String word;
	private String traduzione;
	
	public Parola(String word, String traduzione) {
		this.word = word;
		this.traduzione = traduzione;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTraduzione() {
		return traduzione;
	}
	public void setTraduzione(String traduzione) {
		this.traduzione = traduzione;
	}

	@Override
	public String toString() {
		return word + " " + traduzione;
	}
	
	
}
