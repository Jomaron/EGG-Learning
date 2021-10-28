package utils;

public class Contants {
	// 主机地址
	public static final String HOST = "http://app.lerays.com/api/";
	// ============首页===========
	// 导航条的地址
	public static final String HOST_TITLE = "stream/category/navi?";
	//每一个Fragment地址
	public static final String HOST_AMUSEMENT = "stream/list?cate_sign=%s&cate_list=%d&cate_type=%s&pubtime=%d";	
	// =============发现===========
	//一级
	// 积分商城
	// 红包 http://app.lerays.com/api/activity/dailysign/sign?
	public static final String HOST_RED = HOST + "activity/dailysign/sign?";
	// 积分商城中的奖品兑换 http://app.lerays.com/api/integrate/goods_list?
	public static final String HOST_PRIZE_EXCHANGE = HOST + "integrate/goods_list?";
	// 专题活动 http://app.lerays.com/api/activity/topic/list?
	public static final String HOST_SPECIAL = HOST + "activity/topic/list?";
	// ==========排行==============
	// 排行
	public static final String HOST_RANK = HOST + "stream/rank/navi?";
	// 一级页面 蹿红
	public static final String HOST_RANK_CUANHONG = HOST
			+ "stream/rank/trending";
	// 一级页面 周榜 http://app.lerays.com/api/stream/rank/week
	public static final String HOST_RANK_ZHOUBANG = HOST + "stream/rank/week";
	// 一级页面 月榜
	public static final String HOST_RANK_YUEBANG = HOST + "stream/rank/month";
	// 一级页面 神回复http://app.lerays.com/api/rank/quickreply
	public static final String HOST_RANK_SHENHUIFU = HOST
			+ "stream/rank/quickreply";
	// 一级页面: 分享达人http://app.lerays.com/api/rank/ipn?
	public static final String HOST_RANK_FENXIANG = HOST + "rank/ipn";
	// 一级页面: http://app.lerays.com/api/rank/tavg?
	public static final String HOST_RANK_XIU = HOST + "rank/tavg";

}
