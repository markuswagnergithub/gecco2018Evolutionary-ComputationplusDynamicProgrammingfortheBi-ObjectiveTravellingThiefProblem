package au.edu.adelaide.sc.ttp.operation;

import java.util.List;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.impl.crossover.NullCrossover;

import au.edu.adelaide.sc.ttp.problem.TtpSolution;

public class TtpCrossover implements CrossoverOperator<TtpSolution>{
	private static final long serialVersionUID = 8333078455472998003L;

	NullCrossover<TtpSolution> nullCrossover = new NullCrossover<>();
	
	@Override
	public List<TtpSolution> execute(List<TtpSolution> source) {
		return nullCrossover.execute(source);
	}

	@Override
	public int getNumberOfParents() {
		return 2;
	}

}
