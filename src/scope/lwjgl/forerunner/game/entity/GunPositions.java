package scope.lwjgl.forerunner.game.entity;

public class GunPositions {

	public GunPosition def, strafeLeft, strafeRight, walk, run, jump, crouch, prone, aim, reload;
	public GunPosition current;
	public int handHolds;
	public float recoil;

	public GunPositions(GunPosition def, GunPosition strafeLeft, GunPosition strafeRight, GunPosition walk,
			GunPosition run, GunPosition jump, GunPosition crouch, GunPosition prone, GunPosition aim,
			GunPosition reload, int handHolds, float recoil) {
		this.def = def;
		current = def;
		this.strafeLeft = strafeLeft;
		this.strafeRight = strafeRight;
		this.walk = walk;
		this.run = run;
		this.jump = jump;
		this.crouch = crouch;
		this.prone = prone;
		this.aim = aim;
		this.reload = reload;
		this.handHolds = handHolds;
		this.recoil = recoil;
	}

}
