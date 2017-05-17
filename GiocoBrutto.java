import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.util.*;

public class GiocoBrutto extends Applet implements KeyListener, Runnable{
	
	String pg = "PGFront.png";
	String evil = "pk.png";
	
	Image sfondo = null;
	Image uomo = null;
	Image pk =null;
	Image colpo = null;
	Image nem = null;
	Image schermata = null;
	Image menu = null;
	Image cuore = null;
	Image scuo = null;
	
	Image im = null;
	Graphics gra = null;
	
	int score = 0;
	int contnem = 0;
	int div = 4;
	int dam = 75/4;
	
	boolean vita1 = true;
	boolean vita2 = true;
	boolean spawncuore = false;
	boolean ENDGAME = false;
	
	int hp = 100;
	
	//POSIZIONE GIOCATORE
	int posx = 250;
	int posy = 250;
	
	//POSIZIONE BOOMERANG
	int rposx = posx + 50;
	int rposy = posy + 40;
	
	//POSIZIONE NEMICO
	//int cposx = 400;
	//int cposy = 400;
	
	int cuorex = 0;
	int cuorey = 0;
	
	int dir=0;
	int sx = -50;
	int sy = -50;
	int barra = 75;
	
	boolean stampa = true;
	boolean amen = true;
	boolean morto = false;
	boolean chit = false;
	boolean attivo=false;
	boolean sfon=true;
	boolean cicla = true;
	boolean boomer = false;
	boolean vero = false;
	boolean spawn = true;
	
	boolean su = false;
	boolean giu = false;
	boolean destra = false;
	boolean sinistra = false;
	
	Nemico cattivo = new Nemico(hp, 560, 400, "pk.png");
	
	public void paint(Graphics g){
		
		if(ENDGAME  == false){
			
			if(amen == false){
				
				if(morto == true){
				
				gra.drawImage(schermata, 0, 0, 600, 600, this);
			
				}else{
				
					gra.drawImage(sfondo,sx,sy,800,800,this);
				
					gra.setColor(Color.red);
					gra.drawRect(14, 14, 141, 31);
					
					gra.setColor(Color.white);
					gra.fillRect(15, 15, 140, 30);
					
					gra.setColor(Color.red);
					gra.fillRect((cattivo.x+10), (cattivo.y-20), barra, 10);
					
					gra.setColor(Color.white);
					gra.drawRect((cattivo.x+10), (cattivo.y-20), 75, 10);
					gra.setColor(Color.BLACK);
					gra.setFont(new Font("Verdana",Font.BOLD,17));
					gra.drawString("SCORE    " + score,16,35);
					
					
					if(vita1 == true){
						
						gra.drawImage(cuore,550,16,35,35,this);
						
					}
					
					if(vita2 == true){
						
						gra.drawImage(cuore,510,16,35,35,this);
						
					}
						
					if(spawncuore == true){
						
						gra.drawImage(scuo,cuorex,cuorey,35,35,this);
						
					}
					System.out.println("VITA "+vita1);
					
					gra.setColor(Color.white);
					
					uomo = getImage(getDocumentBase(), pg);	
				
					gra.drawImage(uomo,posx,posy,127,73,this);
				
					nem = getImage(getDocumentBase(), evil);
					
					gra.drawImage(nem, cattivo.x, cattivo.y, 100, 100, this);
					gra.drawImage(pk,rposx,rposy,60,60,this);
				
				}
				
			}else{
				
				gra.drawImage(menu, 0, 0, 600, 600, this);
				
				if(stampa == true){
					
					gra.setColor(Color.white);
					gra.setFont(new Font("Verdana",Font.BOLD,25));
					
					gra.drawString(".:PREMI SPAZIO:.", 180, 530);
					stampa = false;
					
					try{
					
						Thread.sleep(500);
						
					}catch(InterruptedException e){
						
					}
				}else{
					
					stampa = true;
					
					try{
					
						Thread.sleep(500);
						
					}catch(InterruptedException e){
						
					}
				}
			}
		}else{
			
			gra.setColor(Color.black);
			gra.fill3DRect(0,0,600,600,true);
			gra.setColor(Color.white);
			gra.setFont(new Font("Verdana",Font.BOLD,60));
			gra.drawString("SCORE "+score, 50, 300);
			
		}
		g.drawImage(im,0,0,this);
	}
	
