package scope.lwjgl.forerunner.dlc;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.processing.Encdec;
import scope.lwjgl.forerunner.processing.TXTHandler;

public class DLC {
	
	public String location;
	public List<String> encContent = new ArrayList<String>();
	public List<String> content = new ArrayList<String>();
	public List<DLCContent> dlcContent = new ArrayList<DLCContent>();
	public int dlcAmount = 0;
	
	public DLC(String location) {
		this.location = location;
		encContent = TXTHandler.extContent(location);
		parse();
	}
	
	public void parse() {
		for(int i = 0; i < encContent.size(); i++) {
			content.add(Encdec.decrypt(encContent.get(i)));
		}
		for(int i = 0; i < content.size(); i++) {
			String line = content.get(i);
			if(line.startsWith("Weapon[")) {
				dlcAmount++;
				line = line.replace("Weapon[", "");
				line = line.substring(0, line.lastIndexOf("]"));
				DLCWeapon weapon = new DLCWeapon();
				weapon.name = line;
				weapon.lineStart = i;
				boolean endFound = false;
				for(int i2 = 0; i2 < content.size(); i2++) {
					String l2 = content.get(i2);
					if(!endFound) {
						if(l2.startsWith("}") && i2 > i) {
							weapon.lineEnd = i2;
						}
					}
				}
				for(int i3 = weapon.lineStart; i3 < weapon.lineEnd; i3++) {
					String l3 = content.get(i3);
					if(l3.startsWith("var")) {
						l3 = l3.replace("var", "");
						String variable = l3.substring(l3.indexOf("=") + 1, l3.length());
						weapon.parameters.add(variable);
					}
				}
				weapon.init();
				dlcContent.add(weapon);
			}
		}
	}
	
	public void parse2() {
		for(int i = 0; i < encContent.size(); i++) {
			System.out.println(Encdec.encrypt(encContent.get(i)));
		}
	}

}
