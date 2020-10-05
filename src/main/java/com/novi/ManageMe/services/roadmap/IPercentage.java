package com.novi.ManageMe.services.roadmap;

public interface IPercentage {

    // calculate percentage done
    default int getPercentageDone(int parts, int partsdone) {
        if (parts == partsdone) {
            return 100;
        } else {
            return 100/parts*partsdone;
        }
    }
}
