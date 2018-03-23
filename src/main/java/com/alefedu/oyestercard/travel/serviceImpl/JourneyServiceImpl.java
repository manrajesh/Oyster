package com.alefedu.oyestercard.travel.serviceImpl;

import org.springframework.stereotype.Service;

import com.alefedu.oyestercard.travel.Barrier;
import com.alefedu.oyestercard.travel.dao.JourneyDAO;
import com.alefedu.oyestercard.travel.service.JourneyService;

@Service
public class JourneyServiceImpl implements JourneyService {
	
	private JourneyDAO journeyDAO;
	
	public JourneyServiceImpl(JourneyDAO journeyDAO) {
		this.journeyDAO = journeyDAO;
	}
		
	@Override
	public void startTubeJourney(Barrier barrier) {
		journeyDAO.startTubeJourney(barrier);
	}
	
	@Override
	public void startBusJourney(Barrier barrier) {
		journeyDAO.startBusJourney(barrier);
	}

	@Override
	public void endTubeJourney(Barrier barrier) {
		journeyDAO.endTubeJourney(barrier);
	}
	
}
