package com.commandcenter.common.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author r25437
 * @create 2018-08-01 10:14
 * @desc 响应封装
 **/
public class ResponseWrapper extends HttpServletResponseWrapper {
    ByteArrayOutputStream output;
    FilterServletOutputStream filterOutput;
    public ResponseWrapper(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
        output = new ByteArrayOutputStream();
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStream(output);
        }
        return filterOutput;
    }

    public byte[] getDataStream() {
        return output.toByteArray();
    }
}
