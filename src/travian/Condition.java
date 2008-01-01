package travian;

import java.awt.Color;

public class Condition {
	public boolean accept(Village v){
		return true;		
	}
}

class CondAllianceColor extends Condition{
	private int aid;
	private Color c;

	int totalVillages=0;
	int totalPopulation=0;


	public CondAllianceColor(int aid, Color c){
		this.aid=aid;
		this.c=c;
	}
	public boolean accept(Village v){
		boolean result=(aid==v.aid);
		if(result){
			System.out.println("aid found");
			v.c=c;
			totalVillages++;
			totalPopulation+=v.population;
		}
		return result ;
	}
};

