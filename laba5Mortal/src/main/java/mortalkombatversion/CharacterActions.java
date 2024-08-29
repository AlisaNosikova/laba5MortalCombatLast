package mortalkombatversion;

public enum CharacterActions {
    BLOCK_ATTACK(0),
    ATTACK(1),
    WEAK_ATTACK(6);

    private final int code;

    CharacterActions(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return switch (this) {
            case BLOCK_ATTACK -> "block";
            case ATTACK -> "attack";
            case WEAK_ATTACK -> "weak";
        };
    }

    public static CharacterActions from(int code) {
        return switch (Math.abs(code)) {
            case 0 -> CharacterActions.BLOCK_ATTACK;
            case 1 -> CharacterActions.ATTACK;
            case 6 -> CharacterActions.WEAK_ATTACK;
            default -> throw new IllegalArgumentException();
        };
    }
}
