package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AbstractArrayStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_1), new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] testStorageArray = new Resume[3];
        testStorageArray[0] = new Resume(UUID_1);
        testStorageArray[1] = new Resume(UUID_2);
        testStorageArray[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(testStorageArray, this.storage.getAll());
    }

    @Test
    public void save() {
        Resume newResume = new Resume(UUID_4);
        storage.save(newResume);
        Resume resume = storage.get(UUID_4);
        Assert.assertEquals(newResume, resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExists() {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void saveStorageFull() {
        storage.clear();
        for (int i = 0; i <= AbstractArrayStorage.STORAGE_LIMIT+100; i++) {
            try {
                storage.save(new Resume());
            }
            catch (NullPointerException nullPointerException){
                continue;
            }
            catch (StorageException e) {
                Assert.fail();
            }
        }
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume(UUID_3);
        storage.update(updatedResume);
        assertEquals(updatedResume, storage.get(updatedResume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        Resume[] testStorageArray = new Resume[2];
        testStorageArray[0] = new Resume(UUID_1);
        testStorageArray[1] = new Resume(UUID_2);
        Assert.assertArrayEquals(testStorageArray, this.storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExists() {
        storage.delete(UUID_4);
    }
}