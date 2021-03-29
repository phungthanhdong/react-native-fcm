package com.evollu.react.fcm;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Map;
import java.lang.Enum;
import java.net.URLEncoder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.StandardCharsets;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
/* CM's code begin
*/
final class AlertType{
    public int ID;
    public String Name;

    public AlertType(int id, String newName){
        ID = id;
        Name = newName;
    }
}

final  class EnumAlertType
{
    public final  static  AlertType DVR_System_Started = new AlertType(1, "System Started")  ;
    public final  static  AlertType DVR_System_Shutdown = new AlertType(2, "System Shutdown")  ;
    public final  static  AlertType DVR_Insufficient_Disk_Space_Backup = new AlertType(3, "Insufficient Disk Space Backup")  ;
    public final  static  AlertType DVR_CPU_Temperature_High = new AlertType(4, "CPU Temperature High")  ;
    public final  static  AlertType DVR_Video_Loss = new AlertType(5, "Video Loss")  ;
    public final  static  AlertType DVR_Backup_Started = new AlertType(6, "Backup Started")  ;
    public final  static  AlertType DVR_Backup_Completed = new AlertType(7, "Backup Completed")  ;
    public final  static  AlertType DVR_Backup_Stopped = new AlertType(8, "Backup Stopped")  ;
    public final  static  AlertType DVR_Sensor_Triggered = new AlertType(9, "Sensor Triggered")  ;
    public final  static  AlertType DVR_Control_Activated = new AlertType(10, "Control Activated")  ;
    public final  static  AlertType DVR_HDD_Format_Started = new AlertType(11, "HDD Format")  ; // AlertType(11, "HDD Format Started")  ;
    public final  static  AlertType DVR_HDD_Format_Completed = new AlertType(12, "HDD Format")  ; // AlertType(12, "HDD Format Completed")  ;
    public final  static  AlertType DVR_User_Added = new AlertType(13, "User Added")  ;
    public final  static  AlertType DVR_User_Removed = new AlertType(14, "User Removed")  ;
    public final  static  AlertType DVR_User_Logged_in = new AlertType(15, "User Logged in")  ;
    public final  static  AlertType DVR_User_Logged_out = new AlertType(16, "User Logged out")  ;
    public final  static  AlertType DVR_disconnect_from_CMS_server = new AlertType(17, "Disconnect from CMS server")  ;
    public final  static  AlertType DVR_Storage_Setup_Changed = new AlertType(18, "Storage Setup Changed")  ;
    public final  static  AlertType DVR_Video_Recycling_Began = new AlertType(19, "Video Recycling Began")  ;
    public final  static  AlertType DVR_Not_recording = new AlertType(20, "Not recording")  ;
    public final  static  AlertType Setup_Configuration_Changed = new AlertType(21, "Setup Configuration Changed")  ;
    public final  static  AlertType DVR_Partition_Dropped = new AlertType(22, "Partition Dropped")  ;
    public final  static  AlertType CMS_Registration_Expire_Soon = new AlertType(23, "CMS Registration Expire Soon")  ;
    public final  static  AlertType CMS_Registration_Expired = new AlertType(24, "CMS Registration Expired")  ;
    public final  static  AlertType Other_types = new AlertType(25, "Other types")  ;
    public final  static  AlertType DVR_Partition_Added = new AlertType(26, "Partition Added")  ;
    public final  static  AlertType DVR_HASP_Unplugged = new AlertType(27, "HASP Unplugged")  ;
    public final  static  AlertType DVR_HASP_Expired = new AlertType(28, "HASP Expired")  ;
    public final  static  AlertType DVR_Frame_Rate_Changed = new AlertType(29, "Frame Rate Changed")  ;
    public final  static  AlertType DVR_Resolution_Changed = new AlertType(30, "Resolution Changed")  ;
    public final  static  AlertType DVR_Time_Manually_Adjusted = new AlertType(31, "Time Manually Adjusted")  ;
    public final  static  AlertType DVR_is_off_line = new AlertType(32, "NVRs offline")  ;
    public final  static  AlertType DVR_connected_CMS_Server = new AlertType(33, "NVR connected CMS Server")  ;
    public final  static  AlertType DVR_Unstable_Video_Signal = new AlertType(34, "Unstable Video Signal")  ;
    public final  static  AlertType DVR_Video_returned_to_normal = new AlertType(35, "Video returned to normal")  ;
    public final  static  AlertType DVR_VA_detection = new AlertType(36, "VA detection")  ;
    public final  static  AlertType DVR_Record_Less_Than = new AlertType(37, "Record Less Than")  ;
    public final  static  AlertType CMS_HASP_Unplugged = new AlertType(101, "CMS HASP Unplugged")  ;
    public final  static  AlertType CMS_HASP_Found = new AlertType(102, "CMS HASP Found")  ;
    public final  static  AlertType CMS_HASP_Removed = new AlertType(103, "CMS HASP Removed")  ;
    public final  static  AlertType CMS_HASP_Expired = new AlertType(104, "CMS HASP Expired")  ;
    public final  static  AlertType CMS_Server_HASP_Limit_Exceeded = new AlertType(105, "CMS Server HASP Limit Exceeded")  ;
    public final  static  AlertType CMSWEB_Conversion_rate_above_100 = new AlertType(106, "CMSWEB Conversion rate above 100")  ;
    public final  static  AlertType CMSWEB_Door_count_0 = new AlertType(107, "CMSWEB Door count 0")  ;
    public final  static  AlertType CMSWEB_POS_data_missing = new AlertType(108, "CMSWEB POS data missing")  ;
    public final  static  AlertType CMSWEB_CONV_NO_HIGHER = new AlertType(109, "CMSWEB CONV NO HIGHER")  ;
    public final  static  AlertType CMSWEB_CONV_NO_LOWER = new AlertType(110, "CMSWEB CONV NO LOWER")  ;
    public final  static  AlertType CMSWEB_RISK_NO_HIGHER = new AlertType(111, "CMSWEB RISK NO HIGHER")  ;
    public final  static  AlertType CMSWEB_RISK_NO_LOWER = new AlertType(112, "CMSWEB RISK NO LOWER")  ;
    public final  static  AlertType TEMPERATURE_OUT_OF_RANGE = new AlertType(113, "Temperature out of range")  ;
    public final  static  AlertType TEMPERATURE_NOT_WEAR_MASK = new AlertType(114, "Not wearing mask")  ;
    public final  static  AlertType TEMPERATURE_INCREASE_RATE_BY_DAY = new AlertType(115, "Increasing temperature rate by day")  ;
    public final  static  AlertType SOCIAL_DISTANCE = new AlertType(116, "Social distance")  ;
    public final  static  AlertType POS_Exceptions = new AlertType(222, "POS Exceptions")  ;



