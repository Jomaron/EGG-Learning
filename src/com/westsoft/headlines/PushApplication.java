package com.westsoft.headlines;

import java.util.LinkedHashMap;
import java.util.Map;

import org.xutils.x;

import utils.SharePreferenceUtil;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.RemoteViews;
import baidupush.BaiduPush;
import baidupush.client.PushMessageReceiver;

import com.chatdb.MessageDB;
import com.chatdb.RecentDB;
import com.chatdb.UserDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westsoft.headlines.ChatActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PushApplication extends Application {
	
	public final static String API_KEY = "fiWrR2Ki8NkR6r5GHdM2lY7j";
    public final static String SECRIT_KEY = "PTtP7I1EfBnRMu0LimV8fRSIso0dZtgA";
    public static final String SP_FILE_NAME = "push_msg_sp";
    // ======ͷ��===
    public static final int[] heads = { R.drawable.photo};
    public static final int NUM_PAGE = 6;// �ܹ��ж���ҳ
    public static int NUM = 20;// ÿҳ20������,�������һ��ɾ��button
    private static PushApplication mApplication;
    private BaiduPush mBaiduPushServer;
    private Map<String, Integer> mFaceMap = new LinkedHashMap<String, Integer>();
    private SharePreferenceUtil mSpUtil;
    private UserDB mUserDB;
    private MessageDB mMsgDB;
    private RecentDB mRecentDB;
    // private List<User> mUserList;
    private MediaPlayer mMediaPlayer;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private Gson mGson;

    public synchronized static PushApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
    	super.onCreate();
		x.Ext.init(this);
        mApplication = this;
        initFaceMap();
        initData();
    }

    private void initData() {
        mBaiduPushServer = new BaiduPush(BaiduPush.HTTP_METHOD_POST,
                SECRIT_KEY, API_KEY);
        // ��ת��û�� @Expose ע����ֶ�
        mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        mSpUtil = new SharePreferenceUtil(this, SP_FILE_NAME);
        mUserDB = new UserDB(this);
        mMsgDB = new MessageDB(this);
        mRecentDB = new RecentDB(this);
        // mUserList = mUserDB.getUser();
        mMediaPlayer = MediaPlayer.create(this, R.raw.office);
        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    }

    public synchronized UserDB getUserDB() {
        if (mUserDB == null)
            mUserDB = new UserDB(this);
        return mUserDB;
    }

    public synchronized BaiduPush getBaiduPush() {
        if (mBaiduPushServer == null)
            mBaiduPushServer = new BaiduPush(BaiduPush.HTTP_METHOD_POST,
                    SECRIT_KEY, API_KEY);
        return mBaiduPushServer;

    }

    public synchronized Gson getGson() {
        if (mGson == null)
            // ��ת��û�� @Expose ע����ֶ�
            mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                    .create();
        return mGson;
    }

    public synchronized MessageDB getMessageDB() {
        if (mMsgDB == null)
            mMsgDB = new MessageDB(this);
        return mMsgDB;
    }

    public synchronized RecentDB getRecentDB() {
        if (mRecentDB == null)
            mRecentDB = new RecentDB(this);
        return mRecentDB;
    }

    public NotificationManager getNotificationManager() {
        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        return mNotificationManager;
    }

    public synchronized MediaPlayer getMediaPlayer() {
        if (mMediaPlayer == null)
            mMediaPlayer = MediaPlayer.create(this, R.raw.office);
        return mMediaPlayer;
    }

    public synchronized SharePreferenceUtil getSpUtil() {
        if (mSpUtil == null)
            mSpUtil = new SharePreferenceUtil(this, SP_FILE_NAME);
        return mSpUtil;
    }

    public Map<String, Integer> getFaceMap() {
        if (!mFaceMap.isEmpty())
            return mFaceMap;
        return null;
    }

    /**
     * �����һ�ͼ��
     */
    @SuppressWarnings("deprecation")
    public void showNotification() {
        if (!mSpUtil.getMsgNotify())// ����û����ò���ʾ�һ�ͼ�ֱ꣬�ӷ���
            return;

        int icon = R.drawable.notify_general;
        CharSequence tickerText = getResources().getString(
                R.string.app_is_run_background);
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);

        // ������"��������"��Ŀ��
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.notify_status_bar_latest_event_view);
        contentView.setImageViewResource(R.id.icon,
                heads[mSpUtil.getHeadIcon()]);
        contentView.setTextViewText(R.id.title, mSpUtil.getNick());
        contentView.setTextViewText(R.id.text, tickerText);
        contentView.setLong(R.id.time, "setTime", when);
        // ָ�����Ի���ͼ
        mNotification.contentView = contentView;

        Intent intent = new Intent(this, ChatActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // ָ��������ͼ
        mNotification.contentIntent = contentIntent;
        // ������4.0notify
        // Bitmap icon = BitmapFactory.decodeResource(getResources(),
        // heads[mSpUtil.getHeadIcon()]);
        // Notification.Builder notificationBuilder = new Notification.Builder(
        // this).setContentTitle(mSpUtil.getNick())
        // .setContentText(tickerText)
        // .setSmallIcon(R.drawable.notify_general)
        // .setWhen(System.currentTimeMillis())
        // .setContentIntent(contentIntent).setLargeIcon(icon);
        // Notification n = notificationBuilder.getNotification();
        // n.flags |= Notification.FLAG_NO_CLEAR;

        mNotificationManager.notify(PushMessageReceiver.NOTIFY_ID,
                mNotification);
    }

    private void initFaceMap() {
        // TODO Auto-generated method stub
        mFaceMap.put("[����]", R.drawable.f000);
        mFaceMap.put("[��Ƥ]", R.drawable.f001);
        mFaceMap.put("[����]", R.drawable.f002);
        mFaceMap.put("[͵Ц]", R.drawable.f003);
        mFaceMap.put("[�ټ�]", R.drawable.f004);
        mFaceMap.put("[�ô�]", R.drawable.f005);
        mFaceMap.put("[����]", R.drawable.f006);
        mFaceMap.put("[��ͷ]", R.drawable.f007);
        mFaceMap.put("[õ��]", R.drawable.f008);
        mFaceMap.put("[����]", R.drawable.f009);
        mFaceMap.put("[���]", R.drawable.f010);
        mFaceMap.put("[��]", R.drawable.f011);
        mFaceMap.put("[��]", R.drawable.f012);
        mFaceMap.put("[ץ��]", R.drawable.f013);
        mFaceMap.put("[ί��]", R.drawable.f014);
        mFaceMap.put("[���]", R.drawable.f015);
        mFaceMap.put("[ը��]", R.drawable.f016);
        mFaceMap.put("[�˵�]", R.drawable.f017);
        mFaceMap.put("[�ɰ�]", R.drawable.f018);
        mFaceMap.put("[ɫ]", R.drawable.f019);
        mFaceMap.put("[����]", R.drawable.f020);

        mFaceMap.put("[����]", R.drawable.f021);
        mFaceMap.put("[��]", R.drawable.f022);
        mFaceMap.put("[΢Ц]", R.drawable.f023);
        mFaceMap.put("[��ŭ]", R.drawable.f024);
        mFaceMap.put("[����]", R.drawable.f025);
        mFaceMap.put("[����]", R.drawable.f026);
        mFaceMap.put("[�亹]", R.drawable.f027);
        mFaceMap.put("[����]", R.drawable.f028);
        mFaceMap.put("[ʾ��]", R.drawable.f029);
        mFaceMap.put("[����]", R.drawable.f030);
        mFaceMap.put("[����]", R.drawable.f031);
        mFaceMap.put("[�ѹ�]", R.drawable.f032);
        mFaceMap.put("[����]", R.drawable.f033);
        mFaceMap.put("[����]", R.drawable.f034);
        mFaceMap.put("[˯]", R.drawable.f035);
        mFaceMap.put("[����]", R.drawable.f036);
        mFaceMap.put("[��Ц]", R.drawable.f037);
        mFaceMap.put("[����]", R.drawable.f038);
        mFaceMap.put("[˥]", R.drawable.f039);
        mFaceMap.put("[Ʋ��]", R.drawable.f040);
        mFaceMap.put("[����]", R.drawable.f041);

        mFaceMap.put("[�ܶ�]", R.drawable.f042);
        mFaceMap.put("[����]", R.drawable.f043);
        mFaceMap.put("[�Һߺ�]", R.drawable.f044);
        mFaceMap.put("[ӵ��]", R.drawable.f045);
        mFaceMap.put("[��Ц]", R.drawable.f046);
        mFaceMap.put("[����]", R.drawable.f047);
        mFaceMap.put("[����]", R.drawable.f048);
        mFaceMap.put("[��]", R.drawable.f049);
        mFaceMap.put("[���]", R.drawable.f050);
        mFaceMap.put("[����]", R.drawable.f051);
        mFaceMap.put("[ǿ]", R.drawable.f052);
        mFaceMap.put("[��]", R.drawable.f053);
        mFaceMap.put("[����]", R.drawable.f054);
        mFaceMap.put("[ʤ��]", R.drawable.f055);
        mFaceMap.put("[��ȭ]", R.drawable.f056);
        mFaceMap.put("[��л]", R.drawable.f057);
        mFaceMap.put("[��]", R.drawable.f058);
        mFaceMap.put("[����]", R.drawable.f059);
        mFaceMap.put("[����]", R.drawable.f060);
        mFaceMap.put("[ơ��]", R.drawable.f061);
        mFaceMap.put("[Ʈ��]", R.drawable.f062);

        mFaceMap.put("[����]", R.drawable.f063);
        mFaceMap.put("[OK]", R.drawable.f064);
        mFaceMap.put("[����]", R.drawable.f065);
        mFaceMap.put("[����]", R.drawable.f066);
        mFaceMap.put("[Ǯ]", R.drawable.f067);
        mFaceMap.put("[����]", R.drawable.f068);
        mFaceMap.put("[��Ů]", R.drawable.f069);
        mFaceMap.put("[��]", R.drawable.f070);
        mFaceMap.put("[����]", R.drawable.f071);
        mFaceMap.put("[�]", R.drawable.f072);
        mFaceMap.put("[ȭͷ]", R.drawable.f073);
        mFaceMap.put("[����]", R.drawable.f074);
        mFaceMap.put("[̫��]", R.drawable.f075);
        mFaceMap.put("[����]", R.drawable.f076);
        mFaceMap.put("[����]", R.drawable.f077);
        mFaceMap.put("[����]", R.drawable.f078);
        mFaceMap.put("[����]", R.drawable.f079);
        mFaceMap.put("[����]", R.drawable.f080);
        mFaceMap.put("[����]", R.drawable.f081);
        mFaceMap.put("[��]", R.drawable.f082);
        mFaceMap.put("[����]", R.drawable.f083);

        mFaceMap.put("[��ĥ]", R.drawable.f084);
        mFaceMap.put("[�ٱ�]", R.drawable.f085);
        mFaceMap.put("[����]", R.drawable.f086);
        mFaceMap.put("[�ܴ���]", R.drawable.f087);
        mFaceMap.put("[��ߺ�]", R.drawable.f088);
        mFaceMap.put("[��Ƿ]", R.drawable.f089);
        mFaceMap.put("[�����]", R.drawable.f090);
        mFaceMap.put("[��]", R.drawable.f091);
        mFaceMap.put("[����]", R.drawable.f092);
        mFaceMap.put("[ƹ����]", R.drawable.f093);
        mFaceMap.put("[NO]", R.drawable.f094);
        mFaceMap.put("[����]", R.drawable.f095);
        mFaceMap.put("[���]", R.drawable.f096);
        mFaceMap.put("[תȦ]", R.drawable.f097);
        mFaceMap.put("[��ͷ]", R.drawable.f098);
        mFaceMap.put("[��ͷ]", R.drawable.f099);
        mFaceMap.put("[����]", R.drawable.f100);
        mFaceMap.put("[����]", R.drawable.f101);
        mFaceMap.put("[����]", R.drawable.f102);
        mFaceMap.put("[����]", R.drawable.f103);
        mFaceMap.put("[��̫��]", R.drawable.f104);

        mFaceMap.put("[��̫��]", R.drawable.f105);
        mFaceMap.put("[����]", R.drawable.f106);
    }
}
