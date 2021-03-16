package it.polito.tdp.alien;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private TreeMap<String, WordEnhanced> dizionario;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtWord;

    @FXML
    private TextArea txtResult;

    @FXML
    void doReset(ActionEvent event) {
    	txtWord.clear();
    	txtResult.clear();
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	String info[] = txtWord.getText().split(" ");
    	if(info.length == 1) {
	    	String word = info[0].toLowerCase();
	    	if((info[0].contains("?"))) {
	    		if(this.ricercaWildcard(word).isEmpty())
	    			txtResult.setText("Parola non presente");
	    		else {
	    			String s = "";
	    			for(WordEnhanced w : this.ricercaWildcard(word))
	    				s += w.toString() + "\n";

	    			txtResult.setText(s);
	    		}
	    	}
	    	else if(word.matches("((?=.*[a-z]).{1,})")){
	    		if(this.ricercaParola(word) != null)
	    			txtResult.setText(this.ricercaParola(word).toString());
	    		else
	    			txtResult.setText("Parola non presente");
	    	}
    	}
    	if(info.length == 2) {
	    	String word = info[0].toLowerCase();
	    	String traduzione = info[1].toLowerCase();
	    	if(word.matches("((?=.*[a-z]).{1,})") && (traduzione.matches("((?=.*[a-z]).{1,})"))){
	    		if(this.ricercaParola(word) != null) {
	    			this.ricercaParola(word).addTraduzione(traduzione);
	    			txtResult.setText(this.ricercaParola(word).toString());
	    		}
	    		else {
	    			WordEnhanced p = new WordEnhanced(word, traduzione);
	    			this.addParola(p);
	    			txtResult.setText(p.toString());
	    		}
	    	}
    	}
    }
    
    public void addParola(WordEnhanced p) {
		dizionario.put(p.getWord(), p);
	}
	
	public WordEnhanced ricercaParola(String s) {
		if(dizionario.containsKey(s))
			return dizionario.get(s);
		else
			return null;
	}
	
	public ArrayList<WordEnhanced> ricercaWildcard(String s) {
		ArrayList<WordEnhanced> res = new ArrayList<WordEnhanced>();
		String parti[] = s.toLowerCase().split("?");
		System.out.println(parti[0] + " " + parti[1]);
		for(String w : this.dizionario.keySet()) {
			if(w.contains(parti[0]) && w.contains(parti[1])) {
				res.add(this.dizionario.get(w));
			}
		}
		return res;
	}
    @FXML
    void initialize() {
        assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        this.dizionario = new TreeMap<String, WordEnhanced>();
    }
}
