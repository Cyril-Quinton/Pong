//import graphic.Fenetre;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.*;

public class Game {

	private static ArrayList<Surface> arraySurface = new ArrayList<Surface>();
	private static ArrayList<Projectil2> arrayBalle = new ArrayList<Projectil2>();
	private static String dis;
	private static String bord;
	public static Plateform pLeft;
	public static Plateform pRight;
	public static int scoreLeft;
	public static int scoreRight;
	public static ArrayList<Integer> bonusLeft;
	public static ArrayList<Integer> bonusRight;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		displayMenu();
		int choice = readInteger();
		switch (choice) {
		case 1: {
			Settings.wallBound = false;
			beggin1vs1();

			break;
		}
		case 2: {
			Settings.wallBound = true;
			beggin1vs1();

			break;
		}
		}

		new Fenetre();
		while (true) {
			ArrayList<Integer> bonusLeft = new ArrayList<Integer>();
			ArrayList<Integer> bonusRight = new ArrayList<Integer>();
			Boolean isLeftPlayerPlayAgain = null;
			Iterator<Projectil2> itb = arrayBalle.iterator();

			while (itb.hasNext()) {

				Projectil2 balle = itb.next();

				Iterator<Surface> it = arraySurface.iterator();
				while (it.hasNext()) {

					Surface surface = it.next();
					if ((balle.gety() > Settings.topo) | (balle.gety() < 0)) {
						if (balle.gety() > Settings.topo) {
							balle.sety(Settings.topo);
						}
						if (balle.gety() < 0) {
							balle.sety(0);
						}
						balle.seta(-balle.geta());
					}

					if ((balle.getx() >= surface.getLX()
							- (Settings.stepProjectil))
							& (balle.getx() <= (surface.getHX() + Settings.stepProjectil))) {

						if ((balle.gety() >= (surface.getLY()-Settings.stepProjectil))
								& (balle.gety() <= (surface.getHY()+Settings.stepProjectil))) {
							if (surface.isBound()
									& balle.getClass() == Balle.class) {

								if (surface.equals(pLeft)) {
									balle.setPlayer(true);
								}
								if (surface.equals(pRight)) {
									balle.setPlayer(false);
								}
								if (balle.getSens()
										&& (balle.getx() <= surface.getLX())) {
									balle.seta(surface.trajectoryModifier(
											balle.geta(), balle.gety()));
									balle.setSens(surface.sensModifier(balle
											.getSens()));
									break;
								}
								if ((balle.getSens() == false)
										&& (balle.getx() >= surface.getLX())) {
									balle.seta(surface.trajectoryModifier(
											balle.geta(), balle.gety()));
									balle.setSens(surface.sensModifier(balle
											.getSens()));
									break;
								}
								if ((balle.geta()>0) && (balle.gety()<= surface.getLY())) {
									balle.seta(-balle.geta());
									break;
								}
								if ((balle.geta()<0) && (balle.gety()>surface.getHY())) {
									balle.seta(-balle.geta());
									break;
								}
								
							

							}
							if ((surface.isBound() & surface.getClass() != VerticalBord.class)
									& balle.getClass() == Missile.class) {
								if (surface.equals(pLeft)) {
									changeLenght(pLeft, true);
								} else {
									if (surface.equals(pRight)) {
										changeLenght(pRight, true);
									} else {
										it.remove();
									}
								}
							} else {
								if (surface.getClass().equals(
										VerticalBord.class)) {
									if (balle.getClass().equals(Balle.class)) {

										isLeftPlayerPlayAgain = outOfGrid(
												(VerticalBord) surface,
												(Balle) balle);
										itb.remove();
										break;
									}
									if (balle.getClass().equals(Missile.class)) {
										itb.remove();
										break;
									}
								}
								if (surface.getActionType() != 0) {
									if (balle.isLeftPlayer()) {
										bonusLeft.add(surface.getActionType());
									}
									if (balle.isLeftPlayer() == false) {
										bonusRight.add(surface.getActionType());
									}
									it.remove();

									break;
								}
							}
						}
					}

				}

				balle.move();

			}
			createSpecialBonus();
			if (Balle.nbBalle <= 0) {
				newProjectile(isLeftPlayerPlayAgain, "balle");
			}
			Iterator<Integer> itL = bonusLeft.iterator();
			while (itL.hasNext()) {
				Integer bonus = itL.next();
				giveBonus(bonus, true);
			}
			Iterator<Integer> itR = bonusRight.iterator();
			while (itR.hasNext()) {
				Integer bonus = itR.next();
				giveBonus(bonus, false);
			}
			movePlateform();
			display();
			Thread.sleep(Settings.sleepTime);
		}

	}

	public static void changeLenght(Plateform plateform, boolean decrease) {
		if (decrease) {
			if (plateform.getLen() >= 6) {
				plateform.setLY(plateform.getLY() + 2);
				plateform.setHY(plateform.getHY() - 2);
				plateform.setLen();
			}
		} else {
			if (plateform.getLen() < 18) {
				plateform.setLY(plateform.getLY() - 2);
				plateform.setHY(plateform.getHY() + 2);
				plateform.setLen();
			}
		}
	}

	public static void display() {
		bord = new String();
		bord += " ";
		for (int i = 0; i < Settings.xGrid; i++) {
			bord += "―";
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(bord);
		for (int i = 0; i <= Settings.yGrid; i++) {
			dis = new String();
			for (int j = 0; j <= Settings.xGrid; j++) {
				dis += isThereSomething(j, i);

			}

			System.out.println(dis);

		}
		System.out.println(bord);
	}

	public static String isThereSomething(int x, int y) {
		Iterator<Projectil2> itb = arrayBalle.iterator();

		while (itb.hasNext()) {

			Projectil2 balle = itb.next();
			if (((x - 0.5 < balle.getx()) & (balle.getx() < x + 0.5))
					& ((y - 0.5 < balle.gety()) & (balle.gety() < y + 0.5))) {

				return balle.getCaractere();
			}
		}
		Iterator<Surface> it = arraySurface.iterator();
		while (it.hasNext()) {
			Surface surface = it.next();
			if (((surface.getLX() <= x) & (x <= surface.getHX()))
					& ((y >= surface.getLY()) & (surface.getHY() >= y))) {
				return surface.getCaractere();

			}
		}

		return " ";

	}

	public static void movePlateform() {
		if (ClavierListener.lUp) {
			Game.pLeft.setHY(Game.pLeft.getHY() - Settings.pStep);
			Game.pLeft.setLY(Game.pLeft.getLY() - Settings.pStep);
		}
		if (ClavierListener.lDown) {
			Game.pLeft.setHY(Game.pLeft.getHY() + Settings.pStep);
			Game.pLeft.setLY(Game.pLeft.getLY() + Settings.pStep);
		}
		if (ClavierListener.rUp) {
			Game.pRight.setHY(Game.pRight.getHY() - Settings.pStep);
			Game.pRight.setLY(Game.pRight.getLY() - Settings.pStep);
		}
		if (ClavierListener.rDown) {
			Game.pRight.setHY(Game.pRight.getHY() + Settings.pStep);
			Game.pRight.setLY(Game.pRight.getLY() + Settings.pStep);
		}

	}

	public static void createVerticallWall() { // create walls according to
												// settings options
		VerticalBord wall = new VerticalBord(0, 0, 0, Settings.yGrid, true,
				Settings.wallBound, true);
		arraySurface.add(wall);
		VerticalBord wall1 = new VerticalBord(Settings.xGrid, Settings.xGrid,
				0, Settings.yGrid, true, Settings.wallBound, false);
		arraySurface.add(wall1);
	}

	public static void createBalle(float ballex, float balley, float ballea,
			boolean totheRight, boolean leftPlayer) {
		arrayBalle
				.add(new Balle(ballex, balley, ballea, totheRight, leftPlayer));
	}

	public static void createMissile(float missileX, float missileY,
			float missileA, boolean toTheRight, boolean leftPlayer) {
		arrayBalle.add(new Missile(missileX, missileY, missileA, toTheRight,
				leftPlayer));
	}

	public static void createSurface(float lx, float hx, float ly, float hy,
			boolean isVertical, Boolean bound, int actionType) {
		Surface surface = new Surface(lx, hx, ly, hy, isVertical, bound,
				actionType);
		arraySurface.add(surface);

	}

	public static boolean outOfGrid(VerticalBord bord, Balle balle) {
		if (bord.isLeftWall()) {
			scoreRight++;
		} else {
			scoreLeft++;
		}
		Balle.nbBalle--;
		return bord.isLeftWall();

	}

	public static void displayMenu() {
		System.out.println("Command : \t\t up arrow/down arrow for the right player\n" +
				"\t\t left shift/left ctrl for the left player");
		System.out.println("1 : Player vs Player ");
		System.out.println("2 : Practice 2 Players");
		System.out.println("3 : Practice 1 Player");
		System.out.println("4 : solo mode");

	}

	public static int readInteger() {

		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					System.in));
			String chaine = buff.readLine();
			int num = Integer.parseInt(chaine);
			return num;

		} catch (IOException e) {
			return 0;
		}
	}

	public static void beggin1vs1() {
		scoreLeft = 0;
		scoreRight = 0;
		createVerticallWall();
		Random random = new Random();
		boolean player = random.nextBoolean();
		pLeft = new Plateform(10, 10, 20, 30, true, true);
		pRight = new Plateform(Settings.xGrid - 10, Settings.xGrid - 10, 20,
				30, true, true);
		arraySurface.add(pLeft);
		arraySurface.add(pRight);
		newProjectile(player, "balle");
	}

	public static void newProjectile(boolean isPlayerLeft,
			String TypeOfProjectile) {
		float x;
		float y;
		if (isPlayerLeft) {
			x = (float) 11;
			y = (pLeft.getLY() + (pLeft.getLen() / 2));

		} else {
			x = (float) (Settings.xGrid - 11);
			y = (pRight.getLY() + (pRight.getLen() / 2));

		}
		if (TypeOfProjectile == "balle") {
			createBalle(x, y, 0, isPlayerLeft, isPlayerLeft);
		}
		if (TypeOfProjectile == "missile") {
			createMissile(x, y, 0, isPlayerLeft, isPlayerLeft);
		}
	}

	public static void createSpecialBonus() {
		boolean createBonus = new Random().nextInt(Settings.bonusProbability) == 1;
		if (createBonus) {
			int action = new Random().nextInt(5);
			action++;
			createRandomSurface(5, action, false);

		}

	}

	public static void createRandomSurface(int lenght, int typeAction,
			boolean bound) {
		int posX = new Random().nextInt(Settings.xGrid - 50);
		posX += 25;
		int posY = new Random().nextInt(Settings.yGrid - 20);
		posY += 10;
		createSurface(posX, posX+10, posY, posY + lenght, true, bound, typeAction);

	}

	public static void giveBonus(int bonusType, boolean isLeftPlayer) {

		if (bonusType == 1) {
			newProjectile(isLeftPlayer, "balle");
		}
		if (bonusType == 2) {
			createRandomSurface(10, 0, true);
		}
		if (bonusType == 3) {
			newProjectile(isLeftPlayer, "missile");
		}
		if (bonusType == 4) {
			if (isLeftPlayer) {
				changeLenght(pLeft, false);
			} else {
				changeLenght(pRight, false);
			}
		}
		if (bonusType == 5) {
			Settings.hightSpeed();
		}

	}

	public static boolean bounding(Projectil2 balle, Surface rectangle,
			float HBX, float HBY, Iterator<Surface> itR,
			Iterator<Projectil2> itB) {
		boolean isLeftPlayerPlayAgain = true;

		if ((rectangle.getLX() <= balle.getx())
				&& (balle.getx() <= rectangle.getHX())
				&& (rectangle.getHY() <= balle.gety())
				&& (balle.gety() <= rectangle.getHY())) {
			if ((rectangle.getActionType() != 0)
					&& (balle.getClass() == Balle.class)) {
				if (balle.isLeftPlayer()) {
					Game.bonusLeft.add(rectangle.getActionType());
				}
				if (balle.isLeftPlayer() == false) {
					Game.bonusRight.add(rectangle.getActionType());
				}
			}
			if (rectangle.getClass().equals(VerticalBord.class)
					&& !rectangle.isBound()
					&& balle.getClass().equals(Balle.class)) {
				isLeftPlayerPlayAgain = outOfGrid((VerticalBord) rectangle,
						(Balle) balle);
				itB.remove();
			}
			if ((balle.getx() < (rectangle.getLX() + HBX))
					&& (balle.getSens() == true)) {
				rectangle.decreaseLife(balle.getHit());
				if (balle.getClass().equals(Missile.class)
						&& rectangle.getClass().equals(Plateform.class)) {
					if (rectangle.equals(pLeft)) { // attention pour debbugage,
													// pas sur que les nom pLeft
													// et pRight soient
													// accessibles.
						changeLenght(pLeft, true);
					} else {
						if (rectangle.equals(pRight)) {
							changeLenght(pRight, true);
						}
					}
					if (rectangle.isBound() && balle.isBoundy()) {
						balle.setSens(!balle.getSens());
						if (rectangle.getClass().equals(Plateform.class)) {
							balle.seta(rectangle.trajectoryModifier(
									balle.geta(), balle.gety()));
						}
					}

				}
			} else {
				if ((balle.getx() > (rectangle.getHX() - HBX))
						&& (balle.getSens() == false)) {
					rectangle.decreaseLife(balle.getHit());
					if (rectangle.isBound() && balle.isBoundy()) {
						balle.setSens(!balle.getSens());
						if (rectangle.getClass().equals(Plateform.class)) {
							balle.seta(rectangle.trajectoryModifier(
									balle.geta(), balle.gety()));
						}
					}
				} else {
					if ((balle.gety() < (rectangle.getLY() + HBY))
							&& (balle.geta() >= 0)) {
						rectangle.decreaseLife(balle.getHit());
						if (rectangle.isBound() && balle.isBoundy()) {
							balle.seta(-balle.geta());
						}
					} else {
						if ((balle.gety() > (rectangle.getHY() - HBY))
								&& (balle.geta() <= 0)) {
							rectangle.decreaseLife(balle.getHit());
							if (rectangle.isBound() && balle.isBoundy()) {
								balle.seta(-balle.geta());
							}
						}
					}
				}
			}
		}

		if (rectangle.getLife() <= 0) {
			itR.remove();
		}
		return isLeftPlayerPlayAgain;
	}

	public static float HBXCalculator(Balle balle) {
		float HBX = Settings.stepProjectil
				* Math.abs((float) Math.cos(Math.atan((float) balle.geta())));
		return HBX;
	}

	public static float HBYCalculator(Balle balle) {
		float HBY = Settings.stepProjectil
				* Math.abs((float) Math.sin(Math.atan((float) balle.geta())));
		return HBY;
	}
}
