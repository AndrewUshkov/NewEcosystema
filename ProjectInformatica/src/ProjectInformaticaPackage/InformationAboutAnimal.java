package ProjectInformaticaPackage;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InformationAboutAnimal extends JFrame { 
	Image imageInformationAnimal;
	
	LifeForm infoAnimal;
	
	JLabel labelIsMale=new JLabel("Пол:");
	JLabel labelIsMaleInfo=new JLabel();
	
	JLabel labelStarvation=new JLabel("Голод:");
	JLabel labelStarvationInfo=new JLabel();
	
	JLabel labelExhaustion=new JLabel("Усталость:");
	JLabel labelExhaustionInfo=new JLabel();
	
	JLabel labelPassion=new JLabel("Страсть:");
	JLabel labelPassionInfo=new JLabel();
	
	JLabel labelCurrentAction=new JLabel("Действие:");
	JLabel labelCurrentActionInfo=new JLabel();
	
	JLabel labelAge=new JLabel("Возраст:");
	JLabel labelAgeInfo=new JLabel();
	
	JButton enter=new JButton("OK");
		
	
		public InformationAboutAnimal(LifeForm animal) {
			
			this.infoAnimal=animal;
			infoAnimal.setBigCursor(true);
			try {
				imageInformationAnimal=ImageIO.read(new File("Textures/InformationAnimal.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setIconImage(imageInformationAnimal);
			this.setTitle("Информация");
			this.setSize(300,250);
			setLayout(null); 
			add(labelIsMale);
			add(labelStarvation);
			add(labelExhaustion);
			add(labelPassion);
			add(labelCurrentAction);
			add(labelIsMaleInfo);
			add(labelStarvationInfo);
			add(labelExhaustionInfo);
			add(labelPassionInfo);
			add(labelCurrentActionInfo);
			add(labelAge);
			add(labelAgeInfo);
			add(enter);
			
			//расположение элементов в окне
			labelAge.setBounds(5, 5, 150, 20);
			labelAgeInfo.setBounds(160, 5, 150, 20);
			
			labelIsMale.setBounds(5,30,150,20);
			labelIsMaleInfo.setBounds(160,30,150,20);
			
			labelStarvation.setBounds(5, 55, 150, 20);
			labelStarvationInfo.setBounds(160, 55, 150, 20);
			
			labelExhaustion.setBounds(5,80,150, 20);
			labelExhaustionInfo.setBounds(160,80,150, 20);
			
			labelPassion.setBounds(5, 105, 150, 20);
			labelPassionInfo.setBounds(160, 105, 150, 20);
			
			labelCurrentAction.setBounds(5, 130, 150, 20);
			labelCurrentActionInfo.setBounds(160, 130, 150, 20);
			
			enter.setBounds(180, 165, 80, 20);
			
			Exit exit=new Exit(this);
			enter.addActionListener(exit);
			this.repaintInfo();
			
			
					
			
		}
	public void repaintInfo() {
		if (infoAnimal.isMale()) {labelIsMaleInfo.setText("самец");} else {labelIsMaleInfo.setText("самка");}
		labelStarvationInfo.setText(String.valueOf((((int)(infoAnimal.getStarvation()*100))/100))+" %");
		labelExhaustionInfo.setText(String.valueOf(((int)(infoAnimal.getExhaustion()*100))/100)+" %");
		labelPassionInfo.setText(String.valueOf(((int)(infoAnimal.getPassion()*100))/100)+" %");
		labelAgeInfo.setText(String.valueOf(infoAnimal.getAge()));
		switch (infoAnimal.getPreviousAction()) {
		case 0:
			labelCurrentActionInfo.setText("Детеныш");
		break;
		case 1:
			labelCurrentActionInfo.setText("Голод");
		break;
		case 2:
			labelCurrentActionInfo.setText("Страсть");
		break;
		case 3:
			labelCurrentActionInfo.setText("Сон");
		break;
		}
		this.repaint();
	}
	
}

class Exit implements ActionListener {
	InformationAboutAnimal b;
	public Exit(InformationAboutAnimal a) {
		b=a;
	}
	public void actionPerformed(ActionEvent event) {
		b.setVisible(false);
		b.infoAnimal.setBigCursor(false);
	}
}


