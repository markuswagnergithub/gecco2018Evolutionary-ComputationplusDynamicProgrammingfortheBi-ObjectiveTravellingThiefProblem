package au.edu.adelaide.sc.ttp.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class AttainmentSurface {
	
	public static final String EAF_CMD = "../../eaf/eaf";

	public enum Type {
		best("-b"), median("-m"), worst("-w");

		private String option;

		Type(String option) {
			this.option = option;
		}

		public String option() {
			return option;
		}

	};
	

	public static void main(String[] args) throws IOException {
		String folder = "../../../experiments/eil76/";
		
		for (Path file : Files.newDirectoryStream(Paths.get(folder),
				path -> path.toString().endsWith(".inv.txt"))) {
			
			Path output = Paths.get(file.toString() + ".atsf");
			System.out.println(output);
			Files.deleteIfExists(output);
					
			AttainmentSurface as = new AttainmentSurface();
			
			Files.write(output, as.bestTour(file).getBytes(), StandardOpenOption.CREATE);
			
			String content = as.solve(file.toString(), AttainmentSurface.Type.best);
			Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
			
			content = as.solve(file.toString(), AttainmentSurface.Type.median);
			Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
			
			content = as.solve(file.toString(), AttainmentSurface.Type.worst);
			Files.write(output, content.getBytes(), StandardOpenOption.APPEND);
			
		}
		
	}
	
	public String bestTour(Path frontsfile){
		String res = null;
		try (BufferedReader br = Files.newBufferedReader(frontsfile)) {

			double mb = - Double.MAX_VALUE;
			String tour = null;
			String line = null;
			while ((line = br.readLine()) != null) {
				
				if (line.charAt(0) >= '0' && line.charAt(0) <= '9'){
					String[] strs = line.split("\\s");
					double b = Double.valueOf(strs[strs.length-1]);
					if (b > mb){
						mb = b;
						res = tour;
					}
						
				}else if (line.endsWith("tour")){
					tour = line;
				}else if (line.endsWith("reversed")){
					tour = line.substring(0, line.length() - 9);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return res + "\n";
		
	}


	public String parseLine(String line) {
		if(line.charAt(0) >= '0' && line.charAt(0) <= '9'){
			String[] strs = line.split("\\s");
			return strs[0] + "\t" + (-Double.valueOf(strs[strs.length-1])) + "\n";
		}else{
			return "\n";
		}
	}
	

	
	public String solve(String fileName, Type t){
		
		StringBuilder sb = new StringBuilder();
		sb.append("#" + t + "\n");

		try {
			String[] command = new String[]{EAF_CMD, t.option};
			
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			OutputStream stdin = p.getOutputStream();

			
			try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
				
				String line;
				while ((line = br.readLine()) != null) {
					stdin.write(parseLine(line).getBytes());
					stdin.flush();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
	        stdin.close();
			
			String thisLine = null;
			while ((thisLine = stdout.readLine()) != null) {
				String str = thisLine.trim();
				
				if (str.equals("")) continue;
				if(str.charAt(0) >= '0' && str.charAt(0) <= '9'){
					String[] strs = str.split("\\s");
					sb.append(strs[0] + "\t" + (-Double.valueOf(strs[strs.length-1])) + "\n");
				}
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException", e);
		}

		return sb.toString();
	}
	
}
