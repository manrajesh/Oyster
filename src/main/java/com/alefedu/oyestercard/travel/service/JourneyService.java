package com.alefedu.oyestercard.travel.service;

import com.alefedu.oyestercard.travel.Barrier;

public interface JourneyService {
	
	public void startTubeJourney(Barrier barrier);
	
	public void endTubeJourney(Barrier barrier);
	
	public void startBusJourney(Barrier barrier);

}
