package sbctester;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtc.clproc.sbc.balance.ClsReturn;
import com.gtc.clproc.sbc.balance.WsCCBalInqSoap;
import com.gtc.sbcapi.ArrayOfString;
import com.gtc.sbcapi.WsDigiIBFTSoap;

public class BalanceInqTester {
	private String companyCode;
	private String userId;
	private String pw;
	private WsCCBalInqSoap sbcBalanceApi;
	private WsDigiIBFTSoap sbcIbftApi;
	
	public void getBalance(String acct) throws Exception {
		ClsReturn result = sbcBalanceApi.mtdCCBalInquiry(companyCode + "|" + userId, pw, acct);
		System.out.println("returnCode: " + result.getReturnCode());
		System.out.println("returnValue: " + result.getReturnValue());
	}
	
	public void listBanks() throws Exception {
		ArrayOfString aos = sbcIbftApi.getListOfBanks(companyCode + "|" + userId, pw);
		for (String s : aos.getString()) {
			System.out.println(s);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
			System.out.println("Please supply the account number to query");
			System.exit(0);
		}
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("sbctester-balinq-spring-config.xml");
			BalanceInqTester tester = (BalanceInqTester)ctx.getBean("balanceInqTester");
			tester.getBalance(args[0]);
//			tester.listBanks();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setSbcBalanceApi(WsCCBalInqSoap api) {
		this.sbcBalanceApi = api;
	}
	public void setSbcIbftApi(WsDigiIBFTSoap api) {
		this.sbcIbftApi = api;
	}
	

}
