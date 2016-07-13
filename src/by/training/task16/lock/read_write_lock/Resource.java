package by.training.task16.lock.read_write_lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Resource {
	private static final int SLEEP_TIME = 1000;

	private static final Logger logger = LogManager.getRootLogger();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * �����, �������� ������ ������ � ����� � �������� ����� lock() ��
	 * readLock'�, ��� ����� ������� � ��������� ���������� ������. ���� � ���
	 * ����� ������������ ������ ������ (�.�. ����� ���������� �� writeLock'�),
	 * �� ����� ����������� �� ��������� �������� ������. ������ ���� ������
	 * ������ ��� �� ���������� ������ � ������� lock() �� readLock'� � �� ����
	 * ����� �� �������� ������, �� ����� �� ����� ���������� � ���������
	 * ����������.
	 */
	public void readOperation() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to read");

		readWriteLock.readLock().lock();
		try {
			logger.debug("Thread " + Thread.currentThread().getName() + " start to read");
			Thread.sleep(SLEEP_TIME);
			logger.debug("Thread " + Thread.currentThread().getName() + " end to read");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			readWriteLock.readLock().unlock();
		}
	}

	/**
	 * �����, ������� ����� ��������� ������ � ������ ��� ����� � ����� ��������
	 * lock() �� writeLock'�, ��� ����� �������� ������ � ������� ��� ����
	 * �������� ������� � �������, ������� ��� �� ����� �������� ������.
	 */
	public void writeOperation() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to write");

		readWriteLock.writeLock().lock();
		try {
			logger.debug("Thread " + Thread.currentThread().getName() + " start to write");
			Thread.sleep(SLEEP_TIME);
			logger.debug("Thread " + Thread.currentThread().getName() + " end to write");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			readWriteLock.writeLock().unlock();
		}
	}
}
