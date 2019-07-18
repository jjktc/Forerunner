package scope.lwjgl.forerunner.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

import scope.lwjgl.forerunner.game.settings.Options;
import scope.lwjgl.forerunner.gui.ClickerText;

public class Gamepad {

	public static Controllers pad;
	public static int amount;
	public static int GAMEPAD_AXIS_LX = 3;
	public static int GAMEPAD_AXIS_LY = 2;
	public static int GAMEPAD_AXIS_RX = 1;
	public static int GAMEPAD_AXIS_RY = 0;
	public static int GAMEPAD_POV_X = 0;
	public static int GAMEPAD_POV_Y = 1;
	public static int GAMEPAD_BTN_X = 0;
	public static int GAMEPAD_BTN_A = 1;
	public static int GAMEPAD_BTN_B = 2;
	public static int GAMEPAD_BTN_Y = 3;
	public static int GAMEPAD_BTN_LANALOG = 10;
	public static int GAMEPAD_BTN_RANALOG = 11;
	public static int GAMEPAD_BTN_LBTN = 4;
	public static int GAMEPAD_BTN_RBTN = 5;
	public static int GAMEPAD_BTN_LTRIG = 6;
	public static int GAMEPAD_BTN_RTRIG = 7;
	public static int GAMEPAD_BTN_LMID = 8;
	public static int GAMEPAD_BTN_RMID = 9;
	public static String name = "Dual Action";
	public static Controller gamepad;
	public static int id;
	public static boolean[] gbtns;
	public static GamepadBtn[] btns;
	public static float[] axis;
	public static int[] pov = new int[2];
	public static boolean exists = false;
	public static List<Integer> lookups = new ArrayList<Integer>();

	@SuppressWarnings("static-access")
	public static void update() {
		if (Options.useJoystick) {
			gamepad = pad.getController(id);
			gamepad.poll();
			GamepadPov.update();
			for (int i = 0; i < gamepad.getButtonCount(); i++) {
				btns[i].update();
			}
			for (int i = 0; i < gamepad.getButtonCount(); i++) {
				gbtns[i] = gamepad.isButtonPressed(i);
			}
			for (int i = 0; i < gamepad.getAxisCount(); i++) {
				axis[i] = gamepad.getAxisValue(i);
			}
			pov[0] = (int) gamepad.getPovX();
			pov[1] = (int) gamepad.getPovY();

		}
		/*
		 * String printout = name + "\n X1:" + axis[GAMEPAD_AXIS_LX] + "\n Y1:" +
		 * axis[GAMEPAD_AXIS_LY] + "\n X2:" + axis[GAMEPAD_AXIS_RX] + "\n Y2:" +
		 * axis[GAMEPAD_AXIS_RY]; for(int i = 0; i < gbtns.length; i++) { printout +=
		 * "\n btn" + i + ": " + gbtns[i]; } System.out.println(printout);
		 */
	}

	@SuppressWarnings("static-access")
	public static void initiate() {
		pad.poll();
		amount = pad.getControllerCount();
		if (amount == 0) {
			try {
				Controllers.create();
				amount = Controllers.getControllerCount();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (amount > 0) {
			try {
				amount = Controllers.getControllerCount();
				System.out.println(amount + " controllers found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < amount; i++) {
			Controller gptemp = pad.getController(i);
			if (gptemp.getName().contains(name)) {
				exists = true;
				id = i;
				gamepad = pad.getController(i);
				axis = new float[gamepad.getAxisCount()];
				gbtns = new boolean[gamepad.getButtonCount()];
				btns = new GamepadBtn[gamepad.getButtonCount()];
				for (int a = 0; a < btns.length; a++) {
					btns[a] = new GamepadBtn(a);
				}
				lookups.add(0);
			}
		}
	}

	public static void updateLauncherSelection(int lookup, ClickerText... ct) {
		if (lookups.size() <= lookup + 1) {
			int s = lookups.size();
			for (int i = 0; i < lookup + 2 - s; i++) {
				lookups.add(0);
			}
		}
		if (GamepadPov.up) {
			if (lookups.get(lookup) > 0) {
				lookups.set(lookup, lookups.get(lookup) - 1);
			} else {
				lookups.set(lookup, ct.length - 1);
			}
		} else if (GamepadPov.down) {
			if (lookups.get(lookup) + 1 < ct.length) {
				lookups.set(lookup, lookups.get(lookup) + 1);
			} else {
				lookups.set(lookup, 0);
			}
		}
		if (lookups.get(lookup) < 0)
			lookups.set(lookup, 0);
		if (lookups.get(lookup) > ct.length - 1)
			lookups.set(lookup, ct.length - 1);
		if (btns[GAMEPAD_BTN_A].clicked) {
			ct[lookups.get(lookup)].method.run();
		}
	}

	public static void renderLauncherSelection(int lookup, ClickerText... ct) {
		ct[lookups.get(lookup)].renderSelector();
	}

}
