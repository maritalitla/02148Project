package model;

import java.io.IOException;

import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

public class YellowShip extends BasicShip {

	public YellowShip(String id, String mapId, String harbourId, VirtualPort vp, int row, int col){
		super(id,mapId,harbourId,vp,row,col);
		this.size = ShipSize.SMALL;	

	}
	
	@Override
	public void move() {
		/*switch(heading){
		case N:
			xPos
			
		}*/
		Tuple lock;
		try {
			lock = get(new Template(new ActualTemplateField("lock")),mapConnection);
			/*get(new Template(	new ActualTemplateField(id), 
								new FormalTemplateField(Integer.class),
								new FormalTemplateField(Integer.class)),mapConnection);*/
			
			put(lock,mapConnection);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void makeRequest() {
		Template queryTemp = new Template(	new ActualTemplateField)
		if (queryp() == null)
		put(,harbourConnection);
	}

	@Override
	protected void doRun() throws Exception {
		Template shipMsg = new Template(
				new ActualTemplateField("requestAccept"),
				new FormalTemplateField(Integer.class)
				);
		

	}

}
