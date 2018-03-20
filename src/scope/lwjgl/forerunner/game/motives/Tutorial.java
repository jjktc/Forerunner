package scope.lwjgl.forerunner.game.motives;

import org.lwjgl.input.Mouse;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.gui.ClickerText;
import scope.lwjgl.forerunner.gui.CubeBtn;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprites;

public class Tutorial {
	
	public static boolean finished = false, tutDone = false;
	public static ClickerText skip = new ClickerText("Skip", (Main.width - Fonts.fontDefault.getWidthByString("Skip", 2)) / 2, Main.height - Fonts.fontDefault.colH * 2 - 32, 2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
		public void run() {
			tutDone = true;
			finished = true;
			Mouse.setGrabbed(true);
		}
	}, false);
	public static CubeBtn controlBtn = new CubeBtn("W", (int) ((Main.width - Sprites.menu_tutorial.width) / 2 + 16), (int) ((Main.height - Sprites.menu_tutorial.height) / 2 + 16), 1.5f);
	public static CubeBtn controlBtn2 = new CubeBtn("R", (int) ((Main.width - Sprites.menu_tutorial.width) / 2 + 16 + (Sprites.cube_btn.width * 1.5f) + 8), (int) ((Main.height - Sprites.menu_tutorial.height) / 2 + 16), 1.5f);
	public static CubeBtn play = new CubeBtn(Sprites.play, (Main.width - Sprites.cube_btn.width * 2) / 2, Main.height - 256, 2, 2, new Runnable() {
		public void run() {
			finished = true;
			Mouse.setGrabbed(true);
		}
	});
	public static CubeBtn mlclick = new CubeBtn(Sprites.mouse, Sprites.mouse_lclick, (int) ((Main.width - Sprites.menu_tutorial.width) / 2 + 16), (int) ((Main.height - Sprites.menu_tutorial.height) / 2 + 16), 1.5f);
	public static CubeBtn mrclick = new CubeBtn(Sprites.mouse, Sprites.mouse_rclick, (int) ((Main.width - Sprites.menu_tutorial.width) / 2 + 16), (int) ((Main.height - Sprites.menu_tutorial.height) / 2 + 16), 1.5f);
	public static int tutorialStep = 0;
	public static Tutorial.TutorialObject[] tutorialSteps = {
		new Tutorial.TutorialKey("W", new String[] {"Click W to go forward.", "Press and release W"}, InputHandler.KEY_W),
		new Tutorial.TutorialKey("A", new String[] {"Click A to strafe left.", "Press and release A"}, InputHandler.KEY_A),
		new Tutorial.TutorialKey("S", new String[] {"Click S to go backwards.", "Press and release S"}, InputHandler.KEY_S),
		new Tutorial.TutorialKey("D", new String[] {"Click D to strafe right.", "Press and release D"}, InputHandler.KEY_D),
		new Tutorial.TutorialKey("R", new String[] {"Hold R while moving to sprint.", "Hold R and W or A or S or D"}, InputHandler.KEY_W, InputHandler.KEY_A, InputHandler.KEY_S, InputHandler.KEY_D, InputHandler.KEY_R),
		new Tutorial.TutorialKey("SPACE", new String[] {"Click SPACE to jump.", "Press and release SPACE"}, InputHandler.KEY_SPACE),
		new Tutorial.TutorialKey("SHIFT", new String[] {"Click SHIFT to crouch.", "Press and release SHIFT", "To roll, crouch while sprinting"}, InputHandler.KEY_LSHIFT),
		new Tutorial.TutorialKey("CTRL", new String[] {"Click CTRL to prone.", "Press and release CTRL", "To dive, prone while sprinting"}, InputHandler.KEY_LCONTROL),
		new Tutorial.TutorialKey("E", new String[] {"Click E to reload.", "Press and release ALT"}, InputHandler.KEY_E),
		new Tutorial.TutorialKey("TAB", new String[] {"Click TAB to switch weapons.", "Press and release TAB"}, InputHandler.KEY_TAB),
		new Tutorial.TutorialKey("F", new String[] {"Click F to activate/interact.", "Press and release F"}, InputHandler.KEY_F),
		new Tutorial.TutorialMouse(new String[] {"Click left on the mouse to use", "Press and release it"}, 0),
		new Tutorial.TutorialMouse(new String[] {"Click right on the mouse to aim", "Press and release it"}, 1),
		new Tutorial.TutorialMouse(new String[] {"Hold left on the mouse to keep using", "Hold it"}, 11)
	};
	
	public static void update() {
		if(!tutDone) {
			if(Mouse.isGrabbed()) Mouse.setGrabbed(false);
			skip.update();
			controlBtn.update();
			controlBtn2.update();
			if(tutorialStep != 4) {
				if(tutorialStep < 10) {
					if(InputHandler.clicked(tutorialSteps[tutorialStep].KEY[0])) {
						tutorialStep++;
					}
				} else {
					mlclick.update();
					mrclick.update();
					if(tutorialSteps[tutorialStep].mouse == 0) {
						if(InputHandler.mbLeft.clicked) {
							tutorialStep++;
						}
					} else if(tutorialSteps[tutorialStep].mouse == 1) {
						if(InputHandler.mbRight.clicked) {
							tutorialStep++;
						}
					} else if(tutorialSteps[tutorialStep].mouse == 11) {
						if(InputHandler.mbLeft.active) {
							tutorialStep++;
						}
					}
				}
			} else {
				if(InputHandler.active(tutorialSteps[tutorialStep].KEY[tutorialSteps[tutorialStep].KEY.length - 1])) {
					boolean done = false;
					for(int i = 0; i < tutorialSteps[tutorialStep].KEY.length - 1; i++) {
						if(InputHandler.active(tutorialSteps[tutorialStep].KEY[i])) {
							done = true;
						}
					}
					if(done) {
						tutorialStep++;
					}
				}
			}
			if(tutorialStep >= tutorialSteps.length) {
				tutDone = true;
				skip.text = "Play again";
				skip.width = Fonts.fontDefault.getWidthByString("Play again", 2);
				skip.x = (Main.width - skip.width) / 2;
				skip.method = new Runnable() {
					public void run() {
						tutorialStep = 0;
						controlBtn.text = tutorialSteps[tutorialStep].key;
						tutDone = false;
						skip.text = "Skip";
						skip.width = Fonts.fontDefault.getWidthByString("Skip", 2);
						skip.x = (Main.width - skip.width) / 2;
						skip.method = new Runnable() {
							public void run() {
								tutDone = true;
								finished = true;
								Mouse.setGrabbed(true);
							}
						};
					}
				};
				return;
			}
			if(tutorialStep < tutorialSteps.length) {
				controlBtn.text = tutorialSteps[tutorialStep].key;
			}
		} else {
			skip.update();
			play.update();
		}
	}
	
	public static void render() {
		if(!finished) {
			Sprites.menu_tutorial.render((Main.width - Sprites.menu_tutorial.width) / 2, (Main.height - Sprites.menu_tutorial.height) / 2, 1, 1, 1, 1, 0.5f);
			skip.render();
			if(!tutDone) {
				if(tutorialStep <= 9) {
					controlBtn.render();
				} else {
					if(tutorialStep == 10) {
						mlclick.render();
					} else if(tutorialStep == 11) {
						mrclick.render();
					} else if(tutorialStep == 12) {
						mlclick.render();
					}
				}
				Fonts.fontDefault.drawLines(tutorialSteps[tutorialStep].directions, (int) ((Main.width - Sprites.menu_tutorial.width) / 2 + 24 + (Sprites.cube_btn.width * 1.5f)), (int) (((Main.height - Sprites.menu_tutorial.height) / 2 + 16) + (Sprites.cube_btn.height * 1.5f - Fonts.fontDefault.colH * 1.5f) / 2), 1.5f);
			} else {
				Fonts.fontDefault.drawString("The tutorial is over.", (Main.width - Fonts.fontDefault.getWidthByString("The tutorial is over.", 2)) / 2, (Main.height - Fonts.fontDefault.colH * 20) / 2, 2);
				Fonts.fontDefault.drawString("Click below to start.", (Main.width - Fonts.fontDefault.getWidthByString("Click below to start.", 2)) / 2, (Main.height - Fonts.fontDefault.colH * 15) / 2, 2);
				play.render();
			}
		}
	}
	
	public static class TutorialObject {
		
		public String[] directions;
		public int[] KEY;
		public String key;
		public int mouse = -1;
		
		public TutorialObject(String key, String[] directions, int... KEY) {
			this.key = key;
			this.directions = directions;
			this.KEY = KEY;
		}
		
		public TutorialObject(String[] directions, int mouse) {
			this.directions = directions;
			this.mouse = mouse;
		}
		
	}
	
	public static class TutorialKey extends TutorialObject {
		
		public int[] KEY;
		public String[] directions;
		public String key;
		
		public TutorialKey(String key, String[] directions, int... KEY) {
			super(key, directions, KEY);
		}
		
	}
	
	public static class TutorialMouse extends TutorialObject {
		
		public String[] directions;
		public int mouse;
		
		public TutorialMouse(String[] directions, int mouse) {
			super(directions, mouse);
		}
		
	}

}
