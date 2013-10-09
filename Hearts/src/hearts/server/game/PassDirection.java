package hearts.server.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PassDirection {
	LEFT, RIGHT, ACROSS, KEEP;
	
	public static final List<PassDirection> DIRECTIONS = Collections.unmodifiableList(Arrays.asList(values()));
}
