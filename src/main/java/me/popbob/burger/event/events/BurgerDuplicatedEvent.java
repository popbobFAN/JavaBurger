package me.popbob.burger.event.events;

public class BurgerDuplicatedEvent extends BurgerEvent {
    public String duper;

    public BurgerDuplicatedEvent(String duper){
        this.duper = duper;
    }

}
