package au.edu.adelaide.sc.ttp.problem;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class PackingProblem implements BinaryProblem {
	private static final long serialVersionUID = 5574073033858286029L;

	private final int noOfItems;
	private final int noOfObjectives;
	
	public PackingProblem(int noOfItems, int noOfObjectives){
		this.noOfItems = noOfItems;
		this.noOfObjectives = noOfObjectives;
	}
	
	@Override
	public int getNumberOfVariables() {
		return 1;
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
		return "PackingProblem, the sub-problem of TTP";
	}

	@Override
	public void evaluate(BinarySolution solution) {
		throw new RuntimeException("Evaluate of Packing Problem, this method shouldn't be invoked.");
	}

	@Override
	public BinarySolution createSolution() {
		throw new RuntimeException("CreateSolution of Packing Problem, this method shouldn't be invoked.");
	}

	@Override
	public int getNumberOfBits(int index) {
		return noOfItems;
	}

	@Override
	public int getTotalNumberOfBits() {
		return noOfItems;
	}

}
