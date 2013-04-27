package ld26_kiasaki_dagothig.entity;

import java.util.ArrayList;
import java.util.List;

public interface Router extends Machine
{
	public List<Machine> outs = new ArrayList<Machine>();
	
	public void changeDirection();
}
