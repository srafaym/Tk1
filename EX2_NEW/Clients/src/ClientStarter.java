public class ClientStarter {

	public ClientStarter() {
	}

	public static void main(String[] args) {
		 SoapClient soap = new SoapClient(0);
		// SoapClient soap1 = new SoapClient(1);
		RestClient rest = new RestClient(2);
//		RestClient rest1 = new RestClient(3);
	}

}
