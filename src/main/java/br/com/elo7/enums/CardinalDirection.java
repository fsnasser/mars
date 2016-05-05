package br.com.elo7.enums;


public enum CardinalDirection {

	NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');
	
	private char type;

	private CardinalDirection(char type) {
        this.type = type;
    }
	
	public CardinalDirection getCardinalDirectionByType(char type) {
        for (CardinalDirection direction : CardinalDirection.values())
            if (direction.type == type)
                return direction;
        return null;
    }
	
	public char getType() {
		return type;
	}
	
}
