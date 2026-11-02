package com.example.qa.tests;

import com.example.qa.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class UserTest {

    @BeforeMethod
    public void setUp(){
        System.out.println("Before test");
    }

    @AfterMethod
    public void  tearDown(){
        System.out.println("After test");
    }


    @Test
    public void shouldKeepNameFromConstructor() {
        User user = new User(101,"Alice", true);
        Assert.assertEquals(user.getName(), "Alice");
    }

    @Test
    public void shouldChangeName() {
        User secondUser = new User(101,"Alice", true);
        secondUser.setName("Maria");
        Assert.assertEquals(secondUser.getName(), "Maria");
    }

    @Test
    public void shouldActivateUser() {
        User thirdUser = new User(101,"Alice", false);
        Assert.assertFalse(thirdUser.isActive());// ожидаем false
        thirdUser.setActive(true);
        Assert.assertTrue(thirdUser.isActive());  // ожидаем true
    }

    @Test
    public void shouldDeactivateUser() {
        User fourthUser = new User(101,"Alice", true);
        Assert.assertTrue(fourthUser.isActive());// ожидаем true
        fourthUser.setActive(false);
        Assert.assertFalse(fourthUser.isActive());  // ожидаем false
    }

    @Test
    public void shouldKeepIdAfterNameChange() {
        User fifthUser = new User(205,"Anna", true);
        fifthUser.setName("Maria");
        Assert.assertEquals(fifthUser.getName(),"Maria");
        Assert.assertEquals(fifthUser.getId(),205);
    }
}