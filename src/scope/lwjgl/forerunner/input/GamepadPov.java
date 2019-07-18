package scope.lwjgl.forerunner.input;

import scope.lwjgl.forerunner.Time;

public class GamepadPov {

	public static int type0, type1, type2, type3;
	public static boolean up, down, right, left, center;
	public static boolean upA, downA, rightA, leftA, centerA;
	public static int xPos, yPos;
	public static int holdTime0, holdTime1, holdTime2, holdTime3;

	public static void update() {
		upA = (Gamepad.pov[1] == -1);
		downA = (Gamepad.pov[1] == 1);
		leftA = (Gamepad.pov[0] == -1);
		rightA = (Gamepad.pov[0] == 1);
		centerA = (Gamepad.pov[0] == 0 && Gamepad.pov[1] == 0);
		up = false;
		down = false;
		left = false;
		right = false;
		center = false;
		if (type0 == 0 && upA) {
			type0 = 1;
		}
		if (type0 == 1 && !upA) {
			upA = false;
			type0 = 2;
		} else if (type0 == 1 && upA) {
			holdTime0 += Time.anim;
		}
		if (type0 == 2) {
			if (holdTime0 < 1500) {
				up = true;
			}
			upA = false;
			holdTime0 = 0;
			type0 = 0;
		}
		if (type1 == 0 && downA) {
			type1 = 1;
		}
		if (type1 == 1 && !downA) {
			downA = false;
			type1 = 2;
		} else if (type1 == 1 && downA) {
			holdTime1 += Time.anim;
		}
		if (type1 == 2) {
			if (holdTime1 < 1500) {
				down = true;
			}
			downA = false;
			holdTime1 = 0;
			type1 = 0;
		}
		if (type2 == 0 && leftA) {
			type2 = 1;
		}
		if (type2 == 1 && !leftA) {
			leftA = false;
			type2 = 2;
		} else if (type2 == 1 && leftA) {
			holdTime2 += Time.anim;
		}
		if (type2 == 2) {
			if (holdTime2 < 1500) {
				left = true;
			}
			leftA = false;
			holdTime2 = 0;
			type2 = 0;
		}
		if (type3 == 0 && rightA) {
			type3 = 1;
		}
		if (type3 == 1 && !rightA) {
			rightA = false;
			type3 = 2;
		} else if (type3 == 1 && rightA) {
			holdTime3 += Time.anim;
		}
		if (type3 == 2) {
			if (holdTime3 < 1500) {
				right = true;
			}
			rightA = false;
			holdTime3 = 0;
			type3 = 0;
		}
	}

}
