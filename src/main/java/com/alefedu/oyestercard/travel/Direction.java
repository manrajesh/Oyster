package com.alefedu.oyestercard.travel;

public enum Direction {
	IN(1),OUT(2), UNKNOWN(3);
	
	private int value;
	
	private Direction(int direction) {
		this.value = direction;
	}
}
