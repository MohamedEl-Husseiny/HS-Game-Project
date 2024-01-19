package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.heroes.Hero;

@SuppressWarnings({ "serial", "unused" })
public class SelectHero extends JFrame {
	private JPanel heroes;
	private JButton Hunter;
	private JButton Mage;
	private JButton Paladin;
	private JButton Priest;
	private JButton Warlock;

	public SelectHero() {
		this.setSize(1920, 1080);
		this.setVisible(true);
		this.setTitle("HearthStone");
		heroes = new JPanel();
		heroes.setPreferredSize(new Dimension(600, 300));
		heroes.setLayout(new GridLayout(1, 5));
		this.add(heroes, BorderLayout.CENTER);
		Hunter = new JButton("Hunter");
		Mage = new JButton("Mage");
		Paladin = new JButton("Paladin");
		Priest = new JButton("Priest");
		Warlock = new JButton("Warlock");

		heroes.add(Hunter);
		heroes.add(Mage);
		heroes.add(Paladin);
		heroes.add(Priest);
		heroes.add(Warlock);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.revalidate();
		this.repaint();

	}

	public JButton getHunter() {
		return Hunter;
	}

	public void setHunter(JButton hunter) {
		Hunter = hunter;
	}

	public JButton getMage() {
		return Mage;
	}

	public void setMage(JButton mage) {
		Mage = mage;
	}

	public JButton getPaladin() {
		return Paladin;
	}

	public void setPaladin(JButton paladin) {
		Paladin = paladin;
	}

	public JButton getPriest() {
		return Priest;
	}

	public void setPriest(JButton priest) {
		Priest = priest;
	}

	public JButton getWarlock() {
		return Warlock;
	}

	public void setWarlock(JButton warlock) {
		Warlock = warlock;
	}

	public static void main(String[] args) {
		SelectHero s = new SelectHero();
	}

}
