package operations;

public enum Operations {

    CREATE_USER("create user"),
    UPDATE_USER("update user"),
    DELETE_USER("delete user"),
    READ_USER("read user"),
    CREATE_ORDER("create order"),
    DELETE_ORDER("delete order"),
    READ_ORDER("read order"),
    UPDATE_ORDER("update order"),
    READ_APART("read apart"),
    GET_ALL_USERS("get all users"),
    GET_ALL_ORDERS("get all orders"),
    GET_CHOSEN_ORDERS("get chosen orders"),
    GET_ALL_APARTMENTS("get all aparts"),
    EXIT("exit");

    private String name;

    public String getName() {
        return name;
    }

    Operations(String name) {
        this.name = name;
    }
}
