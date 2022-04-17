import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(this.storage, null);
    }

    void save(Resume r) {
        int firstNullIndex = IntStream.range(0, storage.length)
                .filter(i -> storage[i] == null)
                .findFirst()
                .orElse(-1);

        if(firstNullIndex != -1){
            this.storage[firstNullIndex] = r;
        }else {
            throw new RuntimeException("Storage is full");
        }

    }

    Resume get(String uuid) {
        Optional<Resume> optional = Arrays.stream(this.storage)
                .filter(Objects::nonNull)
                .filter(resume -> resume.uuid.equals(uuid))
                .findFirst();

        //Not sure good solution to return new object. possible notFoundException is better solution
        // but the task not allow to change of methods signature
        return optional.orElseGet(Resume::new);
    }

    void delete(String uuid) {
        int index = this.findIndexByUuid(uuid);
        if(index != -1){
            System.arraycopy(this.storage, index + 1, this.storage, index, this.storage.length - index - 1);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(this.storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return (int) Arrays.stream(this.storage).filter(Objects::nonNull).count();
    }

    private int findIndexByUuid(String uuid){
        int resumeIndex = -1;
        for (int i = 0; i < this.storage.length; i++) {
            if(this.storage[i] == null){
                return resumeIndex;
            }else {
                if(this.storage[i] != null && this.storage[i].uuid.equals(uuid)){
                    resumeIndex = i;
                }
            }
        }
        return resumeIndex;
    }
}
