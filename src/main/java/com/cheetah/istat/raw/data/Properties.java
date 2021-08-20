package com.cheetah.istat.raw.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Properties{
    @JsonProperty("PROVINCIA") 
    public String provincia;
    @JsonProperty("REGIONE") 
    public String regione;
    @JsonProperty("ZONA") 
    public String zona;
    public String codice_istat;
    public String data_presubentro;
    public String label;
    public int popolazione;
    public int popolazione_aire;
    public String data_subentro_preferita;
    public String prima_data_subentro;
    public String ultima_data_subentro;
}
