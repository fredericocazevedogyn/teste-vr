package br.com.frederico.vr.domain;

import java.util.List;

public interface Repository<E, I> {

    public void add(E object);

    public void remove(E object);

    public E get(I id);

    public boolean exists(I id);

    public List<E> all();

}
