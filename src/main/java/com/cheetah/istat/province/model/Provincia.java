package com.cheetah.istat.province.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("province")
@Data
public class Provincia {
	@Id
	private String id;

	private String regione;
	private String provincia;
	private Integer comuniSubentro;
	private Integer popolazioneSubentro;
	private Integer popolazioneAireSubentro;
	private Integer comuniPresubentro;
	private Integer popolazionePresubentro;
	private Integer popolazioneAirePresubentro;
}
