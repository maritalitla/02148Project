package Controller;


import java.util.LinkedList;

import javax.swing.Timer;

import model.BasicShip;
import model.Coordinate;
import model.GreenShip;
import model.Heading;
import model.Model;
import model.RedShip;
import model.YellowShip;
import view.GameView;
import view.MainMenu;


public class MainController {
	
	MainMenu mMenu;
	GameView gView;
	ActionHandlerMainMenu actMainMenu;
	RequestListener requestListener;
	DockListener dockListener;
	Model model;
	Timer timer;
	final int mapHeigth = 25;
	final int mapWidth = 25;
	int delay = 50;
	
	
	public MainController() {
		init();
	}
	public void incrementTimer() {
		this.model.incrementTime(0.5);
	}
	
	public void init() {
		//testgrid
		int[][] grid = new int[1][1];
		
		//Main menu view
		mMenu = new MainMenu();
		//Game view
		gView = new GameView(mapHeigth, mapWidth);
		
		//Action handler for main menu
		actMainMenu = new ActionHandlerMainMenu(this);
		requestListener = new RequestListener(this);
		dockListener = new DockListener(this);
		dockListener.init();
		
		this.timer = new Timer(delay, new TimerListener(this));
		
		}
	public void start() {
		this.timer.start();
	}
	public void createModel() {
		this.model = new Model(mapHeigth, mapWidth);
	}
	private void addShip(BasicShip ship){
		model.addShip(ship);
		updateRequests();
	}
	private void updateRequests() {
		gView.getRequestPanel().setRequests(this.model.getRequests());
		System.out.println(this.model.getRequests());
		gView.getRequestPanel().clear();
		gView.getRequestPanel().update();
		gView.getRequestPanel().repaint();
		requestListener.init();
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);

		if (model.getTime() <= 0.5 && model.getTime() >= 0) {
			RedShip s = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 24, 12, Heading.N);
			model.assignPath(s, 1);
			addShip(s);
			
			GreenShip s1 = new GreenShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 12, 0, Heading.E);
			model.assignPath(s1, 2);
			addShip(s1);

			YellowShip s2 = new YellowShip("id3", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
			model.assignPath(s2, 3);
			addShip(s2);

			

		}
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(model.getTime());
		gView.updateMoney(model.getMoney());
		model.viewUpdated();
		
	}
	public void dockClicked(String dock) {
		String ship = this.gView.getRequestPanel().getMarkedShip();
		if (!ship.equals("")){
			try {
				model.acceptRequest(ship, dock);
				
				this.gView.getGamePanel().setCircleId(ship);
				this.gView.getRequestPanel().setMarkedID(ship);
				updateRequests();
				System.out.println(dock + " has been assigned to " + ship);
			}
			catch (IllegalArgumentException e) {
			System.out.println(e);
			}
		}
	}

}


