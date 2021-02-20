package com.company.network;

import com.company.network.states.IceState_Tiles;

import java.util.List;

public class IceNetwork<T extends IceState<T>> {
    private final List<IceNode<T>> nodes;
    private final T currentState;
    private final T intendedState;

    public IceNetwork(List<IceNode<T>> nodes, T initialState, T intendedState) {
        this.nodes = nodes;
        this.currentState = initialState;
        this.intendedState = intendedState;
    }

    public void displayPossibleActions() {
        System.out.println("Distance: "+intendedState.distance(currentState)+"  "+currentState);
        for(IceNode<T> node : nodes) {
            System.out.println("   "+node.getName()+": "+node.evaluateConsequences(currentState, intendedState)+"   ->   "+node.predictAction(currentState));
        }
    }
}