    public static String getNameAlert(int AlertTypeID)
    {
        switch(AlertTypeID)
        {
            case 1:
                return DVR_System_Started.Name;
            case 2:
                return DVR_System_Shutdown.Name;
            case 3:
                return DVR_Insufficient_Disk_Space_Backup.Name;
            case 4:
                return DVR_CPU_Temperature_High.Name;
            case 5:
                return DVR_Video_Loss.Name;
            case 6:
                return DVR_Backup_Started.Name;
            case 7:
                return DVR_Backup_Completed.Name;
            case 8:
                return DVR_Backup_Stopped.Name;
            case 9:
                return DVR_Sensor_Triggered.Name;
            case 10:
                return DVR_Control_Activated.Name;
            case 11:
                return DVR_HDD_Format_Started.Name;
            case 12:
                return DVR_HDD_Format_Completed.Name;
            case 13:
                return DVR_User_Added.Name;
            case 14:
                return DVR_User_Removed.Name;
            case 15:
                return DVR_User_Logged_in.Name;
            case 16:
                return DVR_User_Logged_out.Name;
            case 17:
                return DVR_disconnect_from_CMS_server.Name;
            case 18:
                return DVR_Storage_Setup_Changed.Name;
            case 19:
                return DVR_Video_Recycling_Began.Name;
            case 20:
                return DVR_Not_recording.Name;
            case 21:
                return Setup_Configuration_Changed.Name;
            case 22:
                return DVR_Partition_Dropped.Name;
            case 23:
                return CMS_Registration_Expire_Soon.Name;
            case 24:
                return CMS_Registration_Expired.Name;
            case 25:
                return Other_types.Name;
            case 26:
                return DVR_Partition_Added.Name;
            case 27:
                return DVR_HASP_Unplugged.Name;
            case 28:
                return DVR_HASP_Expired.Name;
            case 29:
                return DVR_Frame_Rate_Changed.Name;
            case 30:
                return DVR_Resolution_Changed.Name;
            case 31:
                return DVR_Time_Manually_Adjusted.Name;
            case 32:
                return DVR_is_off_line.Name;
            case 33:
                return DVR_connected_CMS_Server.Name;
            case 34:
                return DVR_Unstable_Video_Signal.Name;
            case 35:
                return DVR_Video_returned_to_normal.Name;
            case 36:
                return DVR_VA_detection.Name;
            case 37:
                return DVR_Record_Less_Than.Name;
            case 101:
                return CMS_HASP_Unplugged.Name;
            case 102:
                return CMS_HASP_Found.Name;
            case 103:
                return CMS_HASP_Removed.Name;
            case 104:
                return CMS_HASP_Expired.Name;
            case 105:
                return CMS_Server_HASP_Limit_Exceeded.Name;
            case 106:
                return CMSWEB_Conversion_rate_above_100.Name;
            case 107:
                return CMSWEB_Door_count_0.Name;
            case 108:
                return CMSWEB_POS_data_missing.Name;
            case 109:
                return CMSWEB_CONV_NO_HIGHER.Name;
            case 110:
                return CMSWEB_CONV_NO_LOWER.Name;
            case 111:
                return CMSWEB_RISK_NO_HIGHER.Name;
            case 112:
                return CMSWEB_RISK_NO_LOWER.Name;
            case 222:
                return POS_Exceptions.Name;
        }
        return "";
    }

}

