package uk.co.certait.poc.util.generator;

import org.apache.commons.lang3.RandomUtils;

public class AbstractBaseGenerator {

	public String getRandomValue(String[] values) {
		return values[RandomUtils.nextInt(0, values.length)];
	}
}
