package au.edu.adelaide.sc.ttp.operation;

import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.mutation.PermutationSwapMutation;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import au.edu.adelaide.sc.ttp.problem.TtpSolution;

public class TtpMutation implements MutationOperator<TtpSolution>{
	private static final long serialVersionUID = -8026074907554130879L;
	
	private JMetalRandom randomGenerator = JMetalRandom.getInstance();

	BitFlipMutation bfMutation = new BitFlipMutation(0.05);
	PermutationSwapMutation<Integer> psMutation = new PermutationSwapMutation<>(0.05);
	
	@Override
	public TtpSolution execute(TtpSolution source) {
		DefaultBinarySolution mutatedPackingPlan = null;
		DefaultIntegerPermutationSolution mutatedTour = null;
		
		if (randomGenerator.getRandomGenerator().nextDouble() < 0.90){
			mutatedPackingPlan = (DefaultBinarySolution) bfMutation.execute(source.packingPlan);
		}else{
			mutatedTour  = (DefaultIntegerPermutationSolution) psMutation.execute(source.tour); 
		}
		
		if (mutatedPackingPlan == null){
			mutatedPackingPlan = new DefaultBinarySolution(source.packingPlan);
		}
		
		if (mutatedTour == null){
			mutatedTour = new DefaultIntegerPermutationSolution(source.tour);
		}
		return new TtpSolution(source.getProblem(),  mutatedPackingPlan,
				 mutatedTour);
	}

}
