package com.example.demo;

import com.example.demo.apimodels.*;
import com.example.demo.entities.Address;
import com.example.demo.services.AdminService;
import com.example.demo.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BookMyShowDemoApplicationTests {

	@Autowired
	AdminService adminService;

	@Autowired
	BookingService bookingService;

	@Test
	void contextLoads() {
	}

	@Test
	void testApplication(){

		//Add User 1
		adminService.addNewUser(new NewUserRequest("Anurag","xyz@gmail.com"));

		//Add User 2
		adminService.addNewUser(new NewUserRequest("Arjit","ayu@gmail.com"));

		//Add Movie 1
		adminService.addNewMovie(new NewMovieRequestModel("Movie1","Action","English",120,3));

		//Add Movie 2
		adminService.addNewMovie(new NewMovieRequestModel("Movie2","Comedy","Hindi",150,2));

		//Add Theater 1
		Address address = new Address();
		address.setStreetAdress("Street-1");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setPincode("560023");
		adminService.addNewTheater(new NewTheaterRequest("INOX", address, 7));

		//Add Screen 1
		adminService.addNewScreen(new NewScreenRequest(1,"3D"));

		//Add Screen 2
		adminService.addNewScreen(new NewScreenRequest(1,"2D"));

		//Add Seats to Screen 1
		List<NewScreenLayoutRequest.TierDetails> tierDetailsList = new ArrayList<>();
		tierDetailsList.add(new NewScreenLayoutRequest.TierDetails("GOLD",230,20));
		tierDetailsList.add(new NewScreenLayoutRequest.TierDetails("SILVER",180,50));
		adminService.addNewScreenLayout(new NewScreenLayoutRequest(1,tierDetailsList));

		//Add a Movie Schedule
		adminService.addNewMovieSchedule(new NewMovieSceduleRequestModel(1,1,1,new Date(),new Date()));

		//Lock Seats for a Movie Schedule and User 1
		try {
			List<Integer> seatList = new ArrayList<>();
			seatList.add(2);
			seatList.add(3);
			Boolean bool1 = bookingService.lockSeats(new BookingRequestModel(1,1,seatList));
			System.out.println("Seat locks for SeatIds(2,3) Success = "+bool1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Book Seats For Movie Schedule 1 and User 1
		List<Integer> seatList = new ArrayList<>();
		seatList.add(2);
		seatList.add(3);
		Boolean bool2 = bookingService.bookSeats(new BookingRequestModel(1,1,seatList));
		System.out.println("Seat booking for SeatIds (2,3) Success = "+bool2);

	}

}
