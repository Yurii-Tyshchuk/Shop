package ru.skillsad.sad.domain;

import java.util.List;

public interface RemoveAndCreate {
    public void removeEntityFromThisEntity(List<? extends BottomEntity<?>> entity);

    public void createEntityInsideThisEntity(List<? extends BaseEntity> entity);
}
