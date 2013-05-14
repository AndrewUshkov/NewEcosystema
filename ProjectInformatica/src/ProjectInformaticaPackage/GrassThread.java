package ProjectInformaticaPackage;

import java.util.Iterator;

public class GrassThread implements Runnable {

	@Override
	public void run() {
		for (Iterator<Grass> current = Information.getLinkedListOfGrass().iterator(); current.hasNext(); ) {
		    Grass currentGrass = current.next();
		    if (currentGrass.makeDecision()==false) {
		        current.remove();
		    }
		}

	}

}
