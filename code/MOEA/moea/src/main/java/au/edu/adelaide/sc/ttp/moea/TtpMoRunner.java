package au.edu.adelaide.sc.ttp.moea;

import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.ibea.IBEA;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemUtils;

import au.edu.adelaide.sc.ttp.problem.TtpLoader;
import au.edu.adelaide.sc.ttp.problem.TtpProblem;
import au.edu.adelaide.sc.ttp.problem.TtpSolution;

/**
 * Class for configuring and running the IBEA algorithm for TTP
 *
 * @author Junhua Wu <junhua.wu@adelaide.edu.au>
 */
public class TtpMoRunner extends AbstractAlgorithmRunner {
	/**
	 * @param args
	 *            Command line arguments.
	 * @throws java.io.IOException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 *             Invoking command: java
	 *             au.edu.adelaide.sc.ttp.moea.TtpMoRunner instance
	 *             [referenceFront]
	 */
	public static void main(String[] args) throws Exception {
		Problem<TtpSolution> problem;
		TtpIbea<TtpSolution> algorithm;

		String ttpInstance;
		String referenceParetoFront = null;
		if (args.length == 1) {
			ttpInstance = args[0];
		} else if (args.length == 2) {
			ttpInstance = args[0];
			referenceParetoFront = args[1];
		} else {
			ttpInstance = "../../../experiments/eil76/eil76_n375_uncorr_01.ttp";
		}

		problem = new TtpProblem(TtpLoader.load(ttpInstance));

		algorithm = TtpIbeaBuilder.build(problem);

		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

		List<TtpSolution> population = algorithm.getResult();
		long computingTime = algorithmRunner.getComputingTime();

		JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

//		printFinalSolutionSet(population);
		if (referenceParetoFront != null) {
			printQualityIndicators(population, referenceParetoFront);
		}
	}
}