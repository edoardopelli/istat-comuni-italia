package com.cheetah.istat.comuni.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("comuni")
@Data
public class Comune {

	@Id
	private String id;
	
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private double[] coordinates;
	
	private String provincia;
	private String regione;
	private String zona;
	private String codiceIstat;
	private Date dataPreSubentro;
	private String comune;
	private Integer popolazione;
	private Integer popolazioneAire;
	
    public Date dataSubentroPreferita;
    public Date primaDataSubentro;
    public Date ultimaDataSubentro;

}
