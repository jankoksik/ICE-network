package com.company.network;

import com.company.network.states.IceState_Tiles;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args) {
        List<IceNode<IceState_Tiles>> nodes = new ArrayList<>();
        for(int i=0;i<9;i++) {
            IceNode<IceState_Tiles> newNode = new IceNode<>();
            nodes.add(newNode);
        }

        IceState_Tiles startState = new IceState_Tiles(0,0,0,0,0,0,0,0,0);
        IceState_Tiles endState = new IceState_Tiles(1,1,1,1,1,1,1,1,1);
        IceNetwork<IceState_Tiles> brain = new IceNetwork<>(nodes, startState, endState);

        brain.displayPossibleActions();
    }
}
