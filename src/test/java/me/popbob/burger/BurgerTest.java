package me.popbob.burger;

import me.popbob.burger.event.ListenerRegisterer;
import me.popbob.burger.event.events.BurgerDuplicatedEvent;
import me.popbob.burger.event.events.BurgerEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class BurgerTest {

    @Test
    void burgerTest(){
        Burger<Boolean> booleanBurger = new Burger<>();

        booleanBurger.setTopBun(true);
        booleanBurger.setCheese(false);
        booleanBurger.setTomato(true);

        booleanBurger.forEach(System.out::println);

        booleanBurger.remove(true);

        Assertions.assertFalse(booleanBurger.hasTopBun());
        Assertions.assertTrue(booleanBurger.hasCheese());
        Assertions.assertFalse(booleanBurger.hasTomato());

        Iterator<Boolean> it = booleanBurger.iterator();

        while(it.hasNext()){
            // FIXME: 7/25/2023 it.remove doesnt work... it doesnt really matter though since burgers allow for concurrent modification
        }

        booleanBurger.remove(booleanBurger.getCheese());
        Assertions.assertFalse(booleanBurger.hasCheese());

        booleanBurger.setMayo(true);

        booleanBurger.clear();

        Assertions.assertFalse(booleanBurger.hasMayo());
        Assertions.assertEquals(0, booleanBurger.size());
        Assertions.assertTrue(booleanBurger.isEmpty());

        Object[] objects = booleanBurger.toArray();

        Assertions.assertTrue(booleanBurger.contains(objects[8]));

    }

    @Test
    void burgerEventTest(){
        Burger<Boolean> booleanBurger = new Burger<>();

        ListenerRegisterer.listenForEvent(BurgerDuplicatedEvent.class, (BurgerEvent event) -> {
            BurgerDuplicatedEvent e = (BurgerDuplicatedEvent) event;
            return e.duper.equals("popbob");
        });

        booleanBurger.duplicateBurger("popbob");
        booleanBurger.duplicateBurger("duperbob");
    }

}