	public void update(Graphics g){
		
		paint(g);
		
	}
	
	public void init(){
		
		sfondo = getImage(getDocumentBase(), "Room.jpg");
		uomo = getImage(getDocumentBase(), pg);
		pk = getImage(getDocumentBase(), "boomer.gif");
		colpo = getImage(getDocumentBase(), "bullet.png");
		nem =  getImage(getDocumentBase(), evil);
		schermata = getImage(getDocumentBase(), "wc.png");
		menu = getImage(getDocumentBase(), "Skelerang.png");
		cuore = getImage(getDocumentBase(), "heart.png");
		im = createImage(1001,1001);
		scuo = getImage(getDocumentBase(), "cuore.png");
		
		gra = im.getGraphics();
		addKeyListener(this);
		
	}
	
	public void keyTyped(KeyEvent ke){}	
	
    public void keyPressed(KeyEvent ke){
		
		if(ke.getKeyCode()==ke.VK_A){
			
			
			sinistra = true;
			
		}	
		
		if(ke.getKeyCode() == ke.VK_D){
			
			destra = true;
			
		}
		if(ke.getKeyCode()==ke.VK_W){
			
			su = true;
			
		}
		if(ke.getKeyCode()==ke.VK_S){
			
			giu = true;
			
		}
		
		
		if(ke.getKeyCode() == ke.VK_UP){
			cicla=true;
			dir = 2;
		}
		
		if(ke.getKeyCode() == ke.VK_DOWN){
			cicla=true;
			dir = 1;
		}
		
		if(ke.getKeyCode() == ke.VK_RIGHT){
			cicla=true;
			dir = 3;
		}
		
		if(ke.getKeyCode() == ke.VK_LEFT){
			cicla=true;
			dir = 4;
		}
		if(ke.getKeyCode() == ke.VK_SPACE){
			vero = true;
			amen = false;
			}
	}
	public void keyReleased(KeyEvent ke){
		
		if(ke.getKeyCode() == ke.VK_A){
			
			sinistra = false;
			
		}
		
		if(ke.getKeyCode() == ke.VK_D){
			
			destra = false;
			
		}
		
		if(ke.getKeyCode() == ke.VK_W){
			
			su = false;
			
		}
		
		if(ke.getKeyCode() == ke.VK_S){
			
			giu = false;
			
		}
		
	}
	
	
	public void start(){
		
		
		Thread thread1 = new Thread(){					//THREAD 1
			
	
			public void run(){
				
				while(0==0){
					
					try{
						
																						//MOVIMENTO INIZIO
			
						if(vero == true){
							
							if(cattivo.y < posy){
								cattivo.y = cattivo.y + 10;
							}else{
								
								cattivo.y = cattivo.y - 10;
								
							}
							
							if(cattivo.x < posx){
								
								cattivo.x = cattivo.x + 10;
							}else{
								
								cattivo.x = cattivo.x - 10;
								
							}
								
								repaint();									//MOVIMENTO FINE
							
							chit = cattivo.hit(rposx, rposy);
							
							if(chit == true){
								
								barra = barra - dam;
							}
							
							switch(contnem){
							
								case 0: {
									
									if(chit == true){
											
										evil = "pkhit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "pk.png";
										
									}
								
								}	
								
								break;
								case 1:{
									
									evil = "snoop.gif";
									
									if(chit == true){
											
										evil = "snoophit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "snoop.gif";
										
									}
										
									}
								break;
								
								case 2:{
									
									evil = "Pedobear.png";
									
									if(chit == true){
											
										evil = "Pedobearhit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "Pedobear.png";
										
									}
									
								}
								break;
								
								case 3: {
									
									evil = "bulbasaur.png";
									
									if(chit == true){
											
										evil = "bulbasaurhit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "bulbasaur.png";
										
									}
									
								}
								break;
								
								case 4: {
									
									evil = "caccah.png";
									
									if(chit == true){
											
										evil = "caccahit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "caccah.png";
										
									}
									
								} 
								break;
								
								case 5: {
									
									evil = "darthy.png";
									
									if(chit == true){
											
										evil = "darthyhit.png";
										Thread.sleep(200); // immaggine(,puoi) hit
										evil = "darthy.png";
										
									}
									
								} 
							}
							
							
							Thread.sleep(100); //Hitreg
							chit = false;
	
							
							if(cattivo.hp <= 0){			//MORTE DEL NEMICO------------------------
								
								morto = true;
								score = score + 150;
								
								spawncuore = true;
								cuorex = cattivo.x + 10;
								cuorey = cattivo.y + 10;
								
								if(spawn == true){
								
									cattivo.x = 100;
									cattivo.y = 100;
									spawn = false;
								
								}else{
									
									cattivo.x = 500;
									cattivo.y = 450;
									spawn = true;
									
								}
								
								hp = hp + 50;
								cattivo.hp = hp;
								
								Thread.sleep(4000);
								morto = false;
								
								div = div + 2;
								barra = 75;
								dam = barra/div;
								
								contnem++;
								
								
							}
						}
					}catch(InterruptedException e){
					
					}
				}
			}
		};
	
		Thread move= new Thread(){
			
			public void run(){
		
	
				try{
			    	
			    	boolean infinito=true;
			    	boolean hit = false;
			    	
			    	while(infinito==true){
			    		
			    	
				    	while(cicla == true){ 
				    		
				    		if(rposx >= 550 || rposy >= 550 || rposx <= 0 || rposy <= 0){
				    			hit = true;
				    		}
				    		repaint();
				    		boomer = false;
				    		Thread.sleep(30);
				    		
			
				    		if(hit == true || chit == true){		//MODIFICA
				    			
				    			boolean torna = true;
				    			boomer = true;
				    			
				    			while(torna == true){
				    				
						    		if(dir == 1){
						    			
						    			
						    				while(rposy != posy){
							    				rposy = rposy - 10;
							    				Thread.sleep(30); //giu
							    				repaint();
						    				
						    			}
						    			//SHTOP
						    			torna = false;
						    			cicla=false;
						    			hit=false;
						    			
						    		}else if(dir == 2){
						    			
						    			while(rposy != posy){
						    			
						    				rposy = rposy + 10; //su
						    				Thread.sleep(30);
						    				repaint();
						    			
						    			}
						    			
						    			torna = false;
						    			cicla=false;
						    			hit=false;
						    		}else if(dir == 3){
						    			
						    			while(rposx != posx){
						    			
						    				rposx = rposx - 10; //Destra
						    				Thread.sleep(30);
						    				repaint();
						    			
						    			}
						    			
						    			torna = false;
						    			cicla=false;
						    			
						    			hit=false;
						    		}else if(dir==4){
						    			
						    			while(rposx != posx){
						    			
						    				rposx = rposx + 10; //Sinistra
						    				Thread.sleep(30);
						    				repaint();
						    				
						    			}
						    			
						    			torna = false;
						    			cicla=false;
						    			hit=false;
						    		}
				    				
				    			}
				    			
				    		}
				    		
				    		if(dir == 1){
				    			
				    			rposy = rposy + 10; //giu
				    			
				    		}else if(dir == 2){
				    			
				    			rposy = rposy - 10; //su
				    			
				    		}else if(dir == 3){
				    			
				    			rposx = rposx + 10; //Destra
				    			
				    		}else if(dir==4){
				    			
				    			rposx = rposx - 10; //Sinistra
				    			
				    		}
				    		
				    		}
				    	
						Thread.sleep(50);
					    }
			    	}
			    	catch(InterruptedException e){
			    		
			    	}
					
					
				}
				
						
		};									//THREAD 2-----------------------------------------
		
		Thread pg_hit= new Thread(){
			
			public void run(){
				
				while(true){
					
				
					if(posy >= (cattivo.y - 30) && (posy + 117) <= cattivo.y + 130){
		    		
		    			if(posx >= (cattivo.x - 30) && (posx + 63) <= (cattivo.x + 130)){
			    			
		    				if(vita1 == true && vita2 == true){
		    					
		    					System.out.println("LEL "+vita1);
		    					vita1 = false;
		    					
		    					try{
		    					
		    						Thread.sleep(2000);
		    						
		    					}catch(Exception e){
		    						
		    						
		    						
		    					}
		    					
		    				}else if(vita1 == false && vita2 == true){
		    					
		    					vita2 = false;
		    					
		    				}else{
		    					
		    					ENDGAME = true;
		    					
		    				}
		    				
		    			}
						
					}
				}
			}
		};									//THREAD 3-------------------------------------------------
		
		Thread love = new Thread(){
			public void run(){
				
				while(true){
					
					if(posy >= (cuorey - 30) && (posy + 117) <= cuorey + 130){
		    		
		    			if(posx >= (cuorex - 30) && (posx + 63) <= (cuorex + 130)){
			    			
		    				if(vita1 == true && vita2 == true){
		    					
		    								    					
		    				}else if(vita1 == false && vita2 == true){
		    					
		    					spawncuore = false;
		    					vita1 = true;
		    					
		    				}
		    				
		    			}
						
					}
				}
				
			}
		};										//THREAD +4--------------------------------------------
		
		Thread walk = new Thread(){
			
			public void run(){
			
				while(true){
					
					
					while(sinistra == true){
						
						try{
							Thread.sleep(50);
						}catch(Exception e){
							
						}
						
						pg = "PGSin.png";						
					
						if(sx<0){
						
							if(posx>257){
								
								posx=posx-10;
								rposx = rposx - 10;
								
							}
							else{
								
								sx=sx+10;
								cattivo.x = cattivo.x + 10;
								cuorex = cuorex + 10;
								
							}
							
							repaint();
							
						}else if(sx<260 && posx > 0){
							
							posx = posx - 10;
							rposx = rposx - 10;
							
							repaint();
						}
						
					}
					
					while(destra == true){
						
						try{
							Thread.sleep(50);
						}catch(Exception e){
							
						}
						
						pg = "PGDes.png";						
	
						if(sx>-200){
							
							if(posx<243){
								
								posx=posx+10;
								rposx=rposx+10;
								
							}else{
								
								sx=sx-10;
								cattivo.x = cattivo.x - 10;
								cuorex = cuorex - 10;
								
							}
							
							repaint();
							
						}else if(sx>-475  && posx<490){
							
							posx = posx+10;
							rposx=rposx+10;
							repaint();
							
						}
									
					}
					
					while(su == true){
						
						try{
							Thread.sleep(50);
						}catch(Exception e){
							
						}
						
						pg = "PGBack.png";
						
						if(sy < 0){
							
							if(posy > 250){
								
								posy = posy - 10;
								rposy = rposy - 10;
								
							}else{
								
								sy=sy+10;
								cattivo.y = cattivo.y + 10;
								cuorey = cuorey + 10;
							}
							
							repaint();
							
						}else if(sy < 50 && posy > 5){
			
							posy = posy-10;
							rposy = rposy - 10;
							
							repaint();
						}
						
					}
					
					while(giu == true){
						
						try{
							Thread.sleep(50);
						}catch(Exception e){
							
						}
						
						pg = "PGFront.png";						
			
						if(sy > -200){
							
							if(posy < 250){
								
								posy = posy + 10;
								rposy = rposy + 10;
								
								
			
							}else{
								
								sy=sy-10;
								cattivo.y = cattivo.y - 10;
								cuorey = cuorey - 10;
							}
							
							repaint();
							
						}else if(sy < 50 && posy < 520){
							
							posy = posy+10;
							rposy = rposy + 10;
							
							repaint();
						}
						
					}
					
				}
			}
		};
		
		walk.start();
		move.start();
		thread1.start();
		pg_hit.start();
		
	}
	
	
	public void run(){
		
	}
	
	
	
	public void stop(){
			
	}
	
	public void destroy(){
		
	}
	
	
}