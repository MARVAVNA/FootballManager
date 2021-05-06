package services;

import model.Manager;

public class ManagerService {
    public static Manager create(String name) {
        return new Manager(name);
    }
}
