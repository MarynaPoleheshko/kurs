package rgr_3ch.src.my;

import process.Actor;
import widgets.ChooseRandom;

public class ClientGenerator extends Actor {

	
	
	private Main main;
	private Model model;
	private double time;
	private ChooseRandom random;

	public ClientGenerator(String string, Main main, Model model) {
		setNameForProtocol(string);
		this.main=main;
		this.model=model;
	}

	@Override
	protected void rule() {
		initFields();
		int i=1;
		while(getDispatcher().getCurrentTime()<=time){
			Client client=new Client("Client"+String.valueOf(i),main,model);
			i++;
			getDispatcher().printToProtocol("Generator sozdal Client"+String.valueOf(i));
			getDispatcher().addStartingActor(client);
			holdForTime(random.next());
			
		}
	}

	private void initFields() {
		time=main.getChooseData_time_model().getDouble();
		random=main.getChooseRandom_pojavlenie_pas();
	}

}
