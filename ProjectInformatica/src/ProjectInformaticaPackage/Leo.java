package ProjectInformaticaPackage;

import java.awt.Image;
import java.util.Arrays;
import java.util.Iterator;

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
private int decision;
private int timeOfPregnant=-1;
private boolean wantToBorn=false;
private Leo fromWhom=null;
public AnimalWish wish[]= {new AnimalWish(0,0), new AnimalWish(0,0), new AnimalWish(0,0)}; 
private boolean isChild=true;
private char previousAction=0;
private Leo badFemale=null;                                        //самка, несогласная на спаривание
public int timeOfInertion= this.isChild ? 30:0;
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


public Leo (boolean IfMale, int startXPosition, int startYPosition, float startAge, 
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
public float getStarvationCoefficient() {
	return this.starvationCoefficient;
}
public void setPassionCoefficient(float newPCoef) {
	this.passionCoefficient=newPCoef;
}
public float getLegacyStarvationCoefficient() {
	return this.legacyStarvationCoefficient;
}
public void setLegacyStarvationCoefficient(float newLSCoef) {
	this.legacyStarvationCoefficient=newLSCoef;
}
public float getLegacyPassionCoefficient() {
	return this.legacyPassionCoefficient;
}
public void setLegacyPassionCoefficient(float newLPCoef) {
	legacyPassionCoefficient=newLPCoef;
}
public Image getAnimalImage() {
	if (this.isMale()) {
		if (this.previousAction==3) return Information.getImageSleepingLeo();
		if (this.isChild) return Information.getImageLeoChild();
		if (this.previousAction==7) return Information.getImagePassionLeo();
		return Information.getImageLeo();
	} else {
		if (this.previousAction==3) return Information.getImageSleepingLeoFemale();
		if (this.isChild) return Information.getImageLeoFemaleChild();
		if (this.previousAction==7) return Information.getImagePassionLeo();
		return Information.getImageLeoFemale();
	}
}
public boolean femaleAgree() {
	if ((passion<=50)) {
		this.timeOfInertion=0;
		return true; 
	}
	return false;
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
	
	if (nearestGrass.getXPosition()>=this.xPosition) {this.xPosition+=4;} 
	else this.xPosition-=4;
	if (nearestGrass.getYPosition()>=this.yPosition) {this.yPosition+=4;} 
	else this.yPosition-=4;
	if (this.xPosition>=Information.getDefaultWeight()) this.xPosition-=Information.getDefaultWeight();
	if (this.xPosition<=0) this.xPosition+=Information.getDefaultWeight();
	if (this.yPosition>=Information.getDefaultHeight()) this.yPosition-=Information.getDefaultHeight();
	if (this.yPosition<=0) this.yPosition+=Information.getDefaultHeight();
	if (nearestDistance<=Information.getSizeOfCell()) this.starvation+=nearestGrass.eat();
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
private boolean feelHungry() {
	if (!Information.getLinkedListOfHerbivores().isEmpty()) {
		Herbivore nearestHerbivore=Information.getLinkedListOfHerbivores().getFirst();
		int nearestDistance=(nearestHerbivore.getXPosition()-this.xPosition)*(nearestHerbivore.getXPosition()-this.xPosition)+(nearestHerbivore.getYPosition()-this.yPosition)*(nearestHerbivore.getYPosition()-this.yPosition);
		int currentDistance;
		Herbivore currentHerbivore;
		for (Iterator<Herbivore> current = Information.getLinkedListOfHerbivores().iterator(); current.hasNext(); ) {
		    currentHerbivore = current.next();
		    currentDistance=(currentHerbivore.getXPosition()-this.xPosition)*(currentHerbivore.getXPosition()-this.xPosition)+(currentHerbivore.getYPosition()-this.yPosition)*(currentHerbivore.getYPosition()-this.yPosition);
		    
		    if (currentDistance<nearestDistance) {
		    	nearestHerbivore=currentHerbivore; 
		    	nearestDistance=currentDistance;
		    	}
		}
		
		if (nearestHerbivore.getXPosition()>=this.xPosition) {this.xPosition+=4;} 
		else this.xPosition-=4;
		if (nearestHerbivore.getYPosition()>=this.yPosition) {this.yPosition+=4;} 
		else this.yPosition-=4;
		if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
		if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
		if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
		if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
		
		if (nearestDistance<=Information.getSizeOfCell()) {
				this.eatHerbivore(nearestHerbivore);
			}
	
		return true;
	} else { return false;}
	
}
private void eatHerbivore(Herbivore victim) {
	if (victim.isAlive()) victim.kill();
	victim.eatMeat(10);
	this.starvation+=2;
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
				female.setFromWhom(this);
				female.becomePregnant();
				System.out.println("Pregnant");
				} else {
					if (female.previousAction!=3) {
					this.badFemale=female;
					this.timeOfInertion=0;
					}
				}
				
	
}
private boolean feelPassion(Leo badFemale) {
if (this.isMale()) {
	int numberFemaleChildren=0;
	if (Information.getLinkedListOfLeos().size()>1) {
		Leo nearestLeo=null;
		Leo currentLeo;
		int currentDistance=-1;
		int nearestDistance=-1;
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
			currentLeo=current.next();
			if ((currentLeo.isChild())&&(!currentLeo.isMale())) {numberFemaleChildren++;}   //смотрим, есть ли "несовершеннолетние" львицы
			if (    (nearestLeo==null)&&( ((!currentLeo.isMale()))          )&&(!currentLeo.isChild())
					&& (currentLeo.timeOfPregnant==-1)
								&& (currentLeo!=badFemale)
					) 
			{
				nearestLeo=currentLeo; 
				nearestDistance=(nearestLeo.getXPosition()-this.xPosition)
						*(nearestLeo.getXPosition()-this.xPosition)
						+(nearestLeo.getYPosition()-this.yPosition)
						*(nearestLeo.getYPosition()-this.yPosition);
					}
			if ((nearestLeo!=null)&&(nearestLeo!=currentLeo)  &&
					    (!currentLeo.isMale())&&(!currentLeo.isChild())&&
					    (currentLeo.timeOfPregnant==-1) && 
					    (currentLeo!=badFemale)         ) {
				currentDistance=(currentLeo.getXPosition()-this.xPosition)
						*(currentLeo.getXPosition()-this.xPosition)
						+(currentLeo.getYPosition()-this.yPosition)
						*(currentLeo.getYPosition()-this.yPosition);
				if (currentDistance<nearestDistance) {
					nearestDistance=currentDistance; 
					nearestLeo=currentLeo;
					}
				
			}
			
		}
		if ((nearestLeo==null)&&(numberFemaleChildren==0)) {this.timeOfInertion=0; return false;} else {
			if (nearestLeo!=null) {
				if (nearestLeo.getXPosition()>=this.xPosition) {this.xPosition+=4;} else this.xPosition-=4;
				if (nearestLeo.getYPosition()>=this.yPosition) {this.yPosition+=4;} else this.yPosition-=4;
				if (this.xPosition>=Information.getDefaultWeight()) {this.xPosition-=Information.getDefaultWeight();}
				if (this.xPosition<=0) {this.xPosition+=Information.getDefaultWeight();}
				if (this.yPosition>=Information.getDefaultHeight()) {this.yPosition-=Information.getDefaultHeight();}
				if (this.yPosition<=0) {this.yPosition+=Information.getDefaultHeight();}
				if (      (nearestDistance<=Information.getSizeOfCell())&&
									(this.isMale())		) {this.tryMakeChildren(nearestLeo);}
				return true;
				}	
			return true;
			}
		} else return false;
} else return false;
}

