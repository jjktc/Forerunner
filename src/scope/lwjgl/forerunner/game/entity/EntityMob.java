package scope.lwjgl.forerunner.game.entity;

public class EntityMob extends Entity {

	public String name;
	public int maxLives;// maximum lives
	public int lives;// current amount of life out of maxLives
	public int maxEnergy;
	public int energy;
	public int energyLoss;// amount of energy lost for every meter moved
	public boolean dead = false;
	public float x, y, z, width, height, depth;
	public float jumpHeight;
	public int inclineWalkAngle;
	public float walkSpeed, runSpeed;

	public void update() {

	}

	public void render() {

	}

	public void loseLife(int amount) {

	}

	public void gainLife(int amount) {

	}

	public void loseEnergy(int amount) {

	}

	public void gainEnergy(int amount) {

	}

}
