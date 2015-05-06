package rgr_3ch.src.my;

import widgets.regres.Regres2;

public class MyClass1 extends Regres2 {

	public double fi1(double x) {
		return Math.exp(x)/(x);
	}


	public double fi2(double x) {
		
		return 1;
	}

	
	public String getLabelName() {
		
		return "q=a*exp(w)/(1-w)+b";
	}

}
