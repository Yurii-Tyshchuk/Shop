package ru.skillsad.sad.domain.views;

public final class View {
    public interface Id {}

    public interface IdAndName extends Id {}

    public interface IdAndImg extends Id {}

    public interface Full extends IdAndName {}
}
