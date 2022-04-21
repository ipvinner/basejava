package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if(size >= 10000){
            System.out.printf("Storage is full");
        }else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r){
        int index = findIndexByUuid(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        }else {
            System.out.println("Resume with uuid: " + r.getUuid() + " does not exists in storage");
        }
    }

    public Resume get(String uuid) {
        int index = findIndexByUuid(uuid);
        if(index != -1){
            return storage[index];
        }else {
            System.out.println("Resume with uuid: " + uuid + " does not exists in storage");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndexByUuid(uuid);
        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size);
        }else {
            System.out.println("Resume with uuid: " + uuid + " does not exists");
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

    public int size() {
        return size;
    }

    private int findIndexByUuid(String uuid) {
        int resumeIndex = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resumeIndex = i;
                return resumeIndex;
            }
        }
        return resumeIndex;
    }
}
