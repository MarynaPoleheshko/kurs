package rgr_3ch.src.my;

import process.Dispatcher;
import process.IModelFactory;



	


public class Factory implements IModelFactory {
	private Main main;

	public Factory(Main main) {
		this.main = main;
	}
	public Model createModel(Dispatcher dispatcher) {
		Model newModel = new Model(dispatcher, main);
		return newModel;
	}
	
public Model setFactory(){
	
	
	return null;
		
	}
}