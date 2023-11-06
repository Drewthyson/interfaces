package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("entre os dados do contrato:");
		System.out.print("Numero: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (DD/MM/YYYY): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		System.out.print("Entre com o numero de parcelas: ");
		int installments = sc.nextInt();
		
		Contract contract = new Contract(number, date, totalValue);
		
		ContractService contractservice = new ContractService(new PaypalService());
		
		contractservice.processContract(contract, installments);
		
		System.out.println("Parcelas:");
		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
	}

}
