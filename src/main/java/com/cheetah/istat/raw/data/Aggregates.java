package com.cheetah.istat.raw.data;

import java.util.List;

import lombok.Data;
@Data
public class Aggregates{
    public List<AggrByProvince> aggr_by_provinces;
    public List<AggrByRegion> aggr_by_regions;
}
