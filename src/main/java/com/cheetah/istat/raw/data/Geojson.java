package com.cheetah.istat.raw.data;

import java.util.List;

import lombok.Data;
@Data
public class Geojson{
    public String type;
    public List<Feature> features;
}
