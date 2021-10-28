package bean;



public class FoundPointsPrizeDataBean {
	// private String Id;
	// private String scores;
	private int img_src;
	private String name;

	public FoundPointsPrizeDataBean() {
		super();
	}

	public FoundPointsPrizeDataBean(int img_src, String name) {
		super();
		// Id = id;
		// this.scores = scores;
		this.img_src = img_src;
		this.name = name;
	}

	// public String getId() {
	// return Id;
	// }
	//
	// public void setId(String id) {
	// Id = id;
	// }
	//
	// public String getScores() {
	// return scores;
	// }
	//
	// public void setScores(String scores) {
	// this.scores = scores;
	// }

	public int getImg_src() {
		return img_src;
	}

	public void setImg_src(int img_src) {
		this.img_src = img_src;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
