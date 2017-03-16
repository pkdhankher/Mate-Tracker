package com.dhankher.matetracker.eventbus;

import com.dhankher.matetracker.location.Loc;

/**
 * Created by Dhankher on 3/16/2017.
 */

public class UpdatedLocationEvent {
    Loc location;
    public UpdatedLocationEvent(Loc location) {
        this.location = location;

    }
    public Loc updatedLocation(){
        return location;
    }
}
