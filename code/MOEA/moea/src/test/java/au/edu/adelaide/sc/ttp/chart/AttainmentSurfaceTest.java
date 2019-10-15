package au.edu.adelaide.sc.ttp.chart;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class AttainmentSurfaceTest {



	@Test
	public void testSolve() throws IOException {
		String filename = "../../../experiments/eil76/eil76_n75_uncorr_01.ttp.aco.txt";
		
		Path output = Paths.get(filename + ".atsf");
		System.out.print(output);
		Files.deleteIfExists(output);
				
		AttainmentSurface as = new AttainmentSurface();
		
		String content = as.solve(filename, AttainmentSurface.Type.best);
		Files.write(output, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		
		content = as.solve(filename, AttainmentSurface.Type.median);
		Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
		
		content = as.solve(filename, AttainmentSurface.Type.worst);
		Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
		
	}
	
	@Test
	public void testParseLine(){
		AttainmentSurface as = new AttainmentSurface();
		
		System.out.print(as.parseLine("weight	benefit"));
		System.out.print(as.parseLine("0	-4680.0"));
		System.out.print(as.parseLine("1000	397.02306279535674"));
		
	}
	
	@Test
	public void testBestTour(){
		AttainmentSurface as = new AttainmentSurface();
		
		String filename = "../../../experiments/eil76/eil76_n75_uncorr_01.ttp.aco.txt";
		System.out.println(as.bestTour(Paths.get(filename)));
		
	}

}
