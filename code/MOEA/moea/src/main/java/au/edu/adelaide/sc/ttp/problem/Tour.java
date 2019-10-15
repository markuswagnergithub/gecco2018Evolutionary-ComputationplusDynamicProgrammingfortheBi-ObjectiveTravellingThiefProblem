package au.edu.adelaide.sc.ttp.problem;

import java.util.Arrays;

public final class Tour {
	public final Ttp problem;
	public final int length;
	
	public final int[] stops;
	public final long[] adjacentDistances;
	public final long[] traveledDistances;
	public final int[] stopsOfItems;
	public final int[] itemsByTour;
	
	
	public Tour(Ttp problem){
		this(problem, problem.getNoOfNodes());
	}
	
	public Tour(Ttp problem, int length){
		this.problem = problem;
		if (length > problem.getNoOfNodes()){
			this.length = problem.getNoOfNodes();
		}else{
			this.length = length;
		}
		stops = new int[this.length];
		adjacentDistances = new long[this.length];
		traveledDistances = new long[this.length];
		stopsOfItems = new int[problem.getNoOfItems()];
		itemsByTour = new int[problem.getNoOfItems()];
	}
	
	public Tour(Tour old){
		this.problem = old.problem;
		this.length = old.length;
		this.stops = old.stops.clone();
		this.adjacentDistances = old.adjacentDistances.clone();
		this.traveledDistances = old.traveledDistances.clone();
		this.stopsOfItems = old.stopsOfItems.clone();
		this.itemsByTour = old.itemsByTour.clone();
	}
	
	public Ttp getProblem(){
		return problem;
	}
	
	public void update(int[] stops){
		if (stops.length != problem.getNoOfNodes()){
			throw new RuntimeException(
					"Error: inconsistent between no. of cities and stops");
		}
		System.arraycopy(stops, 0, this.stops, 0, stops.length);
		updateAdjacentDistances();
		updateTraveledDistances();
		updateItemsByTour();
	}
	
	
	public void update(int[] stops, long[] adjacentDistances, long[] traveledDistances){
		System.arraycopy(stops, 0, this.stops, 0, stops.length);
		System.arraycopy(adjacentDistances, 0, this.adjacentDistances, 0, adjacentDistances.length);
		System.arraycopy(traveledDistances, 0, this.traveledDistances, 0, traveledDistances.length);
		updateItemsByTour(this.stops);
	}
	
	public long totalDistance(){
		long total = 0;
		for (int i=0;i<this.adjacentDistances.length;i++){
			total += adjacentDistances[i];
		}
		return total;
	}
	
	
	public void updateAdjacentDistances(){
		updateAdjacentDistances(this.stops);
	}
	
	public void updateAdjacentDistances(int[] stops){
		for (int i=1;i < stops.length;i++){
			adjacentDistances[i-1] = problem.distance(stops[i-1], stops[i]);
		}
		adjacentDistances[stops.length-1] = problem.distance(stops[stops.length-1], stops[0]);
	}
	
	public void updateItemsByTour(){
		updateItemsByTour(this.stops);
	}
	
	private void updateItemsByTour(int[] stops){
		
		int mptr = 0;
		for (int c=0;c< stops.length;c++){
			//sort items according to the tour
			for (int i = 0; i < problem.getCity(stops[c]).noOfItemsInCity(); i++) {
				itemsByTour[mptr] = problem.getCity(stops[c]).getItem(i);
				stopsOfItems[mptr] = c;
				mptr++;
			}
		}
		
	}
	
	
	public void updateTraveledDistances(){
		updateTraveledDistances(this.adjacentDistances);
	}
	
	private void updateTraveledDistances(long[] adjacentDistances){
		
		long traveledDistance = 0;
		for (int c=0;c< adjacentDistances.length;c++){
			traveledDistances[c] = traveledDistance;
			traveledDistance += adjacentDistances[c];
		}
		
	}

	@Override
	public String toString() {
		return "Tour [stops=" + Arrays.toString(stops) + "\nadjacentDistances=" + Arrays.toString(adjacentDistances)
				+ "\ntraveledDistances=" + Arrays.toString(traveledDistances) + "]";
	}
	
	
	
}
