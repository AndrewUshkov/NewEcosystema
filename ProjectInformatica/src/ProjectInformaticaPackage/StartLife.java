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
	public static void main(String[] args) {    //����� �������� �������� ���������
		int quantTime;
		
		//���������� � ������� (���������� ����������)
		Information.readSizeOfCellFromConsole();
		Information.loadImages();
		
		MainFrame frame=new MainFrame(); //������� ������� ����
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
		JMenu menuNewWorld=new JMenu("����� ���");
		JMenu menuActions=new JMenu("��������");
		//JMenu menuGraphs=new JMenu("�������");
		menuBar.add(menuNewWorld);
		menuBar.add(menuActions);
		//menuBar.add(menuGraphs);
		//JMenuItem stopItem=new JMenuItem("����������");
		JMenuItem pauseItem=new JMenuItem("�����");
		JMenuItem renewalItem=new JMenuItem("�����������");
		JMenuItem createItem=new JMenuItem("�������..");
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
			if (Information.worldCreated()) {  //��������� ���� ������ ���� �����, ��� ��� �����������
			
		quantTime=Information.getQuantTime();
		if (quantTime>0) {
			
			predatorThread.run();      //��� ��� ������- ��� �������� � �����
			herbivoreThread.run();
			grassThread.run();
			try {
				Thread.sleep(quantTime);/*����� ������ �����������*/ 
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
