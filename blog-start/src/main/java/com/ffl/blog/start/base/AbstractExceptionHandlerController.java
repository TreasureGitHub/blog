package com.ffl.blog.start.base;

import com.ffl.blog.common.constants.AppConstant;
import com.ffl.blog.common.constants.CharConstant;
import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.common.exceptions.runtime.BlogRuntimeException;
import com.ffl.blog.common.i18n.I18nArea;
import com.ffl.blog.pojo.common.CallResult;
import com.ffl.blog.pojo.common.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lff
 * @datetime 2020/05/07 17:42
 */
@ControllerAdvice
public abstract class AbstractExceptionHandlerController extends IBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExceptionHandlerController.class);

    private static I18nArea AREA = AppConstant.I18N_PAGE_CONTROLLER.of(AbstractExceptionHandlerController.class.getSimpleName());

    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public CallResult throwableHander(HttpServletRequest req, Throwable ex) {
        return commonResult(StatusCode.SERVER_SERVICE_EXCEPTION, req, ex);
    }

    private CallResult commonResult(StatusCode resultCode, HttpServletRequest request, Throwable throwable) {

        // 记录日志
        String queryString = request.getRequestURI() + (request.getQueryString() == null ? CharConstant.EMPTY : CharConstant.QUESTION + request.getQueryString());
        LOGGER.error(String.format("[API ERROR] [%s] [%s],info: %s", request.getMethod(), queryString, throwable.getMessage()), throwable);

        if (throwable instanceof BlogException) {
            BlogException e = (BlogException) throwable;
            return CallResult.error(resultCode, e.getI18nKey(), e);
        } else if (throwable instanceof BlogRuntimeException) {
            BlogRuntimeException e = (BlogRuntimeException) throwable;
            return CallResult.error(resultCode, e.getI18nKey(), e);
        } else {
            return CallResult.error(resultCode, AREA.of("commonResult", throwable.getMessage()), throwable);
        }
    }
}
