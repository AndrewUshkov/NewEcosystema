package ProjectInformaticaPackage;

import java.awt.Image;

public interface LifeForm {          //это интерфейс для predator и herbivore
	void setAge(float newAge);
	float getAge();
	void setStarvation(float newStarvation);
	float getStarvation();
	void setPassion(float newPassion);
	float getPassion();
	void setXPosition(int newXPosition);
	int getXPosition();
	void setYPosition(int newXPosition);
	int getYPosition();
	void setStarvationCoefficient(float newSCoef);
	float getStarvationCoefficient();
	void setPassionCoefficient(float newPCoef);
	float getLegacyStarvationCoefficient();
	float getExhaustion();
	void setLegacyStarvationCoefficient(float newLSCoef);
	float getLegacyPassionCoefficient();
	void setLegacyPassionCoefficient(float newLPCoef);
	Image getAnimalImage();
	boolean isMale();
	int getPreviousAction();
	void setBigCursor(boolean a);
	boolean haveBigCursor();
	//CoordinatesXY goToNearestPredator();
	boolean makeDecision();   //любой predator или herbivore должен иметь этот метод, чтобы его вызывали в цикле перебора всех объектов из коллекции
}
