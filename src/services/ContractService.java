package services;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}


	public void processContract(Contract contract, int months) {
			double dueamount = contract.getTotalValue() / months;
		for (int i=1; i<=months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			double interest = onlinePaymentService.interest(dueamount, i);
			double fee = onlinePaymentService.paymentFee(dueamount + interest);
			double quota = dueamount + interest + fee;
			contract.getInstallments().add(new Installment(dueDate, quota));
		}
		
	}
}
