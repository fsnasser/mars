package br.com.elo7.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Plateau {
	
	private long xSize;
	
	private long ySize;
	
	public Plateau(long xSize, long ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@JsonProperty("x_size")
	public long getxSize() {
		return xSize;
	}

	@JsonProperty("y_size")
	public long getySize() {
		return ySize;
	}
	
}
