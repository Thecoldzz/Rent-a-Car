package interfaces.exercice.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import interfaces.exercice.entities.CarRental;
import interfaces.exercice.entities.Vehicle;
import interfaces.exercice.services.BrazilTaxService;
import interfaces.exercice.services.RentalService;


public class Program {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com os dados do aluguel: ");        
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start,finish, new Vehicle(carModel));
        
        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();
        
        RentalService rentalService = new RentalService(pricePerHour,pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(cr);
 
        System.out.println("Invoice: ");
        System.out.print("BASIC PAYMENT: " + String.format("%.2f",cr.getInvoice().getBasicPayment())  + "\n");
        System.out.print("TAX: " + String.format("%.2f",cr.getInvoice().getTax())+ "\n");
        System.out.print("TOTAL: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
        sc.close();
    }
}
