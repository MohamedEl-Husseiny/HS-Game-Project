package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.heroes.*;

@SuppressWarnings({ "serial", "unused" })
public class BattleField extends JFrame {
	Hero A;
	Hero B;
	JPanel oppHandPlayer;
	JPanel Field;
	JPanel currHandPlayer;
	JPanel oppHand;
	JPanel oppHero;
	JPanel oppField;
	JPanel currField;
	JPanel currHero;
	JPanel currHand;
	JPanel endTurn;

	private JButton HeroA;
	private JButton HeroB;

	// JTextArea heroInfo;

	public BattleField() {
		this.setBounds(0, 0, 1920, 1080);
		this.setVisible(true);
		oppHandPlayer = new JPanel();
		Field = new JPanel();
		currHandPlayer = new JPanel();
		oppHand = new JPanel();
		oppHero = new JPanel();
		oppField = new JPanel();
		currField = new JPanel();
		currHero = new JPanel();
		currHand = new JPanel();
		endTurn = new JPanel();
		HeroA = new JButton("HeroA");
		HeroB = new JButton("HeroB");
		HeroA.setLayout(new BorderLayout());
		HeroB.setLayout(new BorderLayout());
		HeroA.setPreferredSize(new Dimension(100, 100));
		currHero.add(HeroA, BorderLayout.CENTER);
		HeroB.setPreferredSize(new Dimension(100, 100));
		oppHero.add(HeroB, BorderLayout.WEST);
		oppHandPlayer.setLayout(new BorderLayout());
		Field.setLayout(new BorderLayout());
		currHandPlayer.setLayout(new BorderLayout());

		oppHandPlayer.add(oppHand, BorderLayout.NORTH);
		oppHandPlayer.add(oppHero, BorderLayout.SOUTH);
		Field.add(oppField, BorderLayout.NORTH);
		Field.add(currField, BorderLayout.SOUTH);
		currHandPlayer.add(currHero, BorderLayout.NORTH);
		currHandPlayer.add(currHand, BorderLayout.SOUTH);

		this.add(oppHandPlayer, BorderLayout.NORTH);
		this.add(Field, BorderLayout.CENTER);
		Field.add(endTurn, BorderLayout.EAST);
		// this.add(heroInfo, BorderLayout.SOUTH);
		this.add(currHandPlayer, BorderLayout.SOUTH);

		oppHand.setLayout(new GridLayout(1, 10));
		oppField.setLayout(new GridLayout(1, 7));
		currField.setLayout(new GridLayout(1, 7));
		currHand.setLayout(new GridLayout(1, 10));

		oppField.setPreferredSize(new Dimension(100, 100));
		currField.setPreferredSize(new Dimension(100, 100));
	}

	public Hero getA() {
		return A;
	}

	public void setA(Hero a) {
		A = a;
	}

	public Hero getB() {
		return B;
	}

	public JPanel getOppHero() {
		return oppHero;
	}

	public void setOppHero(JPanel oppHero) {
		this.oppHero = oppHero;
	}

	public void setB(Hero b) {
		B = b;
	}

	public JPanel getOppHandPayer() {
		return oppHandPlayer;
	}

	public void setOppHandPayer(JPanel oppHandPayer) {
		this.oppHandPlayer = oppHandPayer;
	}

	public JPanel getField() {
		return Field;
	}

	public void setField(JPanel field) {
		Field = field;
	}

	public JPanel getCurrHandPlayer() {
		return currHandPlayer;
	}

	public void setCurrHandPlayer(JPanel currHandPlayer) {
		this.currHandPlayer = currHandPlayer;
	}

	public JPanel getOppHand() {
		return oppHand;
	}

	public void setOppHand(JPanel oppHand) {
		this.oppHand = oppHand;
	}

	public JPanel getOppField() {
		return oppField;
	}

	public void setOppField(JPanel oppField) {
		this.oppField = oppField;
	}

	public JPanel getCurrField() {
		return currField;
	}

	public void setCurrField(JPanel currField) {
		this.currField = currField;
	}

	public JPanel getCurrHero() {
		return currHero;
	}

	public void setCurrHero(JPanel currHero) {
		this.currHero = currHero;
	}

	public JPanel getCurrHand() {
		return currHand;
	}

	public void setCurrHand(JPanel currHand) {
		this.currHand = currHand;
	}

	public JPanel getEndTurn() {
		return endTurn;
	}

	public void setEndTurn(JPanel endTurn) {
		this.endTurn = endTurn;
	}

	public JButton getHeroA() {
		return HeroA;
	}

	public void setHeroA(JButton heroA) {
		HeroA = heroA;
	}

	public JButton getHeroB() {
		return HeroB;
	}

	public void setHeroB(JButton heroB) {
		HeroB = heroB;
	}

}
