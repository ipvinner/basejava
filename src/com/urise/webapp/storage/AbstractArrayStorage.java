package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size);
        } else {
            System.out.println("Resume with uuid: " + uuid + " does not exists");
        }
    }

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid: " + uuid + " does not exists in storage");
            return null;
        }
    }

    protected abstract int findIndex(String uuid);
}
