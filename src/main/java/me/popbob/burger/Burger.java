package me.popbob.burger;

import me.popbob.burger.event.events.BurgerDuplicatedEvent;
import me.popbob.burger.event.events.BurgerPattyAteEvent;
import me.popbob.burger.event.ListenerRegisterer;

import java.lang.reflect.Field;
import java.util.*;

public class Burger<T> implements Collection<T> {

    HashMap<Integer, Field> ingredients = new HashMap<>();

    private T topBun = null;
    private T mustart = null;
    private T ketchup = null;
    private T onions = null;
    private T tomato = null;
    private T lettuce = null;
    private T cheese = null;
    private T patty = null;
    private T mayo = null;
    private T bottomBun = null;


    public Burger(){
        try {
            ingredients.put(0, this.getClass().getDeclaredField("topBun"));
            ingredients.put(1, this.getClass().getDeclaredField("mustart"));
            ingredients.put(2, this.getClass().getDeclaredField("ketchup"));
            ingredients.put(3, this.getClass().getDeclaredField("onions"));
            ingredients.put(4, this.getClass().getDeclaredField("tomato"));
            ingredients.put(5, this.getClass().getDeclaredField("lettuce"));
            ingredients.put(6, this.getClass().getDeclaredField("cheese"));
            ingredients.put(7, this.getClass().getDeclaredField("patty"));
            ingredients.put(8, this.getClass().getDeclaredField("mayo"));
            ingredients.put(9, this.getClass().getDeclaredField("bottomBun"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Burger(T[] ingredients) {
        try {
            this.ingredients.put(0, this.getClass().getDeclaredField("topBun"));
            this.ingredients.put(1, this.getClass().getDeclaredField("mustart"));
            this.ingredients.put(2, this.getClass().getDeclaredField("ketchup"));
            this.ingredients.put(3, this.getClass().getDeclaredField("onions"));
            this.ingredients.put(4, this.getClass().getDeclaredField("tomato"));
            this.ingredients.put(5, this.getClass().getDeclaredField("lettuce"));
            this.ingredients.put(6, this.getClass().getDeclaredField("cheese"));
            this.ingredients.put(7, this.getClass().getDeclaredField("patty"));
            this.ingredients.put(8, this.getClass().getDeclaredField("mayo"));
            this.ingredients.put(9, this.getClass().getDeclaredField("bottomBun"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if(ingredients.length != this.ingredients.size()){
            throw new IndexOutOfBoundsException();
        }

        this.topBun = ingredients[0];
        this.mustart = ingredients[1];
        this.ketchup = ingredients[2];
        this.onions = ingredients[3];
        this.tomato = ingredients[4];
        this.lettuce = ingredients[5];
        this.cheese = ingredients[6];
        this.patty = ingredients[7];
        this.mayo = ingredients[8];
        this.bottomBun = ingredients[9];
    }

    public boolean hasTopBun() {
        return topBun != null;
    }

    public T getTopBun() {
        return topBun;
    }

    public void setTopBun(T topBun) {
        this.topBun = topBun;
    }

    public boolean hasMustart() {
        return mustart != null;
    }

    public T getMustart() {
        return mustart;
    }

    public void setMustart(T mustart) {
        this.mustart = mustart;
    }

    public boolean hasKetchup() {
        return ketchup != null;
    }

    public T getKetchup() {
        return ketchup;
    }

    public void setKetchup(T ketchup) {
        this.ketchup = ketchup;
    }

    public boolean hasOnions() {
        return onions != null;
    }

    public T getOnions() {
        return onions;
    }

    public void setOnions(T onions) {
        this.onions = onions;
    }

    public boolean hasTomato() {
        return tomato != null;
    }

    public T getTomato() {
        return tomato;
    }

    public void setTomato(T tomato) {
        this.tomato = tomato;
    }

    public boolean hasLettuce() {
        return lettuce != null;
    }

    public T getLettuce() {
        return lettuce;
    }

    public void setLettuce(T lettuce) {
        this.lettuce = lettuce;
    }

    public boolean hasCheese() {
        return cheese != null;
    }

    public T getCheese() {
        return cheese;
    }

    public void setCheese(T cheese) {
        this.cheese = cheese;
    }

    public boolean hasPatty() {
        return patty != null;
    }

    public T getPatty() {
        return patty;
    }

    public void setPatty(T patty) {
        this.patty = patty;
    }

    public void eatPatty(){
        if(ListenerRegisterer.fireEvent(new BurgerPattyAteEvent())){
            System.out.println("PATTY EAT CANCELLED...");
            return;
        }
        patty = null;
    }

    public boolean hasMayo(){
        return mayo != null;
    }

    public T getMayo() {
        return mayo;
    }

    public void setMayo(T mayo) {
        this.mayo = mayo;
    }

    public boolean hasBottomBun(){
        return bottomBun != null;
    }

    public T getBottomBun() {
        return bottomBun;
    }

    public void setBottomBun(T bottomBun) {
        this.bottomBun = bottomBun;
    }

    public Burger<T> duplicateBurger(String duper){
        if(ListenerRegisterer.fireEvent(new BurgerDuplicatedEvent(duper))){
            System.out.println("DUPLICATE BURGER CANCELLED?!?!?");
            return null;
        }

        Burger<T> burger = new Burger<>();
        burger.setTopBun(this.topBun);
        burger.setMustart(this.mustart);
        burger.setKetchup(this.ketchup);
        burger.setOnions(this.onions);
        burger.setTomato(this.tomato);
        burger.setLettuce(this.lettuce);
        burger.setCheese(this.cheese);
        burger.setPatty(this.patty);
        burger.setMayo(this.mayo);
        burger.setBottomBun(this.bottomBun);

        return burger;
    }

    private Object getIngredient(int i) {
        Object ingredient = null;

        try {
            ingredient = ingredients.get(i).get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return ingredient;
    }

    private Object getIngredient(Field field){
        try {
            return field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setIngredient(Field field, Object value){
        try {
            field.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int size() {
        int size = 0;

        for(Field field : ingredients.values()){
            if(getIngredient(field) != null){
                size++;
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(Field field : ingredients.values()){
            if(Objects.equals(getIngredient(field), o)){
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new It();
    }

    @Override
    public Object[] toArray() {
        Object[] ingredientArray = new Object[10];

        ingredientArray[0] = this.topBun;
        ingredientArray[1] = this.mustart;
        ingredientArray[2] = this.ketchup;
        ingredientArray[3] = this.onions;
        ingredientArray[4] = this.tomato;
        ingredientArray[5] = this.lettuce;
        ingredientArray[6] = this.cheese;
        ingredientArray[7] = this.patty;
        ingredientArray[8] = this.mayo;
        ingredientArray[9] = this.bottomBun;

        return ingredientArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = false;

        for(Field field : ingredients.values()){
            if(Objects.equals(getIngredient(field), o)){
                setIngredient(field,null);
                removed = true;
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean containsAll = false;

        for(Object obj : c){
           containsAll = contains(obj);
        }

        return containsAll;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;

        for(Object obj : c){
            if(!removed)
                removed = remove(obj);
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for(Field field : ingredients.values()){
            Object obj = getIngredient(field);
            if(!c.contains(obj)){
                setIngredient(field, null);
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public void clear() {
        topBun = null;
        mustart = null;
        ketchup = null;
        onions = null;
        tomato = null;
        lettuce = null;
        cheese = null;
        patty = null;
        mayo = null;
        bottomBun = null;
    }

    @SuppressWarnings("unchecked")
    private class It implements Iterator<T> {
        int ingredientIndex = 0;
        T next = null;

        @Override
        public boolean hasNext() {
            for(int i = ingredientIndex; i < 10; i++) {
                if (getIngredient(i) != null) {
                    next = (T) getIngredient(i);
                    ingredientIndex = i+1;
                    return true;
                }
            }

            return false;
        }

        @Override
        public T next() {
            if(next == null) throw new NoSuchElementException();

            return next;

        }

        @Override
        public void remove() {
            Burger.this.remove(getIngredient(ingredientIndex));
        }

    }

}
