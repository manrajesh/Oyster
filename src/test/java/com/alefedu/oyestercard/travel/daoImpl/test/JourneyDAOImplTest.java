package com.alefedu.oyestercard.travel.daoImpl.test;

import static com.alefedu.oyestercard.travel.AppConstants.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alefedu.oyestercard.travel.Barrier;
import com.alefedu.oyestercard.travel.Card;
import com.alefedu.oyestercard.travel.Direction;
import com.alefedu.oyestercard.travel.Stations;
import com.alefedu.oyestercard.travel.TravelMode;
import com.alefedu.oyestercard.travel.dao.JourneyDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JourneyDAOImplTest {
	@Mock
	private Card oysterCard; 
	
	private Barrier barrier;
	@Autowired
	private JourneyDAO journeyDAO;
	
	private static final Double INITIAL_BALANCE = 30D;
    private static final String NAME = "Rajesh";
	
	@Before
	public void setUp() {
		when(oysterCard.getBalance()).thenReturn(INITIAL_BALANCE);
		when(oysterCard.getName()).thenReturn(NAME);
	}
	
	@Test
	public void startAndEndTubeJourneyInZone1_Test() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.Holborn);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		barrier = new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.EarlsCourt);
		journeyDAO.endTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_ZONE_1_ANYWHERE);
	}
	
	@Test
	public void startAndEndTubeJourneyFromZone1_To_Zone3_Test() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.Holborn);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		
		journeyDAO.endTubeJourney(new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.Wimbledon));
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_ZONE_1_WITH_ANYZONE);
	}
	
	@Test
	public void startAndEndTubeJourneyFromZone2_To_Zone3_Test() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.Wimbledon);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		
		journeyDAO.endTubeJourney(new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.HammerSmith));
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_WITHOUT_ZONE_1_ANYZONE);
	}
	
	@Test
	public void startAndEndTubeJourneyWithInZone3_Test() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.Wimbledon);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		
		journeyDAO.endTubeJourney(new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.Wimbledon));
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_ANY_ONE_ZONE_OUSIDE_ZONE_1);
	}
	
	@Test
	public void startAndEndTubeJourneyWithInZone2_Test() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.HammerSmith);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		
		journeyDAO.endTubeJourney(new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.HammerSmith));
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_ANY_ONE_ZONE_OUSIDE_ZONE_1);
	}
	
	@Test
	public void startAndEndTubeJourney_Accross_ZonesTest() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.EarlsCourt);
		journeyDAO.startTubeJourney(barrier);
		verify(oysterCard, times(1)).addBalance(MAX_FARE * -1);
		
		journeyDAO.endTubeJourney(new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.Wimbledon));
		verify(oysterCard, times(1)).addBalance(MAX_FARE-TUBE_FARE_ZONE_ANY);
	}
	
	@Test
	public void startBusJourneyTest() {
		barrier = new Barrier(oysterCard, Direction.UNKNOWN, TravelMode.BUS, Stations.EarlsCourt);
		journeyDAO.startBusJourney(barrier);
		verify(oysterCard, times(1)).addBalance(BUS_FARE * -1);
	}
	
	
}
