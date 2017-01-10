package model;

import java.io.IOException;

import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.Self;
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
			lock = get(lockTemp,mapConnection);
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
		
		if (queryp(new Template(new ActualTemplateField("reqSent"))) == null){
			try {
				put(new Tuple("req",id),harbourConnection);
				put(new Tuple("reqSent"),Self.SELF);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
	}

	@Override
	protected void doRun() throws Exception {


		makeRequest();
		
		while(true){
			if(!isDocked()){
				checkDockPermission();
				
			}
		
		}
	}

}
