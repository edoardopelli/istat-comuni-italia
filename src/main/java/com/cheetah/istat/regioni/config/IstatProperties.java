package com.cheetah.istat.regioni.config;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class IstatProperties {

	@Value("${anpr.api.url}")
	String apiUrl;
}
