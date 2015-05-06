package rgr_3ch.src.my;

import widgets.regres.Regres2;

public class MyClass2 extends Regres2 {

	
	public double fi1(double x) {
		
		return Math.log(x)/Math.sqrt(x);
	}

	
	public double fi2(double x) {
		
		return 1;
	}


	public String getLabelName() {
	
		return "q=a*ln(w)/sqrt(w)+b";
	}

}
