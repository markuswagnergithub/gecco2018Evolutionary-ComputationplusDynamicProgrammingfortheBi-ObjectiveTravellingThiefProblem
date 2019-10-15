package au.edu.adelaide.sc.ttp.problem;

import org.uma.jmetal.problem.PermutationProblem;
import org.uma.jmetal.solution.PermutationSolution;

public class TourProblem implements PermutationProblem<PermutationSolution<Integer>> {
	private static final long serialVersionUID = -5655819076803711019L;

	private final int noOfCities;
	private final int noOfObjectives;
	
	public TourProblem(int noOfCities, int noOfObjectives){
		this.noOfCities = noOfCities;
		this.noOfObjectives = noOfObjectives;
	}
	
	@Override
	public int getNumberOfVariables() {
		return noOfCities;
	}

	@Override
	public int getNumberOfObjectives() {
		return noOfObjectives;
	}

	@Override
	public int getNumberOfConstraints() {
		return 0;
	}

	@Override
	public String getName() {
		return "TourProblem, the sub-problem of TTP";
	}

	@Override
	public void evaluate(PermutationSolution<Integer> solution) {
		throw new RuntimeException("Evaluate of TourProblem, this method shouldn't be invoked.");
	}

	@Override
	public PermutationSolution<Integer> createSolution() {
		throw new RuntimeException("CreateSolution of TourProblem, this method shouldn't be invoked.");
	}

	@Override
	public int getPermutationLength() {
		return noOfCities;
	}

}
