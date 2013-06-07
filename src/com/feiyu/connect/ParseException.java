package com.feiyu.connect;

/**    解析发生异常时抛出
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-24
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
public class ParseException extends Exception{

    private static final long serialVersionUID = 1L;
    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }


}
