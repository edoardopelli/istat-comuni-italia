package com.cheetah.istat.raw.data;

import lombok.Data;

@Data
public class Feature{
    public String type;
    public Geometry geometry;
    public Properties properties;
}
