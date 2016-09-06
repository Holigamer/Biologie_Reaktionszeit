package de.astepien;

import java.awt.Color;
import java.util.Random;

public class Time {

	public static boolean hasStarted = false;
	public static boolean canClick = false;
	public static boolean finished = false;

	public static long startTime = 0;
	public static long overrideTime = 0;

	public static final int defaultDelay = 2500;
	public static Random random = new Random();

	public static int testNumber = 0;

	public static int test1Score = -1;
	public static int test2Score = -1;
	public static int test3Score = -1;
	public static int test4Score = -1;
	public static int test5Score = -1;

	public static Thread startThread;

	public static void start() {
		Main.frame.start.setEnabled(false);
		Main.frame.reset.setEnabled(false);
		if (!(startThread == null))
			startThread.interrupt();
		startThread = new Thread(new Runnable() {
			private long start = 0;
			private int mode = 3;

			@Override
			public void run() {
				start = System.currentTimeMillis() + 1000;
				Main.frame.countdown.setVisible(true);
				Main.frame.countdown.setText("" + mode);
				while (!finished) {
					if (start < System.currentTimeMillis()) {
						start = System.currentTimeMillis() + 1000;
						mode -= 1;
						Main.frame.countdown.setText("" + mode);
						if (mode <= 0) {
							Main.frame.countdown.setVisible(false);
							hasStarted = true;
							startTime = System.currentTimeMillis();

							int nextTime = random.nextInt(2000);
							if (nextTime < 500)
								nextTime = 500;

							overrideTime = System.currentTimeMillis() + defaultDelay + nextTime;

							Thread t = new Thread(new Runnable() {

								@Override
								public void run() {
									while (!finished) {
										if (System.currentTimeMillis() < overrideTime) {
											// Normal Time
											/* Do Nothing :) */
										} else {
											// Abgelaufen
											canClick = true;
											Main.frame.colorfield.setBackground(Color.RED);
											relayTooLongTime();
											break;
										}
									}
								}
							});

							t.start();
							return;
						}
					}
				}
			}
		});
		startThread.start();
	}

	public static boolean checkClicked() {
		return hasStarted && canClick;
	}

	public static void relayTooLongTime() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!finished) {
					if (System.currentTimeMillis() < overrideTime + 1000) {
					} else {
						cancel(1000);
					}
				}
			}
		});
		t.start();
	}

	public static void reset() {
		test1Score = -1;
		test2Score = -1;
		test3Score = -1;
		test4Score = -1;
		test5Score = -1;
		update(0);
		update(1);
		update(2);
		update(3);
		update(4);
		Main.frame.scoreMain.clearTime();
	}

	public static void cancel(long i) {
		if (checkClicked()) {
			canClick = false;
			hasStarted = false;
			startTime = 0;
			overrideTime = 0;
			Main.frame.colorfield.setBackground(Color.WHITE);
			if (i > 1000)
				i = 1001;
			switch (testNumber) {
			case 0:
				test1Score = (int) i;
				update(0);
				start();
				break;
			case 1:
				test2Score = (int) i;
				update(1);
				start();
				break;
			case 2:
				test3Score = (int) i;
				update(2);
				start();
				break;
			case 3:
				test4Score = (int) i;
				update(3);
				start();
				break;
			case 4:
				test5Score = (int) i;
				update(4);
				finish();
				break;
			default:
				finish();
				return;
			}
			testNumber += 1;
		}
	}

	public static void update(int score) {
		switch (score) {
		case 0:
			if (test1Score >= 1000)
				Main.frame.score0.setOvertime();
			else if (test1Score == -1)
				Main.frame.score0.clearTime();
			else
				Main.frame.score0.setTime(test1Score);
			break;
		case 1:
			if (test2Score >= 1000)
				Main.frame.score1.setOvertime();
			else if (test2Score == -1)
				Main.frame.score1.clearTime();
			else
				Main.frame.score1.setTime(test2Score);
			break;
		case 2:
			if (test3Score >= 1000)
				Main.frame.score2.setOvertime();
			else if (test3Score == -1)
				Main.frame.score2.clearTime();
			else
				Main.frame.score2.setTime(test3Score);
			break;
		case 3:
			if (test4Score >= 1000)
				Main.frame.score3.setOvertime();
			else if (test4Score == -1)
				Main.frame.score3.clearTime();
			else
				Main.frame.score3.setTime(test4Score);
			break;
		case 4:
			if (test5Score >= 1000)
				Main.frame.score4.setOvertime();
			else if (test5Score == -1)
				Main.frame.score4.clearTime();
			else
				Main.frame.score4.setTime(test5Score);
			break;
		default:
			break;
		}
		return;

	}

	public static void finish() {
		testNumber = 0;
		finished = true;
		Main.frame.start.setEnabled(true);
		Main.frame.reset.setEnabled(true);
		Main.frame.countdown.setEnabled(false);
		int complete = (test1Score + test2Score + test3Score + test4Score + test5Score) / 5;
		if (complete >= 1000)
			Main.frame.scoreMain.setOvertime();
		else
			Main.frame.scoreMain.setTime(complete);
	}

}
