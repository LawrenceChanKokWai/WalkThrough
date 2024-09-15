package com.kokwai.resumeapp.domain;

/**
 * @author chankokwai
 * @project ResumeApp
 * @date 15/9/24
 */
public class RequestContext {

    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    private RequestContext() {}

    public static void start()
    {
        USER_ID.remove();
    }

    public static void setUserId( Long userId )
    {
        USER_ID.set( userId );
    }

    public static Long getUserId()
    {
        return USER_ID.get();
    }

}
