package ru.skillsad.sad.domain;

public interface RemoveAndCreate<T extends BaseEntity, B extends BaseEntity & BottomEntity<T>> {
    public void removeProduct(B product);

    public void addProduct(B product);
}
