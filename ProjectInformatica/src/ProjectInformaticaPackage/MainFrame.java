package ProjectInformaticaPackage;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	class MainFrame extends JFrame {                                   
		public MainFrame() {
			setTitle("ProjectInformatica");
			BioPanel lpanel=new BioPanel();
			add(lpanel);
			
			
		}
	}
	
	class BioPanel extends JPanel {
		int x;
		int y;
		int cell=Information.getSizeOfCell();
		boolean isEvent;
		InformationAboutAnimal info;
		public BioPanel() {
			this.addMouseListener(new MouseListener() {
				@Override
	            public void mouseReleased(MouseEvent e) {
	            }
	            @Override
	            public void mousePressed(MouseEvent e) {
	            }
	            @Override
	            public void mouseExited(MouseEvent e) {
	            }
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            }
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if (Information.worldCreated()) {
	            	x=e.getX();
	            	y=e.getY();
	            	isEvent=true;
	            	}
	            }
			});
		}
		
	private LifeForm findAnimal() {
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
		    Leo val = current.next();
		    if ((x>=val.getXPosition())&&
		    		(x<=val.getXPosition()+cell)&&
		    		(y>=val.getYPosition())&&
		    		(y<=val.getYPosition()+cell)) {
		    	return val;
		    }
		}
		    for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores().iterator(); current.hasNext(); ) {
			    Herbivore valh = current.next();
			    if ((x>=valh.getXPosition())&&(x<=valh.getXPosition()+cell)&&(y>=valh.getYPosition())&&(y<=valh.getYPosition()+cell)) {
			    	return valh;
			    }
		    }
		return null;
	}
	private void showInformationAboutAnimal() {
		LifeForm animal;
		if ((animal=this.findAnimal())!=null) {
			if (info!=null) {info.infoAnimal.setBigCursor(false); info.setVisible(false);}
		this.info=new InformationAboutAnimal(animal);
		info.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			info.infoAnimal.setBigCursor(false);
			info.setVisible(false);
			}
			});
		info.setVisible(true);
		}
	}
	public void paintComponent(Graphics g) {    // ������ ���� �������� � ��� �����
						super.paintComponent(g);
						int sizeOfCell=Information.getSizeOfCell();
						
						g.drawImage(Information.getImageGround(),0,0,this.getSize().width,this.getSize().height,null);
						
						
						if (Information.worldCreated()) {
							for (Iterator<Grass> current = Information.getLinkedListOfGrass().iterator(); current.hasNext(); ) {                              //���������� ��� �����
							
								Grass val1 = current.next();
								if (val1.getGrassImage()==null) return;
								if (val1.getAge()>val1.getStartAge()-20) {
						    	
									g.drawImage(Information.getImageVeryYoungTree(), 
									val1.getXPosition()*Information.getSizeOfCell(), 
									val1.getYPosition()*Information.getSizeOfCell(),
									sizeOfCell/2,sizeOfCell/2, 
									null);
						    	
									} else {
								g.drawImage(val1.getGrassImage(), 
								val1.getXPosition()*Information.getSizeOfCell(), 
								val1.getYPosition()*Information.getSizeOfCell(),
								sizeOfCell,
								sizeOfCell, 
								null);
						    }
						}
						
						
						for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
						    Leo val = current.next();
						    Image image;
						    if ((image=val.getAnimalImage())==null) return;
						    
						    
						    if (val.isChild()) {
						    
						    		g.drawImage(image,    //��� ����� � ������� getAnimalImage ����� � ����������� �� ��������� ������� (��������, �� ����) ������� ������ ��������
						    		val.getXPosition(), 
									val.getYPosition(),
									sizeOfCell*(30-val.timeOfInertion)/30,
									sizeOfCell*(30-val.timeOfInertion)/30,
									null);
						    } else
						    	g.drawImage(image,    //��� ����� � ������� getAnimalImage ����� � ����������� �� ��������� ������� (��������, �� ����) ������� ������ ��������
					    		val.getXPosition(), 
								val.getYPosition(),
								sizeOfCell,sizeOfCell, 
								null);
						    
						    
						    if (val.haveBigCursor()) {
						    	g.drawImage(Information.getImageBigCursor(),
						    	val.getXPosition()-25,
						    	val.getYPosition()-25,
						    	25, 
						    	25, 
						    	null);
						    }
						    
						}
						
						for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores().iterator(); current.hasNext(); ) {
						    Herbivore val = current.next();
						    Image image;
						    if ((image=val.getAnimalImage())==null) return;
						    
						    
						    if ((val.isChild())&&(val.isAlive())) {
						    
						    		g.drawImage(image,    //��� ����� � ������� getAnimalImage ����� � ����������� �� ��������� ������� (��������, �� ����) ������� ������ ��������
						    		val.getXPosition(), 
									val.getYPosition(),
									sizeOfCell*(30-val.timeOfInertion)/30,
									sizeOfCell*(30-val.timeOfInertion)/30, 
									null);
						    } else
						    	g.drawImage(image,    //��� ����� � ������� getAnimalImage ����� � ����������� �� ��������� ������� (��������, �� ����) ������� ������ ��������
					    		val.getXPosition(), 
								val.getYPosition(),
								sizeOfCell,sizeOfCell, 
								null);
						    
						    if (val.haveBigCursor()) {
						    	g.drawImage(Information.getImageBigCursor(),
						    	val.getXPosition()-25, 
						    	val.getYPosition()-25, 
						    	25, 
						    	25, 
						    	null);
						    }
						    
						    
						}
						
						if (isEvent) {this.showInformationAboutAnimal(); isEvent=false;}
						if (info!=null) {info.repaintInfo();}
						
					}
						
						
						
						
					
					}
		
		}