private void bornNewLeo() {
	System.out.println("Born new Leo");
	this.exhaustion-=10;
	this.starvation-=10;
	this.wantToBorn=true;
}
private void feelKillInstinct() {
	
}
private boolean feelSleepy() {
	if (this.exhaustion<2*this.exhaustionCoefficient) {
	this.exhaustion+=2*this.exhaustionCoefficient;
	} else this.exhaustion=100;
	return true;
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
	
	if (this.starvation>65) {
		this.wish[0]=new AnimalWish(1,100);
	} else this.wish[0]=new AnimalWish(1,this.starvation);
	    
	this.wish[1]=new AnimalWish(2,this.passion);
	this.wish[2]=new AnimalWish(3,this.exhaustion);
	
	Arrays.sort(wish);        //сортируем желания  льва по степени важности
	this.previousAction=(char)wish[0].getNumberOfAnimalNeed();
	
	if (wish[0].getNumberOfAnimalNeed()==1) this.timeOfInertion=10;
	if (wish[0].getNumberOfAnimalNeed()==2) this.timeOfInertion=5;
	if (wish[0].getNumberOfAnimalNeed()==3) this.timeOfInertion=20;
	return wish[0].getNumberOfAnimalNeed();
	
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
	
}

private boolean doActionWithNumber(int number) {
	if (number==1) return this.feelHungry();
	if (number==2) return this.feelPassion(this.badFemale);
	if (number==3) return this.feelSleepy();
	return false;
}

public void setWantToBorn(boolean a) {
	this.wantToBorn=a;
}
public boolean makeDecision() {
	decision=this.getDecision();
	if (decision==0) {return false;}
	if (decision==4) {this.feelKillInstinct();}
	if (decision==5) { this.bornNewLeo();}
	if (decision==6) {
		switch (this.previousAction) {
		case 1:
			this.feelHungry();
		break;
		case 2:
			this.feelPassion(this.badFemale);
		break;
		case 3:
			this.feelSleepy();
		break;
		case 4:
			this.feelKillInstinct();
		break;
		case 7:
		break;
		}
		}
	if ((decision==1)||(decision==2)||(decision==3)) {
		
		if (!this.doActionWithNumber(wish[0].getNumberOfAnimalNeed())) {
			if (!this.doActionWithNumber(wish[1].getNumberOfAnimalNeed())) {
				this.doActionWithNumber(wish[2].getNumberOfAnimalNeed());   //здесь можно оставить последнее действие без проверок каких-либо,
				this.previousAction=(char) wish[2].getNumberOfAnimalNeed();
				} else 
			this.previousAction=(char) wish[1].getNumberOfAnimalNeed();        //потому что всегда если животное дошло до последнего действия, то оно- сон. (всегда возвращает true)
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
}
