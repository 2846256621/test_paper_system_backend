package com.ydl.examantion.response;

/**
 * 公共代码枚举
 *
 * @author LiuMing
 * @since 2020/7/10
 */
public enum CommonCode implements ResultCode {

    /**
     * 成功状态码
     */
    SUCCESS(true, 10000, "操作成功"),

    /**
     * 失败状态码
     */
    FAIL(false, 99999, "操作失败"),

    /**
     * 非法参数
     */
    INVALID_PARAM(false, 99999, "非法参数"),

    /**
     * 查询数据库为空
     */
    SELECT_SQL_NULL(false, 99999, "查询数据库为空"),
    /**
     * 字符串转换数字失败
     */
    STRING_CONVERT_NUMBER(false, 99999, "字符串转换数字失败"),

    /**
     * 路径不存在，请检查路径是否正确
     */
    PATH_NOT_EXIST(false, 99999, "路径不存在"),

    /**
     * 无效的请求头
     */
    NULL_AUTH_HEADER(false, 99999, "无效的请求头"),

    /**
     * 错误的请求方式
     */
    WRONG_WAY_TO_REQUEST(false, 99999, "错误的请求方式"),

    /**
     * 服务暂时不可用
     */
    SERVER_NOT_SUPPORTED(false, 99999, "服务暂时不可用,请稍后再试"),

    /**
     * 连接服务器超时
     */
    CONNECTION_TIMED_OUT(false, 99999, "连接服务器超时,请稍后再试"),
    /**
     * 连接服务器超时
     */
    FORMATTING_PARAMETERS_FAILED(false, 99999, "格式化参数失败"),
    /**
     * MQ消费者消费数据失败
     */
    MQ_CONSUMER_CONSUMPTION_FAILURE(false, 99999, "MQ消费者消费数据失败"),

    /**
     * 请求超时,拒绝受理
     */
    REQUEST_EXPIRE(false, 99999, "请求已过期,拒绝受理"),
    /**
     * 重放攻击,拒绝受理
     */
    REQUEST_REPEAT(false, 99999, "请求重复,拒绝受理"),
    /**
     * 第三方api认证签名失败
     */
    SIGN_ERROR(false, 99999, "签名认证失败,请联系第三方技术人员"),

    /**
     * 信息有误,请联系客服
     */
    SERVICE_ERROR(false, 99999, "信息有误,请联系客服"),

    /**
     * 数据库记录重复
     */
    DATABASE_RECORD_REPETITION(false, 99999, "数据库中存在唯一约束相同的记录"),
    /**
     * 数据库异常，请联系管理员
     */
    DATABASE_ERROR(false, 99999, "数据库异常，请联系管理员"),
    /**
     * 数据库异常，请联系管理员
     */
    FEIGN_ERROR(false, 99999, "远程服务器请求超时，调用服务降级接口，请联系管理员"),
    /**
     * 系统忙状态码
     */
    SERVER_ERROR(false, 99999, "系统繁忙,请稍后重试"),

    /**
     * 注册成功
     */
    REGISTER_SUCCESS(true,10000,"注册成功");

    /**
     * 操作是否成功
     */
    boolean success;

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;

    /**
     * 构造方法
     *
     * @param success 是否成功
     * @param code    状态码
     * @param message 提示信息
     */
    CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
