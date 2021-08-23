package com.cheetah.istat.scheduled;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cheetah.istat.IstatApiApplication;
import com.cheetah.istat.comuni.model.Comune;
import com.cheetah.istat.comuni.repositories.ComuniRepository;
import com.cheetah.istat.province.model.Provincia;
import com.cheetah.istat.province.repositories.ProvinceRepository;
import com.cheetah.istat.raw.data.AggrByProvince;
import com.cheetah.istat.raw.data.AggrByRegion;
import com.cheetah.istat.raw.data.Feature;
import com.cheetah.istat.raw.data.Properties;
import com.cheetah.istat.raw.data.Root;
import com.cheetah.istat.regioni.config.IstatProperties;
import com.cheetah.istat.regioni.model.Regione;
import com.cheetah.istat.regioni.repositories.RegioniRepository;
import com.cheetah.istat.repositories.RootRepository;
import com.google.gson.Gson;

@Service
public class IstatLoadRawData {

	private static final Logger LOG = LoggerFactory.getLogger(IstatLoadRawData.class);
	@Autowired
	RestTemplate rest;
	
	@Autowired
	IstatProperties props;
	
	@Autowired
	Gson gson;
	
	@Autowired
	RootRepository rootRepository;
	
	@Autowired
	private RegioniRepository regioneRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private ComuniRepository comuniRepository;
	
	@Scheduled(cron = "${cron.pull.data}")
	public void updateRegioni() throws RestClientException, URISyntaxException, ParseException {
		int ops = 0;
		if (LOG.isInfoEnabled()) {
			LOG.info("Inizio schedulazione");
		}
		ResponseEntity<Root> response= rest.getForEntity(new URI(props.getApiUrl()), Root.class);
		Root root = response.getBody();
		Optional<Root> opt = rootRepository.findByLastUpdate(root.getLastUpdate());
		if(!opt.isPresent()) {
			List<Root> list = rootRepository.findAll();
			if(list.size()>0) {
				Root r = list.get(0);
				if(root.getLastUpdate()>r.getLastUpdate()) {
					rootRepository.deleteAll();
					regioneRepository.deleteAll();
					provinceRepository.deleteAll();
					comuniRepository.deleteAll();
					IstatApiApplication.available=false;
				}
			}
			root = rootRepository.save(root);
			Assert.isTrue(root.getId()!=null, "_id is null");
			if (LOG.isInfoEnabled()) {
				LOG.info("Loading regions...");
			}
			List<AggrByRegion> regions = root.getAggregates().getAggr_by_regions();
			for (AggrByRegion regione : regions) {
				Regione r = new Regione();
				r.setComuniPresubentro(regione.getComuni_presubentro());
				r.setComuniSubentro(regione.getComuni_subentro());
				r.setPopolazioneAirePresubentro(regione.getPopolazione_aire_presubentro());
				r.setPopolazioneAireSubentro(regione.getPopolazione_aire_subentro());
				r.setPopolazionePresubentro(regione.popolazione_presubentro);
				r.setPopolazioneSubentro(regione.getPopolazione_subentro());
				r.setRegione(regione.getRegione());
				r = regioneRepository.save(r);
				if (LOG.isDebugEnabled()) {
					LOG.debug("{}",r);
				}
				ops++;
				Assert.isTrue(r.getId()!=null, "_id regione is null");
				
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Loading province....");
			}
			List<AggrByProvince> province = root.getAggregates().getAggr_by_provinces();
			for (AggrByProvince provincia : province) {
				Provincia p = new Provincia();
				p.setComuniPresubentro(provincia.getComuni_presubentro());
				p.setComuniSubentro(provincia.getComuni_subentro());
				p.setPopolazioneAirePresubentro(provincia.getPopolazione_aire_presubentro());
				p.setPopolazioneAireSubentro(provincia.getPopolazione_aire_subentro());
				p.setPopolazionePresubentro(provincia.popolazione_presubentro);
				p.setPopolazioneSubentro(provincia.getPopolazione_subentro());
				p.setRegione(provincia.getRegione());
				p.setProvincia(provincia.getProvincia());
				p=provinceRepository.save(p);
				if (LOG.isDebugEnabled()) {
					LOG.debug("{}",p);
				}
				ops++;
				Assert.isTrue(p.getId()!=null, "_id provincia is null");
				
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Loading comuni...");
			}
			List<Feature> features = root.getGeojson().getFeatures();
			SimpleDateFormat date = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
			date.applyPattern("dd/MM/yyyy");
			for (Feature feature : features) {
				Properties p = feature.getProperties();
				Comune c = new Comune();
				c.setCoordinates(feature.getGeometry().getCoordinates());
				c.setCodiceIstat(p.getCodice_istat());
				c.setComune(StringUtils.capitalize(p.getLabel().toLowerCase()));
				if(p.getData_presubentro()!=null) {
					c.setDataPreSubentro(date.parse(p.getData_presubentro()));
				}
				if(p.getData_subentro_preferita()!=null) {
					c.setDataSubentroPreferita(date.parse(p.getData_subentro_preferita()));
				}
				if(p.getPrima_data_subentro()!=null) {
					c.setPrimaDataSubentro(date.parse(p.getPrima_data_subentro()));
				}
				if(p.getUltima_data_subentro()!=null) {
					c.setUltimaDataSubentro(date.parse(p.getUltima_data_subentro()));
				}
				c.setPopolazione(p.getPopolazione());
				c.setPopolazioneAire(p.getPopolazione_aire());
				c.setProvincia(p.getProvincia());
				c.setRegione(p.getRegione());
				c.setZona(p.getZona());
				c = comuniRepository.save(c);
				if (LOG.isDebugEnabled()) {
					LOG.debug("{}",c);
				}
				ops++;
				Assert.isTrue(c.getId()!=null, "_id comune is null");
				
				
			}
			IstatApiApplication.available=true;
		}
		
		
		
		
		if (LOG.isInfoEnabled()) {
			LOG.info("Inserted records: {}", ops);
			LOG.info("Scheduled job ended");
		}
		
	}
}
