package ProjectInformaticaPackage;

import java.awt.Image;
import java.util.Iterator;
import java.util.Random;

	public class Grass {                 // ������������� ���������, ��� ����� ����� ��� � ���� "�����". �� ���� � ���������� ����� ��� 
	   private int xPosition;           //������� ������������, ������� � ���������� ��������, ������� �������� ����������� ������.
	   private int yPosition;
       private int timeNewAppears=20;    
       private int age;
       private float energyValue;         //������� �������
       private float energyQuantity;     //���������� ������� (����� �������)
       private int startAge;
       private Grass currentGrass;
       private Grass[] neighbours=new Grass[8];   //��� �����������
       private int numberNeighbours=0;
       private int wantToBorn=-1;     //�������� �� ���������
       
       Grass(int xPosition, int yPosition) {
    	   Random rand=new Random();
    	   this.xPosition=xPosition;
    	   this.yPosition=yPosition;
    	   this.startAge=rand.nextInt(500);
    	   this.age=this.startAge;
    	   this.energyValue=3+rand.nextInt(4);
    	   this.energyQuantity=50+rand.nextInt(100);
       }
       
       public boolean makeDecision(){        //���� ����� ���������� �� StartLife ��� ������ ������ ���� ��������� �� ������� �����
    	   if (energyQuantity<=0) {       //������ ��� �� �����
    		   return false;
    		   }
    	   this.age--;                        //����� ������ ������� ����� � ������� ����� ������, ��� ��� ������- ������������ ��� ���
    	   if (age<=0) return false; 
    	   this.timeNewAppears--;
    	   if ((age<=60)&&(this.timeNewAppears<=0)) {
    		   this.doReproduction();
    	   }
    	   return true; 
       }
       public float eat() {
    	   if (age>=50) {
    		   this.energyQuantity-=this.energyValue; 
    		   return energyValue;
    		   } else {
    			   this.energyQuantity-=this.energyValue-2;           // ������ ������ ������ ����������� �� ������ �������
    			   return energyValue-2;          //������ ������, ������� ���� �������� �������
    		   }
    		   
       }
       private void doReproduction() {
    	   this.numberNeighbours=0;
    	   
    	   for (int i=0;i<8;i++) {this.neighbours[i]=null;}
    	   
    	   for (Iterator<Grass> current = Information.getLinkedListOfGrass().iterator(); 
					current.hasNext(); ) {
    		   currentGrass=current.next();
    		   if ((currentGrass.getXPosition()-xPosition==1)&&(currentGrass.getYPosition()-yPosition==0)) {
    			   neighbours[0]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==1)&&(currentGrass.getYPosition()-yPosition==-1)) {
    			   neighbours[1]=currentGrass;
    			   this.numberNeighbours++;
    		   } 
    		   if ((currentGrass.getXPosition()-xPosition==0)&&(currentGrass.getYPosition()-yPosition==-1)) {
    			   neighbours[2]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==-1)&&(currentGrass.getYPosition()-yPosition==-1)) {
    			   neighbours[3]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==-1)&&(currentGrass.getYPosition()-yPosition==0)) {
    			   neighbours[4]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==-1)&&(currentGrass.getYPosition()-yPosition==1)) {
    			   neighbours[5]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==0)&&(currentGrass.getYPosition()-yPosition==1)) {
    			   neighbours[6]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		   if ((currentGrass.getXPosition()-xPosition==1)&&(currentGrass.getYPosition()-yPosition==1)) {
    			   neighbours[7]=currentGrass;
    			   this.numberNeighbours++;
    		   }
    		 }
    	   
    	   
    	   
    	   //������ �� ����� ������� ������
    	   if (this.numberNeighbours<4) {
    		   System.out.println("Born Grass");
    		   int i=-1;
    		   do {
    			   i++;
    		   } while (neighbours[i]!=null);
    		   this.wantToBorn=i;
    	   }
    	   
    	  this.timeNewAppears=20;
    	   
    	   
       }
       public Image getGrassImage() {
    			
    			if ((age>=50)&&(energyQuantity>=60)) return Information.getImageYoungManyGreenTree();
    			if ((age>=50)&&(energyQuantity<60)) return Information.getImageYoungFewGreenTree();
    			if ((age<50)&&(energyQuantity>=60)) return Information.getImageOldManyGreenTree();
    			if ((age<50)&&(energyQuantity<60)) return Information.getImageOldFewGreenTree();
    			return null;
    	}

	public int getXPosition() {
		return xPosition;
	}
	public int wantToBorn() {
		return this.wantToBorn;
	}

	public int getYPosition() {
		return yPosition;
	}
	public int getAge() {
		return this.age;
	}
	public int getStartAge() {
		return this.startAge;
	}
	public void setWantToBorn (int i) {
		this.wantToBorn=i;
	}
}
