package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (findIndex(r.getUuid()) != -1) {
            System.out.println("Resume with uuid: " + r.getUuid() + " is already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Resume with uuid: " + r.getUuid() + " does not exists in storage");
        }
    }



    protected int findIndex(String uuid) {
        return IntStream.range(0, size).
                filter(i -> storage[i].getUuid().equals(uuid)).
                findFirst().
                orElse(-1);
    }
}
