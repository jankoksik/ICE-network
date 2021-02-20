package com.company.network.states;

import com.company.network.IceState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IceState_Tiles extends IceState<IceState_Tiles> {
    public List<Integer> tileStates;

    public IceState_Tiles(Integer... tiles) {
        tileStates = Arrays.asList(tiles);
    }

    public double distance(IceState_Tiles state) {
        List<Integer> targetTileState = state.tileStates;
        double length = Math.min(tileStates.size(), targetTileState.size());

        int differenceCounter = 0;
        for(int i=0;i<length;i++) {
            if(!tileStates.get(i).equals(targetTileState.get(i)))
                differenceCounter += 1;
        }

        return differenceCounter/length;
    }

    @Override
    public String toString() {
        return tileStates.toString();
    }

    public IceState_Tiles clone() {
        Integer[] listClone = new Integer[tileStates.size()];
        for(int i=0;i< tileStates.size();i++) listClone[i] = tileStates.get(i);
        return new IceState_Tiles(listClone);
    }
}
