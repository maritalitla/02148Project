package model;

import org.cmg.resp.topology.VirtualPort;

public class GreenShip extends BasicShip{

	public GreenShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col, Heading h) {
		super(shipId, mapId, harbourId, vp, row, col, h);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.GREEN;
		this.time = 50;
	}


	@Override
	protected int getMoney(int time) {
		return 50;
	}

}
