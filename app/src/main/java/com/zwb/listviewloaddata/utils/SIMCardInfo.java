package com.zwb.listviewloaddata.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 类描述:获取SIM卡信息的工具类
 * 作者：zuowenbin
 * 时间：16-5-10 09:35
 * 邮箱：13716784721@163.com
 */
public class SIMCardInfo {

    /**
     * TelephonyManager提供设备上获取通讯服务信息的入口。 应用程序可以使用这个类方法确定的电信服务商和国家 以及某些类型的用户访问信息。
     * 应用程序也可以注册一个监听器到电话收状态的变化。不需要直接实例化这个类
     * 使用Context.getSystemService(Context.TELEPHONY_SERVICE)来获取这个类的实例。
     */
    private TelephonyManager manager;

    private String IMSI;    //国际移动用户识别码

    public SIMCardInfo(Context context){
        manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取当前手机的SIM卡手机号码
     * @return　NativePhoneNumber本机号码
     */
    public String getNativePhoneNumber(){
        String NativePhoneNumber = null;
        NativePhoneNumber = manager.getLine1Number();
        return manager.getLine1Number();
    }

    /**
     * 获取手机服务商信息
     * @return  ProvidersName手机服务商信息
     */
    public String getProvidersName(){
        String ProvidersName = null;
        //返回唯一的用户ID;就是这张卡的编号
        IMSI = manager.getSubscriberId();
        //IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (IMSI == null || "".equals(IMSI)){
            ProvidersName = "获取服务商信息失败";
        }else if (IMSI.startsWith("46000") || IMSI.startsWith("46002")){
            ProvidersName = "中国移动";
        }else if (IMSI.startsWith("46001")){
            ProvidersName = "中国联通";
        }else if (IMSI.startsWith("46003")){
            ProvidersName = "中国电信";
        }

        return ProvidersName;
    }

}
