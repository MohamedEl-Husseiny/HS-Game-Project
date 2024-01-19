package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import exceptions.FullHandException;
import view.SelectHero;

public class controllerSelectHero implements ActionListener {
	private SelectHero View;
	private Hero A;
	private Hero B;
	private boolean flag;

	public controllerSelectHero() {
		View = new SelectHero();
		View.setVisible(true);
		View.getHunter().addActionListener(this);
		View.getMage().addActionListener(this);
		View.getPaladin().addActionListener(this);
		View.getPriest().addActionListener(this);
		View.getWarlock().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		if (b.getActionCommand().equals("Hunter") && flag == false) {
			try {
				A = new Hunter();

			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
		} else if (b.getActionCommand().equals("Mage") && flag == false) {
			try {
				A = new Mage();

			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
		} else if (b.getActionCommand().equals("Paladin") && flag == false) {
			try {
				A = new Paladin();

			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
		} else if (b.getActionCommand().equals("Priest") && flag == false) {
			try {
				A = new Priest();

			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;

		} else if (b.getActionCommand().equals("Warlock") && flag == false) {
			try {
				A = new Warlock();

			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
		} else if (b.getActionCommand().equals("Hunter") && flag == true) {
			try {
				B = new Hunter();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (b.getActionCommand().equals("Mage") && flag == true) {
			try {
				B = new Mage();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (b.getActionCommand().equals("Paladin") && flag == true) {
			try {
				B = new Paladin();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (b.getActionCommand().equals("Priest") && flag == true) {
			try {
				B = new Priest();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (b.getActionCommand().equals("Warlock") && flag == true) {
			try {
				B = new Warlock();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (A != null && B != null) {
			View.setVisible(false);
			try {
				new Controller(A, B);

			} catch (FullHandException | CloneNotSupportedException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		controllerSelectHero V = new controllerSelectHero();
	}

}
