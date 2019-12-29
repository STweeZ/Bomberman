package jeu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FenetrePrincipale extends JFrame {

	  private static final long serialVersionUID = 1L;

		private int temps; /* Le temps qui s'ecoule */
		private String nom_joueur; /* Nom du joueur */
		private static int largeurFen  = 1360; /* Largeur de la fenetre principale */
		private static int hauteurFen = 760; /* Hauteur de la fenetre principale */
		private JFrame fenetreJeu; /* Creation du JFrame qu'on va utiliser */
		private Terrain terrain; /* Creation d'un objet de type terrain */
		private Personnage player1; /* Objet joueur 1 */
		private Personnage player2; /* Objet joueur 2 */
		private Ennemis ennemis1; /* Objet ennemi 1 */
		private Ennemis ennemis2; /* Objet ennemi 2 */
		private Ennemis ennemis3; /* Objet ennemi 3 */
		private ChoixGame choixGame; /* Le JPanel de type ChoixGame */
		private Panneau panJeu; /* Le JPanel de type Panneau */
		private Menu panMenu; /* Le JPanel de type Menu */
		private Options panOptions; /* Le JPanel de type Options */
		private Options j1; /* Le JPanel utilise quand le curseur pointe sur le joueur 1 dans Options */
		private Options j2; /* Le JPanel utilise quand le curseur pointe sur le joueur 2 dans Options */
		private PanelLose panLoseSolo; /* Le JPanel de type PanelLoseSolo */
		private PanelLose panLoseMulti; /* Le JPanel de type PanelLoseMulti */
		private PanelLose panWinSolo; /* Le JPanel de type PanelLose en solo pour quand le joueur de la partie a gagne */
		private PanelLose panWinJ1; /* Le JPanel de type PanelLose en multi pour quand le joueur 1 a gagne */
		private PanelLose panWinJ2; /* Le JPanel de type PanelLose en multi pour quand le joueur 2 a gagne */
		private PlayMusic musiqueMenu; /* Objet de type PlayMusic pour la musique du menu */
		private PlayMusic musiqueJeu; /* Objet de type PlayMusic pour la musique du jeu */
		private PlayMusic musiqueOptions; /* Objet de type PlayMusic pour la musique dans le panneau Options */
		private PlayMusic musiqueCharacters; /* Objet de type PlayMusic pour la musique du choix de mode de jeu */
		private PlayMusic musiqueLose; /* Objet de type PlayMusic pour la musique de perte de partie */
		private PlayMusic musiqueWin; /* Objet de type PlayMusic pour la musique de win de partie */
		private boolean appliEnRoute=true; /* Si l'application est en route, si la fenetre est ouverte */
		private boolean multi; /* Si le mode choisi est multijoueur ou non */
		private boolean solo; /* Si le mode choisi est solo ou non */
		private boolean doEnnemisMove; /* Booleen qui dit si les ennemis pouvent bouger et donc si on a clique sur le mode de jeu 1 joueur */
		private boolean mortE1; /* Boolean qui indique si l'ennemi 1 est decede */
		private boolean mortE2; /* Boolean qui indique si l'ennemi 2 est decede */
		private boolean mortE3; /* Boolean qui indique si l'ennemi 3 est decede */
		
		private PlaySound death;

		public FenetrePrincipale() {

			fenetreJeu = new JFrame(); /* Fenetre de jeu principale pouvant accueillir plusieurs pannel */

			fenetreJeu.setVisible(true); /* Visibilite du JFrame */

			fenetreJeu.setTitle("Bomberman"); /* Nom de la fenetre */
			fenetreJeu.setSize(largeurFen,hauteurFen); /* Initialisation de la taille de la fenetre JFrame */
			fenetreJeu.setResizable(false); /* Impossible de pouvoir changer la taille de fenetre */

			fenetreJeu.setLocationRelativeTo(null); /* Positionnement au centre de l'ecran */

			fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Fermeture de l'application par defaut qui va se faire en cliquant sur la croix */
			fenetreJeu.setAlwaysOnTop(true); /* Laisse la fenetre au premier plan de l'ecran */
			
			death = new PlaySound("musiques/death.wav");
			

			choixGame = new ChoixGame();
			panMenu = new Menu();

	    /* Initialisation du JPanel choix de jeu et du JPanel du menu principal */

			panLoseSolo = new PanelLose("images/Textures/perdu.jpg");
			panLoseMulti = new PanelLose("images/Textures/perdu.jpg");
			panWinSolo = new PanelLose("images/Textures/gagne.jpg");
			panWinJ1 = new PanelLose("images/Textures/j1gagne.jpg");
			panWinJ2 = new PanelLose("images/Textures/j2gagne.jpg");

	    /* Initialisation des JPanel PanelLose */

			panOptions = new Options("images/Textures/controles.jpg");
			j1 = new Options("images/Textures/controlesJ1.jpg");
			j2 = new Options("images/Textures/controlesJ2.jpg");

	    /* Initialisation des JPanel d'Options */

			musiqueMenu = new PlayMusic("musiques/menu.wav");
			musiqueJeu = new PlayMusic("musiques/ingame.wav");
			musiqueOptions = new PlayMusic("musiques/options.wav");
			musiqueCharacters = new PlayMusic("musiques/characters.wav");
			musiqueLose = new PlayMusic("musiques/lose.wav");
			musiqueWin = new PlayMusic("musiques/win.wav");

	    /* Initialisation des 6 differentes musiques utilisees */

			doEnnemisMove=false; /* Le mouvement des ennemis est initialise a false car ils ne bougent pas  */

			try {
				musiqueMenu.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			} /* Lance la musique, si chemin invalide lance une exception */

			this.allActionListener();
			this.allMouseListener();

	    /* Applique l'ensemble des ActionListener et des MouseListener */

			fenetreJeu.setContentPane(panMenu);

			this.barreMenu();

			fenetreJeu.revalidate();
			
			fenetreJeu.repaint();

	    /* Applique le JPanel Menu et la barre de menu, puis ensuite met a jour le JFrame */

		}

	  public void allActionListener(){

		  	panMenu.getJouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panMenu.getJouer().setForeground(Color.WHITE);
					musiqueMenu.musicArret();
					
					try {
						musiqueCharacters.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.setContentPane(choixGame);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
					
				}
			}); /* Si on clique sur Joueur dans le menu principale, applique le JPanel choixGame, changement de la musique */

			panMenu.getOptions().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panMenu.getOptions().setForeground(Color.WHITE);
					musiqueMenu.musicArret();
					try {
						musiqueOptions.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panOptions);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
					
				}
			}); /* Si on clique sur Options dans le menu principale, applique le JPanel Options, changement de la musique */

			panMenu.getQuitter().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					appliEnRoute=false;
					fenetreJeu.dispose();
				}
			}); /* Si on clique sur Quitter dans le menu principale, ca quitte l'application */


			choixGame.getRetour().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					choixGame.getRetour().setForeground(Color.WHITE);
					if (musiqueCharacters.getEstEnRoute()==true) musiqueCharacters.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			}); /* Dans le menu de choix de mode de jeu -> si clic sur retour, retour au JPanel Menu, changement de la musique */

			choixGame.getJoueurChoix1().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					choixGame.getJoueurChoix1().setForeground(Color.WHITE);
					musiqueCharacters.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();

					ennemis1 = new Ennemis(terrain);
					ennemis2 = new Ennemis(terrain);
					ennemis3 = new Ennemis(terrain);

					mortE1=false;
					mortE2=false;
					mortE3=false;

	        /* Initialisation, les 3 ennemis ne sont pas morts */

					panJeu = new Panneau(terrain,"images/Textures/background.jpg");
					player1 = new Personnage("Player1", terrain);
					ennemis2.add(ennemis3);
					ennemis1.add(ennemis2);
					player1.add(ennemis1);
					panJeu.add(player1);

					multi=false;
					solo=true;
					appliEnRoute=true;
					doEnnemisMove=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			}); /* En cliquant sur le choix 1 joueur, ca creer un terrain avec 1 seul personnage et 3 ennemis, ca ajoute le JPanel contenant tout ca au JFrame, le mode multi est desactive, l'appli est en route, les ennemis sont capables de bouger, et changement de l'ambiance musicale en musique de jeu */

			choixGame.getJoueurChoix2().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					choixGame.getJoueurChoix2().setForeground(Color.WHITE);
					musiqueCharacters.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.requestFocusInWindow();
					
					terrain = new Terrain();

					player1 = new Personnage("Player1", terrain);
					player2 = new Personnage("Player2", terrain);
					panJeu = new Panneau(terrain,"images/Textures/backgroundMulti.jpg");
					player1.add(player2);
					panJeu.add(player1);

					multi=true;
					solo=false;
					appliEnRoute=true;
					
					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			}); /* En cliquant sur le choix 2 joueurs, ca creer un terrain avec 2 personnages, ca ajoute le JPanel contenant tout ca au JFrame, le mode multi est active, l'appli est en route, et changement de l'ambiance musicale en musique de jeu */

			panOptions.getRetour().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panOptions.getRetour().setForeground(Color.WHITE);
					if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			j1.getRetour().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					j1.getRetour().setForeground(Color.WHITE);
					if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			j2.getRetour().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					j2.getRetour().setForeground(Color.WHITE);
					if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

	     /* En cliquant sur Retour dans le menu Options, ca nous retourne au menu principal avec changement des musiques */

			panLoseSolo.getRejouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panLoseSolo.getRejouer().setForeground(Color.WHITE);
					musiqueLose.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();

					ennemis1 = new Ennemis(terrain);
					ennemis2 = new Ennemis(terrain);
					ennemis3 = new Ennemis(terrain);

					panJeu = new Panneau(terrain,"images/Textures/background.jpg");
					player1 = new Personnage("Player1", terrain);
					ennemis2.add(ennemis3);
					ennemis1.add(ennemis2);
					player1.add(ennemis1);
					panJeu.add(player1);

					mortE1=false;
					mortE2=false;
					mortE3=false;

					multi=false;
					solo=true;
					appliEnRoute=true;
					doEnnemisMove=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			});

			panLoseMulti.getRejouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panLoseMulti.getRejouer().setForeground(Color.WHITE);
					musiqueLose.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();
					
					player1 = new Personnage("Player1", terrain);
					player2 = new Personnage("Player2", terrain);
					panJeu = new Panneau(terrain,"images/Textures/backgroundMulti.jpg");
					player1.add(player2);
					panJeu.add(player1);

					multi=true;
					solo=false;
					appliEnRoute=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			});

			panWinSolo.getRejouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinSolo.getRejouer().setForeground(Color.WHITE);
					musiqueWin.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					
					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();

					ennemis1 = new Ennemis(terrain);
					ennemis2 = new Ennemis(terrain);
					ennemis3 = new Ennemis(terrain);

					panJeu = new Panneau(terrain,"images/Textures/background.jpg");
					player1 = new Personnage("Player1", terrain);
					ennemis2.add(ennemis3);
					ennemis1.add(ennemis2);
					player1.add(ennemis1);
					panJeu.add(player1);

					mortE1=false;
					mortE2=false;
					mortE3=false;

					multi=false;
					solo=true;
					appliEnRoute=true;
					doEnnemisMove=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			});

			panWinJ1.getRejouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinJ1.getRejouer().setForeground(Color.WHITE);
					musiqueWin.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}

					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();
					player1 = new Personnage("Player1", terrain);
					player2 = new Personnage("Player2", terrain);
					panJeu = new Panneau(terrain,"images/Textures/backgroundMulti.jpg");
					player1.add(player2);
					panJeu.add(player1);

					multi=true;
					solo=false;
					appliEnRoute=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			});

			panWinJ2.getRejouer().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinJ2.getRejouer().setForeground(Color.WHITE);
					musiqueWin.musicArret();
					try {
						musiqueJeu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					
					fenetreJeu.requestFocusInWindow();

					terrain = new Terrain();
					
					player1 = new Personnage("Player1", terrain);
					player2 = new Personnage("Player2", terrain);
					panJeu = new Panneau(terrain,"images/Textures/backgroundMulti.jpg");
					player1.add(player2);
					panJeu.add(player1);

					multi=true;
					solo=false;
					appliEnRoute=true;

					fenetreJeu.setContentPane(panJeu);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			});

		/* En cliquant sur Rejouer dans la page perte de partie ou quand on a gagne une partie, cela relance une nouvelle partie avec changement des musiques, reinitialisation des objets ennemis et personnages pour redemarrer une nouvelle partie toute propre */

			panLoseSolo.getMenuP().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panLoseSolo.getMenuP().setForeground(Color.WHITE);
					if (musiqueLose.getEstEnRoute()==true) musiqueLose.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			panLoseMulti.getMenuP().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panLoseMulti.getMenuP().setForeground(Color.WHITE);
					if (musiqueLose.getEstEnRoute()==true) musiqueLose.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			panWinSolo.getMenuP().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinSolo.getMenuP().setForeground(Color.WHITE);
					if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			panWinJ1.getMenuP().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinJ1.getMenuP().setForeground(Color.WHITE);
					if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

			panWinJ2.getMenuP().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					panWinJ2.getMenuP().setForeground(Color.WHITE);
					if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			});

		/* En cliquant sur Menu dans la page perte de partie ou quand on a gagne une partie, ca nous retourne au menu principal avec changement des musiques */


	  } /* L'ensemble des ActionListener */

	  public void allMouseListener(){

			panMenu.getJouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panMenu.getJouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panMenu.getJouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Jouer, celui-ci est grise */


			panMenu.getOptions().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panMenu.getOptions().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panMenu.getOptions().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Options, celui-ci est grise */


			panMenu.getQuitter().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panMenu.getQuitter().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panMenu.getQuitter().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Quitter, celui-ci est grise */

			choixGame.getJoueurChoix1().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	choixGame.getJoueurChoix1().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	choixGame.getJoueurChoix1().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur le mode 1 joueur, celui-ci est grise */

			choixGame.getJoueurChoix2().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	choixGame.getJoueurChoix2().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	choixGame.getJoueurChoix2().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur le mode 2 joueurs, celui-ci est grise */

			choixGame.getRetour().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	choixGame.getRetour().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	choixGame.getRetour().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Retour, celui-ci est grise */


			panOptions.getRetour().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panOptions.getRetour().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panOptions.getRetour().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Retour, celui-ci est grise */


			j1.getRetour().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	j1.getRetour().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	j1.getRetour().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Retour, celui-ci est grise */

			j2.getRetour().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	j2.getRetour().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	j2.getRetour().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Retour, celui-ci est grise */


			panOptions.getJ1().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	fenetreJeu.setContentPane(j1);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
			    }
			}); /* Si on met le curseur sur Joueur 2, on va avoir une partie du clavier en surbrillence */

			j1.getJ2().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	fenetreJeu.setContentPane(j2);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
			    }
			}); /* Si on met le curseur sur Joueur 1, on va avoir une partie du clavier en surbrillence */

			panOptions.getJ2().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	fenetreJeu.setContentPane(j2);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
			    }
			}); /* Si on met le curseur sur Joueur 1, on va avoir une partie du clavier en surbrillence */

			j2.getJ1().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	fenetreJeu.setContentPane(j1);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
			    }
			}); /* Si on met le curseur sur Joueur 2, on va avoir une partie du clavier en surbrillence */

			panLoseSolo.getRejouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panLoseSolo.getRejouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panLoseSolo.getRejouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Rejouer, celui-ci est grise */

			panLoseMulti.getRejouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panLoseMulti.getRejouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panLoseMulti.getRejouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Rejouer, celui-ci est grise */

			panWinSolo.getRejouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinSolo.getRejouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinSolo.getRejouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Rejouer, celui-ci est grise */

			panWinJ1.getRejouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinJ1.getRejouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinJ1.getRejouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Rejouer, celui-ci est grise */

			panWinJ2.getRejouer().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinJ2.getRejouer().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinJ2.getRejouer().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Rejouer, celui-ci est grise */

			panLoseSolo.getMenuP().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panLoseSolo.getMenuP().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panLoseSolo.getMenuP().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Menu, celui-ci est grise */

			panLoseMulti.getMenuP().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panLoseMulti.getMenuP().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panLoseMulti.getMenuP().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Menu, celui-ci est grise */

			panWinSolo.getMenuP().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinSolo.getMenuP().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinSolo.getMenuP().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Menu, celui-ci est grise */

			panWinJ1.getMenuP().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinJ1.getMenuP().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinJ1.getMenuP().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Menu, celui-ci est grise */

			panWinJ2.getMenuP().addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	panWinJ2.getMenuP().setForeground(Color.GRAY);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	panWinJ2.getMenuP().setForeground(Color.WHITE);
			    }
			}); /* Si on met le curseur sur Menu, celui-ci est grise */
	  } /* L'ensemble des MouseListener */

	  public void barreMenu(){

			JMenuBar barreMenu = new JMenuBar(); /* Barre de menu */

			JMenu jeu = new JMenu("Jeu");
			barreMenu.add(jeu);

			JMenu aides = new JMenu("Aides");
			barreMenu.add(aides);

			/* Creation des elements du menu */

			JMenuItem menuJeu = new JMenuItem("Menu du jeu");
			jeu.add(menuJeu);

			jeu.addSeparator();

			JMenuItem exitJeu = new JMenuItem("Quitter le jeu");
			jeu.add(exitJeu);

			JMenuItem controlesJeu = new JMenuItem("Controles du jeu");
			aides.add(controlesJeu);

			aides.addSeparator(); /* Separateur entre deux sous-menus */

			JMenuItem musicJeu = new JMenuItem("Musique play/pause");
			aides.add(musicJeu);

			/* Creation des items contenus dans les elements du menu */

			menuJeu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (musiqueMenu.getEstEnRoute()==true) musiqueMenu.musicArret();
					else if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
					else if (musiqueCharacters.getEstEnRoute()==true) musiqueCharacters.musicArret();
					else if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicArret();
					else if (musiqueLose.getEstEnRoute()==true) musiqueLose.musicArret();
					else if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicArret();
					try {
						musiqueMenu.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					doEnnemisMove=false;

					fenetreJeu.setContentPane(panMenu);
					fenetreJeu.revalidate();
					fenetreJeu.repaint();
				}
			}); /* ActionListener pour retourner sur le menu, ca va set le JPanel du menu, et arret et changement des musiques actuelles en musique du menu */

			exitJeu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appliEnRoute=false;
					fenetreJeu.dispose();
				}
			}); /* En cliquant sur quitter dans la barre menu, ca va quitter l'application */

			controlesJeu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (musiqueMenu.getEstEnRoute()==true) musiqueMenu.musicArret();
					else if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
					else if (musiqueCharacters.getEstEnRoute()==true) musiqueCharacters.musicArret();
					else if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicArret();
					else if (musiqueLose.getEstEnRoute()==true) musiqueLose.musicArret();
					else if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicArret();
					try {
						musiqueOptions.musicLancement();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					doEnnemisMove=false;

					fenetreJeu.setContentPane(panOptions);
					fenetreJeu.repaint();
					fenetreJeu.revalidate();
				}
			}); /* ActionListener pour aller dans le menu Options, ca va set le JPanel du menu Options, et arret et changement des musiques actuelles en musique du menu Options */

			musicJeu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (musiqueMenu.getEstEnRoute()==true) musiqueMenu.musicPause();
					else if (musiqueMenu.getEstEnRoute()==false && musiqueMenu.getDejaEteAllume()==1) musiqueMenu.musicPlay();
					else if (musiqueCharacters.getEstEnRoute()==true) musiqueCharacters.musicPause();
					else if (musiqueCharacters.getEstEnRoute()==false && musiqueCharacters.getDejaEteAllume()==1) musiqueCharacters.musicPlay();
					else if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicPause();
					else if (musiqueJeu.getEstEnRoute()==false && musiqueJeu.getDejaEteAllume()==1)  musiqueJeu.musicPlay();
					else if (musiqueOptions.getEstEnRoute()==true) musiqueOptions.musicPause();
					else if (musiqueOptions.getEstEnRoute()==false && musiqueOptions.getDejaEteAllume()==1) musiqueOptions.musicPlay();
					else if (musiqueLose.getEstEnRoute()==true) musiqueLose.musicPause();
					else if (musiqueLose.getEstEnRoute()==false && musiqueLose.getDejaEteAllume()==1) musiqueLose.musicPlay();
					else if (musiqueWin.getEstEnRoute()==true) musiqueWin.musicPause();
					else if (musiqueWin.getEstEnRoute()==false && musiqueWin.getDejaEteAllume()==1) musiqueWin.musicPlay();
				} /* ActionListener pour mettre en pose la musique ou bien la reprendre a l'endroit ou elle a ete mise en pose si elle a deja ete arretee */
			});


			fenetreJeu.setJMenuBar(barreMenu);
	  } /* Creation et application de la barre Menu */

	  public void panelEndLoseSolo() {
		  doEnnemisMove=false;
		  if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
		  try {
				musiqueLose.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		  fenetreJeu.setContentPane(panLoseSolo);
		  fenetreJeu.repaint();
		  fenetreJeu.revalidate();
	  } /* Met en premier plan de page la fenetre comme quoi on a perdu la partie solo, les ennemis ne bougent plus -> doEnnemisMove=false */

	  public void panelEndLoseMulti() {
		  multi=false;
		  if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
		  try {
				musiqueLose.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		  fenetreJeu.setContentPane(panLoseMulti);
		  fenetreJeu.repaint();
		  fenetreJeu.revalidate();
	  } /* Met en premier plan de page la fenetre comme quoi on a perdu la partie en multi */

	  public void panelEndWinSolo() {
		  doEnnemisMove=false;
		  if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
		  try {
				musiqueWin.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}

		  fenetreJeu.setContentPane(panWinSolo);
		  fenetreJeu.repaint();
		  fenetreJeu.revalidate();
	  } /* Met en premier plan de page la fenetre comme quoi on a gagne la partie solo, les ennemis ne bougent plus -> doEnnemisMove=false */

	  public void panelWinJ1() {
		  multi=false;
		  if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
		  try {
				musiqueWin.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		  fenetreJeu.setContentPane(panWinJ1);
		  fenetreJeu.repaint();
		  fenetreJeu.revalidate();
	  } /* Panel indiquand que le joueur 1 a gagne la partie */

	  public void panelWinJ2() {
		  multi=false;
		  if (musiqueJeu.getEstEnRoute()==true) musiqueJeu.musicArret();
		  try {
				musiqueWin.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		  fenetreJeu.setContentPane(panWinJ2);
		  fenetreJeu.repaint();
		  fenetreJeu.revalidate();
	  } /* Panel indiquand que le joueur 2 a gagne la partie */

	  public boolean getAppliEnRoute(){
	    return appliEnRoute;
	  } /* Retourne si l'application est en route ou non */

	  public boolean getMulti() {
		  return multi;
	  } /* Retourne si le mode multijoueur est active ou non */

	  public boolean getSolo() {
		  return solo;
	  } /* Retourne si le mode solo est active ou non */

	  public int getLargeurFen(){
	    return largeurFen;
	  } /* Retourne la largeur de la fenetre */

	  public int getHauteurFen(){
	    return hauteurFen;
	  } /* Retourne la hauteur de la fenetre */

	  public JFrame getFenetreJeu(){
	    return fenetreJeu;
	  } /* Retourne le JFrame principal de l'application */

	  public Personnage getPlayer1() {
		  return player1;
	  } /* Retourne le joueur 1 */

	  public Personnage getPlayer2() {
		  return player2;
	  } /* Retourne le joueur 2 */

	  public Ennemis getEnnemis1() {
		  return ennemis1;
	  } /* Retourne l'ennemi 1 */

	  public Ennemis getEnnemis2() {
		  return ennemis2;
	  } /* Ennemi 2 */

	  public Ennemis getEnnemis3() {
		  return ennemis3;
	  } /* Ennemi 3 */

	  public Panneau getPanJeu() {
		  return panJeu;
	  } /* Retourne le JPanel du jeu */

	  public boolean doEnnemisMove() {
		  return doEnnemisMove;
	  } /* Retourne si les ennemis sont en train de bouger ou non */

	  public Terrain getTerrain() {
		  return terrain;
	  } /* Retourne le terrain */

	  public void mortEnnemis1() {

		  ennemis1.getHitBoxEnnemis().setLocation(4000,4000);
		  ennemis1.setPx(2000);
		  ennemis1.setPy(2000);
		  mortE1=true;
		  try {
				death.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
	  }

	  public void mortEnnemis2() {

		  ennemis2.getHitBoxEnnemis().setLocation(4000,4000);
		  ennemis2.setPx(3000);
		  ennemis2.setPy(3000);
		  mortE2=true;
		  try {
				death.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
	  }

	  public void mortEnnemis3() {

		  ennemis3.getHitBoxEnnemis().setLocation(4000,4000);
		  ennemis3.setPx(4000);
		  ennemis3.setPy(4000);
		  mortE3=true;
		  try {
				death.musicLancement();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
	  }

	  /* Quand un ennemis meurt, il se deplace (l'image ainsi que la hitbox) en dehors de l'ecran */

	  public boolean getMortEnnemis1() {

		  return mortE1;
	  }

	  public boolean getMortEnnemis2() {

		  return mortE2;
	  }

	  public boolean getMortEnnemis3() {

		  return mortE3;
	  }
}