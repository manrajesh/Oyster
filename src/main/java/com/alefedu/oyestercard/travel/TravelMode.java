package com.alefedu.oyestercard.travel;

public enum TravelMode {
	BUS(1),TUBE(2);
	
	private int value;
	
	private TravelMode(int mode) {
		this.value = mode;
	}

}
