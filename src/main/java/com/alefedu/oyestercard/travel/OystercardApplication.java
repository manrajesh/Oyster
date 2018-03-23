package com.alefedu.oyestercard.travel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alefedu.oyestercard.travel.service.JourneyService;

@SpringBootApplication
@ComponentScan({"com.alefedu.oyestercard.travel", "com.alefedu.oyestercard.travel.service", "com.alefedu.oyestercard.travel.serviceImpl", "com.alefedu.oyestercard.travel.dao", "com.alefedu.oyestercard.travel.daoImpl"})
public class OystercardApplication implements CommandLineRunner{
	
	@Autowired
	private JourneyService journeyService;

	public static void main(String[] args) {
		System.out.println("++++++++++++++  Journey Starts here ++++++++++++++");
		SpringApplication.run(OystercardApplication.class, args).close();
		System.out.println("++++++++++++++  Application Stops Here ++++++++++++++");
	}
	
	@Override
	public void run(String... args) throws Exception {
		String name = "Rajesh";
		Double balance = 30.0;
		if(args.length > 0) {
            System.out.println("Spring Boot Oyster Card Application Command Line Arguments passed : "+Arrays.toString(args));
            try {
    			
    			System.out.println("your Oyster card is prepared with :Name " + args[0] + " Balance: $" + args[1]);
    			System.out.println(" Please Enter your recharge Amount in : $");
    			name = args[0];
    			balance = Double.valueOf(args[1]);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		}else {
            System.out.println("No Command Line Arguments passed And Default Oyster card is prepared with Name : " + name + " And Balance : $" + balance );
		}
		
		Card card = new Card(name, balance);
		System.out.println("Great you have new card with Balance :$" + card.getBalance());
		
		 // Travel begins
		
		Barrier checkIntoTube = new Barrier(card, Direction.IN, TravelMode.TUBE, Stations.Holborn);
		Barrier checkOutTube = new Barrier(card, Direction.OUT, TravelMode.TUBE, Stations.EarlsCourt);
		journeyService.startTubeJourney(checkIntoTube);
		System.out.println("You have Entered into : " + checkIntoTube.getZone() + " Station : " + Stations.Holborn);
		journeyService.endTubeJourney(checkOutTube);
		System.out.println("You have ComeOut off :" + checkOutTube.getZone() + " Station : " + Stations.EarlsCourt);
		
		Barrier checkIntoBus = new Barrier(card, Direction.UNKNOWN, TravelMode.BUS, Stations.EarlsCourt);
		journeyService.startBusJourney(checkIntoBus);
		System.out.println("You have ComeOut off Bus :" + checkIntoBus.getZone() + " Station : " + Stations.Chelsea);
		
		Barrier checkIntoTubeAgain = new Barrier(card, Direction.IN, TravelMode.TUBE, Stations.EarlsCourt);
		journeyService.startTubeJourney(checkIntoTubeAgain);
		System.out.println("You have Entered into :" + checkIntoTubeAgain.getZone() + " Station : " + Stations.EarlsCourt);
		
		Barrier checkOutTubeAgain = new Barrier(card, Direction.OUT, TravelMode.TUBE, Stations.HammerSmith);
		journeyService.endTubeJourney(checkOutTubeAgain);
		System.out.println("You have ComeOut off :" + checkOutTubeAgain.getZone() + " Station : " + Stations.HammerSmith);
		
		System.out.println("*********Final Balance after the trips is****** :$" + card.getBalance() );
	}
}
