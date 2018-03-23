package com.alefedu.oyestercard.travel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alefedu.oyestercard.travel.dao.JourneyDAO;
import com.alefedu.oyestercard.travel.serviceImpl.JourneyServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OystercardApplicationTests {
	
	private Card oysterCard; 
	
	private Barrier barrier;
	
	@Mock
	private JourneyDAO journeyDAO;
	
	@InjectMocks
	private JourneyServiceImpl journeyServiceImpl;
	
	private static final Double ZERO_VALUE = 0.0;
	private static final Double INITIAL_BALANCE = 30D;
    private static final String NAME = "Rajesh";
	
	@Before
	public void setUp() {
		oysterCard = new Card(NAME, INITIAL_BALANCE);
	}

	@Test
	public void addCardBalanceTest() {
		oysterCard.addBalance(20.0);
		assertEquals(50.0, oysterCard.getBalance(), ZERO_VALUE);
		oysterCard.addBalance(20.0 * -1);
		assertEquals(30.0, oysterCard.getBalance(), ZERO_VALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionWhenAddingNegativeBalance() {
		oysterCard.addBalance(31.0 * -1);
	}
	
	@Test
	public void startTubeJourneyTest() {
		barrier = new Barrier(oysterCard, Direction.IN, TravelMode.TUBE, Stations.Holborn);
		journeyServiceImpl.startTubeJourney(barrier);
		verify(journeyDAO, times(1)).startTubeJourney(barrier);
	}
	
	@Test
	public void endTubeJourneyTest() {
		
		barrier = new Barrier(oysterCard, Direction.OUT, TravelMode.TUBE, Stations.EarlsCourt);
		journeyServiceImpl.endTubeJourney(barrier);
		verify(journeyDAO, times(1)).endTubeJourney(barrier);
	}
	
	@Test
	public void startBusJourneyTest() {
		barrier = new Barrier(oysterCard, Direction.OUT, TravelMode.BUS, Stations.EarlsCourt);
		journeyServiceImpl.startBusJourney(barrier);
		verify(journeyDAO, times(1)).startBusJourney(barrier);
	}
	
	
	
	

}
