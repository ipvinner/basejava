package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");
    private static final Resume RESUME_4 = new Resume("uuid4");
    private static final Resume RESUME_NOT_EXIST = new Resume("dummy");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, this.assertSize());
    }

    @Test
    public void get() {
        Assert.assertEquals(this.assertGet(RESUME_2.getUuid()), new Resume(RESUME_2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_NOT_EXIST.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] testStorageArray = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(testStorageArray, this.storage.getAll());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        Assert.assertEquals(RESUME_4, this.assertGet(RESUME_4.getUuid()));
        Assert.assertEquals(4, this.assertSize());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExists() {
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void saveStorageFull() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        storage.update(RESUME_3);
        assertEquals(RESUME_3, this.assertGet(RESUME_3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(RESUME_3.getUuid());
        Resume[] testStorageArray = {RESUME_1, RESUME_2};
        Assert.assertEquals(2, this.assertSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExists() {
        storage.delete(RESUME_4.getUuid());
    }

    private int assertSize() {
        return storage.size();
    }

    private Resume assertGet(String uuid) {
        return storage.get(uuid);
    }
}