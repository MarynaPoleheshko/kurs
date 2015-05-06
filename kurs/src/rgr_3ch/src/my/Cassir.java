package rgr_3ch.src.my;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;

public class Cassir extends Actor{
	private QueueForTransactions ocheredVCassu;
	private Main main;
	private Model model;
	private double time;
	private ChooseRandom random;
	private BooleanSupplier isClient;
	/**
	 * @param string
	 * @param main
	 * @param model
	 */
	public Cassir (String string, Main main, Model model) {
		setNameForProtocol(string);
		this.main=main;
		this.model=model;
		setHistoForActorWaitingTime(model.getWaitTimeHistoCasir());
	}
	protected void rule() {
		initFields();		
		while(getDispatcher().getCurrentTime()<=time){
			
			try {
				waitForCondition(()-> ocheredVCassu.size()>0, "Має бути клієнт");
				
				Client client = (Client) ocheredVCassu.removeFirst();
				holdForTime(random.next());
				client.setBilt(true);
			} catch (DispatcherFinishException e) {
				return;				
			}						
		}
	}
	
	private void initFields() {
		time=main.getChooseData_time_model().getDouble();
		random=main.getChooseRandom_obsluzivanije_v_kassa();
		ocheredVCassu=model.getOcheredVCassu();		
	}
		public String toString() {
		
		return "zdet clienta v ochered";
	}
		public void setFinishTime(double finishTime) {
			this.time = finishTime;
			
		}
}
