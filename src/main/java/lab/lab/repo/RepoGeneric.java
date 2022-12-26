package lab.lab.repo;

import lab.lab.domain.Entity;

import java.util.Collection;
import java.util.List;

public interface RepoGeneric<T extends Entity> {
    void create(T e);
    void update(int id,T e);
    Collection<T> getAll();
    T get(int id);
    void delete(int id);
}
