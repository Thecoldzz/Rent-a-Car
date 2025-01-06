package interfaces.exercice.services;

import java.time.Duration;

import interfaces.exercice.entities.CarRental;
import interfaces.exercice.entities.Invoice;



public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;

    private TaxService taxService;

    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }
    public void processInvoice(CarRental carRental){
        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes/60;
        double basicPayment;
        if(hours <= 12.0){
            basicPayment = pricePerHour * Math.ceil(hours); // arredonda as horas ex : 4h 10 min  >>> 5h 
        }else{
            basicPayment = pricePerDay * Math.ceil(hours/24.0); // faz a conversão pra dias horas / 24.0 = dia
        }   
        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
    
}
