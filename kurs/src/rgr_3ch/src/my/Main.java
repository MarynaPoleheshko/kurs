package rgr_3ch.src.my;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import process.Dispatcher;
import rnd.Negexp;
import rnd.Uniform;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import widgets.regres.RegresAnaliser;
import widgets.trans.TransProcessManager;
import widgets.experiments.ExperimentManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private Diagram diagram;
	private Diagram diagram_1;
	private Diagram diagram_3;
	private Diagram diagram_4;
	private Diagram diagram_2;
	private JButton button;
	private ChooseData chooseData_time_model;
	private ChooseData chooseData_cnt_seats;
	private ChooseData chooseData_cnt_stuardes;
	private ChooseData chooseData_cnt_cas;
	private ChooseRandom chooseRandom_time_posadki;
	private ChooseRandom chooseRandom_time_fluing;
	private ChooseRandom chooseRandom_obsluziv_stuardessa;
	private ChooseRandom chooseRandom_obsluzivanije_v_kassa;
	private ChooseRandom chooseRandom_pojavlenie_pas;
	private JPanel panel_3;
	JTabbedPane tabbedPane;
	JSplitPane splitPane;
	private ChooseData chooseData_cntPlane;
	private Diagram getDiagramHistoQueueToLoader;
	private JTextArea textArea;
	private JComboBox comboBox;
	private JButton btnStat;
	private Model model;
	private JPanel panel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 633);

		setContentPane(getContentPane());

		getContentPane().add(getSplitPane(), "name_12641413006510");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				null,
				"\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438 \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u043E\u0432",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(250, 10));
		getSplitPane().setLeftComponent(panel);
		panel.setLayout(null);

		chooseRandom_pojavlenie_pas = new ChooseRandom();
		chooseRandom_pojavlenie_pas.setRandom(new Negexp(1));
		chooseRandom_pojavlenie_pas
				.setTitle("\u0418\u043D\u0442\u0435\u0440\u0432\u0430\u043B \u043C\u0435\u0436\u0434\u0443 \u043F\u043E\u044F\u0432\u043B\u0435\u043D\u0438\u044F\u043C\u0438 \u043F\u0430\u0441\u0441\u0430\u0436\u0438\u0440\u0430");
		chooseRandom_pojavlenie_pas.setBounds(0, 21, 250, 63);
		panel.add(chooseRandom_pojavlenie_pas);

		chooseRandom_obsluzivanije_v_kassa = new ChooseRandom();
		chooseRandom_obsluzivanije_v_kassa
				.setTitle("\u0412\u0440\u0435\u043C\u044F \u043E\u0431\u0441\u043B\u0443\u0436\u0438\u0432\u0430\u043D\u0438\u044F \u0432 \u043A\u0430\u0441\u0441\u0435");
		chooseRandom_obsluzivanije_v_kassa.setBounds(0, 85, 250, 63);
		chooseRandom_obsluzivanije_v_kassa.setRandom(new Uniform(1, 5));
		panel.add(chooseRandom_obsluzivanije_v_kassa);

		chooseRandom_obsluziv_stuardessa = new ChooseRandom();
		chooseRandom_obsluziv_stuardessa
				.setTitle("\u0412\u0440\u0435\u043C\u044F \u043E\u0431\u0441\u043B\u0443\u0436\u0438\u0432\u0430\u043D\u0438\u044F \u0441\u0442\u044E\u0430\u0440\u0434\u0435\u0441\u0441\u044B");
		chooseRandom_obsluziv_stuardessa.setBounds(0, 148, 250, 69);
		chooseRandom_obsluziv_stuardessa.setRandom(new Uniform(1, 8));
		panel.add(chooseRandom_obsluziv_stuardessa);

		chooseRandom_time_fluing = new ChooseRandom();
		chooseRandom_time_fluing
				.setTitle("\u0412\u0440\u0435\u043C\u044F \u043F\u043E\u043B\u0435\u0442\u0430 \u0441\u0430\u043C\u043E\u043B\u0435\u0442\u0430");
		chooseRandom_time_fluing.setBounds(0, 219, 250, 63);
		chooseRandom_time_fluing.setRandom(new Uniform(20, 30));
		panel.add(chooseRandom_time_fluing);

		chooseRandom_time_posadki = new ChooseRandom();
		chooseRandom_time_posadki
				.setTitle("\u0412\u0440\u0435\u043C\u044F \u0434\u043B\u044F \u043F\u043E\u0441\u0430\u0434\u043A\u0438 \u043F\u0430\u0441\u0441\u0430\u0436\u0438\u0440\u043E\u0432");
		chooseRandom_time_posadki.setBounds(0, 282, 250, 63);
		chooseRandom_time_posadki.setRandom(new Uniform(1, 10));
		panel.add(chooseRandom_time_posadki);

		chooseData_cnt_cas = new ChooseData();
		chooseData_cnt_cas.setText("2");
		chooseData_cnt_cas.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				getDiagram().setVerticalMaxText(
						getChooseData_cnt_cas().getText());

			}
		});
		chooseData_cnt_cas
				.setTitle("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043A\u0430\u0441\u0441\u0438\u0440\u043E\u0432");
		chooseData_cnt_cas.setBounds(0, 341, 250, 46);
		panel.add(chooseData_cnt_cas);

		chooseData_cnt_stuardes = new ChooseData();
		chooseData_cnt_stuardes.setText("3");
		chooseData_cnt_stuardes.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					String s = getChooseData_cnt_stuardes().getText();
					int m = Integer.parseInt(s) * 5 + 5;
					s = Integer.toString(m);
					getDiagram_1().setVerticalMaxText(s);
				} catch (NumberFormatException e) {
				}
			}
		});

		chooseData_cnt_stuardes
				.setTitle("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0441\u0442\u044E\u0430\u0440\u0434\u0435\u0441\u0441");
		chooseData_cnt_stuardes.setBounds(0, 386, 250, 46);
		panel.add(chooseData_cnt_stuardes);

		chooseData_cnt_seats = new ChooseData();
		chooseData_cnt_seats.setText("15");
		chooseData_cnt_seats
				.setTitle("\u0415\u043C\u043A\u043E\u0441\u0442\u044C \u0441\u0430\u043C\u043E\u043B\u0435\u0442\u0430");
		chooseData_cnt_seats.setBounds(0, 431, 250, 46);
		panel.add(chooseData_cnt_seats);

		chooseData_time_model = new ChooseData();
		chooseData_time_model.setText("1000");
		chooseData_time_model.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				if (getPanel_3() != null && getPanel_3().isShowing()) {
					String s = chooseData_time_model.getText();
					getDiagram().setHorizontalMaxText(s);
					getDiagram_1().setHorizontalMaxText(s);
					getDiagram_2().setHorizontalMaxText(s);
					getDiagram_3().setHorizontalMaxText(s);
					getDiagram_4().setHorizontalMaxText(s);
				}
			}
		});
		chooseData_time_model
				.setTitle("\u0412\u0440\u0435\u043C\u044F \u043C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F");
		chooseData_time_model.setBounds(0, 475, 250, 46);
		panel.add(chooseData_time_model);

		chooseData_cntPlane = new ChooseData();
		chooseData_cntPlane.setText("5");
		chooseData_cntPlane.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				getDiagram_3().setVerticalMaxText(
						getChooseData_cntPlane().getText());
			}
		});
		chooseData_cntPlane
				.setTitle("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0441\u0430\u043C\u043E\u043B\u0435\u0442\u043E\u0432");
		chooseData_cntPlane.setBounds(0, 523, 250, 46);
		panel.add(chooseData_cntPlane);

		getSplitPane().setRightComponent(getTabbedPane());

		JPanel panel_2 = new JPanel();
		getTabbedPane()
				.addTab("\u0415\u043A\u0441\u043F\u0435\u0440\u0438\u043C\u0435\u043D\u0442",
						null, panel_2, null);
		panel_2.setLayout(new CardLayout(0, 0));

		ExperimentManager experimentManager = new ExperimentManager();
		experimentManager.getChooseDataFactors().setTitle("\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u0430\u0441\u0438\u0440\u0456\u0432");
		experimentManager.getChooseDataFactors().setText("1 2 3 4");
		experimentManager.setFactory((d) -> new Model(d, this));
		panel_2.add(experimentManager, "name_763398340914985");

		;
	}

	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);

			panel_3 = new JPanel();
			getTabbedPane().addTab("Test", null, panel_3, null);
			panel_3.setLayout(new GridLayout(3, 2, 0, 0));

			diagram = new Diagram();
			diagram.getDiagramPanel().setGridByY(2);
			diagram.setHorizontalMaxText("1000");
			diagram.setPainterColor(Color.RED);
			diagram.setTitleText("\u041E\u0447\u0435\u0440\u0435\u0434\u044C \u0432 \u043A\u0430\u0441\u0441\u0443");
			panel_3.add(diagram);

			diagram_1 = new Diagram();
			diagram_1.setPainterColor(Color.RED);
			diagram_1.setHorizontalMaxText("1000");
			diagram_1
					.setTitleText("\u041E\u0447\u0435\u0440\u0435\u0434\u044C \u043A \u0441\u0442\u044E\u0430\u0440\u0434\u0435\u0441\u0441\u0430\u043C");
			panel_3.add(diagram_1);

			diagram_3 = new Diagram();
			diagram_3.setPainterColor(Color.RED);
			diagram_3.setHorizontalMaxText("1000");
			diagram_3
					.setTitleText("\u041E\u0447\u0435\u0440\u0435\u0434\u044C \u0441\u0430\u043C\u043E\u043B\u0435\u0442\u043E\u0432");
			panel_3.add(diagram_3);

			diagram_4 = new Diagram();
			diagram_4.setPainterColor(Color.RED);
			diagram_4.setHorizontalMaxText("1000");
			diagram_4
					.setTitleText("\u0423\u0448\u0435\u0434\u0448\u0438\u0435 \u043D\u0430 \u0432\u043E\u043A\u0437\u0430\u043B");
			panel_3.add(diagram_4);

			JPanel panel_4 = new JPanel();
			panel_3.add(panel_4);
			panel_4.setLayout(null);

			button = new JButton(
					"\u041F\u043E\u0441\u0442\u0440\u043E\u0438\u0442\u044C");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					doStart();
				}

			});
			button.setBounds(105, 45, 134, 89);
			panel_4.add(button);

			diagram_2 = new Diagram();
			diagram_2.setVerticalMaxText("20");
			diagram_2.setPainterColor(Color.RED);
			diagram_2.setHorizontalMaxText("1000");
			diagram_2
					.setTitleText("\u041E\u0447\u0435\u0440\u0435\u0434\u044C \u043D\u0430 \u043F\u043E\u0441\u0430\u0434\u043A\u0443 \u0432 \u0441\u0430\u043C\u043E\u043B\u0435\u0442");
			panel_3.add(diagram_2);

			JPanel panel_1 = new JPanel();
			getTabbedPane()
					.addTab("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430",
							null, panel_1, null);
			panel_1.setLayout(null);

			btnStat = new JButton("Start");
			btnStat.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					doStart2();

				}

				private void doStart2() {

					final Dispatcher dispatcher = new Dispatcher();
					Factory factory = new Factory(Main.this);
					model = factory.createModel(dispatcher);
					model.initForStat();
					getDiagramHistoQueueToLoader().clear();
					getTextArea().setText("");
					getJButtonStat().setEnabled(false);
					getComboBox().setEnabled(false);
					dispatcher.start();
					new Thread() {
						public void run() {
							try {
								dispatcher.getThread().join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							getJButtonStat().setEnabled(true);
							getComboBox().setEnabled(true);
							model.getDiscretHistoToCassa().showRelFrec(
									getDiagramHistoQueueToLoader());
							getTextArea().setText(
									model.getDiscretHistoToCassa().toString());
							getTextArea().select(0, 0);
						}
					}.start();
				}
			});
			btnStat.setBounds(252, 318, 89, 23);
			panel_1.add(btnStat);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(742, 442, -718, -410);
			panel_1.add(scrollPane);

			textArea = new JTextArea();
			textArea.setBounds(316, 74, 247, 209);
			panel_1.add(textArea);

			getDiagramHistoQueueToLoader = new Diagram();
			getDiagramHistoQueueToLoader.setBounds(25, 74, 247, 197);
			panel_1.add(getDiagramHistoQueueToLoader);

			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (comboBox.getSelectedItem() == "Черга в кассу") {
						model.getDiscretHistoToCassa().showRelFrec(
								getDiagramHistoQueueToLoader());
						getTextArea().setText(
								model.getDiscretHistoToCassa().toString());
						getTextArea().select(0, 0);
					} else if (comboBox.getSelectedItem() == "Черга до стюардесси") {
						model.getDiscretHistoKStuardessa().showRelFrec(
								getDiagramHistoQueueToLoader());
						getTextArea().setText(
								model.getDiscretHistoKStuardessa().toString());
						getTextArea().select(0, 0);
					}

					else if (comboBox.getSelectedItem() == "Черга літаків") {
						model.getDiscretHistoPlane().showRelFrec(
								getDiagramHistoQueueToLoader());
						getTextArea().setText(
								model.getDiscretHistoPlane().toString());
						getTextArea().select(0, 0);
					}

					else if (comboBox.getSelectedItem() == "Черга на посадку у літак") {
						model.getDiscretHistoToPosadka().showRelFrec(
								getDiagramHistoQueueToLoader());
						getTextArea().setText(
								model.getDiscretHistoToPosadka().toString());
						getTextArea().select(0, 0);
					}

					// else if
					// (comboBox.getSelectedItem()=="Час перебування у черзі в кассу"){

					// }
					// else if
					// (comboBox.getSelectedItem()=="Час перебування у черзі до срюардесси"){

					// }
					// else if
					// (comboBox.getSelectedItem()=="Час перебування у черзі на посадку"){

					// }

					// else if
					// (comboBox.getSelectedItem()=="Час перебування у черзі літаків"){

					// }

				}
			});
			comboBox.setBounds(169, 11, 266, 29);
			panel_1.add(comboBox);
			final String[] items = { "Черга в кассу", "Черга до стюардесси",
					"Черга на посадку у літак", "Черга літаків",
					"Час перебування у черзі в кассу",
					"Час перебування у черзі до срюардесси",
					"Час перебування у черзі на посадку",
					"Час перебування у черзі літаків" };
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(
					items);
			comboBox.setModel(model);

			panel_5 = new JPanel();
			tabbedPane.addTab("\u041F\u041F", null, panel_5, null);
			panel_5.setLayout(new CardLayout(0, 0));

			TransProcessManager transProcessManager = new TransProcessManager();
			transProcessManager.setFactory((d)->new Model(d, this));
			panel_5.add(transProcessManager, "name_763356898877712");

		}

		return tabbedPane;
	}

	public JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();

		}
		return splitPane;
	}

	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new CardLayout(0, 0));
		}
		return contentPane;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public Diagram getDiagram_1() {
		return diagram_1;
	}

	public Diagram getDiagram_3() {
		return diagram_3;
	}

	public Diagram getDiagram_4() {
		return diagram_4;
	}

	public Diagram getDiagram_2() {
		return diagram_2;
	}

	public JButton getButton() {
		return button;
	}

	private void doStart() {
		final Dispatcher dispatcher = new Dispatcher();
		Factory factory = new Factory(Main.this);
		Model model = factory.createModel(dispatcher);
		model.initForTest();
		getButton().setEnabled(false);
		getDiagram().clear();
		getDiagram_1().clear();
		getDiagram_2().clear();
		getDiagram_3().clear();
		getDiagram_4().clear();

		dispatcher.start();
		new Thread() {
			public void run() {
				try {
					dispatcher.getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getButton().setEnabled(true);
			}
		}.start();

	}

	public ChooseData getChooseData_time_model() {
		return chooseData_time_model;
	}

	public ChooseData getChooseData_cnt_seats() {
		return chooseData_cnt_seats;
	}

	public ChooseData getChooseData_cnt_stuardes() {
		return chooseData_cnt_stuardes;
	}

	public ChooseData getChooseData_cnt_cas() {
		return chooseData_cnt_cas;
	}

	public ChooseRandom getChooseRandom_time_posadki() {
		return chooseRandom_time_posadki;
	}

	public ChooseRandom getChooseRandom_time_fluing() {
		return chooseRandom_time_fluing;
	}

	public ChooseRandom getChooseRandom_obsluziv_stuardessa() {
		return chooseRandom_obsluziv_stuardessa;
	}

	public ChooseRandom getChooseRandom_obsluzivanije_v_kassa() {
		return chooseRandom_obsluzivanije_v_kassa;
	}

	public ChooseRandom getChooseRandom_pojavlenie_pas() {
		return chooseRandom_pojavlenie_pas;
	}

	public JPanel getPanel_3() {
		return panel_3;
	}

	public ChooseData getChooseData_cntPlane() {
		return chooseData_cntPlane;
	}

	public Diagram getDiagramHistoQueueToLoader() {
		return getDiagramHistoQueueToLoader;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getJButtonStat() {
		return btnStat;
	}

}
