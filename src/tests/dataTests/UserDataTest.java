package tests.dataTests;

import growthCalculator.data.UserData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 13-12-2015.
 */
public class UserDataTest {
    private UserData userData;

    @Before
    public void setUp() throws Exception {
        userData = new UserData();
    }

    @Test
    public void testGetAges() throws Exception {
        userData.add(1, 213);
        userData.add(13, 23123);
        userData.add(17, 231);
        ArrayList<Integer> ages = new ArrayList<>(Arrays.asList(new Integer[]{1, 13, 17}));
        for (Integer age: userData.getAges())
            assertTrue(ages.contains(age));
    }
}