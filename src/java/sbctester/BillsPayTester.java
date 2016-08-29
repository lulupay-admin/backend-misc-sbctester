package sbctester;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtc.sbcapi.billspay.WsDigiBillsPaySoap;

public class BillsPayTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("sbctester-billspay-spring-config.xml");
			WsDigiBillsPaySoap sbcapi = (WsDigiBillsPaySoap) ctx.getBean("sbcBillsPayApi");
			String result = sbcapi.payInstitution("vmoney|maker", "m1m1m1m1", "0025", "856321452", "10.00", "TEST123");
			System.out.println(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
