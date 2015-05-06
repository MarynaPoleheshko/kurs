package rgr_3ch.src.my;

import java.util.HashMap;
import java.util.Map;

import process.Actor;
import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import stat.DiscretHisto;
import stat.Histo;
import widgets.experiments.IExperimentable;
import widgets.trans.ITransProcesable;

public class Model implements IExperimentable, ITransProcesable {
	public double timePosadka;
	private Dispatcher dispetcher;
	private Main main;
	private Cassir cassir;
	private ClientGenerator clientGenerator;
	private QueueForTransactions ocheredVCassu;
	private QueueForTransactions ocheredNaVokzal;
	private QueueForTransactions ocheredKStuardessa;
	private MultiActor multiCassir;
	private QueueForTransactions  ocheredToPosadka;
	private QueueForTransactions ocheredPlane;
	private MultiActor multiPlane;
	private MultiActor multiStuardessa;
	private DiscretHisto discretHistoToCassa;
	private DiscretHisto discretHistoKStuardessa;
	private DiscretHisto discretHistoToPosadka;
	private DiscretHisto discretHistoPlane;
	private Histo histoWaitBilet;
	private Histo timeByPlane;
	private Histo timeByPosadka;
	private Histo histoCasir;
	private Plane plane;
	private Stuardessa stuardessa;

	public Model(Dispatcher dispatcher, Main main) {
		this.dispetcher=dispatcher;
		this.main=main;
		//Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	private void componentsToStartList() {
		dispetcher.addStartingActor(getClientGenerator());
		dispetcher.addStartingActor(getMultiCassir());
		dispetcher.addStartingActor(getMultiPlane());
		dispetcher.addStartingActor(getMultiStuardessa());
	}

	private Actor getClientGenerator() {
		if (clientGenerator==null){
			clientGenerator=new ClientGenerator("ClientGenerator",main, this);
		}
		return clientGenerator;
	}

	public void initForStat() {
		// TODO Auto-generated method stub
	}

	public void initForTest() {
		getOcheredPlane().setPainter(main.getDiagram_3().getPainter());
		getOcheredVCassu().setPainter(main.getDiagram().getPainter());
		getOcheredNaVokzal().setPainter(main.getDiagram_4().getPainter());
		getOcheredKStuardessa().setPainter(main.getDiagram_1().getPainter());
		getOcheredToPosadka().setPainter(main.getDiagram_2().getPainter());
	}

	public QueueForTransactions getOcheredVCassu(){
		if(ocheredVCassu==null){
			ocheredVCassu=new QueueForTransactions();
			ocheredVCassu.setDispatcher(dispetcher);

			ocheredVCassu.setNameForProtocol("Черга в кассу");
			ocheredVCassu.setDiscretHisto(getDiscretHistoToCassa());
		}
		return ocheredVCassu;
	}

	public DiscretHisto getDiscretHistoToCassa() {
		if(discretHistoToCassa==null){discretHistoToCassa=new DiscretHisto();
		}
		return discretHistoToCassa;
	}

	public QueueForTransactions getOcheredNaVokzal(){
		if(ocheredNaVokzal ==null){
			ocheredNaVokzal=new QueueForTransactions();
			ocheredNaVokzal.setDispatcher(dispetcher);
			ocheredNaVokzal.setNameForProtocol("Черга на вокзал");

		}
		return ocheredNaVokzal;
	}

	public QueueForTransactions getOcheredKStuardessa(){
		if(ocheredKStuardessa ==null){
			ocheredKStuardessa=new QueueForTransactions();
			ocheredKStuardessa.setDispatcher(dispetcher);
			ocheredKStuardessa.setNameForProtocol("Черга до стюардеси");
			ocheredKStuardessa.setDiscretHisto(getDiscretHistoKStuardessa());
		}
		return ocheredKStuardessa;
	}

	public DiscretHisto getDiscretHistoKStuardessa() {
		if(discretHistoKStuardessa==null){
			discretHistoKStuardessa=new DiscretHisto();}
		return discretHistoKStuardessa;
	}

	public QueueForTransactions getOcheredToPosadka(){
		if(ocheredToPosadka ==null){
			ocheredToPosadka=new QueueForTransactions();
			ocheredToPosadka.setDispatcher(dispetcher);
			ocheredToPosadka.setNameForProtocol("Черга на посадку");
			ocheredToPosadka.setDiscretHisto(getDiscretHistoToPosadka());
		}
		return ocheredToPosadka;
	}