class MyNotiContent {
    private String serverid;
    public String getServerid() {
        return serverid;
    }
    public void setServerid(String newServerid) {
        serverid = newServerid;
    }

    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String newContent) {
        content = newContent;
    }

    private String action;
    public String getAction() {
        return action;
    }
    public void setAction(String newAction) {
        action = newAction;
    }

    private String type;
    public String getType() {
        return type;
    }
    public void setType(String newType) {
        type = newType;
    }

//    public void setAll_NotiContent(MyNotiContent newObj)
//    {
//        serverid = newObj.getServerid();
//        content = newObj.getContent();
//        action = newObj.getAction();
//    }
}

class MyNoti {

    private String ID;
    public String getID() {
        return ID;
    }
    public void setID(String newId) {
        ID = newId;
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    private String body;
    public String getBody() {
        return body;
    }
    public void setBody(String newBody) {
        body = newBody;
    }

    private Boolean isContent;
    public Boolean getIsContent() {
        return isContent;
    }
    public void setIsContent(Boolean newIsContent) {
        isContent = newIsContent;
    }

    private MyNotiContent Content;
    public MyNotiContent getContent() {
        return Content;
    }
    public void setContent(MyNotiContent newObj) {

        //Content = Content.setAll_NotiContent(newContent);
        Content.setServerid(newObj.getServerid());
        Content.setContent(newObj.getContent());
        Content.setAction(newObj.getAction());
        Content.setType(newObj.getType());
    }

    public Boolean canDisplay() {
        return title != null && !title.isEmpty() && body != null && !body.isEmpty();
    }
}

/**
/* CM's code end
*/

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Log.d(TAG, "Remote message received " + remoteMessage.toString());
        Intent i = new Intent("com.evollu.react.fcm.ReceiveNotification");
        i.putExtra("data", remoteMessage);
        handleBadge(remoteMessage);
        buildLocalNotification(remoteMessage);

        final RemoteMessage temp = remoteMessage;
        final Intent message = i;
        
