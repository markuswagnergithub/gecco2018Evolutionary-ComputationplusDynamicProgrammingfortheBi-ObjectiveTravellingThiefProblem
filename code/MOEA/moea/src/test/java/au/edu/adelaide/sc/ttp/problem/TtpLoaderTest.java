package au.edu.adelaide.sc.ttp.problem;


import org.junit.Test;

public class TtpLoaderTest {

	@Test
	public void testLoad() {
		String ttpInstance = "../../../experiments/eil76/eil76_n375_uncorr_01.ttp";
		
		Ttp ttp = TtpLoader.load(ttpInstance);
		System.out.println(ttp.toString());
	}

}
