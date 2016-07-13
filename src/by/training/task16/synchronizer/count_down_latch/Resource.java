package by.training.task16.synchronizer.count_down_latch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final int COUNT = 5;
	private static final Logger logger = LogManager.getRootLogger();
	/**
	 * ��������� ������ ��� ���������� ������� ������� �� ��� ���, ���� ��
	 * ���������� ������������ ���������� ��������, ������������� � ������
	 * �������.
	 */
	private CountDownLatch countDownLatch = new CountDownLatch(COUNT);

	/**
	 * ����� ����� ���������� �������� operation1 �������� ����� countDown(),
	 * ��� ����� ������� ������� �� �������.
	 * 
	 * ����� ����� ����� �������� ����� await(), ��� �������� � �������� �������
	 * ��������� �������� countDownLatch'� (��� ����� �������� ���������� �����
	 * 5-� �������� �������� operation1), ����� ����� ��� ������ ���������� ����
	 * ������.
	 * 
	 * @throws InterruptedException
	 */
	public void operation1() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start the operation1");
		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end the operation1");

		countDownLatch.countDown();

		countDownLatch.await();
	}

	public void operation2() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start the operation2");
		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end the operation2");
	}
}
