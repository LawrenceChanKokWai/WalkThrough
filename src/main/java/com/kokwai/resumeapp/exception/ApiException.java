package com.kokwai.resumeapp.exception;

/**
 * @author chankokwai
 * @project ResumeApp
 * @date 15/9/24
 */
public class ApiException extends RuntimeException {

    public ApiException( String message )
    {
        super( message );
    }

    public ApiException()
    {
        super( "An error occurred" );
    }

}
