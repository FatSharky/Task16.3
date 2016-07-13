package by.training.task16.synchronizer.phaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final int COUNT = 5;
	private static final Logger logger = LogManager.getRootLogger();
	/**
	 * ��������� ������ Phaser �� ��������� ������ 5, ��� �������� ���-��
	 * ���������������� ������� � ������������ ������ (�����).
	 */
	private Phaser phaser = new Phaser(COUNT);

	/**
	 * ����� ���������� ����� �������� ����� �������� �����
	 * arriveAndAwaitAdvance(), ������� ��������� ����� �� ������� ������ �����
	 * �� ������ ����� ���������� ��������. ����� ���� ��� ������ ����������
	 * ���� ������.
	 * 
	 * ����� ���������� ����� �������� ����� �������� ����� arrive(), ��� �����
	 * ������� � ���������� ������������ ����, ��� ���� ����� �� �����
	 * ���������� � ��������� ���� ������.
	 * 
	 * @throws InterruptedException
	 */
	public void useResource() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
		Thread.sleep(SLEEP_TIME);

		logger.debug("Thread " + Thread.currentThread().getName() + " come to first point and wait");
		phaser.arriveAndAwaitAdvance();

		logger.debug("Thread " + Thread.currentThread().getName() + " continue to use resource");
		Thread.sleep(SLEEP_TIME);

		logger.debug("Thread " + Thread.currentThread().getName() + " come to second point and continue");
		phaser.arrive();

		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
	}
}
