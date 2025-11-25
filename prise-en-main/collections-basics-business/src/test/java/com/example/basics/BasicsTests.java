package com.example.basics;

import com.example.basics.demos.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class BasicsTests {

    @Test void shoppingCart_allowsDuplicates_andOrder() {
        var cart = new ShoppingCart();
        cart.add("A"); cart.add("B"); cart.add("A");
        assertEquals(2, cart.count("A"));
        assertEquals(List.of("A","B","A"), cart.list());
    }

    @Test void newsletter_deduplicates() {
        var n = new NewsletterRecipients();
        n.add("alice@example.com");
        n.add("ALICE@example.com");
        assertTrue(n.contains("alice@example.com"));
        assertEquals(1, n.snapshot().size());
    }

    @Test void attendance_presence() {
        var a = new Attendance();
        a.checkIn("Bob"); a.checkOut("Bob");
        assertFalse(a.isPresent("Bob"));
    }

    @Test void backNavigation_lifo() {
        var nav = new BackNavigation();
        nav.visit("A"); nav.visit("B");
        assertEquals("B", nav.back().orElse(""));
        assertEquals(1, nav.size());
    }

    @Test void printQueue_fifo() {
        var q = new PrintQueue();
        q.submit("job1"); q.submit("job2");
        assertEquals("job1", q.nextJob().orElse(""));
        assertEquals(1, q.size());
    }

    @Test void recentlyViewed_boundedDeque() {
        var rv = new RecentlyViewed(2);
        rv.see("X"); rv.see("Y"); rv.see("Z");
        assertEquals(List.of("Z","Y"), rv.list());
    }

    @Test void deliveryRoute_dequeEnds() {
        var r = new DeliveryRoute();
        r.addRegularStop("S1"); r.addUrgentStop("U1");
        assertEquals(List.of("U1","S1"), r.snapshot());
        assertEquals("U1", r.nextStop().orElse(""));
    }
}
