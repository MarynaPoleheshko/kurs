package rgr_3ch.src.my;

import java.util.function.BooleanSupplier;
import javax.security.auth.login.FailedLoginException;
import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;

public class Stuardessa extends Actor{
	private QueueForTransactions ocheredVCassu;
	private QueueForTransactions ocheredToStuardessa;
	private QueueForTransactions ocheredPlane;
	private QueueForTransactions ocheredToPosadka;
	private Main main;
	private Model model;
	private Double time;
	private ChooseRandom randomPosadka;
	private BooleanSupplier conditionSt;
	private int kolMest;
	private ChooseRandom random;
	public Stuardessa (String string, Main main, Model model){
		setNameForProtocol(string);
		this.main=main;
		this.model=model;
		ocheredPlane=model.getOcheredPlane();
		ocheredToStuardessa=model.getOcheredKStuardessa();
		ocheredToPosadka=model.getOcheredToPosadka();
		kolMest=main.getChooseData_cnt_seats().getInt();
		time=main.getChooseData_time_model().getDouble();
		randomPosadka=main.getChooseRandom_time_posadki();
		random=main.getChooseRandom_obsluzivanije_v_kassa();
	}
	@Override
	protected void rule() {
		initFields();
		initCondition();
		while(getDispatcher().getCurrentTime()<=time){
			try {
				waitForCondition(conditionSt,"самолет должен быть и пассажиры или очередь за билетами");
			} catch (DispatcherFinishException e) {
				return;
			}
			
			if (ocheredPlane.size()>0 && ocheredToPosadka.size()>=kolMest){
				getDispatcher().printToProtocol("стюардесса забирает пассажиров из очереди на посадку");
				for (int i = 0; i < kolMest; i++)
					ocheredToPosadka.removeFirst();
				getDispatcher().printToProtocol("стюардесса ведет пассажиров на посадку");
				Plane p = (Plane) ocheredPlane.removeFirst();
				holdForTime(randomPosadka.next());
				p.setFull(true);
				getDispatcher().printToProtocol("стюардесса сажает посадила пассажиров в самолет");
				double startTime=getDispatcher().getCurrentTime();
				double waitingTime= model.timePosadka-startTime;
				model.getHistoByPosadka().add(waitingTime);
				holdForTime(randomPosadka.next());
			}
			else{
			//ocheredToStuardessa.size()>0
				Client client=(Client) ocheredToStuardessa.removeFirst();	
				holdForTime(random.next());
				client.setBilt(true);
			}
		}
	}
	private void initCondition() {
		conditionSt=()-> ocheredPlane.size()>0 && ocheredToPosadka.size()>=kolMest||ocheredToStuardessa.size()>0;
	}
	private void initFields() {
	}
	
	public void setFinishTime(double finishTime) {
		this.time = finishTime;		
	}
}
