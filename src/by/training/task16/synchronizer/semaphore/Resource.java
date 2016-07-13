package by.training.task16.synchronizer.semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final int COUNT = 3;
	private static final Logger logger = LogManager.getRootLogger();
	/**
	 * �������� ������ ������������ ���-�� �������. ������ � ������ �������
	 * ����������� � ������� ��������. ���� �� ������ ����, �� ������
	 * �����������, � �������� �������� �����������.
	 */
	private Semaphore semaphore = new Semaphore(COUNT);

	/**
	 * ����� ������ acquire() ���������� ���������� �������� �������� � ����
	 * ������ � ����������� ������, ������ ���� �������� �������� ����� 0,
	 * ������ �������� ����� �������� ������� � ������� ��������� (������� �����
	 * �����������, ���� ������ ����� �� ��������� ������).
	 * 
	 * @throws InterruptedException
	 */
	public void useResource() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");

		semaphore.acquire();
		logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");

		semaphore.release();
	}
}
