package sbctester;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtc.sbcapi.egc.ClsReturn2;
import com.gtc.sbcapi.egc.WsDigiP2PSoap;

public class EgcTester {
    private WsDigiP2PSoap sbcP2PApi;
    
    private String username;
    private String pwd;

    public void queryTxn(String referenceNo) throws Exception {
        ClsReturn2 response = sbcP2PApi.inquireEGCCashOutByRefNo(
                this.username, 
                this.pwd, 
                referenceNo, 
                referenceNo + "-" + System.currentTimeMillis());
    	System.out.println("status: " + response.getStatus());
    	System.out.println("description: " + response.getDescription());
    }
    
    public void setSbcP2PApi(WsDigiP2PSoap api) {
        this.sbcP2PApi = api;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

	public static void main(String args[]) {
		if (args == null || args.length < 1) {
			System.out.println("Please supply the reference number to query");
			System.exit(0);
		}
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("sbctester-egc-spring-config.xml");
			EgcTester tester = (EgcTester)ctx.getBean("egcTester");
			tester.queryTxn(args[0]);
//			tester.listBanks();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
