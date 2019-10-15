package au.edu.adelaide.sc.ttp.problem;

import java.util.Arrays;

public final class City {
    public final int index;
    public final int x;
    public final int y;
    
    private final int[] items;
	
    public City(int index, int x, int y){
    	this.index = index;
    	this.x = x;
    	this.y = y;
    	this.items = null;
    }
    
    public City(City old, int[] items){
    	this.index = old.index;
    	this.x = old.x;
    	this.y = old.y;
    	if (items != null){
    		this.items = items.clone();
    	}else{
    		this.items = null;
    	};
    }
    
    public int noOfItemsInCity(){
    	if (items == null) return 0;
    	return items.length;
    }
    
    public int getItem(int pos){
    	return items[pos];
    }

	@Override
	public String toString() {
		return "City [index=" + index + ", x=" + x + ", y=" + y + ", items=" + Arrays.toString(items) + "]";
	}
    
}
