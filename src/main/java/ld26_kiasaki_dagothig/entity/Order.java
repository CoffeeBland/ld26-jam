package ld26_kiasaki_dagothig.entity;

import org.newdawn.slick.SlickException;

public class Order {

	public BlockShape shape;
	public BlockColor color;
	public Integer qty;
	public Integer value;
	
	public Order(){
		
	}
	public Order(BlockShape pShape, BlockColor pColor, Integer pQty, Integer pValue){
		shape = pShape;
		color = pColor;
		qty = pQty;
		value = pValue;
	}
	
	public Block getBlock(){
		Block tmpBlock = new BlockImpl();
		try {
			tmpBlock.setColor(color);
			tmpBlock.setShape(shape);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return tmpBlock;
	}
	
	public BlockShape getShape() {
		return shape;
	}
	public void setShape(BlockShape shape) {
		this.shape = shape;
	}
	public BlockColor getColor() {
		return color;
	}
	public void setColor(BlockColor color) {
		this.color = color;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
