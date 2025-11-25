package com.example.basics.demos;

import java.util.*;

// File d'impressions (FIFO): premier document = premier servi
public class PrintQueue {
	private final ArrayDeque<String> queue = new ArrayDeque<>(); // FIFO

	public void submit(String job) {
		queue.offerLast(job);
	}

	public Optional<String> nextJob() {
		return queue.isEmpty() ? Optional.empty() : Optional.of(queue.pollFirst());
	}

	public int size() {
		return queue.size();
	}

	public static void demo() {
		var pq = new PrintQueue();
		pq.submit("job1");
		pq.submit("job2");
		pq.submit("job3");
		System.out.println("[PrintQueue] next=" + pq.nextJob().orElse("-") + ", remaining=" + pq.size());
	}
}
