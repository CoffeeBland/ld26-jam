package ld26_kiasaki_dagothig;

import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Order;

public class GameLevel {

	private int level;
	private String name;
	private List<Order> needed;
	private List<Order> possibleOrders;
	private Order truckContent;
	
	public GameLevel(int pLevel, String pName, List<Order> pNeeded, List<Order> pPossibleOrders, Order pTruckContent){
		level = pLevel;
		name = pName;
		needed= pNeeded;
		possibleOrders = pPossibleOrders;
		truckContent = pTruckContent;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Order> getNeeded() {
		return needed;
	}
	public void setNeeded(List<Order> needed) {
		this.needed = needed;
	}
	public List<Order> getPossibleOrders() {
		return possibleOrders;
	}
	public void setPossibleOrders(List<Order> possibleOrders) {
		this.possibleOrders = possibleOrders;
	}
	public Order getTruckContent() {
		return truckContent;
	}
	public void setTruckContent(Order truckContent) {
		this.truckContent = truckContent;
	}
	
}