        // We need to run this on the main thread, as the React code assumes that is true.
        // Namely, DevServerHelper constructs a Handler() without a Looper, which triggers:
        // "Can't create handler inside thread that has not called Looper.prepare()"
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                // Construct and load our normal React JS code bundle
                ReactInstanceManager mReactInstanceManager = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager();
                ReactContext context = mReactInstanceManager.getCurrentReactContext();
                // If it's constructed, send a notification
                if (context != null) {
					// Log.d(TAG, "Shark: context != null");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(message);
                } else {
                    // Otherwise wait for construction, then send the notification
                    mReactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                        public void onReactContextInitialized(ReactContext context) {
							// Log.d(TAG, "Shark: wait for construction");
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(message);
                        }
                    });
                    if (!mReactInstanceManager.hasStartedCreatingInitialContext()) {
						// Log.d(TAG, "Shark: Construct it in the background");
                        // Construct it in the background
						buildLocalNotificationInBackground(temp);
                        // mReactInstanceManager.createReactContextInBackground();
                    }
                }
            }
        });
    }

    public void handleBadge(RemoteMessage remoteMessage) {
        BadgeHelper badgeHelper = new BadgeHelper(this);
        if (remoteMessage.getData() == null) {
            return;
        }

        Map data = remoteMessage.getData();
        if (data.get("badge") == null) {
            return;
        }

        try {
            int badgeCount = Integer.parseInt((String)data.get("badge"));
            badgeHelper.setBadgeCount(badgeCount);
        } catch (Exception e) {
            Log.e(TAG, "Badge count needs to be an integer", e);
        }
    }

    public void buildLocalNotification(RemoteMessage remoteMessage) {
        if(remoteMessage.getData() == null){
            // Log.d(TAG, "GOND buildLocalNotification noDATA");
            return;
        }
        Map<String, String> data = remoteMessage.getData();
        String customNotification = data.get("custom_notification");
        if(customNotification != null){
            try {
                Log.d(TAG, "Shark: new FIRLocalMessagingHelper");
                Bundle bundle = BundleJSONConverter.convertToBundle(new JSONObject(customNotification));
                FIRLocalMessagingHelper helper = new FIRLocalMessagingHelper(this.getApplication());
                helper.sendNotification(bundle);
            } catch (JSONException e) {
                Log.d(TAG, "GOND buildLocalNotification eror: " + e.getMessage());
                e.printStackTrace();
            }
            // Log.d(TAG, "GOND buildLocalNotification send completed");
        } else {
            // Log.d(TAG, "GOND buildLocalNotification no custom notify");
        }
    }
	
	/**
	* CM's code: begin
	*/
    enum NOTIFY_ACTION {
        ADD,
        EDIT,
        DELETE,
        REFRESH,
        LOG_OUT,
        DIMISS,
        DIMISS_BLOCK,
        NVR_STATUS,
        PWD_CHANGE,
        NEWIMAGE,
        STREAM_CREATED,
        WARNING,
    }

    private String GetAlertServerityString(int alertType) {
        switch (alertType) {
          case 1:
            return "Normal";
          case 2:
            return "Caution";
          case 3:
            return "Warning";
          case 4:
            return "Urgent";
          default:
            return "Unknow";
        }
      }

    public void buildLocalNotificationInBackground(RemoteMessage remoteMessage) {
        if(remoteMessage.getData() == null){
            return;
        }

        Map<String, String> data2 = remoteMessage.getData();
        String action = data2.get("action");
        String type = data2.get("type");
        String content = data2.get("content");
        String serverid = data2.get("serverid");

        MyNoti noti = new MyNoti();

        Log.d(TAG, "Shark: Content: " + content);
        Log.d(TAG, "Shark: type: " + type);
        switch (type)
        {
            case "USER":
                noti = OnUserEvent(action, content);
                break;
            case "SITE":
                noti = OnSiteEvent(action, content);
                break;
            case "DVR":
                Log.d(TAG, "Shark: case DVR");
                noti.setTitle("DVR configuration.");
                //JSONObject obj1 = new JSONObject(content);
                try {
                    JSONObject jsonArray = new JSONObject(content);
                    String Name = jsonArray.has("Name") == true ? jsonArray.get("Name").toString() : "";
                    noti.setBody("DVR: " + Name + " has changed.");
                }
                catch (Throwable t) {
                    noti.setBody("DVR has changed.");
                }

                break;
            case "ALERT_TYPE":

                if(NOTIFY_ACTION.valueOf(action) == NOTIFY_ACTION.EDIT){
                    noti.setTitle("Alert Settings.");
                    noti.setBody("Alert Settings has changed.");
                    noti.setIsContent(false);
                    //noti.setID("ALERT_TYPE_NOTIFICATE");
                }
                break;
            case "ALERT":
                Log.d(TAG, "Shark: Content Alert: " + content);
                noti = OnAlertEvent(action, content);
                break;
            case "ALARM":
                if(NOTIFY_ACTION.valueOf(action) == NOTIFY_ACTION.ADD)
                {
                    noti.setIsContent(true);
                    noti.setTitle("CMS Alarms.");
                    try {
                        JSONObject jsonArray = new JSONObject(content);
                        String KAlertType = jsonArray.has("KAlertType") == true ? jsonArray.get("KAlertType").toString() : "";
                        String AlertName = ""; //Integer.parseInt(KAlertType) == EnumAlertType.DVR_VA_detection.ID ? "VA detection" : "Sensor triggered";
                        switch (Integer.parseInt(KAlertType)) {
                            case 9: //EnumAlertType.DVR_Sensor_Triggered.ID:
                                AlertName = EnumAlertType.DVR_Sensor_Triggered.Name;
                                break;
                            case 36: //EnumAlertType.DVR_VA_detection.ID:
                                AlertName = EnumAlertType.DVR_VA_detection.Name;
                                break;
                            case 113: //EnumAlertType.TEMPERATURE_OUT_OF_RANGE.ID:
                                AlertName = EnumAlertType.TEMPERATURE_OUT_OF_RANGE.Name;
                                break;
                            case  114: //EnumAlertType.TEMPERATURE_NOT_WEAR_MASK.ID:
                                AlertName = EnumAlertType.TEMPERATURE_NOT_WEAR_MASK.Name;
                                break;
                            case 115: //EnumAlertType.TEMPERATURE_INCREASE_RATE_BY_DAY.ID:
                                AlertName = EnumAlertType.TEMPERATURE_INCREASE_RATE_BY_DAY.Name;
                                break;
                            case 116: //EnumAlertType.SOCIAL_DISTANCE.ID:
                                AlertName = EnumAlertType.SOCIAL_DISTANCE.Name;
                                break;
                            default:
                                Log.d("GOND", "ALARM notify, Unknow KAlertType: " + KAlertType);
                                return;
                        }
                        String site_name = jsonArray.has("SiteName") == true ? jsonArray.get("SiteName").toString() : "";

                        noti.setBody(site_name + ": " + AlertName);
                        String KDVR = jsonArray.has("KDVR") == true ? jsonArray.get("KDVR").toString() : "";
                        noti.setID(MsgId(Integer.parseInt(KAlertType), Integer.parseInt(KDVR)));
                    }
                    catch (Throwable t) {
                        //noti.setBody("Has ALARM");
                        return;
                    }
                }
                break;
            case "EXCEPTION":
                if(NOTIFY_ACTION.valueOf(action) == NOTIFY_ACTION.ADD) {
                    Log.d(TAG, "Shark: Content Alert: " + content);
                    String msg = "POS Exception";
                    noti.setIsContent(true);
                    try {
                        JSONObject jsonArray = new JSONObject(content);
                        String site_name = jsonArray.has("SiteName") == true ? jsonArray.get("SiteName").toString() : "";

                        String DateTime = jsonArray.has("DateTime") == true ? jsonArray.get("DateTime").toString() : "";
                        String TranID = jsonArray.has("TranID") == true ? jsonArray.get("TranID").toString() : "";
                        JSONArray flagsA = jsonArray.getJSONArray("Flagname");
                        String flags = "";
                        if(flagsA.length() > 0)
                        {
                            flags = flagsA.optString(0);
                            for(int i=1;i<flagsA.length();i++)
                                flags+= ", " + flagsA.optString(i);
                        }

                        Log.d(TAG, "Shark: Content Flags: " + flags);

                        if(DateTime != "")
                        {
                            if(DateTime.contains("T") == true)
                            {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                Date date1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(DateTime);
                                DateTime = dateFormat.format(date1);
                            }

                        }

                        Log.d(TAG, "Shark: Body: " + site_name + " (" + DateTime + "): " + msg);
                        //noti.setBody(site_name + " (" + DateTime + "): " + msg);
                        noti.setTitle("POS EXCEPTION | " + site_name);
                        noti.setBody("(" + DateTime + ") " + flags);
                        String KDVR = jsonArray.has("KDVR") == true ? jsonArray.get("KDVR").toString() : "";
                        noti.setID("msg_pos_exception_" + TranID);
                    }
                    catch (Throwable t) {
                        //noti.setBody("Has ALARM");
                        return;
                    }
                }
                break;
            case "PVM":
                 try {
                    JSONObject contentData = new JSONObject(content);

                    String siteName = contentData.has("SiteName") == true ? contentData.get("SiteName").toString() : "";
                    String dvrName = contentData.has("DVRName") == true ? contentData.get("DVRName").toString() : "";

                    noti.setTitle("OAM");
                    noti.setIsContent(true);

                    if (NOTIFY_ACTION.valueOf(action) == NOTIFY_ACTION.WARNING) {
                        String aboveCapacity = contentData.has("AboveCapacity") == true ? contentData.get("AboveCapacity").toString() : "?";
                        noti.setBody("Site " + siteName + (dvrName != "" && dvrName != null && dvrName != "null" ? " - " + dvrName : "") + " - Above " + (int)Double.parseDouble(aboveCapacity) + "%25 capacity");
                        noti.setID("pvm_notify_" + (contentData.has("KDVR") == true ? contentData.get("KDVR").toString() : ""));
                    } else if (NOTIFY_ACTION.valueOf(action) == NOTIFY_ACTION.DIMISS) {
                        String alertServerity = contentData.has("KAlertType") == true ? GetAlertServerityString(Integer.parseInt(contentData.get("KAlertType").toString())) : "!";
                        String cmsUser = contentData.has("CMSUser") == true ? contentData.get("CMSUser").toString() : "Unknown user";
                        noti.setBody("Site " + siteName + (dvrName != "" && dvrName != null && dvrName != "null" ? " - " + dvrName : "") +  " - " + alertServerity + " has been acknowledged by " + cmsUser);
                        noti.setID("pvm_notify_" + (contentData.has("KDVR") == true ? contentData.get("KDVR").toString() : ""));
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    Log.d(TAG, "GOND Parse notification json data failed: " + e.getMessage());
                    return;
                }
                break;

        }

        //{serverid: serverid, content: noti.isContent?content: null, action:action, type: type }
//        MyNotiContent Content = new MyNotiContent();
//        Content.setAction(action);
//        Content.setContent(content);
//        Content.setServerid(serverid);
//        String temp = Content.toString();
        if (!noti.canDisplay()) {
            Log.d("GOND", "Notification will not display: " + content);
            return;
        }

        JSONObject content_notification = new JSONObject();
        try {
            content_notification.put("serverid", serverid);
            content_notification.put("action", action);
            content_notification.put("content", content);
            content_notification.put("type", type);
        } catch(JSONException e) {
            e.printStackTrace();
        }


        JSONObject custom_notification = new JSONObject();
        Double vibrate = new Double(300);
        try {
//            custom_notification.put("vibrate", "500");
            custom_notification.put("vibrate", vibrate);
            //custom_notification.put("fire_date", "NAME OF STUDENT");
            custom_notification.put("title", URLEncoder.encode(noti.getTitle(), String.valueOf(StandardCharsets.UTF_8)));
            custom_notification.put("body", URLEncoder.encode(noti.getBody(), String.valueOf(StandardCharsets.UTF_8)));
            custom_notification.put("priority", "high");
            custom_notification.put("show_in_foreground", "true");
            custom_notification.put("icon", "noti_icon");
            custom_notification.put("sound", "default");
            custom_notification.put("id", noti.getID());
            custom_notification.put("color", "#436D8F");
            custom_notification.put("content", content_notification.toString());
            custom_notification.put("channel", "common");

            Bundle bundle = BundleJSONConverter.convertToBundle(custom_notification);
            FIRLocalMessagingHelper helper = new FIRLocalMessagingHelper(this.getApplication());
            helper.sendNotification(bundle);

        } catch (JSONException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Map<String, String> data = remoteMessage.getData();

//        String customNotification = data.get("custom_notification");
//        if(customNotification != null){
//            try {
//                Log.d(TAG, "Shark: new FIRLocalMessagingHelper");
//                Bundle bundle = BundleJSONConverter.convertToBundle(new JSONObject(customNotification));
//                FIRLocalMessagingHelper helper = new FIRLocalMessagingHelper(this.getApplication());
//                helper.sendNotification(bundle);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    public MyNoti OnUserEvent(String action, String content)
    {
        //String a = NOTIFY_ACTION.REFRESH.getName();
        MyNoti noti = new MyNoti();
        String body = "";
        Boolean isContent = false;
        switch(NOTIFY_ACTION.valueOf(action))
        {
            case REFRESH:
                body = "User has updated.";
                break;
            case DELETE:
                body = "User has deleted.";
            case LOG_OUT:
                body = "User has expired.";
            case PWD_CHANGE:
                body = "User's login info has changed.";
            default:
                isContent = true;
                body = "User's profile has changed.";
                break;

        }
        noti.setTitle("CMS User.");
        noti.setBody(body);
        noti.setIsContent(isContent);
        noti.setID("user_notify");
        return noti;
    }

    public MyNoti OnSiteEvent(String action, String content)
    {
        //String a = NOTIFY_ACTION.REFRESH.getName();
        MyNoti noti = new MyNoti();
        String body = "";
        Boolean isContent = false;

        String Name = "";
        try {
            JSONObject jsonArray = new JSONObject(content);
            Name = jsonArray.get("Name").toString();
        }
        catch (Throwable t) {
            Name = "";
        }
        Log.d(TAG, "Shark: action Site: " + action);

        switch(NOTIFY_ACTION.valueOf(action))
        {
            case ADD:
                body = "Site '" + Name + "' has added.";
                break;
            case EDIT:
                body = "Site '" + Name + "' has updated.";
                break;
            case DELETE:
                body = "Site '" + Name + "' has deleted.";
                break;
            default:
                body = "";
                break;

        }
        noti.setTitle("CMS Site.");
        noti.setBody(body);
        noti.setIsContent(isContent);
        //noti.setID("sites_notify");
        return noti;
    }

    String MsgId(int altType, int kdvr)
    {
        if(altType != 0 && kdvr != 0)
            return "msg_health_" + kdvr + '_' + altType;
        if(kdvr != 0 && altType == 0)
            return "msg_health_" + kdvr;
        if( kdvr == 0 && altType != 0)
            return "msg_health_" + altType;

        return "msg_health";
    }
    public MyNoti OnAlertEvent(String action, String content)
    {
        //String a = NOTIFY_ACTION.REFRESH.getName();
        MyNoti noti = new MyNoti();
        String body = "";
        Boolean isContent = false;
        String id = "";

        String Name = "";
        String AlertType = "";
        //String isOffline = "";
//        final JSONObject jsonArray;
//        try {
//            jsonArray = new JSONObject(content);
//
//            Name = jsonArray.get("Name").toString();
//            AlertType = jsonArray.get("AlertType").toString();
//
//
//        }
//        catch (Throwable t) {
//            Name = "";
//            AlertType = "";
//        }
        Log.d(TAG, "Shark: action Site: " + action);
        Log.d("GOND", "GOND OnAlertEvent, action: " + NOTIFY_ACTION.valueOf(action) + ", content: " + content);

        switch(NOTIFY_ACTION.valueOf(action))
        {
            case NVR_STATUS:
                //{"AlertType":32,"isOffline":true,"NVRs":[{"Key":3028,"Value":"2017-10-18T08:21:00.9130117-04:00"}]}

                try {
                    JSONObject jsonArray = new JSONObject(content);

                    //Name = jsonArray.get("Name").toString();
                    AlertType = jsonArray.has("AlertType") == true ? jsonArray.get("AlertType").toString() : "";
                    String alt_name = EnumAlertType.getNameAlert(Integer.parseInt(AlertType));
                    if(jsonArray.has("NVRs") == true)
                    {
                        //int NVRs =Integer.parseInt(jsonArray.get("NVRs").toString());
                        //boolean isOffline = Boolean.parseBoolean(jsonArray.get("isOffline").toString());
                        JSONArray NVRs = jsonArray.getJSONArray("NVRs");
                        if(NVRs.length() > 0)
                        {
                            if(jsonArray.has("isOffline") == false || (Boolean) jsonArray.get("isOffline") == false) {
                                body= alt_name  + " resolved.";
                            } else {
                                int count = jsonArray.has("Count") == true ? (int)jsonArray.get("Count") : 1;
                                body= alt_name  + (count > 0 ? " (" + count + ")" : "");
                            }
                            id = MsgId(Integer.parseInt(AlertType), 0);
                        }
                        isContent = true;


                    }

                }
                catch (Throwable t) {
                    body = "";
                }

                break;
            case ADD:
                /*isContent = true;
                try {
                    JSONObject jsonArray = new JSONObject(content);

                    //Name = jsonArray.get("Name").toString();
                    AlertType = jsonArray.has("AlertType") == true ? jsonArray.get("AlertType").toString() : "";
                    body = EnumAlertType.getNameAlert(Integer.parseInt(AlertType));
                    String KDVR = jsonArray.has("KDVR") == true ? jsonArray.get("KDVR").toString() : "" ;
                    id = MsgId(Integer.parseInt(AlertType), Integer.parseInt(KDVR));


                }
                catch (Throwable t) {
                    body = "";
                }

                break;*/
            case EDIT:
                isContent = true;
                try {
                    JSONObject jsonArray = new JSONObject(content);
                    String siteName = jsonArray.has("SiteName") == true ? (jsonArray.get("SiteName").toString() + ": ") : "";
                    //Name = jsonArray.get("Name").toString();
                    AlertType = jsonArray.has("AlertType") == true ? jsonArray.get("AlertType").toString() : "";
                    body = siteName + EnumAlertType.getNameAlert(Integer.parseInt(AlertType));
                    String KDVR = jsonArray.has("KDVR") == true ? jsonArray.get("KDVR").toString() : "" ;
                    id = MsgId(Integer.parseInt(AlertType), Integer.parseInt(KDVR));


                }
                catch (Throwable t) {
                    body = "";
                }
                break;
            case DELETE:
                isContent = false;
                try {
                    JSONObject jsonArray = new JSONObject(content);

                    //Name = jsonArray.get("Name").toString();
                    String siteName = jsonArray.has("SiteName") == true ? (jsonArray.get("SiteName").toString() + ": ") : "";
                    AlertType = jsonArray.has("AlertType") == true ? jsonArray.get("AlertType").toString() : "";
                    body = EnumAlertType.getNameAlert(Integer.parseInt(AlertType));
                    body = siteName + body + " resolved.";
                    String KDVR = jsonArray.has("KDVR") == true ? jsonArray.get("KDVR").toString() : "" ;
                    id = MsgId(Integer.parseInt(AlertType), Integer.parseInt(KDVR));


                }
                catch (Throwable t) {
                    body = "";
                }

                break;
            case REFRESH:
                isContent = true;
                body = "Alert dismissed.";
                break;
            case DIMISS_BLOCK:
                isContent = false;
                try {
                    JSONObject jsonArray = new JSONObject(content);

                    int Total = jsonArray.has("Total") == true ? Integer.parseInt(jsonArray.get("Total").toString()) : 0;
                    //AlertType = jsonArray.get("AlertType") != null ? jsonArray.get("AlertType").toString() : "";

                    if(jsonArray.has("User") == false)
                    {
                        body = "User ";
                    }
                    else
                    {
                        JSONObject jsonUser = new JSONObject(jsonArray.get("User").toString());
                        body = jsonUser.has("FName") == true ? jsonUser.get("FName").toString() + " " : "";
                        body += jsonUser.has("LName") == true ? jsonUser.get("LName").toString() : "";

                    }
                    body += " dismissed " + Total;
                    body += Total > 1? " alerts." : " alert";

                }
                catch (Throwable t) {
                    body = "";
                }
                break;
            case DIMISS:
                isContent = false;
                try {
                    JSONObject jsonArray = new JSONObject(content);

                    if(jsonArray.has("Detail") == true)
                    {
                        JSONObject jsonDetail = new JSONObject(jsonArray.get("Detail").toString());
                        if(jsonDetail.has("User") == false)
                        {
                            body = "User ";
                        }
                        else
                        {
                            JSONObject jsonUser = new JSONObject(jsonDetail.get("User").toString());
                            body = jsonUser.has("FName") == true ? jsonUser.get("FName").toString() + " " : "";
                            body += jsonUser.has("LName") == true ? jsonUser.get("LName").toString() : "";
                        }
                    }
                    body += " dismissed ";
                    AlertType = jsonArray.has("AlertType") == true ? jsonArray.get("AlertType").toString() : "";
                    String AlertName = EnumAlertType.getNameAlert(Integer.parseInt(AlertType));
                    if(AlertName != "")
                    {
                        body += AlertName;
                    }
                    else
                    {
                        body += "alert.";
                    }

                }
                catch (Throwable t) {
                    body = "";
                }

                break;
            default:
                body = "";
                break;

        }
        noti.setTitle("CMS Health.");
        noti.setBody(body);
        noti.setIsContent(isContent);
        noti.setID(id);
        return noti;
    }

//    public MyNoti OnPVMEvent(String action, String content) {
//
//    }
	/**
	* CM's code: end
	*/
}
