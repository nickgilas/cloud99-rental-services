package com.cloud99.rental.service;

import com.cloud99.rental.domain.document.account.Feature;
import com.cloud99.rental.repo.document.FeatureRepo;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService implements RentalService, CrudService<Feature, FeatureRepo> {

	@Autowired
	private FeatureRepo featureRepo;

	@Override
	public Feature create(Feature feature) {

		feature.setEnabledDate(LocalDateTime.now());
		return featureRepo.save(feature);
	}

	@Override
	public Optional<Feature> read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feature update(Feature document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Feature document) {
		// TODO Auto-generated method stub

	}

}
