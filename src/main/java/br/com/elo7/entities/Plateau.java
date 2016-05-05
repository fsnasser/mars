package br.com.elo7.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.elo7.exceptions.ProbeNotFoundException;
import br.com.elo7.exceptions.ProbeOutPlateauException;
import br.com.elo7.exceptions.ProbeSequenceException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Plateau {
	
private int xSize;
	
	private int ySize;
	
	private List<Probe> probes;
	
	public Plateau(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		probes = new ArrayList<Probe>();
	}

	@JsonIgnore
	public int getxSize() {
		return xSize;
	}

	@JsonIgnore
	public int getySize() {
		return ySize;
	}

	@JsonIgnore
	public List<Probe> getProbes() {
		return probes;
	}
	
	public void addProbe(Probe probe) throws ProbeOutPlateauException {
		long probeX = probe.getX();
		long probeY = probe.getY();
		if(!probeOutPlateau(probeX, probeY)){
			probes.add(probe);
		} else {
			throw new ProbeOutPlateauException();
		}
	}
	
	public void probePositionUpdated(Probe probe) throws ProbeSequenceException, ProbeNotFoundException, ProbeOutPlateauException {
		int index = probes.indexOf(probe);
		if(index >= 0) {
			int probeX = probe.getX();
			int probeY = probe.getY();
			switch (probe.getDirection()) {
			case NORTH:
				probe.setY(++probeY);
				break;
			case SOUTH:
				probe.setY(--probeY);
				break;
			case EAST:
				probe.setX(++probeX);
				break;
			case WEST:
				probe.setX(--probeX);
				break;
			default:
				throw new ProbeSequenceException();
			}
			if(!probeOutPlateau(probe.getX(), probe.getY())) {				
				probes.set(index, probe);
			} else {
				throw new ProbeOutPlateauException();
			}
		} else {
			throw new ProbeNotFoundException();
		}
	}
	
	@JsonProperty("probe_coordinates")
	public List<String> getProbeCoordinates() {
		List<String> coordinates = new ArrayList<String>();
		probes.forEach(probe -> {
			StringBuilder builder = new StringBuilder();
			builder.append(probe.getX()).append(" ").append(probe.getY()).append(" ").append(probe.getDirectionType());
			coordinates.add(builder.toString());
		});
		return coordinates;
	}

	private boolean probeOutPlateau(long probeX, long probeY) {
		if(probeX < 0 || probeX > xSize) return true;
		if(probeY < 0 || probeY > ySize) return true;
		return false;
	}
	
}
