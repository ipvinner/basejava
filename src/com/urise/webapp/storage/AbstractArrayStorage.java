package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid: " + uuid + " does not exists in storage");
            return null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume with uuid: " + r.getUuid() + " is already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else {
            doSave(index, r);
            size++;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume with uuid: " + r.getUuid() + " does not exists in storage");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size);
        } else {
            System.out.println("Resume with uuid: " + uuid + " does not exists");
        }
    }

    protected abstract int findIndex(String uuid);
    protected abstract void doSave(int index, Resume r);
}
