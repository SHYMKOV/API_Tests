package api;

public enum Gender {

    MALE(0),
    FEMALE(1),
    OTHER(2);

    private final int gen;

    Gender(int i) {
        this.gen = i;
    }

    public int getGen() {
        return gen;
    }

    public static Gender fromInt (int i){
        for(Gender gender : Gender.values()){
            if(gender.getGen() == i){
                return gender;
            }
        }
       throw new IllegalArgumentException("value " + i + " cant convert to Gender");
    }

}
