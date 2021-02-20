package com.company.network;

public abstract class IceState<T extends IceState<T>> {
    public abstract double distance(T state);
    public static <U extends IceState<U>> double distance(U stateBefore, U stateAfter) {
        return stateBefore.distance(stateAfter);
    }
}
