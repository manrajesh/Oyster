package com.alefedu.oyestercard.travel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Stations {
	Holborn("ZONE_1"),
	EarlsCourt("ZONE_1_2"),
	Chelsea("ZONE_1_2"),
	Wimbledon("ZONE_3"),
	HammerSmith("ZONE_2");
	
	private static final Map<Stations, String> ELEMENTS;
	
	static {
		Map<Stations, String> elements = new HashMap<Stations, String>();
		for (Stations value : values()) {
			elements.put(value, value.getZoneName());
		}
		ELEMENTS = Collections.unmodifiableMap(elements);
	}

	private String zone;
	
	private Stations(String zone) {
		this.zone= zone;
	}
	
	public String getZoneName() {
		return zone;
	}
	
	 public static String elementOf(Stations abbr) {
	        return ELEMENTS.get(abbr);
	    }
	
}