	public DiscretHisto getDiscretHistoToPosadka() {
		if (discretHistoToPosadka==null){discretHistoToPosadka=new DiscretHisto();}
		return discretHistoToPosadka;
	}

	public QueueForTransactions getOcheredPlane(){		
		if(ocheredPlane==null){			
			ocheredPlane=new QueueForTransactions();
			ocheredPlane.setDispatcher(dispetcher);
			ocheredPlane.setNameForProtocol("Черга у літак");
			ocheredPlane.setDiscretHisto(getDiscretHistoPlane());
		}
		return ocheredPlane;		
	}

	public DiscretHisto getDiscretHistoPlane() {
		if (discretHistoPlane==null){discretHistoPlane=new DiscretHisto();}
		return discretHistoPlane;
	}

	public MultiActor getMultiCassir() {
		if (multiCassir == null) { 	multiCassir = new MultiActor();
		multiCassir.setNameForProtocol("MultiCassir ");
		multiCassir.setOriginal(getCassir());
		multiCassir.setNumberOfClones(main.getChooseData_cnt_cas().getInt());
		}
		return multiCassir;
	}

	private Cassir getCassir() {
		if (cassir == null){
			cassir = new Cassir("Casir", main, this);
		}
		return cassir;
	}

	public MultiActor getMultiPlane() {
		if (multiPlane == null) { 	multiPlane = new MultiActor();
		multiPlane.setNameForProtocol("MultiActor для літакiв");
		multiPlane.setOriginal(getPlane());
		multiPlane.setNumberOfClones(main.getChooseData_cntPlane().getInt());
		}
		return multiPlane;
	}

	private Plane getPlane() {
		if (plane == null){
			plane = new Plane("Лiтак", main, this);
		}	
		return plane;
	}

	public MultiActor getMultiStuardessa() {
		if (multiStuardessa == null) { 	multiStuardessa = new MultiActor();
		multiStuardessa.setNameForProtocol("MultiActor для стюардесс");
		multiStuardessa.setOriginal(getStuardessa());
		multiStuardessa.setNumberOfClones(main.getChooseData_cnt_stuardes().getInt());
		}
		return multiStuardessa;
	}
	
	private Stuardessa getStuardessa() {
		if (stuardessa == null){
			stuardessa = new Stuardessa("Стюардесса", main, this);
		}
		return stuardessa;
	}

	public Histo getHistoWaitBilet() {
		if (histoWaitBilet==null){histoWaitBilet=new Histo();}
		return histoWaitBilet;
		
	}
	public Histo getHistoByPlane() {
		if (timeByPlane==null){timeByPlane=new Histo();}
		return timeByPlane;
		
	}
	public Histo getHistoByPosadka() {
		if (timeByPosadka==null){timeByPosadka=new Histo();}
		return timeByPosadka;
		
	}

	@Override
	public void initForExperiment(double factor) {
		getMultiCassir().setNumberOfClones((int)(factor));			
	}

	@Override
	public Map<String, Double> getResultOfExperiment() {	
		
		Map<String, Double> transMap = new HashMap<>();
		transMap.put("Середнє значення черги в касу", getDiscretHistoToCassa().getAverage());
		transMap.put("Середнє значення черги до стюардеси", getDiscretHistoKStuardessa().getAverage());
		
		return transMap;

		//return getDiscretHistoToCassa().getAverage();
		
	}

	@Override
	public void initForTrans(double finishTime) {
		getCassir().setFinishTime(finishTime);
		getPlane().setFinishTime(finishTime);
		getStuardessa().setFinishTime(finishTime);
		main.getChooseData_time_model().setDouble(finishTime);
		
	}

	@Override
	public void resetTransAccum() {
		getOcheredVCassu().resetAccum();
		
	}

	@Override
	public Map<String, Double> getTransResult() {
		Map<String, Double> transMap = new HashMap<>();
		transMap.put("Черга в касу", getDiscretHistoToCassa().getAverage());
		//transMap.put("Черга на посадку", getDis).getAccumAverage());
		transMap.put("Черга до стюардеси", getDiscretHistoKStuardessa().getAverage());
		transMap.put("Черга до літака", getDiscretHistoPlane().getAverage());
		//transMap.put("Черга до літака", getDiscretHistoPlane().getAverage());
		return transMap;
		//return getOcheredVCassu().getAvg();
	}

	public Histo getWaitTimeHistoCasir() {
		// TODO Auto-generated method stub
		return histoCasir;
	}




}



