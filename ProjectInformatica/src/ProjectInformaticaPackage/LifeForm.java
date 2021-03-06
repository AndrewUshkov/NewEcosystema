package ProjectInformaticaPackage;

import java.awt.Image;

public interface LifeForm {          //��� ��������� ��� predator � herbivore
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
	boolean makeDecision();   //����� predator ��� herbivore ������ ����� ���� �����, ����� ��� �������� � ����� �������� ���� �������� �� ���������
}
