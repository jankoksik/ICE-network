package com.company.network;

import com.company.network.states.IceState_Tiles;

public class IceNetwork {
    public static void main(String [] args) {
        IceState_Tiles state1 = new IceState_Tiles(0,0,0,0,1,0,0,0,0);
        IceState_Tiles state2 = new IceState_Tiles(1,0,1,0,1,0,1,0,1);
        System.out.println(state1.distance(state2));
        System.out.println(state2.distance(state1));
        System.out.println(IceState.distance(state1, state2));
    }
}
