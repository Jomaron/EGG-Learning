package adapter;

public class GridViewData {
	private String name;
	private int iconId;

	GridViewData(String name, int iconId) {
		this.name = name;
		this.iconId = iconId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
}
