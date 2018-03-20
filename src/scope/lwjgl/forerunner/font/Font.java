package scope.lwjgl.forerunner.font;

import static org.lwjgl.opengl.GL11.glColor4f;

import java.util.List;

import scope.lwjgl.forerunner.sprites.Spritesheet;

public class Font {
	
	public int colW, colH;
	public Char c0,	c1,	c2,	c3,	c4,	c5,	c6,	c7,	c8,	c9;
	public Char ca,	cb,	cc,	cd,	ce,	cf,	cg,	ch,	ci,	cj,	ck,	cl,	cm,	cn,	co,	cp,	cq,	cr,	cs,	ct,	cu,	cv,	cw,	cx,	cy,	cz;
	public Char cA,	cB,	cC,	cD,	cE,	cF,	cG,	cH,	cI,	cJ,	cK,	cL,	cM,	cN,	cO,	cP,	cQ,	cR,	cS,	cT,	cU,	cV,	cW,	cX,	cY, cZ;
	public Char cPeriod, cExclamation, cQuestion, cComma, cSQuote, cDQuote, cBSlash, cFSlash, cLParan, cRParan, cLBracket, cRBracket, cLBrace, cRBrace, cPlus, cEquals, cMinus, cDollar, cApostrophe, cCopyright, cRegistered, cTrademark, cLArrow, cRArrow, cUArrow, cNumber, cPole, cPercent, cColan, cSColan;
	public Spritesheet sheet;
	
