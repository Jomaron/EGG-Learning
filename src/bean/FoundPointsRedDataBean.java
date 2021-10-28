package bean;

import java.util.List;

/*
 * 发现   积分商城   红包来袭
 * */
public class FoundPointsRedDataBean {
	private boolean status;
	private int count;
	private dataBean data;

	public FoundPointsRedDataBean(boolean status, int count, dataBean data) {
		super();
		this.status = status;
		this.count = count;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public dataBean getData() {
		return data;
	}

	public void setData(dataBean data) {
		this.data = data;
	}

	// 第二个实体类data
	public static class dataBean {
		private int maxnum;
		private int today_state;
		private List<banner_imgBean> banner_img;

		public dataBean(int maxnum, int today_state,
				List<banner_imgBean> banner_img) {
			super();
			this.maxnum = maxnum;
			this.today_state = today_state;
			this.banner_img = banner_img;
		}

		public int getMaxnum() {
			return maxnum;
		}

		public void setMaxnum(int maxnum) {
			this.maxnum = maxnum;
		}

		public int getToday_state() {
			return today_state;
		}

		public void setToday_state(int today_state) {
			this.today_state = today_state;
		}

		public List<banner_imgBean> getBanner_img() {
			return banner_img;
		}

		public void setBanner_img(List<banner_imgBean> banner_img) {
			this.banner_img = banner_img;
		}

		// 第三个实体类banner_img
		public static class banner_imgBean {
			private String img_src;
			private actionBean action;

			public banner_imgBean(String img_src, actionBean action) {
				super();
				this.img_src = img_src;
				this.action = action;
			}

			public String getImg_src() {
				return img_src;
			}

			public void setImg_src(String img_src) {
				this.img_src = img_src;
			}

			public actionBean getAction() {
				return action;
			}

			public void setAction(actionBean action) {
				this.action = action;
			}

			// 第四个实体类action
			public static class actionBean {
				private String target;
				private String type;
				private String value;

				public actionBean(String target, String type, String value) {
					super();
					this.target = target;
					this.type = type;
					this.value = value;
				}

				public String getTarget() {
					return target;
				}

				public void setTarget(String target) {
					this.target = target;
				}

				public String getType() {
					return type;
				}

				public void setType(String type) {
					this.type = type;
				}

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}
			}
		}
	}

}
