package org.yankun.dashboard.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.yankun.dashboard.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public double getSaveCoal(double power) {
		return power * 0.4;
	}

	@Override
	public double getSaveCO2(double power) {
		return power * 0.96;
	}

	@Override
	public double geSubsidies(double power) {
		return power * 0.42;
	}

	@Override
	public double getUsed(Date start, Date end) {
		long msStartDiff = new Date().getTime() - start.getTime();
		long msEndDiff = end.getTime() - new Date().getTime();
		
		if (msStartDiff > 0 && msEndDiff < 0) {
			return ((double)msStartDiff / 1000) * (3 / 60 / 60);
		}
		return 0;
	}

	@Override
	public double getRestIncome(double power, double used) {
		return (power - used) * 0.4;
	}

}
