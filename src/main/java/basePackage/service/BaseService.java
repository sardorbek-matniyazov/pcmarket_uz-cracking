package basePackage.service;

import basePackage.payload.Status;


public interface BaseService<T, R> {
    Status add(T t);
    Status update(Long id, T t);
    Status delete(Long id);
}
