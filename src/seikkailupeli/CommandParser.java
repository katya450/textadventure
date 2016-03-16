package seikkailupeli;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommandParser {
	
	public static List<String> parse (String command) {
		
		//kutsu sanaListaa joka parsii stringistä sanat erilleen! parametrina menee stringi sanoja.
		List<String> splitted = sanaLista(command);		
		
		//kutsu metodia joka kaivaa listasta kaksi sanaa. Parametrina sanalista, jossa sanat eroteltuna välillä
		List<String> kahdenSananLista = kaksiSanaa(splitted);

		//kutsu metodia joka kaivaa kaksi kirjainta kahdesta sanasta. parametrina kahden sanan lista.	
		List<String> kahdenKirjaimenLista = kaksiKirjainta(kahdenSananLista); 

		return kahdenKirjaimenLista; 		
		}
	
	public static List<String> sanaLista (String sanat) {
		//splitillä Stringi läpikäytävä ja palautettava lista
		return Arrays.asList(sanat.split(" "));
	}
	
	public static List<String> kaksiSanaa (List<String> splitLista) {
		if(splitLista.size() <= 2) {
			return splitLista;				
		}
		List<String> otetutSanat = splitLista.subList(0,2);	//käy läpi listaa ja ota 2 ekaa sanaa
		return otetutSanat;
	}
	
	public static List<String> kaksiKirjainta (List<String> ekatSanat) {

		return ekatSanat.stream().map(sana -> sananPituus(sana)).collect(Collectors.toList());
	}
	
	public static String sananPituus (String sana) {
		
		if(sana.length() <= 2) {
			return sana;
		} 
		String otetutKirjaimet = sana.substring(0,2);	//käy sana läpi ja ota kaks ekaa merkkiä
		return otetutKirjaimet;
	}
}