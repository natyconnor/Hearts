package hearts.server.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PassDirection {
	LEFT ((byte)1), 
	RIGHT ((byte)-1),
	ACROSS((byte)2),
	KEEP((byte)0);
	
	private byte indexVal;
	
	PassDirection(byte num)
	{
		indexVal = num;
	}
	
	public byte getIndexVal()
	{
		return indexVal;
	}
	
	public static final List<PassDirection> DIRECTIONS = Collections.unmodifiableList(Arrays.asList(values()));
}
