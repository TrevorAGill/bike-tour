package dao;

import models.Campsite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;


public class Sql2oCampsiteDaoTest {

    private Sql2oCampsiteDao campsiteDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        campsiteDao = new Sql2oCampsiteDao(sql2o);

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void Campsite_instantiatesCorrectly() {
        Campsite newCampsite = newCampsite();
        assertTrue(newCampsite instanceof Campsite);
    }

    @Test
    public void getNameFromNewCampsite_PCT () throws Exception {
        Campsite newCampsite = newCampsite();
        assertEquals("PCT Camp",newCampsite.getName());
    }

    @Test
    public void getCostFromNewCampsite_PCT () throws Exception {
        Campsite newCampsite = newCampsite();
        assertEquals(10, newCampsite.getCost());
    }

    @Test
    public void getRatingFromNewCampsite_PCT () throws Exception {
        Campsite newCampsite = newCampsite();
        assertEquals(3,newCampsite.getRating());
    }

    @Test
    public void getId_and_addingCampsiteSetsId () throws Exception {
        Campsite newCampsite = newCampsite();
        int localCampsiteId = newCampsite.getId();
        campsiteDao.add(newCampsite);
        assertNotEquals(localCampsiteId,newCampsite.getId());
    }

    @Test
    public void findById() throws Exception {
        Campsite newCampsite = newCampsite();
        campsiteDao.add(newCampsite);
        int thisId = newCampsite.getId();
        Campsite foundCampsite = campsiteDao.findById(thisId);
        assertEquals(thisId, foundCampsite.getId());
    }

    @Test
    public void returnListOfAllCampsites() throws Exception {
        Campsite newCampsite = newCampsite();
        Campsite newCampsite2 = new Campsite("Lewis and Clarke", 4, 3, 1);
        campsiteDao.add(newCampsite);
        campsiteDao.add(newCampsite2);
        assertEquals(2, campsiteDao.getAll().size());
    }

    @Test
    public void getAllByTour_returnsAllCampsitesForTour() throws Exception {
        Campsite newCampsite = newCampsite();
        Campsite newCampsite2 = new Campsite("Lewis and Clarke", 4, 3, 1);
        Campsite newCampsite3 = new Campsite("Lewis and Clarke", 4, 3, 2);
        campsiteDao.add(newCampsite);
        campsiteDao.add(newCampsite2);
        campsiteDao.add(newCampsite3);
        List tourCamps = campsiteDao.getAllByTour(1);
        assertEquals(2, tourCamps.size());
    }

    @Test
    public void updateASingleCampsite() throws Exception {
        Campsite newCampsite = newCampsite();
        String oldName = newCampsite.getName();
        campsiteDao.add(newCampsite);
        Campsite foundCampsite = campsiteDao.findById(newCampsite.getId());
        campsiteDao.update("Test",4,4, 1, foundCampsite.getId());
        Campsite updatedCampsite = campsiteDao.findById(foundCampsite.getId());
        assertNotEquals(oldName, updatedCampsite.getName());
    }

    @Test
    public void deleteById() throws Exception {
        Campsite newCampsite = newCampsite();
        campsiteDao.add(newCampsite);
        int idToDelete = newCampsite.getId();
        campsiteDao.deleteById(idToDelete);
        assertEquals(0, campsiteDao.getAll().size());
    }


    //helper
    public Campsite newCampsite() {
        return new Campsite("PCT Camp", 3, 10, 1);
    }



}