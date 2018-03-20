package scope.lwjgl.forerunner.gui;

import org.lwjgl.input.Mouse;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.States;
import scope.lwjgl.forerunner.world.World;

public class Menu {
	
	public static boolean viewable = false, viewableS = false;
	public static int sizeRate = 30;
	public static int viewW = 0, viewH = 0, viewWs = 0, viewHs, viewMWs = 0, viewMHs = 0;
	public static int viewXs = 0, viewYs = 0;
	public static int typeS = 0;
	public static int anim = 0;
	public static int state;//0 = nothing, 1 = opening width, 2 = opening height, 3 = closing width, 4 = closing height, 5 = small_nothing, 6 = small_opening width, 7 = small_opening height, 8 = small_closing width, 9 = small_closing height
	public static String sName = "";
	public static Runnable sUpdate;
	public static Runnable sRender;
	public static Btn btnQuit = new Btn(Sprites.menuBtn, Sprites.menuBtn_hover, Sprites.menuBtn_clicked, "Quit", (Main.width - Sprites.menuBtn.width) / 2, (int) (Main.height - Sprites.menuBtn.height * 1.5), 1, 2, new Runnable() {
		public void run() {
			Main.kill();
		}
	}, false);
	
	public static Btn btnWeaponStats = new Btn(Sprites.menuBtn, Sprites.menuBtn_hover, Sprites.menuBtn_clicked, "Weapon Stats", (Main.width - Sprites.menu.width) / 2 + 8, 16, 1, 1, new Runnable() {
		public void run() {
			openSmallMenu(0, 0, 0, "weapon_stats", new Runnable() {//2
				public void run() {
					
				}
			}, new Runnable() {
				public void run() {
					Fonts.fontDefault.drawString(World.player.selectedWeapon.name, 4, 4, 1);
					Fonts.fontDefault.drawString("Damage:" + World.player.selectedWeapon.damage, 4, 18, 1);
					Fonts.fontDefault.drawString("Accuracy:" + World.player.selectedWeapon.accuracy, 4, 32, 1);
					Fonts.fontDefault.drawString("Rate Of Fire:" + World.player.selectedWeapon.rateOfFire, 4, 46, 1);
					Fonts.fontDefault.drawString("Range:" + World.player.selectedWeapon.range, 4, 60, 1);
				}
			});
		}
	}, false);
	
	public static Btn btnCloseSmallMenu = new Btn(Sprites.menu_small_back, Sprites.menu_small_back_hover, Sprites.menu_small_back_clicked, "", 0, 0, 1, 1, new Runnable() {
		public void run() {
			closeSmallMenu();
		}
	}, false);
	
	public static void update() {
		switch(state) {
			case 0:
				break;
			case 1:
				anim += Time.anim;
				viewH = 4;
				if(viewW < Sprites.menu.width) {
					if(anim >= 20) {
						if(viewW + sizeRate < Sprites.menu.width) {
							viewW += sizeRate;
						} else {
							viewW = Sprites.menu.width;
						}
						anim -= 20;
					}
				} else {
					state = 2;
				}
				break;
			case 2:
				anim += Time.anim;
				if(viewH < Sprites.menu.height) {
					if(anim >= 20) {
						if(viewH + sizeRate < Sprites.menu.height) {
							viewH += sizeRate;
						} else {
							viewH = Sprites.menu.height;
						}
						anim -= 20;
					}
				} else {
					state = 0;
				}
				break;
			case 3:
				anim += Time.anim;
				if(viewH != 4) {
					if(anim >= 20) {
						if(viewH - sizeRate > 4) {
							viewH -= sizeRate;
						} else {
							viewH = 4;
						}
						anim -= 20;
					}
				} else {
					state = 4;
				}
				break;
			case 4:
				anim += Time.anim;
				if(viewW != 0) {
					if(anim >= 20) {
						if(viewW - sizeRate > 0) {
							viewW -= sizeRate;
						} else {
							viewW = 0;
						}
						anim -= 20;
					}
				} else {
					state = 0;
					viewable = false;
					States.stateGame.paused = false;
					Mouse.setGrabbed(true);
				}
				break;
			case 5:
				if(viewWs > viewMWs) {
					
				} else if(viewWs < viewMWs) {
					
				} else {
					if(viewHs > viewMHs) {
						
					} else if(viewHs < viewMHs) {
						
					}
				}
				break;
			case 6:
				anim += Time.anim;
				viewHs = 4;
				if(viewWs < viewMWs) {
					if(anim >= 20) {
						if(viewWs + sizeRate < viewMWs) {
							viewWs += sizeRate;
						} else {
							viewWs = viewMWs;
						}
						anim -= 20;
					}
				} else {
					state = 7;
				}
				break;
			case 7:
				anim += Time.anim;
				if(viewHs < viewMHs) {
					if(anim >= 20) {
						if(viewHs + sizeRate < viewMHs) {
							viewHs += sizeRate;
						} else {
							viewHs = viewMHs;
						}
						anim -= 20;
					}
				} else {
					state = 5;
				}
				break;
			case 8:
				anim += Time.anim;
				if(viewHs != 4) {
					if(anim >= 20) {
						if(viewHs - sizeRate > 4) {
							viewHs -= sizeRate;
						} else {
							viewHs = 4;
						}
						anim -= 20;
					}
				} else {
					state = 9;
				}
				break;
			case 9:
				anim += Time.anim;
				if(viewWs != 0) {
					if(anim >= 20) {
						if(viewWs - sizeRate > 0) {
							viewWs -= sizeRate;
						} else {
							viewWs = 0;
						}
						anim -= 20;
					}
				} else {
					state = 5;
					viewableS = false;
				}
				break;
			case 10:
				anim += Time.anim;
				if(viewHs != 4) {
					if(anim >= 20) {
						if(viewHs - sizeRate > 4) {
							viewHs -= sizeRate;
						} else {
							viewHs = 4;
						}
						anim -= 20;
					}
				} else {
					state = 11;
				}
				break;
			case 11:
				anim += Time.anim;
				if(viewWs != 0) {
					if(anim >= 20) {
						if(viewWs - sizeRate > 0) {
							viewWs -= sizeRate;
						} else {
							viewWs = 0;
						}
						anim -= 20;
					}
				} else {
					state = 3;
					viewableS = false;
				}
				break;
		}
		if(viewableS) {
			sUpdate.run();
			btnCloseSmallMenu.x = viewXs + viewWs - Sprites.menu_small_back.width - 3;
			btnCloseSmallMenu.y = viewYs + viewHs - Sprites.menu_small_back.height - 3;
			btnCloseSmallMenu.update();
		}
		if(state == 0 || state > 4) {
			btnQuit.update();
			btnWeaponStats.update();
			if(state == 0) {
				
			} else if(state == 5) {
				
			}
		}
	}
	
