package com.mfzp.network.utils;

/**
 * Created by mofang on 2016/9/14.
 */
public class RequestConstants {

    /**
     * HTTP Status Code
     */
    public static final int HTTP_OK = 200;  // 正常访问且有返回值
    public static final int HTTP_NO_CONTENT = 204;  // 正常访问没有返回值
    public static final int HTTP_BAD_REQUEST = 400; // 请求参数错误
    public static final int HTTP_FORBIDDEN = 403; // 没有权限，拒绝访问
    public static final int HTTP_NOT_FOUND = 404;   // 资源未找到
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500; // 服务器内部错误
    /**
     * 请求返回的状态值
     */
    public static final int REQUEST_SUCCESS = 0;    // 请求成功
    public static final int REQUEST_FAILURE = -1;   // 请求失败
    public static final int TOKEN_NOT_EXIST = -2;   // token不存在，需要重新登录以刷新token
    public static final int RESERVE_TIME_INCORRECT = -301;  // 面试时段不正确(调用预约时，面试时段已经结束)
    public static final int RESERVE_RESUME_NOT_EXIST = -302;    // 预约时，简历不存在
    public static final int RESERVE_RESUME_INCOMPLETE = -303;   // 预约时，简历不完整
    public static final int PARAM_INCORRECT = -4;   // 请求参数不正确
    public static final int CALL_TIME_LIMIT = -5;    // 拨打电话时间限制
    public static final int DECRYPTION_FAILURE = -6;    // 解密失败
    public static final int MOBILE_UNREGISTERED = -7;   // 手机号码未注册，该用户不存在，请直接使用验证码登录！
    public static final int NO_PASSWORD = -8;   // 没有密码登录


    /**
     * 自定义异常
     */
    public static final int EXCEPITON_TIME_OUT = 1101; //请求超时处理
    public static final int EXCEPITON_NETWORK_DISABLE = 1102; //网络未连接

    public static final int UNDEFINED_CODE = 1099;   // 未定义的响应码code
}
