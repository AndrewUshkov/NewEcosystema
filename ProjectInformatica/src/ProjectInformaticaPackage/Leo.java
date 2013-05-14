package ProjectInformaticaPackage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.*;

import javax.imageio.ImageIO;

public class Leo implements LifeForm {
private boolean male;
private float age;
private float passion;
private float starvation;
private float exhaustion;
private int xPosition;
private int yPosition;
private float starvationCoefficient;
private float passionCoefficient;
private float exhaustionCoefficient;
private float legacyStarvationCoefficient;
private float legacyPassionCoefficient;
private float legacyExhaustionCoefficient;
private float decision;
private int timeOfPregnant=-1;
private boolean wantToBorn=false;
private Leo fromWhom=null;
//ArrayList <LeoWish> leoNeeds;
private boolean isChild=true;
private char previousAction=0;
private Leo badFemale=null;                                        //самка, несогласная на спаривание
public int timeOfInertion= this.isChild ? 30:0;
private boolean hasNoThread=true;
private boolean bigCursor=false;
public float getExhaustion() {
	return exhaustion;
}
public void setExhaustion(float exhaustion) {
	this.exhaustion = exhaustion;
}
public float getExhaustionCoefficient() {
	return exhaustionCoefficient;
}
public void setExhaustionCoefficient(float exhaustionCoefficient) {
	this.exhaustionCoefficient = exhaustionCoefficient;
}
public boolean isChild() {
	return this.isChild;
}
public float getLegacyExhaustionCoefficient() {
	return legacyExhaustionCoefficient;
}
public void setLegacyExhaustionCoefficient(float legacyExhaustionCoefficient) {
	this.legacyExhaustionCoefficient = legacyExhaustionCoefficient;
}
ArrayList<Leo> listOfLeos=Information.getListOfLeos();


public Leo (/*MainFrame window, */boolean IfMale, int startXPosition, int startYPosition, float startAge, 
						float startStarvation, float startPassion, float startExhaustion,
								float startStarvationCoefficient, float startPassionCoefficient, float startExhaustionCoefficient,
										float legacyStarvationCoefficient, float legacyPassionCoefficient, float legacyExhaustionCoefficient) {
	this.male=IfMale;
	this.xPosition=startXPosition;
	this.yPosition=startYPosition;
	this.age=startAge;
	this.starvation=startStarvation;
	this.passion=startPassion;
	this.exhaustion=startExhaustion;
	this.legacyPassionCoefficient=legacyPassionCoefficient;
	this.legacyStarvationCoefficient=legacyStarvationCoefficient;
	this.legacyExhaustionCoefficient=legacyExhaustionCoefficient;
	this.passionCoefficient=startPassionCoefficient;
	this.starvationCoefficient=startStarvationCoefficient;
	this.exhaustionCoefficient=startExhaustionCoefficient;
	
}
public float getAge() {
	return age;
}
public void setAge(float newAge) {
	this.age=newAge;
}
public float getPassion() {
	return passion;
}
public int getPreviousAction() {
	return this.previousAction;
}
public void setPassion(float passion) {
	this.passion = passion;
}
public float getStarvation() {
	return starvation;
}
public void setStarvation(float starvation) {
	this.starvation = starvation;
}
public int getXPosition() {
	return xPosition;
}
public void setXPosition(int xPosition) {
	this.xPosition = xPosition;
}
public int getYPosition() {
	return yPosition;
}
public void setYPosition(int yPosition) {
	this.yPosition = yPosition;
}
public boolean isMale() {
	return this.male;
}
public void moveToRelative(int xRelative, int yRelative) {
	this.xPosition+=xRelative;
	this.yPosition+=yRelative;
}
public void moveToAbs(int xAbs, int yAbs) {
	this.xPosition=xAbs;
	this.yPosition=yAbs;
}
public void setStarvationCoefficient(float newSCoef) {
	
}
public float getStarvationCoefficient() {
	return this.starvationCoefficient;
}
public void setPassionCoefficient(float newPCoef) {
	this.passionCoefficient=newPCoef;
}
public float getLegacyStarvationCoefficient() {
	return this.starvationCoefficient;
}
public void setLegacyStarvationCoefficient(float newLSCoef) {
	this.starvationCoefficient=newLSCoef;
}
public float getLegacyPassionCoefficient() {
	return this.legacyPassionCoefficient;
}
public void setLegacyPassionCoefficient(float newLPCoef) {
	legacyPassionCoefficient=newLPCoef;
}
public Image getAnimalImage() {
	if (this.isMale()) {
		if (this.previousAction==3) {return Information.getImageSleepingLeo();}
		if (this.isChild) {return Information.getImageLeoChild();}
		if (this.previousAction==7) {return Information.getImagePassionLeo();}
		return Information.getImageLeo();
	} else {
		if (this.previousAction==3) {return Information.getImageSleepingLeoFemale();}
		if (this.isChild) {return Information.getImageLeoFemaleChild();}
		if (this.previousAction==7) {return Information.getImagePassionLeo();}
		return Information.getImageLeoFemale();
	}
}
public boolean femaleAgree() {
	if ((passion<=50)&&(this.previousAction!=3)) return true; return false;
}
public CoordinatesXY goToNearestPredator() {
	CoordinatesXY Coords=new CoordinatesXY();
	/*int index=0;
	for (int i=0;i<Information.getAmountPredator();i++) {
		if ((listOfLifeForms.get(i).getXPosition()+listOfLifeForms.get(i).getYPosition())*(listOfLifeForms.get(i).getXPosition()+listOfLifeForms.get(i).getYPosition())
				< (listOfLifeForms.get(index).getXPosition()+listOfLifeForms.get(index).getYPosition())*(listOfLifeForms.get(index).getXPosition()+listOfLifeForms.get(index).getYPosition())) {
			index=i;
		}
	}
	Coords.setXCoord(listOfLifeForms.get(index).getXPosition());
	Coords.setYCoord(listOfLifeForms.get(index).getYPosition());
	if (Coords.getXCoord()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
	if (Coords.getYCoord()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
	return Coords;*/
	Iterator<Leo> currentLifeForm = Information.getLinkedListOfLeos().iterator();
	if (currentLifeForm.hasNext()) {currentLifeForm.next();}
	Leo nearestLeo=Information.getLinkedListOfLeos().getFirst();
    while(currentLifeForm.hasNext()){
         Leo element =currentLifeForm.next();
         if ((element.getXPosition()+element.getYPosition())*(element.getXPosition()+element.getYPosition())
 				< (nearestLeo.getXPosition()+nearestLeo.getYPosition())*(nearestLeo.getXPosition()+nearestLeo.getYPosition())) {
        	 nearestLeo=element;
         }
        
    }
    
    
    Coords.setXCoord(nearestLeo.getXPosition());
	Coords.setYCoord(nearestLeo.getYPosition());
	if (Coords.getXCoord()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
	if (Coords.getYCoord()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
	if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
	if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
	if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
	if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
	float x=Coords.getXCoord();
	float y=Coords.getYCoord();
	if ((this.starvation<=100-2*this.starvationCoefficient)&&((xPosition-x)*(xPosition-x)+(yPosition-y)*(yPosition-y)<Information.getSizeOfCell()*Information.getSizeOfCell())) this.starvation+=2*this.starvationCoefficient;
	return Coords;
    
    
    
}
public void setFromWhom(Leo FromWhom) {
	this.fromWhom=FromWhom;
}
public void goToNearestGrass() {
	if (!Information.getLinkedListOfGrass().isEmpty()) {
	Grass nearestGrass=Information.getLinkedListOfGrass().getFirst();
	int nearestDistance=(nearestGrass.getXPosition()-this.xPosition)*(nearestGrass.getXPosition()-this.xPosition)+(nearestGrass.getYPosition()-this.yPosition)*(nearestGrass.getYPosition()-this.yPosition);
	int currentDistance;
	Grass currentGrass;
	for (Iterator<Grass> current = Information.getLinkedListOfGrass().iterator(); current.hasNext(); ) {
	    currentGrass = current.next();
	    currentDistance=(currentGrass.getXPosition()-this.xPosition)*(currentGrass.getXPosition()-this.xPosition)+(currentGrass.getYPosition()-this.yPosition)*(currentGrass.getYPosition()-this.yPosition);
	    
	    if (currentDistance<nearestDistance) {nearestGrass=currentGrass; nearestDistance=currentDistance;}
	}
	
	if (nearestGrass.getXPosition()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
	if (nearestGrass.getYPosition()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
	if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
	if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
	if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
	if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
	if (nearestDistance<=Information.getSizeOfCell()) {this.starvation+=nearestGrass.getEnergyValue();}
	
	}
}
public boolean wantToBorn() {
	return this.wantToBorn;
}

public void setTimeOfPregnant(int time) {
	this.timeOfPregnant=time;
}
public Leo getFromWhom() {
	return this.fromWhom;
}
private void feelHungry() {
	if (!Information.getLinkedListOfHerbivores().isEmpty()) {
		Herbivore nearestHerbivore=Information.getLinkedListOfHerbivores().getFirst();
		int nearestDistance=(nearestHerbivore.getXPosition()-this.xPosition)*(nearestHerbivore.getXPosition()-this.xPosition)+(nearestHerbivore.getYPosition()-this.yPosition)*(nearestHerbivore.getYPosition()-this.yPosition);
		int currentDistance;
		Herbivore currentHerbivore;
		for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores().iterator(); current.hasNext(); ) {
		    currentHerbivore = current.next();
		    currentDistance=(currentHerbivore.getXPosition()-this.xPosition)*(currentHerbivore.getXPosition()-this.xPosition)+(currentHerbivore.getYPosition()-this.yPosition)*(currentHerbivore.getYPosition()-this.yPosition);
		    
		    if (currentDistance<nearestDistance) {nearestHerbivore=currentHerbivore; nearestDistance=currentDistance;}
		}
		
		if (nearestHerbivore.getXPosition()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
		if (nearestHerbivore.getYPosition()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
		if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
		if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
		if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
		if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
		if (nearestDistance<=Information.getSizeOfCell()) {
				this.eatHerbivore(nearestHerbivore);
			}
	
	}
	
}
private void eatHerbivore(Herbivore victim) {
	if (victim.isAlive()) victim.kill();
	victim.eatMeat(5);
	this.starvation+=5;
	if (this.starvation>100) this.starvation=100;
}
private void becomePregnant() {
	this.timeOfPregnant=30;
}
private void tryMakeChildren(Leo female) {
	if (female.femaleAgree()) {
				this.setXPosition(female.getXPosition());
				this.setYPosition(female.getYPosition());
			    this.timeOfInertion=10;
			    female.timeOfInertion=10;
			    this.previousAction=7;
			    female.previousAction=7;
				this.passion=100;
				female.setPassion(100);
				this.badFemale=null;
				//Information.getLinkedListOfLeos().add(new Leo(true, this.getXPosition(), this.getYPosition(), 400, 50, 100, 100,     (float)1,1,3,   0,0,0));
				female.setFromWhom(this);
				female.becomePregnant();
				System.out.println("Pregnant");
				} else {
					this.badFemale=female;
				}
				
	
}
private int feelPassion(Leo badFemale) {
if (this.isMale()) {
	if (!Information.getLinkedListOfLeos().isEmpty()) {
		//LifeForm nearestLeo=Information.getLinkedListOfLifeForms().getFirst();
		Leo nearestLeo=null;
		Leo currentLeo;
		int currentDistance=-1;
		int nearestDistance=-1;
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
			currentLeo=current.next();
			if (    (nearestLeo==null)&&( ((!currentLeo.isMale()))          )&&(!currentLeo.isChild())
					&& (currentLeo.timeOfPregnant==-1)
								&& (!currentLeo.equals(badFemale))
					) 
			{
				nearestLeo=currentLeo; 
				nearestDistance=(nearestLeo.getXPosition()-this.xPosition)*(nearestLeo.getXPosition()-this.xPosition)+(nearestLeo.getYPosition()-this.yPosition)*(nearestLeo.getYPosition()-this.yPosition);
					}
			if ((nearestLeo!=null)&&(nearestLeo!=currentLeo)  &&
					    ((!currentLeo.isMale()))&&(!currentLeo.isChild())&& (currentLeo.timeOfPregnant==-1)         ) {
				currentDistance=(currentLeo.getXPosition()-this.xPosition)*(currentLeo.getXPosition()-this.xPosition)+(currentLeo.getYPosition()-this.yPosition)*(currentLeo.getYPosition()-this.yPosition);
				if (currentDistance<nearestDistance) {nearestDistance=currentDistance; nearestLeo=currentLeo;}
				
			}
			
		}
		if (nearestLeo==null) {return -1;}
		if (nearestLeo.getXPosition()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
		if (nearestLeo.getYPosition()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
		if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
		if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
		if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
		if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
		if (      (nearestDistance<=Information.getSizeOfCell())&&
									(this.isMale())		) {this.tryMakeChildren(nearestLeo);}
		return 0;
		//int nearestDistance=(nearestLeo.getXPosition()-this.xPosition)*(nearestLeo.getXPosition()-this.xPosition)+(nearestLeo.getYPosition()-this.yPosition)*(nearestLeo.getYPosition()-this.yPosition);
		
		/*Leo newCurrentLeo;
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
		    newCurrentLeo = current.next();
		    currentDistance=(newCurrentLeo.getXPosition()-this.xPosition)*(newCurrentLeo.getXPosition()-this.xPosition)+(newCurrentLeo.getYPosition()-this.yPosition)*(newCurrentLeo.getYPosition()-this.yPosition);
		    
		    if (currentDistance<nearestDistance) {nearestLeo=(Leo)newCurrentLeo; nearestDistance=currentDistance;}
		}
		
		if (nearestLeo!=null) {
		if (nearestLeo.getXPosition()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
		if (nearestLeo.getYPosition()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
		//if (nearestDistance<=Information.getSizeOfCell()) {this.starvation+=nearestGrass.getEnergyValue();}
		}*/
		} else return -1;
} else return 0;
}

private void bornNewLeo() {
	System.out.println("Born new Leo");
	this.exhaustion-=10;
	this.starvation-=10;
	this.wantToBorn=true;
}
private void feelKillInstinct() {
	
}
private void feelSleepy() {
	if (this.exhaustion<2*this.exhaustionCoefficient) {
	this.exhaustion+=2*this.exhaustionCoefficient;
	} else this.exhaustion=100;
}
private int getDecision() {        // здесь происходит сравнивание всех шкал и определение, что делать дальше
	if (this.timeOfInertion==0) {
			if (this.isChild) this.isChild=false;
			this.age-=0.5;                                              //установка новых значений полей
			this.starvation-=this.starvationCoefficient;
			
			if (passion>20) {this.passion-=this.passionCoefficient;}
			if (passion<=20) this.badFemale=null;
			
			this.exhaustion-=this.exhaustionCoefficient;
			if (this.timeOfPregnant>0) this.timeOfPregnant--;
	
	
	
	if (this.age<=0) {System.out.println("Age"); return 0;}
	if (this.starvation<=0) {System.out.println("Starvation"); return 0;}
	if (this.exhaustion<=0) return 3;
	
	if (this.timeOfPregnant==0) {return 5;}
	if ((this.starvation<=this.exhaustion)&&(this.starvation<=this.passion)) {this.previousAction=1; this.timeOfInertion=10; return 1;}
	if ((this.starvation>=this.passion)&&(this.passion<=this.exhaustion)) {this.previousAction=2; this.timeOfInertion=5; return 2;}
	if ((this.starvation>=this.exhaustion)&&(this.exhaustion<=this.passion)) {this.previousAction=3; this.timeOfInertion=20; return 3;}
	return 3;
	} else {
			this.timeOfInertion--;  //время инерции- это чтобы например львы спали не до того как другая шкала станет меньше шкалы exhaustion, а хотя бы спали некоторое минимальное число ходов
			if (!this.isChild) {
					this.age-=0.5;                                              //установка новых значений полей
					this.starvation-=this.starvationCoefficient;
					
					if (passion>20) {this.passion-=this.passionCoefficient;}  
					if (passion<=20) this.badFemale=null;
					
					this.exhaustion-=this.exhaustionCoefficient;
					if (this.timeOfPregnant>0) this.timeOfPregnant--;
			
					if (this.age<=0) {System.out.println("Age"); return 0;}
					if (this.starvation<=0) {System.out.println("Starvation"); return 0;}
					if (this.exhaustion<=0) {this.timeOfInertion=0; return 3;}
					
			if (this.timeOfPregnant==0) {this.timeOfInertion=0; return 5;}
			
			
			
			
			}
			return 6;
	}
	/*int length=this.leoNeeds.length;
	Arrays.sort(this.leoNeeds);*/
	
	/*ArrayList<LeoWish> leoNeeds= new  ArrayList<LeoWish>();
		leoNeeds.add(new LeoWish(1,this.starvation));
		leoNeeds.add(new LeoWish(2,this.passion));
		leoNeeds.add(new LeoWish(3,this.exhaustion));
	
		
	Collections.sort(leoNeeds);
	return leoNeeds.get(leoNeeds.size()-1).getNumberOfLeoNeed();*/
	
}

public void setWantToBorn(boolean a) {
	this.wantToBorn=a;
}
public boolean makeDecision() {
	decision=this.getDecision();
	if (decision==0) {return false;}
	if (decision==1) {System.out.println("Hungry "+this.starvation+" "+this.exhaustion+" "+this.passion); this.feelHungry();/*goToNearestGrass();*/}
	if (decision==2) {System.out.println("Passion "+this.starvation+" "+this.exhaustion+" "+this.passion); if (this.feelPassion(this.badFemale)==-1) {}}
	if (decision==4) {this.feelKillInstinct();}
	if (decision==3) {System.out.println("Sleep "+this.starvation+" "+this.exhaustion+" "+this.passion); this.feelSleepy();}
	if (decision==5) {System.out.println("Want to born"); this.bornNewLeo();}
	if (decision==6) {
			System.out.println("Previous action"); 
			switch (this.previousAction) {
			case 0:
				System.out.println("Childhood");
			break;
			case 1:
				System.out.println("Hungry "+this.starvation+" "+this.exhaustion+" "+this.passion); this.feelHungry();/*goToNearestGrass();*/
			break;
			case 2:
				System.out.println("Passion "+this.starvation+" "+this.exhaustion+" "+this.passion); if (this.feelPassion(this.badFemale)==-1) {}
			break;
			case 3:
				System.out.println("Sleep "+this.starvation+" "+this.exhaustion+" "+this.passion); this.feelSleepy();
			break;
			case 4:
				this.feelKillInstinct();
			break;
			case 7:
			break;
			}
			}
	
	return true;
}
public void setBigCursor(boolean a) {
	this.bigCursor=a;
}
public boolean haveBigCursor() {
	return this.bigCursor;
}
/*public boolean hasNoThread() {  // сейчас не используется
	return this.hasNoThread;
}*/
/*public void hasThread() {       //сейчас не используется
	this.hasNoThread=false;
}*/
}
