package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int findIndex(String uuid) {
        return IntStream.range(0, size).
                filter(i -> storage[i].getUuid().equals(uuid)).
                findFirst().
                orElse(-1);
    }

    @Override
    protected void doSave(int index, Resume r) {
        storage[size] = r;
    }
}
