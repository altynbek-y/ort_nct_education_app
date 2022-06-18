package com.example.admin.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import nami.project.indie.ort_nct.database.AppDao;
import nami.project.indie.ort_nct.database.TestDatabase;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class SimpleEntityReadWriteTest {
    private AppDao userDao;
    private TestDatabase db;

    @Before
    public void createDb() {

    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        userDao.insert(user);
//        List<User> byName = userDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
    }
}
