package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void parking_boy_can_park_a_car_into_the_parking_lot() {

        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//        ParkingTicket parkingTicket = new ParkingTicket();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);


        //then
        assertNotNull(parkingTicket);

    }

    @Test
    void parking_boy_can_fetch_the_car(){

        //give
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when

        Car car = parkingBoy.fetch(parkingTicket);
        //then

        assertNotNull(car);
    }

    @Test
    void parking_boy_should_park_multiple_cars (){

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car nissan = new Car();
        Car mazda = new Car();

        //when
        ParkingTicket ticketNissan = parkingBoy.park(nissan);
        ParkingTicket ticketMazda = parkingBoy.park(mazda);

        assertEquals(nissan,parkingBoy.fetch(ticketNissan));
        assertEquals(mazda,parkingBoy.fetch(ticketMazda));

        //then
//        assertEquals(nissan,car);
        assertEquals(-8, parkingLot.getAvailableParkingPosition());

    }

    @Test
    void parking_boy_should_fetch_car_using_corresponding_ticket (){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car nissan = new Car();
        ParkingTicket ticket = parkingBoy.park(nissan);

        //when

        Car car = parkingBoy.fetch(ticket);

        //then
        assertEquals(nissan,car);

    }

    @Test
    void should_not_give_car_if_no_ticket_was_given(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car nissan = new Car();
        ParkingTicket ticket = new ParkingTicket();

        Car car = parkingBoy.fetch(ticket);

        assertNull(car);
    }

    @Test
    void should_give_no_car_if_ticket_is_already_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car nissan = new Car();

        ParkingTicket ticketNissan = parkingBoy.park(nissan);
        parkingBoy.fetch(ticketNissan);

        assertNull(parkingBoy.fetch(ticketNissan));
    }

    @Test
    void parkingLot_should_not_return_a_ticket_if_no_capacity_left() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car eleven = new Car();

        IntStream.rangeClosed(0,10).forEach(a -> {
            parkingBoy.park(new Car());
        });

        System.out.println(parkingLot.getAvailableParkingPosition());
        assertNull(parkingBoy.park(eleven));

    }
}
