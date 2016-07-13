package by.training.task16.lock.condition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final Logger logger = LogManager.getRootLogger();

	private Lock lock = new ReentrantLock();

	private Condition operation1Made = lock.newCondition();
	private Condition operation2Made = lock.newCondition();

	private boolean operation1MadeFlag = false;

	/**
	 * ��� ����� � ����� ����� �������� ����� lock(), ��� ����� �������
	 * ���������� �� ������, ���� �� ������ ��� ������������ ������ �������, ��
	 * ������ ����� ����� ���������� �� ������� ������������ �������. try ����
	 * �������� operation1 ��� �����������, ����� ����� ������ ������ await()
	 * ����������� �� ��� ��� ���� �� ���������� operation2 � �� �������
	 * Condition �� ��������� ����� signal(). ����� ���������� operation1 �����
	 * ������ �������� �������� ���������� �� true.
	 */
	public void operation1() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation1");

		lock.lock();
		try {

			while (operation1MadeFlag) {
				operation2Made.await();
			}
			logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation1");
			Thread.sleep(SLEEP_TIME);

			operation1MadeFlag = true;
			logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation1");

			operation1Made.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			lock.unlock();
		}
	}

	/**
	 * �� �� �����
	 */
	public void operation2() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation2");

		lock.lock();
		try {

			while (!operation1MadeFlag) {
				operation1Made.await();
			}
			logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation2");
			Thread.sleep(SLEEP_TIME);

			operation1MadeFlag = false;
			logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation2");

			operation2Made.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
