package me.popbob.burger.event;

import me.popbob.burger.event.events.BurgerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ListenerRegisterer {

    private static HashMap<Class<? extends BurgerEvent>, List<Function<BurgerEvent, Boolean>>> eventer = new HashMap<>();

    public static void listenForEvent(Class<? extends BurgerEvent> event, Function<BurgerEvent, Boolean> onEvent){
        var lister = eventer.get(event);
        if(lister == null){
            lister = new ArrayList<>();
            lister.add(onEvent);
            eventer.put(event, lister);
        } else {
            lister.add(onEvent);
        }
    }

    public static boolean fireEvent(BurgerEvent event){
        var list = eventer.get(event.getClass());
        if(list == null) return false;

        boolean cancelled = false;

        for(var function : list) {
            boolean cancel = function.apply(event);
            if(!cancelled){
                cancelled = cancel;
            }
        }

        return cancelled;
    }

}
