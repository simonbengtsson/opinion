/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.model;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caroline
 */
public class PostTest {
    
    public PostTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getText method, of class Post.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        Post instance = new Post();
        String expResult = "";
        String result = instance.getText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Post.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Post instance = new Post();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class Post.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Post instance = new Post();
        GPS expResult = null;
        GPS result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPicturePath method, of class Post.
     */
    @Test
    public void testGetPicturePath() {
        System.out.println("getPicturePath");
        Post instance = new Post();
        String expResult = "";
        String result = instance.getPicturePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostDate method, of class Post.
     */
    @Test
    public void testGetPostDate() {
        System.out.println("getPostDate");
        Post instance = new Post();
        Date expResult = null;
        Date result = instance.getPostDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLink method, of class Post.
     */
    @Test
    public void testGetLink() {
        System.out.println("getLink");
        Post instance = new Post();
        String expResult = "";
        String result = instance.getLink();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHageTags method, of class Post.
     */
    @Test
    public void testGetHageTags() {
        System.out.println("getHageTags");
        Post instance = new Post();
        List expResult = null;
        List result = instance.getHageTags();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setText method, of class Post.
     */
    @Test
    public void testSetText() {
        System.out.println("setText");
        String text = "";
        Post instance = new Post();
        instance.setText(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class Post.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        GPS pos = null;
        Post instance = new Post();
        instance.setPosition(pos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPicturePath method, of class Post.
     */
    @Test
    public void testSetPicturePath() {
        System.out.println("setPicturePath");
        String path = "";
        Post instance = new Post();
        instance.setPicturePath(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPostDate method, of class Post.
     */
    @Test
    public void testSetPostDate() {
        System.out.println("setPostDate");
        Date date = null;
        Post instance = new Post();
        instance.setPostDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLink method, of class Post.
     */
    @Test
    public void testSetLink() {
        System.out.println("setLink");
        String link = "";
        Post instance = new Post();
        instance.setLink(link);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Post.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Post instance = new Post();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
