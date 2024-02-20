package com.example.tddstartmadvirus.chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

	@DisplayName("1만원 납부할 시, 만료일은 한달 뒤이다.")
	@Test
	void ten_thousand_won() {
		LocalDate billingDate = LocalDate.of(2019, 3, 1);
		int payAmount = 10_000;

		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

		assertThat(expiryDate).isEqualTo(LocalDate.of(2019, 4, 1));
	}

}
