package com.qikserve.hackdazespringdata.util.generator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplementaryDDLGenerator {

	@Autowired
	private DataSource ds;

	@PostConstruct
	public void generateDatabaseViews() throws SQLException, IOException {
		Connection conn = ds.getConnection();
		conn.createStatement();
		Statement s1 = conn.createStatement();
		s1.execute("drop table if exists customer_summary_data_vw");
		s1.close();

		Statement s2 = conn.createStatement();
		InputStream in = this.getClass().getResourceAsStream("/sql/create-customer-summary-vw.sql");
		s2.execute(IOUtils.toString(in, "UTF-8"));
		in.close();
		s2.close();
	}
}
