import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        return Arrays.stream(storage)
                .filter(resume -> resume.uuid.equals(uuid))
                .findFirst().get();
    }

    void delete(String uuid) {
        int index = findIndexByUuid(uuid);
        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }

    private int findIndexByUuid(String uuid) {
        int resumeIndex = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resumeIndex = i;
                return resumeIndex;
            }
        }
        return resumeIndex;
    }
}