	public Font(Spritesheet sheet, int colW, int colH) {
		this.colW = colW;
		this.colH = colH;
		this.sheet = sheet;
		
		c0 = new Char(sheet, colW * 0, colH * 0, colW, colH);
		c1 = new Char(sheet, colW * 1, colH * 0, colW, colH);
		c2 = new Char(sheet, colW * 2, colH * 0, colW, colH);
		c3 = new Char(sheet, colW * 3, colH * 0, colW, colH);
		c4 = new Char(sheet, colW * 4, colH * 0, colW, colH);
		c5 = new Char(sheet, colW * 5, colH * 0, colW, colH);
		c6 = new Char(sheet, colW * 6, colH * 0, colW, colH);
		c7 = new Char(sheet, colW * 7, colH * 0, colW, colH);
		c8 = new Char(sheet, colW * 8, colH * 0, colW, colH);
		c9 = new Char(sheet, colW * 9, colH * 0, colW, colH);
		ca = new Char(sheet, colW * 0, colH * 1, colW, colH);
		cb = new Char(sheet, colW * 1, colH * 1, colW, colH);
		cc = new Char(sheet, colW * 2, colH * 1, colW, colH);
		cd = new Char(sheet, colW * 3, colH * 1, colW, colH);
		ce = new Char(sheet, colW * 4, colH * 1, colW, colH);
		cf = new Char(sheet, colW * 5, colH * 1, colW, colH);
		cg = new Char(sheet, colW * 6, colH * 1, colW, colH);
		ch = new Char(sheet, colW * 7, colH * 1, colW, colH);
		ci = new Char(sheet, colW * 8, colH * 1, colW, colH);
		cj = new Char(sheet, colW * 9, colH * 1, colW, colH);
		ck = new Char(sheet, colW * 10, colH * 1, colW, colH);
		cl = new Char(sheet, colW * 11, colH * 1, colW, colH);
		cm = new Char(sheet, colW * 12, colH * 1, colW, colH);
		cn = new Char(sheet, colW * 13, colH * 1, colW, colH);
		co = new Char(sheet, colW * 14, colH * 1, colW, colH);
		cp = new Char(sheet, colW * 15, colH * 1, colW, colH);
		cq = new Char(sheet, colW * 16, colH * 1, colW, colH);
		cr = new Char(sheet, colW * 17, colH * 1, colW, colH);
		cs = new Char(sheet, colW * 18, colH * 1, colW, colH);
		ct = new Char(sheet, colW * 19, colH * 1, colW, colH);
		cu = new Char(sheet, colW * 20, colH * 1, colW, colH);
		cv = new Char(sheet, colW * 21, colH * 1, colW, colH);
		cw = new Char(sheet, colW * 22, colH * 1, colW, colH);
		cx = new Char(sheet, colW * 23, colH * 1, colW, colH);
		cy = new Char(sheet, colW * 24, colH * 1, colW, colH);
		cz = new Char(sheet, colW * 25, colH * 1, colW, colH);
		cA = new Char(sheet, colW * 0, colH * 2, colW, colH);
		cB = new Char(sheet, colW * 1, colH * 2, colW, colH);
		cC = new Char(sheet, colW * 2, colH * 2, colW, colH);
		cD = new Char(sheet, colW * 3, colH * 2, colW, colH);
		cE = new Char(sheet, colW * 4, colH * 2, colW, colH);
		cF = new Char(sheet, colW * 5, colH * 2, colW, colH);
		cG = new Char(sheet, colW * 6, colH * 2, colW, colH);
		cH = new Char(sheet, colW * 7, colH * 2, colW, colH);
		cI = new Char(sheet, colW * 8, colH * 2, colW, colH);
		cJ = new Char(sheet, colW * 9, colH * 2, colW, colH);
		cK = new Char(sheet, colW * 10, colH * 2, colW, colH);
		cL = new Char(sheet, colW * 11, colH * 2, colW, colH);
		cM = new Char(sheet, colW * 12, colH * 2, colW, colH);
		cN = new Char(sheet, colW * 13, colH * 2, colW, colH);
		cO = new Char(sheet, colW * 14, colH * 2, colW, colH);
		cP = new Char(sheet, colW * 15, colH * 2, colW, colH);
		cQ = new Char(sheet, colW * 16, colH * 2, colW, colH);
		cR = new Char(sheet, colW * 17, colH * 2, colW, colH);
		cS = new Char(sheet, colW * 18, colH * 2, colW, colH);
		cT = new Char(sheet, colW * 19, colH * 2, colW, colH);
		cU = new Char(sheet, colW * 20, colH * 2, colW, colH);
		cV = new Char(sheet, colW * 21, colH * 2, colW, colH);
		cW = new Char(sheet, colW * 22, colH * 2, colW, colH);
		cX = new Char(sheet, colW * 23, colH * 2, colW, colH);
		cY = new Char(sheet, colW * 24, colH * 2, colW, colH);
		cZ = new Char(sheet, colW * 25, colH * 2, colW, colH);
		cPeriod = new Char(sheet, colW * 0, colH * 3, colW, colH);
		cExclamation = new Char(sheet, colW * 1, colH * 3, colW, colH);
		cQuestion = new Char(sheet, colW * 2, colH * 3, colW, colH);
		cComma = new Char(sheet, colW * 3, colH * 3, colW, colH);
		cSQuote = new Char(sheet, colW * 4, colH * 3, colW, colH);
		cDQuote = new Char(sheet, colW * 5, colH * 3, colW, colH);
		cBSlash = new Char(sheet, colW * 6, colH * 3, colW, colH);
		cFSlash = new Char(sheet, colW * 7, colH * 3, colW, colH);
		cLParan = new Char(sheet, colW * 8, colH * 3, colW, colH);
		cRParan = new Char(sheet, colW * 9, colH * 3, colW, colH);
		cLBracket = new Char(sheet, colW * 10, colH * 3, colW, colH);
		cRBracket = new Char(sheet, colW * 11, colH * 3, colW, colH);
		cLBrace = new Char(sheet, colW * 12, colH * 3, colW, colH);
		cRBrace = new Char(sheet, colW * 13, colH * 3, colW, colH);
		cPlus = new Char(sheet, colW * 14, colH * 3, colW, colH);
		cEquals = new Char(sheet, colW * 15, colH * 3, colW, colH);
		cMinus = new Char(sheet, colW * 16, colH * 3, colW, colH);
		cDollar = new Char(sheet, colW * 17, colH * 3, colW, colH);
		cApostrophe = new Char(sheet, colW * 18, colH * 3, colW, colH);
		cCopyright = new Char(sheet, colW * 19, colH * 3, colW, colH);
		cRegistered = new Char(sheet, colW * 20, colH * 3, colW, colH);
		cTrademark = new Char(sheet, colW * 21, colH * 3, colW, colH);
		cLArrow = new Char(sheet, colW * 22, colH * 3, colW, colH);
		cRArrow = new Char(sheet, colW * 23, colH * 3, colW, colH);
		cUArrow = new Char(sheet, colW * 24, colH * 3, colW, colH);
		cNumber = new Char(sheet, colW * 25, colH * 3, colW, colH);
		cPole = new Char(sheet, colW * 26, colH * 3, colW, colH);
		cPercent = new Char(sheet, colW * 27, colH * 3, colW, colH);
		cColan = new Char(sheet, colW * 28, colH * 3, colW, colH);
		cSColan = new Char(sheet, colW * 29, colH * 3, colW, colH);
	}
	
