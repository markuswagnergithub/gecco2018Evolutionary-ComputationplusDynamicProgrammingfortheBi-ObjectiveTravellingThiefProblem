package au.edu.adelaide.sc.ttp.problem;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
	
	public static final int[] toArray(List<Integer> list){
		if (list == null) return null;
		return list.stream().mapToInt(Integer::intValue).toArray();
	}
	
	public static final List<Integer> asList(int[] array){
		if (array == null) return null;
		return IntStream.of(array).boxed().collect(Collectors.toList());
	}

	public static void reverse(final int[] x, final int from, final int to) {
		for (int i = from, j = to; i < j; i++, j--) {
			final int tmp = x[i];
			x[i] = x[j];
			x[j] = tmp;
		}
	}
	
	public static void swap(final int[] x, final int city1, final int city2) {
		final int tmp = x[city2];
		x[city2] = x[city1];
		x[city1] = tmp;
	}
	
	public static void jump(final int[] x, final int prev, final int dest) {
		final int tmp = x[dest];
		if (prev < dest) {
			for (int i = dest; i > prev + 1; i--) {
				x[i] = x[i - 1];
			}
			x[prev + 1] = tmp;
		} else {
			for (int i = dest; i < prev; i++) {
				x[i] = x[i + 1];
			}
			x[prev] = tmp;
		}
	}
	
	public static String uniqueId(){
		
		return UUID.randomUUID().toString();
	}
	
}
