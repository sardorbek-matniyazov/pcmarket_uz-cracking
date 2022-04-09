package basePackage.service;

import basePackage.payload.Status;

import java.util.List;


public interface BaseService<T, R> {
    List<R> getAll();
    R get(Long id);
    Status add(T t);
    Status update(Long id, T t);
    Status delete(Long id);
}
