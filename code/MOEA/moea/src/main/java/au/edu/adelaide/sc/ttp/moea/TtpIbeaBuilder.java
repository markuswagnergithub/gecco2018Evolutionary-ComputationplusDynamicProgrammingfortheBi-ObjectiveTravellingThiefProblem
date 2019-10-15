package au.edu.adelaide.sc.ttp.moea;

import java.util.List;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;

import au.edu.adelaide.sc.ttp.operation.TtpCrossover;
import au.edu.adelaide.sc.ttp.operation.TtpMutation;
import au.edu.adelaide.sc.ttp.problem.TtpSolution;


public class TtpIbeaBuilder {
	
	public static TtpIbea<TtpSolution> build(Problem<TtpSolution> problem){
		
		
	    int populationSize = 100;
	    int archiveSize = 100;
	    int maxEvaluations = 1000000;

	    CrossoverOperator<TtpSolution> crossover = new TtpCrossover();
	    MutationOperator<TtpSolution> mutation = new TtpMutation();

	    SelectionOperator<List<TtpSolution>, TtpSolution> selection = new BinaryTournamentSelection<TtpSolution>();
	    
	    return build(problem, populationSize, archiveSize, maxEvaluations, crossover, mutation, selection);
	}
	
	public static TtpIbea<TtpSolution> build(Problem<TtpSolution> problem, int populationSize, int archiveSize,int maxEvaluations,
			CrossoverOperator<TtpSolution> crossover, MutationOperator<TtpSolution> mutation,
			SelectionOperator<List<TtpSolution>, TtpSolution> selection){
		 return new TtpIbea<TtpSolution>(problem, populationSize, archiveSize, maxEvaluations, selection, crossover,
		            mutation);
	}
	

}
