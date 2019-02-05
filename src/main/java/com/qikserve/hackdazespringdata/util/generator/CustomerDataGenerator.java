package com.qikserve.hackdazespringdata.util.generator;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qikserve.hackdazespringdata.domain.Address;
import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.repository.CustomerRepository;

@Component
public class CustomerDataGenerator extends AbstractBaseGenerator {

	private String[] forenames = { "John", "Jack", "James", "Joe", "Alan", "Steven", "David", "Susan", "Karen", "Emily",
			"Edna", "Stan", "Michael", "Jennifer" };

	private String[] surnames = { "Jackson", "Smith", "Johnson", "Anderson", "Stevenson", "Kennedy", "Henderson",
			"McDonald", "Davis", "Young", "Jones", "Johnston", "Henshaw", "Davidson", "Davies" };

	private String[] streetNames = { "Greenhill", "Montpelier", "Morningside", "Leith", "Bruntsfield", "Newington" };

	private String[] streetTypes = { "Road", "Avenue", "Street", "Park" };

	private String[] towns = { "Edinburgh", "Glasgow", "Aberdeen", "Dundee" };

	@Autowired
	private CustomerRepository repository;

	@Transactional
	public void generateData(int count) {

		for (int i = 0; i < count; ++i) {
			Customer customer = new Customer();
			customer.setForename(getRandomValue(forenames));
			customer.setSurname(getRandomValue(surnames));
			customer.setDateOfBirth(LocalDate.now().minusDays(RandomUtils.nextInt(5400, 15000)));
			customer.setEmailAddress(StringUtils.join(customer.getForename(), ".", customer.getSurname(),
					RandomUtils.nextInt(1000, 9999), "@test.com"));
			customer.setPassword(RandomStringUtils.randomAlphanumeric(10));

			Address address = new Address();
			address.setHouseNumber(RandomStringUtils.randomNumeric(2));
			address.setLine1(getRandomValue(streetNames) + " " + getRandomValue(streetTypes));
			address.setTown(getRandomValue(towns));
			address.setPostCode("XX1 1XX");

			customer.setAddress(address);

			repository.save(customer);
		}
	}
}
