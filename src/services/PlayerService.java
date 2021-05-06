package services;

import model.Player;

public class PlayerService {
    public static Player create(String name) {
        return new Player(name);
    }
}
