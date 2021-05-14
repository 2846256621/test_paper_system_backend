package com.ydl.examantion.response;

/**
 * 结果代码接口
 *
 * @author LiuMing
 * @since 2020/7/10
 */
public interface ResultCode {
    /**
     * 响应状态，为 true 代表正常响应，为 false 代表异常响应
     *
     */
    boolean success();

    /**
     * 响应状态码
     *
     */
    int code();

    /**
     * 提示信息
     *
     */
    String message();
}
