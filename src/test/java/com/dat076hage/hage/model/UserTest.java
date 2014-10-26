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
public class UserTest {
    
    public UserTest() {
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
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User("", "", "", "", "", "carro namn");
        String expResult = "carro namn";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "carro";
        User instance = new User("", "", "", "", "", name);
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("test for getUsername");
        User instance = new User("carro", "", "", "", "", "");
        String expResult = "carro";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class User.
     */
    @Test
    public void testGetDescription() {
        System.out.println(" test for getDescription");
        User instance = new User("carro", "this is my description", "", "", "", "");
        String expResult = "this is my description";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    
    /**
     * Test of getMemberDate method, of class User.
     */
    @Test 
    public void testGetMemberDate() {
        System.out.println("getMemberDate");
        User instance = new User();
        Date expResult = null;
        Date result = instance.getMemberDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getPosts method, of class User.
     */
    @Test
    public void testGetPosts() {
        System.out.println("getPosts");
        User instance = new User();
        List<Post> expResult = null;
        List<Post> result = instance.getPosts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsRange method, of class User.
     */
    @Test
    public void testGetPostsRange() {
        System.out.println("getPostsRange");
        int fromIndex = 0;
        int toIndex = 0;
        User instance = new User();
        List<Post> expResult = null;
        List<Post> result = instance.getPostsRange(fromIndex, toIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFollowing method, of class User.
     */
    @Test
    public void testGetFollowing() {
        System.out.println("getFollowing");
        User instance = new User();
        List<User> expResult = null;
        List<User> result = instance.getFollowing();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFollowers method, of class User.
     */
    @Test
    public void testGetFollowers() {
        System.out.println("getFollowers");
        User instance = new User();
        List<User> expResult = null;
        List<User> result = instance.getFollowers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getHash method, of class User.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        User instance = new User();
        String expResult = "";
        String result = instance.getHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTwitterApiHash method, of class User.
     */
    @Test
    public void testGetTwitterApiHash() {
        System.out.println("getTwitterApiHash");
        User instance = new User();
        String expResult = "";
        String result = instance.getTwitterApiHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class User.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        User instance = new User();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of assaignPost method, of class User.
     */
    @Test
    public void testAssaignPost() {
        System.out.println("assaignPost");
        Post post = null;
        User instance = new User();
        instance.assaignPost(post);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removePost method, of class User.
     */
    @Test
    public void testRemovePost() {
        System.out.println("removePost");
        Post post = null;
        User instance = new User();
        instance.removePost(post);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createNewPost method, of class User.
     */
    @Test
    public void testCreateNewPost() {
        System.out.println("createNewPost");
        String content = "";
        User instance = new User();
        Post expResult = null;
        Post result = instance.createNewPost(content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of follow method, of class User.
     */
    @Test
    public void testFollow() {
        System.out.println("follow");
        User user = null;
        User instance = new User();
        instance.follow(user);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of unfollow method, of class User.
     */
    @Test
    public void testUnfollow() {
        System.out.println("unfollow");
        User user = null;
        User instance = new User();
        instance.unfollow(user);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isFollowedBy method, of class User.
     */
    @Test
    public void testIsFollowedBy() {
        System.out.println("isFollowedBy");
        User user = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isFollowing(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
