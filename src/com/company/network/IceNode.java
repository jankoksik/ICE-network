package com.company.network;

import org.jetbrains.annotations.NotNull;

public class IceNode<T extends IceState<T>> {

    public T predictAction(T currentState) {
        return currentState;
    }
    public String getName() {
        return "Blank node";
    }

    public double evaluateConsequences(T currentState, @NotNull T intendedState) {
        T predictedState = predictAction(currentState);
        return intendedState.distance(currentState) - intendedState.distance(predictedState);
    }

}
