package com.home.ticketmaster.TicketMaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Theatre {
    private final String id;
    private final String name;
    private List<Screen> screenList;

    public Theatre(@NonNull String id, @NonNull String name){
        this.id = id;
        this.name = name;
        this.screenList = new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen){
        this.screenList.add(screen);
    }

}
