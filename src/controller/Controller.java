package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.ReverbType;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.omg.CORBA.Current;

import model.cards.minions.*;
import model.cards.spells.*;
import model.cards.Card;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Priest;
import view.BattleField;
import view.SelectHero;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

@SuppressWarnings("unused")
public class Controller implements ActionListener, GameListener {
	private BattleField fieldView;
	// private SelectHero SelectView;

	private Game model;
	private ArrayList<JButton> arrayButtonsHand;
	private ArrayList<JButton> arrayButtCurrField;
	private ArrayList<JButton> arrayButtOppField;
	private Minion Attacker;
	private Minion Target;
	private Boolean myhand = false;
	private Boolean myfield = false;
	private boolean oppfield = false;
	private LeechingSpell s = null;
	private MinionTargetSpell mt = null;
	private HeroTargetSpell ht = null;
	private Boolean flag = false;
	private Boolean flag2 = false;
	private Boolean flag3 = false;
	private Minion AT;
	private HeroTargetSpell hs = null;
	private MinionTargetSpell ms = null;

	public Controller(Hero A, Hero B) throws FullHandException,
			CloneNotSupportedException, IOException {
		arrayButtonsHand = new ArrayList<JButton>();
		arrayButtCurrField = new ArrayList<JButton>();
		arrayButtOppField = new ArrayList<JButton>();
		model = new Game(A, B);
		fieldView = new BattleField();
		fieldView.getHeroA().addActionListener(this);
		fieldView.getHeroB().addActionListener(this);
		JTextArea HeroA = new JTextArea(model.getCurrentHero().toString());
		HeroA.setEditable(false);
		HeroA.setPreferredSize(new Dimension(300, 110));
		fieldView.getCurrHero().add(HeroA, BorderLayout.WEST);
		JTextArea HeroB = new JTextArea(model.getOpponent().toString());
		HeroB.setEditable(false);
		HeroB.setPreferredSize(new Dimension(300, 110));
		fieldView.getOppHero().add(HeroB, BorderLayout.WEST);
		JButton useHeroPower = new JButton("HeroPower");
		useHeroPower.setLayout(new BorderLayout());
		useHeroPower.setPreferredSize(new Dimension(100, 100));
		useHeroPower.addActionListener(this);
		fieldView.getCurrHandPlayer().add(useHeroPower, BorderLayout.EAST);
		JButton endTurn = new JButton("EndTurn");
		endTurn.addActionListener(this);
		endTurn.setPreferredSize(new Dimension(200, 200));
		fieldView.getEndTurn().add(endTurn);
		fieldView.setVisible(true);
		fieldView.revalidate();
		fieldView.repaint();
		for (int i = 0; i < model.getOpponent().getHand().size(); i++) {
			JButton b = new JButton();
			TextArea a1 = new TextArea();
			b.add(a1);
			b.addActionListener(this);

			a1.setEditable(false);
			a1.setPreferredSize(new Dimension(100, 100));
			fieldView.getOppHand().add(a1);
		}

		for (int i = 0; i < model.getOpponent().getField().size(); i++) {
			JButton j1 = new JButton();
			arrayButtOppField.add(j1);
			JTextArea t1 = new JTextArea();
			j1.add(t1);
			j1.addActionListener(this);
			t1.append(model.getOpponent().getField().get(i).toString());
			fieldView.getOppField().add(j1);
		}

		for (int i = 0; i < model.getCurrentHero().getHand().size(); i++) {
			JButton j1 = new JButton();
			arrayButtonsHand.add(j1);
			JTextArea t1 = new JTextArea();
			j1.add(t1);
			t1.append(model.getCurrentHero().getHand().get(i).toString());
			t1.setEditable(false);
			j1.addActionListener(this);
			t1.setPreferredSize(new Dimension(100, 100));
			fieldView.getCurrHand().add(j1);
		}
		for (int i = 0; i < model.getCurrentHero().getField().size(); i++) {
			JButton j1 = new JButton();
			arrayButtCurrField.add(j1);
			JTextArea t1 = new JTextArea(model.getCurrentHero().getField()
					.get(i).toString());
			j1.add(t1);
			t1.append(model.getCurrentHero().getField().get(i).toString());
			t1.setEditable(false);
			j1.addActionListener(this);
			t1.setPreferredSize(new Dimension(100, 100));
			fieldView.getCurrField().add(j1);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();

		// if(myfield==true){

		for (int i = 0; i < model.getCurrentHero().getField().size(); i++) {
			if (b == arrayButtCurrField.get(i)) {
				Attacker = model.getCurrentHero().getField().get(i);
				break;

			}
		}
		// }

		if (b.getActionCommand().equals("EndTurn")) {
			try {
				model.endTurn();
				helper();
				fieldView.revalidate();
				fieldView.repaint();
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} else if (b.getActionCommand().equals("HeroPower")) {
			if (model.getCurrentHero() instanceof Priest
					|| model.getCurrentHero() instanceof Mage) {
				flag = true;
			}

			else {
				try {
					model.getCurrentHero().useHeroPower();
				} catch (NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				} catch (HeroPowerAlreadyUsedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				} catch (NotYourTurnException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				} catch (FullHandException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				} catch (FullFieldException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
					;
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		} else {
			for (int i = 0; i < model.getCurrentHero().getHand().size(); i++) {

				if (b == arrayButtonsHand.get(i)) {
					myhand = true;
					break;
				}
			}
		}
		if (flag == true) {
			for (int i = 0; i < model.getCurrentHero().getField().size(); i++) {
				if (b == arrayButtCurrField.get(i)) {
					AT = model.getCurrentHero().getField().get(i);
					break;
				}
			}
			for (int i = 0; i < model.getOpponent().getField().size(); i++) {
				if (b == arrayButtOppField.get(i)) {
					AT = model.getOpponent().getField().get(i);
					break;
				}
			}
		}
		for (int i = 0; i < model.getOpponent().getField().size(); i++) {
			if (b == arrayButtOppField.get(i)) {
				Target = model.getOpponent().getField().get(i);
				break;
			}
		}
		if (b.getActionCommand().equals("HeroB")) {
			flag2 = true;

		}
		if (b.getActionCommand().equals("HeroA")) {
			flag3 = true;

		}
		if (flag == true && flag3 == true
				&& model.getCurrentHero() instanceof Priest) {
			try {
				((Priest) model.getCurrentHero()).useHeroPower(model
						.getCurrentHero());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			flag3 = false;
		}
		if (flag == true && flag2 == true
				&& model.getCurrentHero() instanceof Priest) {
			try {
				((Priest) model.getCurrentHero()).useHeroPower(model
						.getOpponent());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			flag2 = false;
		}
		if (AT != null && flag == true
				&& model.getCurrentHero() instanceof Priest) {
			try {
				((Priest) model.getCurrentHero()).useHeroPower((Minion) AT);
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			AT = null;

		}
		if (flag == true && flag3 == true
				&& model.getCurrentHero() instanceof Mage) {
			try {
				((Mage) model.getCurrentHero()).useHeroPower(model
						.getCurrentHero());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			flag3 = false;
		}
		if (flag == true && flag2 == true
				&& model.getCurrentHero() instanceof Mage) {
			try {
				((Mage) model.getCurrentHero()).useHeroPower(model
						.getOpponent());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			flag2 = false;
		}
		if (AT != null && flag == true
				&& model.getCurrentHero() instanceof Mage) {
			Hero m = (Mage) model.getCurrentHero();
			try {
				((Mage) m).useHeroPower((Minion) AT);
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag = false;
			AT = null;
		}
		if (s != null && Target != null) {
			try {
				model.getCurrentHero().castSpell(s, Target);
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			s = null;
			Target = null;
		}
		if (mt != null && Target != null) {
			try {
				model.getCurrentHero()
						.castSpell((MinionTargetSpell) mt, Target);
			} catch (NotYourTurnException | NotEnoughManaException
					| InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			mt = null;
			Target = null;
		}

		if (myhand == true) {
			int index = arrayButtonsHand.indexOf(b);

			if (model.getCurrentHero().getHand().get(index) instanceof Minion) {
				try {
					model.getCurrentHero().playMinion(
							(Minion) model.getCurrentHero().getHand()
									.get(index));
					helper();
					fieldView.revalidate();
					fieldView.repaint();

				} catch (NotYourTurnException | NotEnoughManaException
						| FullFieldException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				}
				myhand = false;
			}

			else if (model.getCurrentHero().getHand().get(index) instanceof Spell) {
				if (model.getCurrentHero().getHand().get(index) instanceof AOESpell) {
					try {
						model.getCurrentHero().castSpell(
								(AOESpell) model.getCurrentHero().getHand()
										.get(index),
								model.getOpponent().getField());
					} catch (NotYourTurnException | NotEnoughManaException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.toString());
					}
				} else if (model.getCurrentHero().getHand().get(index) instanceof FieldSpell) {
					try {
						model.getCurrentHero().castSpell(
								(FieldSpell) model.getCurrentHero().getHand()
										.get(index));
					} catch (NotYourTurnException | NotEnoughManaException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.toString());
					}
				} else if (model.getCurrentHero().getHand().get(index) instanceof LeechingSpell) {
					s = (LeechingSpell) model.getCurrentHero().getHand()
							.get(index);

				} else if (model.getCurrentHero().getHand().get(index) instanceof MinionTargetSpell
						&& model.getCurrentHero().getHand().get(index) instanceof HeroTargetSpell) {
					hs = (HeroTargetSpell) model.getCurrentHero().getHand()
							.get(index);
					ms = (MinionTargetSpell) model.getCurrentHero().getHand()
							.get(index);
				} else if (model.getCurrentHero().getHand().get(index) instanceof MinionTargetSpell) {
					mt = (MinionTargetSpell) model.getCurrentHero().getHand()
							.get(index);
				} else if (model.getCurrentHero().getHand().get(index) instanceof HeroTargetSpell) {
					try {
						model.getCurrentHero().castSpell(
								(HeroTargetSpell) model.getCurrentHero()
										.getHand().get(index),
								model.getOpponent());
					} catch (NotYourTurnException | NotEnoughManaException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.toString());
					}
				}
			}

		}
		if (Target != null && ms != null) {

			try {
				model.getCurrentHero()
						.castSpell((MinionTargetSpell) ms, Target);
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}

			Target = null;
			ms = null;
		}

		if (flag2 == true && hs != null) {
			try {
				model.getCurrentHero().castSpell((HeroTargetSpell) hs,
						model.getOpponent());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
			flag2 = false;
			hs = null;
		}

		for (int i = 0; i < model.getCurrentHero().getField().size(); i++) {

			if (b == arrayButtCurrField.get(i)) {
				myfield = true;
				break;
			}
		}
		for (int i = 0; i < model.getOpponent().getField().size(); i++) {

			if (b == arrayButtOppField.get(i)) {
				oppfield = true;
				break;
			}
		}
		if (Attacker != null && flag2 == true) {
			try {
				model.getCurrentHero().attackWithMinion(Attacker,
						model.getOpponent());
			} catch (CannotAttackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TauntBypassException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotSummonedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Attacker = null;
		}

		if (Attacker != null && Target != null) {

			try {
				model.getCurrentHero().attackWithMinion(Attacker, Target);
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}

			fieldView.revalidate();
			fieldView.repaint();
			Attacker = null;
			Target = null;
			myfield = false;
			oppfield = false;

		}
		if (model.getCurrentHero().getCurrentHP() == 0
				|| model.getOpponent().getCurrentHP() == 0)
			onGameOver();
		flag3 = false;
		flag2 = false;
		myhand = false;
		helper();
		fieldView.revalidate();
		fieldView.repaint();
	}

	public void helper() {
		fieldView.getCurrHero().removeAll();
		fieldView.getOppHero().removeAll();
		fieldView.getOppHand().removeAll();
		fieldView.getCurrHand().removeAll();
		fieldView.getCurrField().removeAll();
		fieldView.getOppField().removeAll();
		arrayButtonsHand.clear();
		arrayButtCurrField.clear();
		arrayButtOppField.clear();
		// JButton Hero1 = new JButton("HeroA");
		// JButton Hero2 = new JButton("HeroB");
		fieldView.getHeroA().setLayout(new BorderLayout());
		fieldView.getHeroB().setLayout(new BorderLayout());
		fieldView.getHeroA().addActionListener(this);
		fieldView.getHeroB().addActionListener(this);
		fieldView.getHeroA().setPreferredSize(new Dimension(100, 100));
		fieldView.getCurrHero().add(fieldView.getHeroA(), BorderLayout.CENTER);
		fieldView.getCurrHandPlayer().add(fieldView.getCurrHero(),
				BorderLayout.CENTER);
		fieldView.getCurrHero().add(fieldView.getHeroA(), BorderLayout.CENTER);
		fieldView.getOppHandPayer().add(fieldView.getOppHero(),
				BorderLayout.CENTER);
		fieldView.getHeroB().setPreferredSize(new Dimension(100, 100));
		fieldView.getOppHero().add(fieldView.getHeroB(), BorderLayout.WEST);
		JTextArea HeroA = new JTextArea(model.getCurrentHero().toString());
		HeroA.setEditable(false);
		HeroA.setPreferredSize(new Dimension(300, 100));
		fieldView.getCurrHero().add(HeroA, BorderLayout.WEST);
		JTextArea HeroB = new JTextArea(model.getOpponent().toString());
		HeroB.setEditable(false);
		HeroB.setPreferredSize(new Dimension(300, 100));
		fieldView.getOppHero().add(HeroB, BorderLayout.WEST);
		for (int i = 0; i < model.getOpponent().getHand().size(); i++) {
			TextArea a1 = new TextArea();
			JButton b = new JButton();
			b.add(a1);
			b.addActionListener(this);
			a1.setEditable(false);
			a1.setPreferredSize(new Dimension(100, 100));
			fieldView.getOppHand().add(b);
		}
		for (int i = 0; i < model.getCurrentHero().getHand().size(); i++) {
			JButton j1 = new JButton();
			arrayButtonsHand.add(j1);
			JTextArea t1 = new JTextArea();
			j1.add(t1);
			t1.append(model.getCurrentHero().getHand().get(i).toString());
			t1.setEditable(false);
			j1.addActionListener(this);
			t1.setPreferredSize(new Dimension(100, 100));
			fieldView.getCurrHand().add(j1);
		}
		for (int i = 0; i < model.getCurrentHero().getField().size(); i++) {
			JButton j1 = new JButton();
			arrayButtCurrField.add(j1);
			JTextArea t1 = new JTextArea(model.getCurrentHero().getField()
					.get(i).toString());
			j1.add(t1);
			t1.append(model.getCurrentHero().getField().get(i).toString());
			t1.setEditable(false);
			j1.addActionListener(this);
			t1.setPreferredSize(new Dimension(100, 100));
			fieldView.getCurrField().add(j1);
		}
		for (int i = 0; i < model.getOpponent().getField().size(); i++) {
			JButton j1 = new JButton();
			arrayButtOppField.add(j1);
			JTextArea t1 = new JTextArea(model.getOpponent().getField().get(i)
					.toString());
			j1.add(t1);
			t1.append(model.getOpponent().getField().get(i).toString());
			t1.setEditable(false);
			j1.addActionListener(this);
			t1.setPreferredSize(new Dimension(100, 100));
			fieldView.getOppField().add(j1);
		}
	}

	@Override
	public void onGameOver() {
		if (model.getCurrentHero().getCurrentHP() == 0
				|| model.getOpponent().getCurrentHP() == 0)
			if (model.getCurrentHero().getCurrentHP() != 0)
				JOptionPane.showMessageDialog(null, model.getCurrentHero()
						.getName() + " Wins!");
			else if (model.getOpponent().getCurrentHP() != 0)
				JOptionPane.showMessageDialog(null, model.getOpponent()
						.getName() + " Wins!");
		fieldView.setVisible(false);

	}
}
