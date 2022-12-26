package lab.lab.repo;

import lab.lab.domain.Entity;
import lab.lab.domain.User;
import lab.lab.domain.validators.Validator;

import java.util.*;

public class RepoMemory<T extends Entity> implements RepoGeneric<T>{
    private Validator<T> validator;
    Map<Integer,T> elems; //MAp - dict abstract

    private static int c = 0 ;

    public RepoMemory(Validator<T> v) {
        validator = v;
        elems = new HashMap<Integer,T>(); //Hashmap e un anumit tip de dictionar
    }

    @Override
    public void create(T e) {
        e.setId(c);
        elems.put(c,e);
        c++;
    }

    @Override
    public void update(int id,T e) {
        if (elems.containsKey(id)) {
            e.setId(id);
            elems.put(id, e);
        }
        else
            throw new RuntimeException("Nu exista entitate cu acest Id: "+id);
    }

    @Override
    public Collection<T> getAll() {
        return elems.values();
    }

    @Override
    public T get(int id) {
        if (elems.containsKey(id))
            return elems.get(id);
        else
            throw new RuntimeException("Nu exista entitate cu acest Id: "+id);
    }

    @Override
    public void delete(int id) {
        if (elems.containsKey(id))
            elems.remove(id);
        else
            throw new RuntimeException("Nu exista entitate cu acest Id: "+id);
    }
}
