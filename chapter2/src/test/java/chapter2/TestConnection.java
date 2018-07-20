package chapter2;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.lemon.smartwebapp.chapter2.util.DBCPUtil;


public class TestConnection {
	
	@Test
	public void testConnection() {
		try {
			Assert.assertNotNull(DBCPUtil.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
