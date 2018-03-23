package com.alefedu.oyestercard.travel;

import com.alefedu.oyestercard.travel.Stations;

public class Barrier {
	
	private Card oysterCard;
	private Direction direction;
	private TravelMode modeOfJourney;
	private Stations zone;
	
	public Barrier(Card oysterCard, Direction direction, TravelMode mode, Stations zone) {
		this.oysterCard = oysterCard;
		this.direction = direction;
		this.modeOfJourney = mode;
		this.zone = zone;
	}

	public String getZone() {
		return Stations.elementOf(this.zone);
	}

	public void setZone(Stations zone) {
		this.zone = zone;
	}

	public Card getOysterCard() {
		return oysterCard;
	}

	public void setOysterCard(Card oysterCard) {
		this.oysterCard = oysterCard;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public TravelMode getModeOfJourney() {
		return modeOfJourney;
	}

	public void setModeOfJourney(TravelMode modeOfJourney) {
		this.modeOfJourney = modeOfJourney;
	}  
	
}
