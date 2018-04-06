package dkeep.logic;

import java.util.Random;

/**
 * Guard.java- Class that handles Guard's movement and behaviour.
 * @author Joao Fidalgo & Francisco Friande
 * @see GameElement
 *
 */
/**
 * @author Friande
 *
 */
/**
 * @author Friande
 *
 */
public class Guard extends GameElement {

	private String guardMove[];
	private String guardMove_backwards[];
	private int guardIndex;
	private boolean asleep;
	private int sleep_counter;
	private boolean moving_front;
	private boolean aux_flag; // flag para se acaba de acordar no drunken e para se acaba de mudar de direcçao
								// no suspicious

	/**
	 * @param xi Integer variable of the X-axis coordinate.
	 * @param yi Integer variable of the Y-axis coordinate.
	 * Class constructor.
	 */
	public Guard(int xi, int yi) {
		super(xi, yi);
		String aux2[] = { "a", "s", "s", "s", "s", "a", "a", "a", "a", "a", "a", "s", "d", "d", "d", "d", "d", "d", "d",
				"w", "w", "w", "w", "w" };
		String aux3[] = { "d", "w", "w", "w", "w", "d", "d", "d", "d", "d", "d", "w", "a", "a", "a", "a", "a", "a", "a",
				"s", "s", "s", "s", "s" };
		this.guardMove = aux2;
		this.guardMove_backwards = aux3;
		this.guardIndex = 0;
		this.moving_front = true;
		this.sleep_counter = 0;
		this.asleep = false;
		this.setAux_flag(false);

	}

	/**
	 * Increments index of the movement's array, backwards movement or forward movement.
	 */
	public void incrementIndex() {
		if ((this.guardIndex) == (this.guardMove.length) - 1)
			this.guardIndex = 0;
		else
			this.guardIndex++;
	}

	/**
	 * Decrements index of the movement's array, backwards movement or forward movement.
	 */
	public void decrementIndex() {
		if ((this.guardIndex) == 0)
			this.guardIndex = (this.guardMove.length) - 1;
		else
			this.guardIndex--;
	}

	
	/**
	 * @return String variable of the next Guard's movement.
	 * Gets next Guard's movement.
	 */
	public String getNextPosition() {
		if (this.moving_front)
			return guardMove[guardIndex];
		else
			return guardMove_backwards[guardIndex];
	}

	/**
	 * @param p  Integer variable meaning probability.
	 * @return Boolean of Sleeping State of the guard.
	 * Generates a random boolean, defined by probability p of the guard's sleeping condition.
	 */
	public boolean get_randAsleep(int p) {
		return randomBool(p);
	}

	/**
	 * @param p  Integer variable meaning probability.
	 * @return Boolean of Changing direction of the guard.
	 * Generates a random boolean, defined by probability p of the guard's changing direction of movement.
	 */
	public boolean get_randChangeDir(int p) {
		return randomBool(p);
	}

	/**
	 * @return True if the Guard is moving front, false if it's moving backwards.
	 * Tells if the guard is moving front or backwards.
	 */
	public boolean isMoving_front() {
		return moving_front;
	}

	/**
	 * @param b Boolean variable to be set in the Moving Direction.
	 * Sets Moving Front to b variable.
	 */
	public void setMoving_front(boolean b) {
		this.moving_front = b;
	}

	/**
	 * @return True if guard is asleep, false if awake.
	 * Tells if the Guard is asleep.
	 */
	public boolean isAsleep() {
		return asleep;
	}


	/**
	 * @param p  Integer variable meaning probability.
	 * @return True if the guard is asleep.
	 * Sees if the guard has just awoken, if so, he can't sleep in that round. If not, sets it's Sleep State according to probability p.
	 */
	public boolean asleep_func(int p) {
		if (this.getAux_flag()) // se acabou de acordar
			return false;
		if (!asleep)
			this.setAsleep(this.get_randAsleep(p));
		return this.isAsleep();
	}

	/**
	 * @param asleep Boolean variable of sleep state.
	 * Sets Guard sleep state to asleep variable.
	 */
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	
	
	/**
	 * @return Boolean- True if guard is asleep, false if awoke.
	 * If guard is sleeping and counter increments, return true. If has awoken, set counter to zero and flag awoken to true and returns false.
	 */
	public boolean increment_sleepCounter() {
		if (this.sleep_counter < 4) { // nr de rondas que dorme (n)
			this.sleep_counter++;
			return true;
		}
		else {
			this.sleep_counter = 0;
			this.setAsleep(false);
			this.setAux_flag(true);
			return false;
		}
	}

	/**
	 * @return True if has just awoken, false if not.
	 * Gets Guard "waking up" flag.
	 */
	public boolean getAux_flag() {
		return aux_flag;
	}

	/**
	 * @param aux_flag Boolean variable of the state of the awoken flag
	 * Sets Guard "waking up" flag to aux_flag.
	 */
	public void setAux_flag(boolean aux_flag) {
		this.aux_flag = aux_flag;
	}

	/**
	 * @param probability Integer that determines probability of the random calculator.
	 * @return Returns random bool.
	 * Function to calculate a pseudo-random Boolean , according to probability.
	 */
	public boolean randomBool(int probability) {
		int aux;
		Random rand = new Random();
		aux = rand.nextInt(probability + 1);
		if (aux < probability)
			return false;
		else
			return true;
	}

	/////////////////////////////////////////////////////////////
	//////// FUNCOES LVL 1/////////
	//////////////////////////////////////////////////////////////

	/**
	 * @param g Game variable.
	 * Handles Rookie movement.
	 */
	public void moveRookieGuard(Game g) {
		this.moveGuard(g);
		this.nextGuardIndex();
	}
	/**
	 * @param g Game variable.
	 * Handles Guard movement, according to getNextPosition function.
	 */
	public void moveGuard(Game g) {
		int coor[] = this.getCoord();
		g.setElemTable(coor, " ");
		Game.moveHandler(this.getNextPosition(), coor);
		g.setElemTable(coor, "G");
	}

	/**
	 * @param g Game variable.
	 * Handles Drunken movement.
	 */
	public void moveDrunkenGuard(Game g) {
		if (!this.asleep_func(5) || this.getAux_flag()) { // para o caso de dormir duas vezes seguidas
			this.moveGuard(g);
			this.nextGuardIndex();
			if (this.getAux_flag())
				this.setAux_flag(false);
		}

		else {
			if(this.increment_sleepCounter())
				g.setElemTable(this.getCoord(), "g");
			else { //just woke up
				this.setAux_flag(true); // just woke up flag(so he doesnt sleep 2 times or more in a row)
				if (this.get_randChangeDir(5)) {
					this.setMoving_front(!this.isMoving_front());
					nextGuardIndex();
				}
				this.moveGuard(g);
				this.nextGuardIndex();
				
			}
		}
	}

	/**
	 * @param g Game variable.
	 * Handles Suspicious movement.
	 */
	public void moveSuspiciousGuard(Game g) {
		this.moveGuard(g);
		if (this.getAux_flag()) { // se acabou de mudar de dir
			this.nextGuardIndex();
			this.setAux_flag(false);
		}

		else {

			if (this.get_randChangeDir(7)) {
				this.setMoving_front(!this.isMoving_front());
				this.setAux_flag(true);
			} else
				nextGuardIndex();
		}

	}

	/**
	 * Calculates next index of the guard's movement.
	 */
	public void nextGuardIndex() {
		if (this.isMoving_front())
			this.incrementIndex();
		else
			this.decrementIndex();
	}

}
