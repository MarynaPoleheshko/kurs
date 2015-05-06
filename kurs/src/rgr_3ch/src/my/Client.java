package rgr_3ch.src.my;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.Dispatcher;
import process.DispatcherFinishException;

public class Client extends Actor {

	private Main main;
	private Model model;
	private boolean bilet;
	private BooleanSupplier isBilt;

	public Client(String string, Main main, Model model) {
		setNameForProtocol(string);
		this.main = main;
		this.model = model;
	}

	protected void rule() {
		if (model.getOcheredVCassu().size() < main.getChooseData_cnt_cas()
				.getInt() * 5) {
			model.getOcheredVCassu().add(this);
		} else {
			if (model.getOcheredKStuardessa().size() > main
					.getChooseData_cnt_stuardes().getInt() * 5) {
				model.getOcheredNaVokzal().add(this);
				return;
			}

			model.getOcheredKStuardessa().add(this);

		}
		double startTime = getDispatcher().getCurrentTime();
		try {
			waitForCondition(() -> bilet, "kogda budet bilet");
		} catch (DispatcherFinishException e) {
			return;
		}
		double waitTime = getDispatcher().getCurrentTime() - startTime;
		model.timePosadka = getDispatcher().getCurrentTime();
		model.getHistoWaitBilet().add(waitTime);
		model.getOcheredToPosadka().add(this);
	}

	public void setBilt(boolean b) {
		bilet = true;
	}
}
