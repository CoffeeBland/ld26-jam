package ld26_kiasaki_dagothig;

import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.entity.Block;
import ld26_kiasaki_dagothig.entity.Order;
import ld26_kiasaki_dagothig.entity.Processor;

public class GameLevel {

	private int level;
	private String name;
	private List<Order> needed;
	private List<Order> originalNeeded;
	private List<Order> possibleOrders;
	private List<Processor> processorShop = new ArrayList<Processor>();
	private Order truckContent;
	
	public GameLevel(int pLevel, String pName, List<Order> pNeeded, List<Order> pPossibleOrders, Order pTruckContent, List<Processor> tProcessorShop){
		level = pLevel;
		name = pName;
		needed = pNeeded;
		originalNeeded = new ArrayList<Order>(pNeeded);
		possibleOrders = pPossibleOrders;
		truckContent = pTruckContent;
		processorShop = tProcessorShop;
	}
	
	public Order getNeededByBlock(Block tBlock){
		for (Order tO : getNeeded()){
			if (tO.getBlock().equals(tBlock)){
				return tO;
			}
		}
		return null;
	}
	public Order getOriginalNeededByBlock(Block tBlock){
		for (Order tO : getOriginalNeeded()){
			if (tO.getBlock().equals(tBlock)){
				return tO;
			}
		}
		return null;
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
	public List<Order> getOriginalNeeded() {
		return originalNeeded;
	}
	public void setOriginalNeeded(List<Order> originalNeeded) {
		this.originalNeeded = originalNeeded;
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
	public List<Processor> getProcessorShop() {
		return processorShop;
	}
	public void setProcessorShop(List<Processor> processorShop) {
		this.processorShop = processorShop;
	}
	
}
