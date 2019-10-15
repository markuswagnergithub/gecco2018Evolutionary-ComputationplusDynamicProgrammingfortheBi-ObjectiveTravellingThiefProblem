package au.edu.adelaide.sc.ttp.problem;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.PermutationProblem;
import org.uma.jmetal.problem.Problem;

public class TtpProblem implements Problem<TtpSolution> {
	private static final long serialVersionUID = 3124702129104956178L;
	
	private final Ttp ttp;
	
	public final BinaryProblem packingProblem;
	public final PermutationProblem<?> tourProblem;
	
	public final int NO_OF_OBJECTIVES = 2;
	
	public TtpProblem(Ttp ttp){
		this.ttp = ttp;
		this.packingProblem = new PackingProblem(ttp.m, NO_OF_OBJECTIVES);
		this.tourProblem = new TourProblem(ttp.n, NO_OF_OBJECTIVES);
	}
	
	
	@Override
	public void evaluate(TtpSolution solution) {
		double benefit = 0.0;
		long carryingWeight = 0;
		
		for (int c = 0; c < solution.tour.getNumberOfVariables() - 1; c++) {

			final int cityIdx = solution.tour.getVariableValue(c) + 1;

			double currentProfit = 0;
			for (int i = 0; i < ttp.getCity(cityIdx).noOfItemsInCity(); i++) {
				int itemIdx = ttp.getCity(cityIdx).getItem(i);
				if (solution.packingPlan.getVariableValue(0).get(itemIdx - 1)) {
					currentProfit += ttp.getItem(itemIdx).profit;
					carryingWeight += ttp.getItem(itemIdx).weight;
				}
			}

			final int nextCityIdx = solution.tour.getVariableValue(c + 1) + 1;
			int distance = ttp.distance(cityIdx, nextCityIdx);

			// Formula 1 in the paper
			benefit += currentProfit - ttp.rentingRate * distance / (ttp.maxSpeed - ttp.nu * carryingWeight);
		}
		
		final int lastCityIdx = solution.tour.getVariableValue(solution.tour.getNumberOfVariables() - 1) + 1;
		
		double currentProfit = 0;
		for (int i = 0; i < ttp.getCity(lastCityIdx).noOfItemsInCity(); i++) {
			int itemIdx = ttp.getCity(lastCityIdx).getItem(i);
			if (solution.packingPlan.getVariableValue(0).get(itemIdx - 1)) {
				currentProfit += ttp.getItem(itemIdx).profit;
				carryingWeight += ttp.getItem(itemIdx).weight;
			}
		}
 
		final int firstCityIdx = solution.tour.getVariableValue(0) + 1;
		int distance = ttp.distance(lastCityIdx, firstCityIdx);
		
		// Formula 1 in the paper
		benefit += currentProfit - ttp.rentingRate * distance / (ttp.maxSpeed - ttp.nu * carryingWeight);

		if (carryingWeight <= ttp.capacity){
			solution.setObjective(0, carryingWeight);
			solution.setObjective(1, - benefit); //negative benefit for optimization
		}else{
			solution.setObjective(0, Double.MAX_VALUE);
			solution.setObjective(1, Double.MAX_VALUE); //negative benefit for optimization
		}
	    
		
	}

	@Override
	public TtpSolution createSolution() {
		return new TtpSolution(this);
	}

	@Override
	public int getNumberOfVariables() {
		return ttp.m + ttp.n;
	}

	@Override
	public int getNumberOfObjectives() {
		return 2;
	}

	@Override
	public int getNumberOfConstraints() {
		return 0;
	}

	@Override
	public String getName() {
		return "Travelling Thief Problem";
	}


}
