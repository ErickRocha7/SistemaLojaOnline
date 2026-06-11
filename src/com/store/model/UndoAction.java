package com.store.model;

public abstract class UndoAction {
    public abstract void undo();

    public abstract String getDescription();
}