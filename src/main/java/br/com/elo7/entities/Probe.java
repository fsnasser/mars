package br.com.elo7.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.elo7.enums.CardinalDirection;
import br.com.elo7.enums.ProbeCommand;
import br.com.elo7.exceptions.InvalidDirectionException;
import br.com.elo7.exceptions.ProbeNotFoundException;
import br.com.elo7.exceptions.ProbeOutPlateauException;
import br.com.elo7.exceptions.ProbeSequenceException;


public class Probe {
	
	private int x;
	
	private int y;
	
	private CardinalDirection direction;
	
	private List<ProbeCommand> probeCommands;
	
	public Probe(int x, int y, char direction, String sequence) throws ProbeSequenceException, InvalidDirectionException {
		this.x = x;
		this.y = y;
		this.direction = CardinalDirection.NORTH;
		this.direction = this.direction.getCardinalDirectionByType(direction);
		if(this.direction == null) {
			throw new InvalidDirectionException();
		}
		this.probeCommands = getProbeCommandsBySequence(sequence);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void moveOnPlateau(Plateau plateau) throws ProbeNotFoundException, ProbeOutPlateauException {
		for (ProbeCommand command : probeCommands) {
			moveAccordingCommand(command, plateau);
		}
	}
	
	public CardinalDirection getDirection() {
		return direction;
	}

	public char getDirectionType() {
		return direction.getType();
	}
	
	public List<ProbeCommand> getProbeCommandsBySequence(String sequence) throws ProbeSequenceException {		
		if(sequence != null && !sequence.trim().equals("")) {
			List<ProbeCommand> probeCommands = new ArrayList<ProbeCommand>();
			char[] commands = sequence.toCharArray();
			for (char command : commands) {
				ProbeCommand probeCommand = ProbeCommand.AHEAD;
				probeCommand = probeCommand.getCommandByType(command);
				if(probeCommand != null) {
					probeCommands.add(probeCommand);
				} else {
					throw new ProbeSequenceException();
				}
			}
			return probeCommands;
		}
		throw new ProbeSequenceException();
	}
	
	private void moveAccordingCommand(ProbeCommand command, Plateau plateau) throws ProbeNotFoundException, ProbeOutPlateauException {
		switch (command) {
		case LEFT:
			rotateToLeft();
			break;
		case RIGHT:
			rotateToRight();
			break;
		case AHEAD:
			move(plateau);
			break;
		default:
			break;
		}
	}
	
	private void rotateToLeft() {
		switch (direction) {
		case NORTH:
			direction = CardinalDirection.WEST;
			break;
		case SOUTH:
			direction = CardinalDirection.EAST;
			break;
		case EAST:
			direction = CardinalDirection.NORTH;
			break;
		case WEST:
			direction = CardinalDirection.SOUTH;
			break;
		default:
			break;
		}
	}
	
	private void rotateToRight() {
		switch (direction) {
		case NORTH:
			direction = CardinalDirection.EAST;
			break;
		case SOUTH:
			direction = CardinalDirection.WEST;
			break;
		case EAST:
			direction = CardinalDirection.SOUTH;
			break;
		case WEST:
			direction = CardinalDirection.NORTH;
			break;
		default:
			break;
		}
	}
	
	private void move(Plateau plateau) throws ProbeNotFoundException, ProbeOutPlateauException {
		plateau.probePositionUpdated(this);
	}
	
}
