package com.alefedu.oyestercard.travel.daoImpl;

import static com.alefedu.oyestercard.travel.AppConstants.*;

import org.springframework.stereotype.Repository;

import com.alefedu.oyestercard.travel.Barrier;
import com.alefedu.oyestercard.travel.Direction;
import com.alefedu.oyestercard.travel.TravelMode;
import com.alefedu.oyestercard.travel.dao.JourneyDAO;

@Repository
public class JourneyDAOImpl implements JourneyDAO{
	public String boardingAtZone, leavingAtZone;
	
	@Override
	public void startTubeJourney(Barrier barrier) {
		if(barrier.getModeOfJourney() == TravelMode.TUBE) {
			if(barrier.getDirection() == Direction.IN) {
				boardingAtZone = barrier.getZone();
				barrier.getOysterCard().addBalance(MAX_FARE * -1);
			}
		}
	}

	@Override
	public void endTubeJourney(Barrier barrier) {
		if(barrier.getModeOfJourney() == TravelMode.TUBE) {
			if(barrier.getDirection() == Direction.OUT) {
				leavingAtZone = barrier.getZone();
				if(leavingAtZone.contains("ZONE_1") && boardingAtZone.contains("ZONE_1") && leavingAtZone.contains(boardingAtZone)){
					barrier.getOysterCard().addBalance(MAX_FARE-TUBE_FARE_ZONE_1_ANYWHERE);
				}else if(leavingAtZone.equals(boardingAtZone) && (!leavingAtZone.contains("ZONE_1") && !boardingAtZone.contains("ZONE_1"))) {
					barrier.getOysterCard().addBalance(MAX_FARE-TUBE_FARE_ANY_ONE_ZONE_OUSIDE_ZONE_1);
				}else if(!leavingAtZone.equals(boardingAtZone) && (leavingAtZone.contains("ZONE_1") || boardingAtZone.contains("ZONE_1")) && !(((leavingAtZone.equals("ZONE_1_2") && boardingAtZone.equals("ZONE_3")) || (leavingAtZone.equals("ZONE_3") && boardingAtZone.equals("ZONE_1_2"))))) {
					barrier.getOysterCard().addBalance(MAX_FARE-TUBE_FARE_ZONE_1_WITH_ANYZONE);
				}else if(!leavingAtZone.equals(boardingAtZone) && !leavingAtZone.equals("ZONE_1") && !boardingAtZone.equals("ZONE_1") && !leavingAtZone.equals("ZONE_1_2") && !boardingAtZone.equals("ZONE_1_2")) {
					barrier.getOysterCard().addBalance(MAX_FARE-TUBE_FARE_WITHOUT_ZONE_1_ANYZONE);
				}else if(!leavingAtZone.equals(boardingAtZone) && ((leavingAtZone.equals("ZONE_1_2") && boardingAtZone.equals("ZONE_3")) || (leavingAtZone.equals("ZONE_3") && boardingAtZone.equals("ZONE_1_2")))) {
					barrier.getOysterCard().addBalance(MAX_FARE-MAX_FARE);
				}
			}
		}
	}

	@Override
	public void startBusJourney(Barrier barrier) {
		if(barrier.getModeOfJourney() == TravelMode.BUS) {
				barrier.getOysterCard().addBalance(BUS_FARE * -1);
		}
	}

}
