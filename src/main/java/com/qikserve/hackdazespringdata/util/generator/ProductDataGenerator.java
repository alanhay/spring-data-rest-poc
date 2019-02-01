package com.qikserve.hackdazespringdata.util.generator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qikserve.hackdazespringdata.domain.Product;
import com.qikserve.hackdazespringdata.domain.repository.ProductRepository;

@Component
public class ProductDataGenerator {

	@Autowired
	private ProductRepository repository;

	@Transactional
	public void generateData() throws IOException {

		InputStream in = this.getClass().getResourceAsStream("/data/products.csv");
		List<String> lines = IOUtils.readLines(in, "UTF-8");

		for (String line : lines) {
			String[] parts = line.split(",");

			Product product = new Product();
			product.setSku(parts[0]);
			product.setName(parts[1]);
			product.setPrice(new BigDecimal(parts[2]));
			product.setStockCount(RandomUtils.nextInt(0, 1000));

			repository.save(product);
		}

		in.close();
	}
}
