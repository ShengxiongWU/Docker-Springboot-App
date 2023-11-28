package main;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class LoggingResponseWrapper extends HttpServletResponseWrapper {
    private static final Logger log = LoggerFactory.getLogger(LoggingResponseWrapper.class);

    public LoggingResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException, IOException {
        log.info("getOutputStream() was called", new Exception("Stack trace for getOutputStream() call"));
        return super.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        log.info("getWriter() was called", new Exception("Stack trace for getWriter() call"));
        return super.getWriter();
    }
}