package ru.skillsad.sad.domain;

public interface BottomEntity<T extends BaseEntity> {
    public T getUpEntity();
    public void setUpEntity(T entity);
}
