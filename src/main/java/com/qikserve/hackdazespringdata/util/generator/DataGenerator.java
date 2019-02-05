package com.qikserve.hackdazespringdata.util.generator;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

	@Autowired
	private CustomerDataGenerator customerGenerator;

	@Autowired
	private ProductDataGenerator productDataGenerator;

	@Autowired
	private OrderDataGenerator orderDataGenerator;

	@PostConstruct
	public void generateData() throws IOException {
		customerGenerator.generateData(100);
		productDataGenerator.generateData();
		orderDataGenerator.generateData();
	}
}
