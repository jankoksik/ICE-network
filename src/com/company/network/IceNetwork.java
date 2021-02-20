package com.company.network;

import com.company.network.states.IceState_Tiles;

import java.util.List;

public class IceNetwork<T extends IceState<T>> {
    private final List<IceNode<T>> nodes;
    private T currentState;
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
    public void runGreedyAlgorithm() {
        System.out.println("Start: "+currentState+"  |  "+currentState.distance(intendedState));

        while(currentState.distance(intendedState)>0) {
            double maxChange = 0;
            IceNode<T> maxNode = null;

            for(IceNode<T> node : nodes) {
                double thisChange = node.evaluateConsequences(currentState, intendedState);
                if(thisChange > maxChange) {
                    maxChange = thisChange;
                    maxNode = node;
                }
            }

            if(maxChange == 0) {
                System.out.println("Impossible task!");
                break;
            }

            currentState = maxNode.predictAction(currentState);
            System.out.println(maxNode.getName()+": "+currentState+"  |  "+currentState.distance(intendedState));

        }
    }

}
