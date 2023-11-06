package services;

public class PaypalService implements OnlinePaymentService {

	private static final double FEE_PERCENTAGE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;
	
	@Override
	public double paymentFee(double amount) {
		double retorno = amount * FEE_PERCENTAGE;
		return retorno;
	}

	@Override
	public double interest(double amount, int months) {
		double retorno = amount * months * MONTHLY_INTEREST;
		return retorno;
	}
	
}
