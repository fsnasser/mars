package br.com.elo7.enums;


public enum ProbeCommand {

	RIGHT('R'), LEFT('L'), AHEAD('M');
	
	private char type;

	private ProbeCommand(char type) {
        this.type = type;
    }
	
	public ProbeCommand getCommandByType(char type) {
        for (ProbeCommand command : ProbeCommand.values())
            if (command.type == type)
                return command;
        return null;
    }
	
}
