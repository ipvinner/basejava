import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

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
        int firstNullIndex = IntStream.range(0, storage.length)
                .filter(i -> storage[i] == null)
                .findFirst()
                .orElse(-1);

        if (firstNullIndex != -1) {
            storage[firstNullIndex] = r;
            size++;
        } else {
            throw new RuntimeException("Storage is full");
        }

    }

    Resume get(String uuid) {
        Optional<Resume> optional = Arrays.stream(storage)
                .filter(Objects::nonNull)
                .filter(resume -> resume.uuid.equals(uuid))
                .findFirst();

        return optional.orElseGet(null);
    }

    void delete(String uuid) {
        int index = this.findIndexByUuid(uuid);
        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return size;
    }

    private int findIndexByUuid(String uuid) {
        int resumeIndex = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return resumeIndex;
            } else {
                if (this.storage[i] != null && this.storage[i].uuid.equals(uuid)) {
                    resumeIndex = i;
                }
            }
        }
        return resumeIndex;
    }
}