	public void drawString(String text, int x, int y, float scale) {
		int x2 = x;
		int y2 = y;
		int lwidth = 0;
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			x2 += lwidth;
			/*if(width >= Launcher.width) {
				x2 = 0;
				y2 += 17;
				width = 0;
			}*/
			if(c != ' ') {
				lwidth = (int) ((colW * scale) + scale * 3);
			}
			switch(c) {
				case 'A':
					cA.render(x2, y2, scale);
					break;
				case 'B':
					cB.render(x2, y2, scale);
					break;
				case 'C':
					cC.render(x2, y2, scale);
					break;
				case 'D':
					cD.render(x2, y2, scale);
					break;
				case 'E':
					cE.render(x2, y2, scale);
					break;
				case 'F':
					cF.render(x2, y2, scale);
					break;
				case 'G':
					cG.render(x2, y2, scale);
					break;
				case 'H':
					cH.render(x2, y2, scale);
					break;
				case 'I':
					cI.render(x2, y2, scale);
					break;
				case 'J':
					cJ.render(x2, y2, scale);
					break;
				case 'K':
					cK.render(x2, y2, scale);
					break;
				case 'L':
					cL.render(x2, y2, scale);
					break;
				case 'M':
					cM.render(x2, y2, scale);
					break;
				case 'N':
					cN.render(x2, y2, scale);
					break;
				case 'O':
					cO.render(x2, y2, scale);
					break;
				case 'P':
					cP.render(x2, y2, scale);
					break;
				case 'Q':
					cQ.render(x2, y2, scale);
					break;
				case 'R':
					cR.render(x2, y2, scale);
					break;
				case 'S':
					cS.render(x2, y2, scale);
					break;
				case 'T':
					cT.render(x2, y2, scale);
					break;
				case 'U':
					cU.render(x2, y2, scale);
					break;
				case 'V':
					cV.render(x2, y2, scale);
					break;
				case 'W':
					cW.render(x2, y2, scale);
					break;
				case 'X':
					cX.render(x2, y2, scale);
					break;
				case 'Y':
					cY.render(x2, y2, scale);
					break;
				case 'Z':
					cZ.render(x2, y2, scale);
					break;
				case 'a':
					ca.render(x2, y2, scale);
					break;
				case 'b':
					cb.render(x2, y2, scale);
					break;
				case 'c':
					cc.render(x2, y2, scale);
					break;
				case 'd':
					cd.render(x2, y2, scale);
					break;
				case 'e':
					ce.render(x2, y2, scale);
					break;
				case 'f':
					cf.render(x2, y2, scale);
					break;
				case 'g':
					cg.render(x2, y2, scale);
					break;
				case 'h':
					ch.render(x2, y2, scale);
					break;
				case 'i':
					ci.render(x2, y2, scale);
					break;
				case 'j':
					cj.render(x2, y2, scale);
					break;
				case 'k':
					ck.render(x2, y2, scale);
					break;
				case 'l':
					cl.render(x2, y2, scale);
					break;
				case 'm':
					cm.render(x2, y2, scale);
					break;
				case 'n':
					cn.render(x2, y2, scale);
					break;
				case 'o':
					co.render(x2, y2, scale);
					break;
				case 'p':
					cp.render(x2, y2, scale);
					break;
				case 'q':
					cq.render(x2, y2, scale);
					break;
				case 'r':
					cr.render(x2, y2, scale);
					break;
				case 's':
					cs.render(x2, y2, scale);
					break;
				case 't':
					ct.render(x2, y2, scale);
					break;
				case 'u':
					cu.render(x2, y2, scale);
					break;
				case 'v':
					cv.render(x2, y2, scale);
					break;
				case 'w':
					cw.render(x2, y2, scale);
					break;
				case 'x':
					cx.render(x2, y2, scale);
					break;
				case 'y':
					cy.render(x2, y2, scale);
					break;
				case 'z':
					cz.render(x2, y2, scale);
					break;
				case '0':
					c0.render(x2, y2, scale);
					break;
				case '1':
					c1.render(x2, y2, scale);
					break;
				case '2':
					c2.render(x2, y2, scale);
					break;
				case '3':
					c3.render(x2, y2, scale);
					break;
				case '4':
					c4.render(x2, y2, scale);
					break;
				case '5':
					c5.render(x2, y2, scale);
					break;
				case '6':
					c6.render(x2, y2, scale);
					break;
				case '7':
					c7.render(x2, y2, scale);
					break;
				case '8':
					c8.render(x2, y2, scale);
					break;
				case '9':
					c9.render(x2, y2, scale);
					break;
				case '.':
					cPeriod.render(x2, y2, scale);
					break;
				case '!':
					cExclamation.render(x2, y2, scale);
					break;
				case '?':
					cQuestion.render(x2, y2, scale);
					break;
				case ',':
					cComma.render(x2, y2, scale);
					break;
				case '"':
					cDQuote.render(x2, y2, scale);
					break;
				case '/':
					cBSlash.render(x2, y2, scale);
					break;
				case '(':
					cLParan.render(x2, y2, scale);
					break;
				case ')':
					cRParan.render(x2, y2, scale);
					break;
				case '[':
					cLBracket.render(x2, y2, scale);
					break;
				case ']':
					cRBracket.render(x2, y2, scale);
					break;
				case '{':
					cLBrace.render(x2, y2, scale);
					break;
				case '}':
					cRBrace.render(x2, y2, scale);
					break;
				case '+':
					cPlus.render(x2, y2, scale);
					break;
				case '=':
					cEquals.render(x2, y2, scale);
					break;
				case '-':
					cMinus.render(x2, y2, scale);
					break;
				case '$':
					cDollar.render(x2, y2, scale);
					break;
				case '*':
					cApostrophe.render(x2, y2, scale);
					break;
				case '©':
					cCopyright.render(x2, y2, scale);
					break;
				case '®':
					cRegistered.render(x2, y2, scale);
					break;
				case '™':
					cTrademark.render(x2, y2, scale);
					break;
				case '<':
					cLArrow.render(x2, y2, scale);
					break;
				case '>':
					cRArrow.render(x2, y2, scale);
					break;
				case '^':
					cUArrow.render(x2, y2, scale);
					break;
				case '#':
					cNumber.render(x2, y2, scale);
					break;
				case '|':
					cPole.render(x2, y2, scale);
					break;
				case '%':
					cPercent.render(x2, y2, scale);
					break;
				case ':':
					cColan.render(x2, y2, scale);
					break;
				case ';':
					cSColan.render(x2, y2, scale);
					break;
				case ' ':
					lwidth = (int) (6 * scale);
					break;
			}
		}
	}
	
	public void drawString(String text, int x, int y, float scale, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		drawString(text, x, y, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void drawLines(List<String> lines, int x, int y, float scale) {
		for(int i = 0; i < lines.size(); i++) {
			drawString(lines.get(i), x, (int) (y + (i * ((colH + 2) * scale))), scale);
		}
	}
	
	public void drawLines(List<String> lines, int x, int y, float scale, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		drawLines(lines, x, y, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void drawLines(String[] lines, int x, int y, float scale) {
		for(int i = 0; i < lines.length; i++) {
			drawString(lines[i], x, (int) (y + (i * ((colH + 2) * scale))), scale);
		}
	}
	
	public void drawLines(String[] lines, int x, int y, float scale, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		for(int i = 0; i < lines.length; i++) {
			drawString(lines[i], x, (int) (y + (i * ((colH + 2) * scale))), scale);
		}
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public int[] getSizeByString(String text, float scale) {
		int[] size = new int[2];
		int lwidth = 0;
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) != ' ') {
				lwidth = (int) ((colW * scale) + scale * 3);
			} else {
				lwidth = (int) (6 * scale);
			}
			size[0] += lwidth;
		}
		size[0] -= scale * 3;
		size[1] = (int) (colH * scale);
		return size;
	}
	
	public int getWidthByString(String text, float scale) {
		int[] size = getSizeByString(text, scale);
		return size[0];
	}
	
	public int getHeightByString(String text, float scale) {
		int[] size = getSizeByString(text, scale);
		return size[1];
	}

}
