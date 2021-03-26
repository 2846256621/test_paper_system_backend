package com.ydl.examantion.response;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *  统一的返回结果类
 * @author  LiuMing
 * @since  2020/7/10
 */
@Data
@ApiModel(value="响应结果",description="响应结果")
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应状态，为 true 代表正常响应，为 false 代表异常响应
     */
    @ApiModelProperty(value = "响应状态，为 true 代表正常响应，为 false 代表异常响应")
    boolean success ;

    /**
     * 响应状态码
     */
    @ApiModelProperty(value = "响应状态码")
    int code ;

    /**
     * 响应状态信息
     */
    @ApiModelProperty(value = "响应状态信息")
    String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public ResponseResult() {
    }

    /**
     * 构造方法 通过ResultCode 给各个属性赋值
     *
     * @param resultCode {@link ResultCode} 提供了公共的返回结果的信息
     * @author LiuMing
     * @date 2020/8/16
     */
    private ResponseResult(ResultCode resultCode) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    /**
     * 构造方法 通过ResultCode和message给各个属性赋值
     *
     * @param resultCode {@link ResultCode} 提供了公共的返回结果的信息
     * @param message 自定义错误信息
     * @author tanmingfei
     * @date 2020/10/17
     */
    private ResponseResult(ResultCode resultCode, String message) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = (( message == null || "".equals(message)) ? resultCode.message(): message);
    }

    /**
     * 返回有数据的返回结果
     * @param data {@link T } 返回的数据
     * @author LiuMing
     * @date 2020/8/16
     */
    private ResponseResult(T data) {
        this.success = CommonCode.SUCCESS.success();
        this.code = CommonCode.SUCCESS.code();
        this.message = CommonCode.SUCCESS.message();
        this.data = data;
    }

    /**
     * 操作成功
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult success(){
        ResponseResult<Object> result = new ResponseResult<>(CommonCode.SUCCESS);
        result.setData(null);
        return result ;
    }

    /**
     *  返回自定义Code信息
     * @param resultCode {@link ResultCode} 响应状态码枚举
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult code(ResultCode resultCode){
        ResponseResult<Object> responseResult = new ResponseResult<>(resultCode);
        responseResult.setData(null);
        return  responseResult;
    }

    /**
     *  返回自定义Code信息
     * @param resultCode {@link ResultCode} 响应状态码枚举
     * @return {@link ResponseResult}
     * @author tanmingfei
     * @date 2020/10/17
     */
    public static  ResponseResult code(ResultCode resultCode, String message){
        ResponseResult<Object> responseResult = new ResponseResult<>(resultCode,message);
        responseResult.setData(null);
        return  responseResult;
    }


    /**
     *  返回自定义数据信息
     * @param object {@link Object} 返回的数据
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult data(Object object){
        return new ResponseResult<>(object) ;
    }

    /**
     *  返回自定义数据信息
     * @param resultCode {@link ResultCode} 响应状态码枚举
     * @param object {@link Object} 返回的数据
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult codeAndData(ResultCode resultCode,Object object){
        ResponseResult responseResult = new ResponseResult<>(resultCode);
        responseResult.setData(object);
        return  responseResult;
    }

    /**
     *  返回分页信息
     *
     * @param page {@link Page} 分页实体类
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static <T> ResponseResult<PageModel<T>> page(Page<T> page){
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setCurrentPage(page.getCurrent());
        pageModel.setSize(page.getSize());
        pageModel.setTotalCount(page.getTotal());
        pageModel.setPages(page.getPages());
        pageModel.setDataList(page.getRecords());
        pageModel.setIsNext(page.hasNext());
        return new ResponseResult<>(pageModel) ;
    }

    /**
     * 操作失败
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult fail(){
        ResponseResult result = new ResponseResult<>(CommonCode.FAIL);
        result.setData(null);
        return result;
    }

    /**
     * 操作失败并返回自定义信息
     * @return {@link ResponseResult}
     * @author LiuMing
     * @date 2020/8/16
     */
    public static ResponseResult fail(String msg){
        ResponseResult<Object> result = new ResponseResult<>(CommonCode.FAIL);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
