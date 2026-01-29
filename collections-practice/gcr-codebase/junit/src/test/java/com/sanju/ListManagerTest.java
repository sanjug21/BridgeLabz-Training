package com.sanju;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListManagerTest {

    private ListManager listManager;
    private List<Integer> list;

    @Before
    public void setUp() {
        listManager = new ListManager();
        list = new ArrayList<>();
    }

    @Test
    public void testAddElement() {
        listManager.addElement(list, 10);
        listManager.addElement(list, 20);
        assertEquals(2, list.size());
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
    }

    @Test
    public void testRemoveElement() {
        list.add(5);
        list.add(15);
        list.add(25);

        listManager.removeElement(list, 15);

        assertEquals(2, list.size());
        assertFalse(list.contains(15));
        assertTrue(list.contains(5));
        assertTrue(list.contains(25));
    }

    @Test
    public void testGetSize() {
        assertEquals(0, listManager.getSize(list));

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, listManager.getSize(list));
    }
}
