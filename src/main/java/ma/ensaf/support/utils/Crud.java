package ma.ensaf.support.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Crud<E extends Identifiable<ID>, ID> {
    public List<E> data = new ArrayList<>();

    public void add(E obj) {
        data.add(obj);
    }
    public void update (E obj) {

    }
    public void remove(E obj) {

    }
    public Optional<E> findById(ID id) {
        //TODO voir optional
        return data.stream().filter(e -> id.equals(e.getId())).findFirst();
    }
    
}
