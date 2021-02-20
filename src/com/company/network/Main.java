package com.company.network;

import com.company.network.states.IceState_Tiles;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args) {
        List<IceNode<IceState_Tiles>> nodes = new ArrayList<>();
        for(int i=0;i<9;i++) {
            int finalI = i;
            IceNode<IceState_Tiles> newNode = new IceNode<>(){
                public String getName() {
                    return "Node "+ finalI;
                }

                public IceState_Tiles predictAction(IceState_Tiles currentState) {
                    IceState_Tiles newState = currentState.clone();
                    newState.tileStates.set(finalI, 1);
                    return newState;
                }
            };
            nodes.add(newNode);
        }

        IceState_Tiles startState = new IceState_Tiles(0,1,0,0,0,0,0,1,0);
        IceState_Tiles endState = new IceState_Tiles(1,1,1,1,1,1,1,1,1);
        IceNetwork<IceState_Tiles> brain = new IceNetwork<>(nodes, startState, endState);

        brain.displayPossibleActions();
    }
}
