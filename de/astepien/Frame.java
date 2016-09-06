package de.astepien;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Frame extends JFrame {
	private static final long serialVersionUID = 448079204128908996L;

	public Frame() {
		setTitle("Reaktionszeit Testprogramm by Aleksander Stepien 9c");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(null);
		setSize(600, 600);
		setResizable(false);

		colorfield = new JTextField();
		colorfield.setText(null);
		colorfield.setEditable(false);
		colorfield.setBounds(50, 75, 500, 300);
		colorfield.setBackground(Color.WHITE);
		colorfield.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}// NO NEED

			@Override
			public void mousePressed(MouseEvent e) {
				if (Time.checkClicked()) {
					Time.cancel(System.currentTimeMillis() - Time.overrideTime);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}// NO NEED

			@Override
			public void mouseEntered(MouseEvent e) {
			}// NO NEED

			@Override
			public void mouseClicked(MouseEvent e) {
			}// NO NEED
		});

		start = new JButton("Starte den Test");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Time.hasStarted) {
					Time.finished = false;
					Time.testNumber = 0;
					Time.reset();
					Time.start();

				}
			}
		});
		start.setBounds(50, 400, 200, 30);

		reset = new JButton("Ergebinsse Resetten");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Time.reset();
			}
		});
		reset.setBounds(350, 400, 200, 30);

		info = new JLabel("Wenn du auf 'Starte den Test' klickst, wird in einem");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(0, 5, 600, 20);

		info2 = new JLabel("unbestimmtem Moment das Feld ROT.");
		info2.setHorizontalAlignment(SwingConstants.CENTER);
		info2.setBounds(0, 20, 600, 20);

		info3 = new JLabel("Klicke dann so schnell wie möglich auf das Feld!");
		info3.setHorizontalAlignment(SwingConstants.CENTER);
		info3.setBounds(0, 35, 600, 20);

		info4 = new JLabel("ms -> Millisekunden (1 Sekunde / 1000) | Raktionszeit basierend auf Wahrnehmungssumme");
		info4.setHorizontalAlignment(SwingConstants.CENTER);
		info4.setBounds(0, 50, 600, 20);

		credits = new JLabel("Programmiert von Aleksander Stepien");
		credits.setHorizontalAlignment(SwingConstants.CENTER);
		credits.setBounds(0, 550, 600, 20);

		countdown = new JLabel("3");
		countdown.setHorizontalAlignment(SwingConstants.CENTER);
		countdown.setBounds(0, 405, 600, 20);
		countdown.setVisible(false);

		time = new JLabel("Zeit:");
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setBounds(0, 425, 600, 20);

		time2 = new JLabel("Gesamt:");
		time2.setHorizontalAlignment(SwingConstants.CENTER);
		time2.setBounds(0, 450, 300, 20);

		time3 = new JLabel("Alle 5 Tests:");
		time3.setHorizontalAlignment(SwingConstants.CENTER);
		time3.setBounds(300, 450, 300, 20);

		scoreMain = new JCustomLabel();
		scoreMain.setHorizontalAlignment(SwingConstants.CENTER);
		scoreMain.setBounds(0, 475, 300, 20);

		score0 = new JCustomLabel();
		score0.setHorizontalAlignment(SwingConstants.CENTER);
		score0.setBounds(300, 475, 300, 20);

		score1 = new JCustomLabel();
		score1.setHorizontalAlignment(SwingConstants.CENTER);
		score1.setBounds(300, 490, 300, 20);

		score2 = new JCustomLabel();
		score2.setHorizontalAlignment(SwingConstants.CENTER);
		score2.setBounds(300, 505, 300, 20);

		score3 = new JCustomLabel();
		score3.setHorizontalAlignment(SwingConstants.CENTER);
		score3.setBounds(300, 520, 300, 20);

		score4 = new JCustomLabel();
		score4.setHorizontalAlignment(SwingConstants.CENTER);
		score4.setBounds(300, 535, 300, 20);

		add(colorfield);
		add(start);
		add(reset);
		add(info);
		add(info2);
		add(info3);
		add(info4);
		add(credits);
		add(time);
		add(time2);
		add(time3);
		add(scoreMain);
		add(score0);
		add(score1);
		add(score2);
		add(score3);
		add(score4);
		add(countdown);
	}

	public static class JCustomLabel extends JLabel {
		private static final long serialVersionUID = 2864038442941482398L;

		public JCustomLabel() {
			super();
			clearTime();
		}

		public void setTime(int time) {
			if (time <= 170)
				setForeground(new Color(85, 255, 0));// LIGHTGREEN
			if (time > 170 && time <= 220)
				setForeground(new Color(48, 145, 0));// GREEN
			if (time > 220 && time <= 400)
				setForeground(new Color(201, 188, 0));// YELLOW
			if (time > 400 && time <= 600)
				setForeground(new Color(201, 147, 0));// ORANGE
			if (time > 600 && time <= 850)
				setForeground(new Color(255, 93, 82));// LIGHT_RED
			if (time > 850)
				setForeground(new Color(194, 13, 0));// RED
			setText("" + time + " ms");
		}

		public void clearTime() {
			setForeground(Color.BLACK);
			setText("n.a.");
		}

		public void setOvertime() {
			setForeground(Color.RED);
			setText("> 1000 ms");
		}

	}

	public JTextField colorfield;
	public JButton start, reset;
	public JLabel info, info2, info3, info4, credits, time, time2, time3, countdown;
	public JCustomLabel scoreMain, score0, score1, score2, score3, score4;

}
