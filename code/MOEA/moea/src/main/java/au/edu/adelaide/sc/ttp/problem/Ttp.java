package au.edu.adelaide.sc.ttp.problem;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Ttp implements Serializable{
	private static final long serialVersionUID = -6797777701918722219L;

	public enum KnapsackType {Uncorrelated, UncorrelatedWithSimilarWeights, BoundedStronglyCorrelated};
	public enum WeightType {CEIL_2D, EUC_2D};
	
	public final String name;
	public final String path;
	public final WeightType edgeWeightType;
	public final int n; //No. of Nodes
	public final int m; //No. of Items
	public final long capacity;
	
	public final double maxSpeed;
	public final double minSpeed;
	public final double rentingRate;
	public final double nu;
	
	private final Map<Integer, City> cities;
	private final Map<Integer, Item> items;
	
	
	public Ttp(String problemName, String path, int noOfNodes, int noOfItems, double rentingRate, 
			double minSpeed, double maxSpeed, long capacity, WeightType weightType, 
			Map<Integer, City> cities, Map<Integer, Item> items){
		this.name = problemName;
		this.path = path;
		this.n = noOfNodes;
		this.m = noOfItems;
		this.rentingRate = rentingRate;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.edgeWeightType = weightType;
		this.nu = (maxSpeed - minSpeed)/capacity;
		
		if (cities != null){
			this.cities = Collections.unmodifiableMap(new HashMap<Integer, City>(cities));
		}else{
			this.cities = null;
		}
		if (items != null){
			this.items = Collections.unmodifiableMap(new HashMap<Integer, Item>(items));
		}else{
			this.items = null;
		}
	}
	
	
	public int getNoOfNodes(){
		return n;
	}
	
	public int getNoOfItems() {
		return m;
	}

	
	public City getCity(int cityIdx){
		return cities.get(cityIdx);
	}
	
	public Item getItem(int index){
		return items.get(index);
	}
	
	
	@Override
	public String toString() {
		return toJsonString();
	}

	public String toJsonString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	
	public long totalWeight(){
		long total = 0;
		for (Item item:items.values()){
			total += item.weight;
		}
		return total;
	}
	
	public double getSpeedCoef(){
		return nu;
	}
	
	public int distance(int cityIdxA, int cityIdxB){
		return distance(this.cities.get(cityIdxA), this.cities.get(cityIdxB));
	}
	
	public int distance(City a, City b){
		double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
		if (this.edgeWeightType == WeightType.CEIL_2D){
			return (int)Math.ceil(distance);
		}else if (this.edgeWeightType == WeightType.EUC_2D){
			return (int)Math.rint(distance);
		}else{
			throw new RuntimeException("Unsupported type of edge weight.");
		}
	}
	



}
