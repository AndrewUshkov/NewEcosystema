package ProjectInformaticaPackage;

import java.util.Iterator;

public class PredatorThread implements Runnable {

	Thread predator;
	
	PredatorThread () {
		predator=new Thread();
		predator.start();
	}
	
	
	@Override
	public void run() {
		for (Iterator<Leo> current = Information.getLinkedListOfLeos().iterator(); current.hasNext(); ) {
	    	LifeForm currentAnimal = current.next();
	    	if (currentAnimal.makeDecision()==false) { //false появляется, если объект погибает
	        	current.remove();
	    	}
		}
		
		Information.checkIsLeoBorn();
	}
		
	
}
