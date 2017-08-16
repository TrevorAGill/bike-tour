package dao;

import models.Tour;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oTourDaoTest {

    private Sql2oTourDao tourDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        tourDao = new Sql2oTourDao(sql2o);

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void Tour_instantiatesCorrectly() {
        Tour newTour = newTour();
        assertTrue(newTour instanceof Tour);
    }

    @Test
    public void getNameFromNewTour_PCT () throws Exception {
        Tour newTour = newTour();
        assertEquals("PCT",newTour.getName());
    }

    @Test
    public void getDifficultyFromNewTour_PCT () throws Exception {
        Tour newTour = newTour();
        assertEquals(3,newTour.getDifficulty());
    }

    @Test
    public void getRatingFromNewTour_PCT () throws Exception {
        Tour newTour = newTour();
        assertEquals(5,newTour.getRating());
    }

    @Test
    public void getId_and_addingTourSetsId () throws Exception {
        Tour newTour = newTour();
        int localTourId = newTour.getId();
        tourDao.add(newTour);
        assertNotEquals(localTourId,newTour.getId());
    }

    @Test
    public void findById() throws Exception {
        Tour newTour = newTour();
        tourDao.add(newTour);
        int thisId = newTour.getId();
        Tour foundTour = tourDao.findById(thisId);
        assertEquals(thisId, foundTour.getId());
    }

    @Test
    public void returnListOfAllTours() throws Exception {
        Tour newTour = newTour();
        Tour newTour2 = new Tour("Lewis and Clarke", 4, 3);
        tourDao.add(newTour);
        tourDao.add(newTour2);
        assertEquals(2, tourDao.getAll().size());
    }

    @Test
    public void updateASingleTour() throws Exception {
        Tour newTour = newTour();
        String oldName = newTour.getName();
        tourDao.add(newTour);
        Tour foundTour = tourDao.findById(newTour.getId());
        tourDao.update("Test",4,4, foundTour.getId());
        Tour updatedTour = tourDao.findById(foundTour.getId());
        assertNotEquals(oldName, updatedTour.getName());
    }

    @Test
    public void deleteById() throws Exception {
        Tour newTour = newTour();
        tourDao.add(newTour);
        int idToDelete = newTour.getId();
        tourDao.deleteById(idToDelete);
        assertEquals(0, tourDao.getAll().size());
    }


    //helper
    public Tour newTour() {
        return new Tour("PCT", 3, 5);
    }




}