	public static void render() {
		Sprites.menu.render((Main.width - viewW) / 2, (Main.height - viewH) / 2, viewW, viewH, 1, 1, 1, 0.75f);
		if(state == 0 || state > 4) {
			btnQuit.render();
			btnWeaponStats.render();
		}
		if(viewableS) {
			if(typeS == 0) {
				Sprites.menu_small.render(viewXs, viewYs, viewWs, viewHs, 1, 1, 1, 0.8f);
			} else if(typeS == 1) {
				Sprites.menu_small2.render(viewXs, viewYs, viewWs, viewHs, 1, 1, 1, 0.8f);
			} else if(typeS == 2) {
				Sprites.menu_small3.render(viewXs, viewYs, viewWs, viewHs, 1, 1, 1, 0.8f);
			} else if(typeS == 3) {
				Sprites.menu_small4.render(viewXs, viewYs, viewWs, viewHs, 1, 1, 1, 0.8f);
			}
			sRender.run();
			btnCloseSmallMenu.render();
		}
	}
	
	public static void changeVis(boolean view) {
		if(view) {
			viewable = true;
			state = 1;
			States.stateGame.paused = true;
			Mouse.setGrabbed(false);
		} else {
			if(viewableS) {
				state = 10;
			} else {
				state = 3;
			}
		}
	}
	
	public static void openSmallMenu(int x, int y, int type, String name, Runnable u, Runnable r) {
		if(!viewableS) {
			typeS = type;
			if(type == 0) {
				if(!viewableS) {
					viewableS = true;
				} else {
					viewWs = 0;
					viewHs = 0;
				}
				viewXs = x;
				viewYs = y;
				viewMWs = Sprites.menu_small.width;
				viewMHs = Sprites.menu_small.height;
				state = 6;
			} else if(type == 1) {
				if(!viewableS) {
					viewableS = true;
				} else {
					viewWs = 0;
					viewHs = 0;
				}
				viewXs = x;
				viewYs = y;
				viewMWs = Sprites.menu_small2.width;
				viewMHs = Sprites.menu_small2.height;
				state = 6;
			} else if(type == 2) {
				if(!viewableS) {
					viewableS = true;
				} else {
					viewWs = 0;
					viewHs = 0;
				}
				viewXs = x;
				viewYs = y;
				viewMWs = Sprites.menu_small3.width;
				viewMHs = Sprites.menu_small3.height;
				state = 6;
			} else if(type == 3) {
				if(!viewableS) {
					viewableS = true;
				} else {
					viewWs = 0;
					viewHs = 0;
				}
				viewXs = x;
				viewYs = y;
				viewMWs = Sprites.menu_small4.width;
				viewMHs = Sprites.menu_small4.height;
				state = 6;
			}
		}
		sName = name;
		sUpdate = u;
		sRender = r;
	}
	
	public static void closeSmallMenu() {
		state = 8;
	}

}
