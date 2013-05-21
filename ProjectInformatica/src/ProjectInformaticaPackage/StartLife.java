package ProjectInformaticaPackage;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class StartLife extends JFrame {
	public static void main(String[] args) {    //здесь начинает работать программа
		int quantTime;
		
		//подготовка к запуску (считывание информации)
		Information.readSizeOfCellFromConsole();
		Information.loadImages();
		
		MainFrame frame=new MainFrame(); //создаем главное окно
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		Image icon=kit.getImage("Textures/IconLeave.jpg");
		frame.setIconImage(icon);
		frame.setLocation(0, 0);
		frame.setBounds(0,0 , screenSize.width, screenSize.height);
		frame.setExtendedState(MAXIMIZED_BOTH);
		Information.readDefaultHeightFromConsole(frame);
		Information.readDefaultWeightFromConsole(frame);
		
		
		
		JMenuBar menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menuNewWorld=new JMenu("Новый мир");
		JMenu menuActions=new JMenu("Действия");
		//JMenu menuGraphs=new JMenu("Графики");
		menuBar.add(menuNewWorld);
		menuBar.add(menuActions);
		//menuBar.add(menuGraphs);
		//JMenuItem stopItem=new JMenuItem("Остановить");
		JMenuItem pauseItem=new JMenuItem("Пауза");
		JMenuItem renewalItem=new JMenuItem("Возобновить");
		JMenuItem createItem=new JMenuItem("Создать..");
		//menuActions.add(stopItem);
		menuActions.add(pauseItem);
		menuActions.add(renewalItem);
		menuNewWorld.add(createItem);
		PauseAction pause= new PauseAction(0);
		PauseAction renewal=new PauseAction(100);
		CreateAction newWorld=new CreateAction();
		pauseItem.addActionListener(pause);
		renewalItem.addActionListener(renewal);
		createItem.addActionListener(newWorld);
		
		
		frame.setVisible(true);
		
		
		
		
		PredatorThread predatorThread=new PredatorThread();
		HerbivoreThread herbivoreThread=new HerbivoreThread();
		GrassThread grassThread=new GrassThread();
		
		do {
			System.out.println("");
			if (Information.worldCreated()) {  //запускаем нити только если знаем, что мир сформирован
			
		quantTime=Information.getQuantTime();
		if (quantTime>0) {
			
			predatorThread.run();      //эти три потока- для животных и травы
			herbivoreThread.run();
			grassThread.run();
			try {
				Thread.sleep(quantTime);/*чтобы успеть просмотреть*/ 
				} catch (Exception e) {}
			try {
			predatorThread.predator.join();
			predatorThread.predator.join();
			predatorThread.predator.join();
			} catch (InterruptedException e) {System.out.println("Can't wait threads");}
			frame.repaint();
		}	
			}
		} while (true);
		
		
	}
}
class PauseAction implements ActionListener {
	int time;
	public PauseAction(int quantTime) {
		time=quantTime;
	}
	public void actionPerformed(ActionEvent event) {
		if (time==0) {}
		Information.setQuantTime(time);
	}
}
class CreateAction implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		JFrame test=new CreateNewWorld();
		test.setVisible(true);
		
	}
}
