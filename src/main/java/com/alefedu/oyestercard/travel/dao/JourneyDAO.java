package com.alefedu.oyestercard.travel.dao;

import com.alefedu.oyestercard.travel.Barrier;

public interface JourneyDAO {
	
	public void startTubeJourney(Barrier barrier);
	
	public void endTubeJourney(Barrier barrier);
	
	public void startBusJourney(Barrier barrier);


}
