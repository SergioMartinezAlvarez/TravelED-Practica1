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
		Seat[] TravelSeats = new Seat[nSeats];
		for(int i=0 ;i<TravelSeats.length;i++){
			TravelSeats[i]= null;
		
		}
	//TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en esta clase
	   //debe crear el array de asientos
	   
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
	   //TODO 
	   // Debe crear el array de asientos
	   
   }






@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return DEFAULT_DISCOUNT;
}


@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	int NumberOfSoldSeats = 0;
	Seat[] TravelSeats = new Seat[nSeats];
	for(int i = 0; i < nSeats; i++){
		if(TravelSeats[i] != null){
			NumberOfSoldSeats +=1;
		}
	}
	return NumberOfSoldSeats;
}


@Override
public int getNumberOfNormalSaleSeats() {
	// TODO Auto-generated method stub
	int NumberOfNormalSeats = 0;
	Seat[] TravelSeats = new Seat[nSeats];
	for(int i = 0; i < nSeats; i++){
		if(!TravelSeats[i].getAdvanceSale()){
			NumberOfNormalSeats +=1;
		}
	}
	
	return NumberOfNormalSeats;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	int NumberOfAdvanceSaleSeats = 0;
	Seat[] TravelSeats = new Seat[nSeats];
	for(int i = 0; i < nSeats; i++){
		if(TravelSeats[i].getAdvanceSale()){
			NumberOfAdvanceSaleSeats +=1;
		}
	}

	return NumberOfAdvanceSaleSeats;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub

	return nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	int NumberOfAvailableSeats = 0;
	Seat[] TravelSeats = new Seat[nSeats];
	for(int i = 0; i < nSeats; i++){
		if(TravelSeats[i] == null){
			NumberOfAvailableSeats +=1;
		}
	}
	return NumberOfAvailableSeats;

}

//Dudilla, preguntar si la posicion de parametro se rige desde 0 o desde 1.
@Override
public Seat getSeat(int pos) {
	// TODO Auto-generated method stub
	Seat[] TravelSeats = new Seat[nSeats];
	if (pos >= 0 && pos < nSeats){
		return TravelSeats[pos];
	}else{
		System.out.println("Invalid position");
		return null;
	}
}

//Misma duda que en el metodo anterior.
@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	Seat[] TravelSeats = new Seat[nSeats];
	if (pos >= 0 && pos < nSeats){
		Person holder = TravelSeats[pos].getHolder();
		TravelSeats[pos] = null;
		return holder;
	}else{
		System.out.println("Invalid position");
		return null;
	}

}


//En estos dos creo q me falta algo, pero no se que es. Seguramente coger la edad desde el array llamando a la funcion getAge de la clase Person.
private boolean isChildren(int age) {
	// TODO Auto-generated method stub
	Boolean isChildren = false;
	if (age < 18) {
		isChildren = true;
	}
	return isChildren;
}

private boolean isAdult(int age) {
	// TODO Auto-generated method stub
	Boolean isAdult = false;
	if (age >= 18) {
		isAdult = true;
	}
	return isAdult;
}


@Override
public List<Integer> getAvailableSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	
	
	return lista;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	
	
	return lista;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	// TODO Auto-generated method stub
	int ConsecutiveSeats = 0;
	for(int i = 0; i < nSeats - 1 ; i++){ //nSeats - 1 para que no se salga del array al luego añadir 1
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
	// TODO Auto-generated method stub
	Person person = p;
	Seat[] TravelSeats = new Seat[nSeats];
	for(int i = 0; i < nSeats; i++){
		if(TravelSeats[i].getHolder().equals(person)){
			return TravelSeats[i].getAdvanceSale();
		}
	}
	return false;
}


@Override
public Date getTravelDate() {
	// TODO Auto-generated method stub
	return this.travelDate;
}


@Override
public boolean sellSeatPos(int pos, String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	boolean sell = false;
	Seat[] TravelSeats = new Seat[nSeats];
	if (pos >= 0 && pos < nSeats){
		if(TravelSeats[pos] == null){
			Person person = new Person(nif, name, edad);
			TravelSeats[pos] = new Seat(isAdvanceSale, person);
			sell = true;
		}else{
			System.out.println("Seat already sold");
		}
	}
	return sell;
}


@Override
public int getNumberOfChildren() {
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
	Double AllMoneyCollected = 0.0;
	for(int i = 0; i < nSeats; i++){
		if(seats[i] != null){
			AllMoneyCollected += getSeatPrice();
		}
	}
	
	return AllMoneyCollected;
}


@Override
public int getPosPerson(String nif) {
	// TODO Auto-generated method stub
	return 0;	
}


@Override
public int sellSeatFrontPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int sellSeatRearPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	return 0;
}




@Override
public Double getSeatPrice(Seat seat) {
	// TODO Auto-generated method stub
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