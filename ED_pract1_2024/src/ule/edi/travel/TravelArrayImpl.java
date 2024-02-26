package ule.edi.travel;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.*;

public class TravelArrayImpl implements Travel {
	
	private static final Double DEFAULT_PRICE = 100.0;
	private static final Byte DEFAULT_DISCOUNT = 25;
	private static final Byte CHILDREN_EXMAX_AGE = 18;
	private Date travelDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
	
   public TravelArrayImpl(Date date, int nSeats){
	   this.travelDate = date;
	   this.nSeats = nSeats;
	   this.price = DEFAULT_PRICE;
	   this.discountAdvanceSale = DEFAULT_DISCOUNT;
		Seat[] TravelSeats = new Seat[nSeats];
		for(int i=0 ;i<TravelSeats.length;i++){
			TravelSeats[i]= null;
		
		}   
   }
   
   
   public TravelArrayImpl(Date date, int nSeats, Double price, Byte discount){
	   this.travelDate = date;
	   this.nSeats = nSeats;
	   this.price = price;
	   this.discountAdvanceSale = discount;
	   Seat[] TravelSeats = new Seat[nSeats];
	   for(int i=0 ;i<TravelSeats.length;i++){
			TravelSeats[i]= null;
	
		}
   }






@Override
public Byte getDiscountAdvanceSale() {
	return DEFAULT_DISCOUNT;
}


@Override
public int getNumberOfSoldSeats() {
	int NumberOfSoldSeats = 0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] != null){
			NumberOfSoldSeats +=1;
		}
	}
	return NumberOfSoldSeats;
}


@Override
public int getNumberOfNormalSaleSeats() {
	int NumberOfNormalSeats = 0;
	for(int i = 0; i < nSeats; i++){
		if(!seats[i].getAdvanceSale()){
			NumberOfNormalSeats +=1;
		}
	}
	
	return NumberOfNormalSeats;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	int NumberOfAdvanceSaleSeats = 0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i].getAdvanceSale()){
			NumberOfAdvanceSaleSeats +=1;
		}
	}

	return NumberOfAdvanceSaleSeats;
}


@Override
public int getNumberOfSeats() {
	return nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	int NumberOfAvailableSeats = 0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] == null){
			NumberOfAvailableSeats +=1;
		}
	}
	return NumberOfAvailableSeats;

}


@Override
public Seat getSeat(int pos) {
	if (pos > 0 && pos <= nSeats){
		return seats[pos - 1];
	}else{

		return null;
	}
}

//Misma duda que en el metodo anterior.
@Override
public Person refundSeat(int pos) {
	if (pos > 0 && pos <= nSeats){
		Person holder = seats[pos - 1].getHolder();
		seats[pos - 1] = null;
		return holder;
	}else{

		return null;
	}

}


private boolean isChildren(int age) {
	Boolean isChildren = false;
	if (age < CHILDREN_EXMAX_AGE) {
		isChildren = true;
	}
	return isChildren;
}

private boolean isAdult(int age) {
	Boolean isAdult = false;
	if (age >= CHILDREN_EXMAX_AGE) {
		isAdult = true;
	}
	return isAdult;
}


@Override
public List<Integer> getAvailableSeatsList() {
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	for(int i = 0; i < nSeats; i++){
		if(seats[i] == null){
			lista.add(i + 1);
		}
	}
	
	return lista;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	for(int i = 0; i < nSeats; i++){
		if(seats[i].getAdvanceSale()){
			lista.add(i + 1);
		}
	}
	
	return lista;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	int ConsecutiveSeats = 0;
	for(int i = 0; i < nSeats - 1 ; i++){
		if(seats[i] == seats[i+1] ){
			ConsecutiveSeats += 1;
		}else{
			ConsecutiveSeats = 0;
		
		}
	}
	return ConsecutiveSeats;
}

//El return false lo noto raro.
@Override
public boolean isAdvanceSale(Person p) {
	Person person = p;
	Boolean x = false;
	for(int i = 0; i < nSeats; i++){
		if(seats[i].getHolder().equals(person)){
			if(seats[i].getAdvanceSale()){
				x = true;
			}
		}
	}
	return x;
}


@Override
public Date getTravelDate() {
	return this.travelDate;
}


@Override
public boolean sellSeatPos(int pos, String nif, String name, int edad, boolean isAdvanceSale) {
	boolean sell = false;
	if (pos > 0 && pos <= nSeats){
		if(seats[pos - 1] == null){
			Person person = new Person(nif, name, edad);
			seats[pos - 1] = new Seat(isAdvanceSale, person);
			sell = true;
		}else{
		}
	}
	return sell;
}


@Override
public int getNumberOfChildren() {
	int NumberOfChildren = 0;
	for(int i = 0; i < nSeats; i++){
		if(isChildren(seats[i].getHolder().getAge())){
			return NumberOfChildren += 1;
		}
	}
	return NumberOfChildren;
}


@Override
public int getNumberOfAdults() {
	int NumberOfAdults = 0;
	for(int i = 0; i < nSeats; i++){
		if(isAdult(seats[i].getHolder().getAge())){
			return NumberOfAdults += 1;
		}
	}
	return NumberOfAdults;
}



@Override
public Double getCollectionTravel() {
	Double AllMoneyCollected = 0.0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] != null){
			AllMoneyCollected += getSeatPrice(seats[i]);
		}
	}
	
	return AllMoneyCollected;
}


@Override
public int getPosPerson(String nif) {
	int PosPerson = -1;
	for(int i = 0; i < nSeats; i++){
		if(seats[i].getHolder().getNif().equals(nif)){
			PosPerson = i;
		}
	}
	return PosPerson;	
}


@Override
public int sellSeatFrontPos(String nif, String name, int edad, boolean isAdvanceSale) {
	int sellSeatFrontPos = 0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] == null){
			Person person = new Person(nif, name, edad);
			seats[i] = new Seat(isAdvanceSale, person);
			sellSeatFrontPos = i;
		}
	}

	return sellSeatFrontPos;
}


@Override
public int sellSeatRearPos(String nif, String name, int edad, boolean isAdvanceSale) {
	int sellSeatRearPos = 0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] == null){
			Person person = new Person(nif, name, edad);
			seats[i] = new Seat(isAdvanceSale, person);
			sellSeatRearPos = i;
		}
	}

	return sellSeatRearPos;
}




@Override
public Double getSeatPrice(Seat seat) {
	Double SeatPrice = 0.0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i].getAdvanceSale()){
			SeatPrice += getDiscountAdvanceSale();
		}else{
			SeatPrice += DEFAULT_PRICE;
		}
	}
	return SeatPrice;
}


@Override
public double getPrice() {
	return this.price;
}


}	