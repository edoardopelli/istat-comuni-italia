package com.cheetah.istat.raw.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document("raw_data")
public class Root{
	@Id
	private String id;
	
    public Geojson geojson;
    public Summaries summaries;
    public List<Fornitori> fornitori;
    public Charts charts;
    public Aggregates aggregates;
    @JsonProperty("last_update")
    public int lastUpdate;
}
