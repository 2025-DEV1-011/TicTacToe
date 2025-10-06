package com.game.tictactoe.model;

public enum Player {
    X, O;

    @Override
    public String toString() {
        return name();
    }
}

