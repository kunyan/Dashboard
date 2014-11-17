package org.yankun.dashboard.service;

import java.util.Date;

public interface CalculatorService {
	double getSaveCoal(double power);
	double getSaveCO2(double power);
	double geSubsidies(double power);
	double getUsed(Date start, Date end);
	double getRestIncome(double power, double used);
}
