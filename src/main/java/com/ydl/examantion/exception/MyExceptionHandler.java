package com.ydl.examantion.exception;

import com.google.common.collect.ImmutableMap;
import com.ydl.examantion.response.CommonCode;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;


/**
 * 全局的异常拦截器
 *
 * @author LiuMing
 * @since 2020/7/10
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    /**
     * 使用EXCEPTIONS存放异常类型和错误代码的映射，
     * ImmutableMap的特点的一旦创建不可改变，并且线程安全
     */
    public static ImmutableMap<Class<? extends Exception>, ResultCode> EXCEPTION;
    /**
     * 使用builder来构建一个异常类型和错误代码的异常
     */
    public static ImmutableMap.Builder<Class<? extends Exception>, ResultCode> builder = ImmutableMap.builder();

    static {
        //在类初始化时，map构造器中加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
        //请求方式不正确
        builder.put(HttpRequestMethodNotSupportedException.class, CommonCode.WRONG_WAY_TO_REQUEST);
        //缺少必要请求头
        builder.put(MissingRequestHeaderException.class, CommonCode.NULL_AUTH_HEADER);
        //数据库记录重复
        builder.put(DuplicateKeyException.class, CommonCode.DATABASE_RECORD_REPETITION);
        //一项操作成功地执行，但在释放数据库资源时发生异常
        builder.put(CleanupFailureDataAccessException.class, CommonCode.DATABASE_ERROR);
        //数据访问资源彻底失败，例如不能连接数据库
        builder.put(DataAccessResourceFailureException.class, CommonCode.DATABASE_ERROR);
        //Insert或Update数据时违反了完整性，例如违反了惟一性限制
        builder.put(DataIntegrityViolationException.class, CommonCode.DATABASE_ERROR);
        //某些数据不能被检测到，例如不能通过关键字找到一条记录
        builder.put(DataRetrievalFailureException.class, CommonCode.DATABASE_ERROR);
        //错误使用数据访问资源，例如用错误的SQL语法访问关系型数据库
        builder.put(InvalidDataAccessResourceUsageException.class, CommonCode.DATABASE_ERROR);
        //有错误发生，但无法归类到某一更为具体的异常中
        builder.put(UncategorizedDataAccessException.class, CommonCode.DATABASE_ERROR);
    }

//    /**
//     * 自定义异常 公共异常 （不会打印日志）
//     */
//    @ExceptionHandler(CustomNoOpsException.class)
//    public ResponseResult<Object> customNoOpsException(CustomNoOpsException e) {
//        ResponseResult<Object> responseResult = ResponseResult.code(e.getResultCode());
//        responseResult.setData(null);
//        return responseResult;
//    }
//
//    /**
//     * 自定义异常 公共异常 （打印info级别日志）
//     */
//    @ExceptionHandler(CustomInfoException.class)
//    public ResponseResult<Object> customInfoException(CustomInfoException e) {
//        log.info("捕获自定义认证异常",e);
//        ResponseResult<Object> responseResult = ResponseResult.code(e.getResultCode(),e.getMessage());
//        responseResult.setData(null);
//        return responseResult;
//    }

//    /**
//     * 自定义异常 公共异常 （打印error级别日志）
//     */
//    @ExceptionHandler(CustomErrorException.class)
//    public ResponseResult<Object> customErrorException(CustomErrorException e) {
//        log.error("捕获自定义异常", e);
//        ResponseResult<Object> responseResult = ResponseResult.code(e.getResultCode());
//        responseResult.setData(null);
//        return responseResult;
//    }

    /**
     * 请求参数校验(输出message信息)异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("请求参数校验(输出message信息)异常: {}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        if (e.getBindingResult().getFieldError() != null) {
            objectResponseResult.setMessage(this.getBindingResultMessage(e.getBindingResult()));
        } else {
            objectResponseResult.setMessage("参数校验不通过");
        }
        return objectResponseResult;
    }

    /**
     * 请求参数校验(类型转换)异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("请求参数校验(类型转换)异常:{}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        objectResponseResult.setMessage("参数（" + e.getName() + "）类型转换异常");
        return objectResponseResult;
    }

    /**
     * 请求参数校验(缺少)异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<Object> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("请求参数校验(缺少)异常:{}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        objectResponseResult.setMessage(e.getParameterName() + "为必须参数");
        return objectResponseResult;
    }

    /**
     * 请求参数校验(缺失)异常
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseResult<Object> unexpectedTypeException(UnexpectedTypeException e) {
        log.error("请求参数校验(缺少)异常:{}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        objectResponseResult.setMessage("请求参数校验(缺失)异常");
        return objectResponseResult;
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult<Object> bindException(BindException e) {
        log.error("参数校验异常:{}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        objectResponseResult.setMessage(this.getBindingResultMessage(e.getBindingResult()));
        return objectResponseResult;
    }

    /**
     * 请求参数校验(校验数值)异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult<Object> constraintViolationException(ConstraintViolationException e) {
        log.error("请求参数校验(校验数值)异常:{}", e.getMessage(), e);
        ResponseResult<Object> objectResponseResult = ResponseResult.code(CommonCode.INVALID_PARAM);
        objectResponseResult.setData(null);
        objectResponseResult.setMessage(e.getMessage());
        return objectResponseResult;
    }

//    /**
//     * 权限不足
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseResult<Object> accessDeniedException(AccessDeniedException e) {
//        log.info("权限不足:{}", e.getMessage(), e);
//        ResponseResult<Object> objectResponseResult = ResponseResult.code(AuthCode.UN_AUTHORISE);
//        objectResponseResult.setData(null);
//        return objectResponseResult;
//    }
//
    private String getBindingResultMessage(BindingResult bindingResult) {
        return "[" + bindingResult.getFieldError().getField() + "] " + bindingResult.getFieldError().getDefaultMessage();
    }

    /**
     * 所有异常捕获
     */
    @ExceptionHandler({Exception.class})
    public ResponseResult<Object> exception(Exception e) {
        log.error("捕获异常:",e);
        ResponseResult<Object> responseResult;
        //判断MAP是否为空
        if (ObjectUtils.isEmpty(EXCEPTION)) {
            EXCEPTION = builder.build();
        }
        ResultCode resultCode = EXCEPTION.get(e.getClass());
        if (ObjectUtils.isEmpty(resultCode)) {
            responseResult = ResponseResult.code(CommonCode.SERVER_ERROR);
        } else {
            responseResult = ResponseResult.code(resultCode);
        }
        responseResult.setData(null);
        return responseResult;
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder {@link WebDataBinder}
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }
}
