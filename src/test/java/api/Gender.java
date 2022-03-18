package api;

public enum Gender {

    MALE(0),
    FEMALE(1),
    OTHER(2);

    private final int gen;

    Gender(int i) {
        this.gen = i;
    }

}
