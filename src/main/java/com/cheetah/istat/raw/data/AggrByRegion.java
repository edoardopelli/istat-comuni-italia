package com.cheetah.istat.raw.data;

import lombok.Data;

@Data
public class AggrByRegion{
    public String regione;
    public int comuni_subentro;
    public int popolazione_subentro;
    public int popolazione_aire_subentro;
    public int comuni_presubentro;
    public int popolazione_presubentro;
    public int popolazione_aire_presubentro;
}
