package com.lemon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new TestThread());
			t.start();
		}
	}

}

class TestThread implements Runnable{

	@Override
	public void run() {
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL("http://localhost:8080/test1/customer/add2");
			URLConnection connection = url.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(
            connection.getInputStream()));
			String line;
		    while ((line = in.readLine()) != null) {
		        result .append(line);
		    }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(Thread.currentThread().getName()+":"+result.toString());
	}
	
}
