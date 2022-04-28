package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(int index, Resume r) {
        int saveIndex = -index - 1;
        System.arraycopy(storage, saveIndex, storage, saveIndex + 1, size - saveIndex);
        storage[saveIndex] = r;
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
