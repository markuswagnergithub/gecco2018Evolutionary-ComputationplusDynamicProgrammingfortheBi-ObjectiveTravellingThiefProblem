package au.edu.adelaide.sc.ttp.problem;


import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

public class TtpSolution extends AbstractGenericSolution<Integer, TtpProblem>{
	private static final long serialVersionUID = 6156187220420830173L;

	public final DefaultBinarySolution packingPlan;
	public final DefaultIntegerPermutationSolution tour;
	
	
	public TtpSolution(TtpProblem ttpProblem){
		super(ttpProblem);
		packingPlan = new DefaultBinarySolution(ttpProblem.packingProblem);
		tour = new DefaultIntegerPermutationSolution(ttpProblem.tourProblem);
	}
	
	public TtpSolution(TtpSolution ttpSolution){
		super(ttpSolution.problem);
		packingPlan = new DefaultBinarySolution(ttpSolution.packingPlan);
		tour = new DefaultIntegerPermutationSolution(ttpSolution.tour);
	}
	
	public TtpSolution(TtpProblem ttpProblem, DefaultBinarySolution packingPlan,
			DefaultIntegerPermutationSolution tour) {
		super(ttpProblem);
		this.packingPlan = new DefaultBinarySolution(packingPlan);
		this.tour = new DefaultIntegerPermutationSolution(tour);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TtpSolution [");
		sb.append("Benefit: " + this.getObjective(1));
		sb.append(", Weight: " + this.getObjective(0));
//		sb.append(", tour: {");
//	    for (int i=0; i<tour.getNumberOfVariables(); i++) {
//	    	sb.append(tour.getVariableValue(i) + ", ");
//	    }
//	    sb.delete(sb.length() - 2, sb.length());
//	    sb.append("}");
//	    sb.append(", packingPlan: " + packingPlan.getVariableValue(0));
	    
		sb.append("]");
		return sb.toString();
	}


	@Override
	public String getVariableValueString(int index) {
		return getVariableValue(index).toString();
	}


	@Override
	public Solution<Integer> copy() {
		return new TtpSolution(this);
	}

	@Override
	public Integer getVariableValue(int index) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public void setVariableValue(int index, Integer value) {
		throw new RuntimeException("Not implemented.");
	}

	@Override
	public int getNumberOfVariables() {
		throw new RuntimeException("Not implemented.");
	}

	public TtpProblem getProblem(){
		return this.problem;
	}
	
}
