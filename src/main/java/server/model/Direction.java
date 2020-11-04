package server.model;

public enum Direction implements IEnum {
    Enter(1),
    Exit(2);

   private final Integer value;

    Direction(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static Direction getDirectionEnum(Integer val){
        switch (val) {
            case 1:
                return Enter;
            case 2 :
                return Exit;
            default:
                return null;
        }
    }
}
