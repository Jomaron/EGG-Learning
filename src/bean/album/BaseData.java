package bean.album;

import java.io.Serializable;

import com.baidu.android.itemview.helper.BaseStyle;

/**
 * @desc:网络数据类的基类
 *BLOG:http://blog.sina.com.cn/cuil11
 * @date: 2015�?7�?3�? 下午4:40:54
 * QQ2050542273
 * @email:15162925211@163.com
 */
public abstract class BaseData extends BaseStyle implements Serializable {

    protected static final int STATUS_OK = 0;
    protected static final int STATUS_ERROR = -1;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private transient int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
