package com.cheetah.istat.regioni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("regioni")
@Data
public class Regione {

	@Id
	private String id;

	public String regione;
	public int comuniSubentro;
	public int popolazioneSubentro;
	public int popolazioneAireSubentro;
	public int comuniPresubentro;
	public int popolazionePresubentro;
	public int popolazioneAirePresubentro;

}
