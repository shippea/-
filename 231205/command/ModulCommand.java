package command;

public class ModulCommand implements Command {

	@Override
	public float calc(int i, int j) {
		return i%j;
	}

}
