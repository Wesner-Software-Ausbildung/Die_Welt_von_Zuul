package de.szut.zuul.gamecontrol;

import de.szut.zuul.exceptions.ItemNotFoundException;
import de.szut.zuul.exceptions.ItemTooHeavyException;

public class ZuulUI {

    /**
     *
     * @param args
     */
    // Тут я добавил параметр throws, если что удалить
    public static void main(String[] args) throws ItemTooHeavyException, ItemNotFoundException {
        Game game = new Game();
        game.play();
    }
}


