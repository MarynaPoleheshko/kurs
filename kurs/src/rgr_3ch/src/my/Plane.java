package rgr_3ch.src.my;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;

public class Plane extends Actor {

	private boolean full;
	private Main main;
	private Model model;
	private QueueForTransactions ocheredPlane;
	private ChooseRandom timeRndFl;
	private BooleanSupplier planeIsFull;
	private double time;

	public void setFull(boolean b) {
		full = b;
	}

	public Plane(String string, Main main, Model model) {
		setNameForProtocol(string);
		this.main = main;
		this.model = model;
	}

	@Override
	protected void rule() {
		initFields();
		initCondition();
		while (getDispatcher().getCurrentTime() <= time) {
			double startTime = getDispatcher().getCurrentTime();
			holdForTime(timeRndFl.next());
			ocheredPlane.add(this);
			try {
				waitForCondition(planeIsFull, "Літак має бути завантаженим");
			} catch (DispatcherFinishException e) {
				return;
			}
			ocheredPlane.remove(this);
			double waitTime = getDispatcher().getCurrentTime() - startTime;
			model.getHistoByPlane().add(waitTime);
			holdForTime(timeRndFl.next());
			full = false;
			
		}
	}

	private void initCondition() {
		planeIsFull = () -> full;

	}

	private void initFields() {
		ocheredPlane = model.getOcheredPlane();
		timeRndFl = main.getChooseRandom_time_fluing();
		time = main.getChooseData_time_model().getDouble();

	}

	public void setFinishTime(double finishTime) {
		this.time = finishTime;		
	}
}
