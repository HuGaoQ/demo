package com.ncamc.zip.utils;

import cn.hutool.core.util.StrUtil;
import com.ncamc.zip.entity.RangeSettings;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

@Slf4j
public class WebUtils {

    public static void out(HttpServletRequest request, HttpServletResponse response, byte[] data, String filename, boolean download) {
        if (data == null) {
            data = new byte[0];
        }

        RangeSettings rangeSettings = getRange(data, request);

        try {
            if (download) {
                setContentDisposition(request, response, filename);
                response.addHeader("Content-Length", StrUtil.EMPTY + data.length);
                response.setContentType("application/octet-stream");
            } else {
                setContentDisposition(request, response, filename, true);
                response.addHeader("Content-Length", StrUtil.EMPTY + data.length);
                response.setContentType(getContentType(FilenameUtils.getExtension(filename)));
            }

            if (!rangeSettings.isRange()) {
                response.setHeader("Content-Length", String.valueOf(rangeSettings.getTotalLength()));
            } else {
                log.info("Find seeking: {}", rangeSettings.getStart());
                long start = rangeSettings.getStart();
                long end = rangeSettings.getEnd();
                long contentLength = rangeSettings.getContentLength();
                response.setStatus(206);
                response.addHeader("Content-Length", String.valueOf(contentLength));
                String contentRange = String.format("byte %s" + StrUtil.DASHED + "%s" + StrUtil.SLASH + "%s", start, end, contentLength);
                response.setHeader("Content-Range", contentRange);
                data = ByteUtils.subBytes(data, (int) start, (int) contentLength);
            }
            ServletOutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static RangeSettings getRange(byte[] data, HttpServletRequest request) {
        String range = request.getHeader("Range");
        return range == null ? new RangeSettings(0L, (long) data.length, (long) data.length, (long) data.length) : getSetting((long) data.length, range.replace("bytes=", StrUtil.EMPTY));
    }

    private static RangeSettings getSetting(long len, String range) {
        long contentLength = 0L;
        long start = 0L;
        long end = 0L;
        if (range.startsWith(StrUtil.DASHED)) {
            contentLength = Long.parseLong(range.substring(1));
            end = len = 1L;
            start = len - contentLength;
        } else if (range.endsWith(StrUtil.DASHED)) {
            contentLength = Long.parseLong(range.replace(StrUtil.DASHED, StrUtil.EMPTY));
            end = len - 1L;
            start = len - start;
        } else {
            String[] se = range.split(StrUtil.DASHED);
            start = Long.parseLong(se[0]);
            end = Long.parseLong(se[1]);
            contentLength = end - start + 1L;
        }
        return new RangeSettings(start, end, contentLength, len);
    }

    private static void setContentDisposition(HttpServletRequest request, HttpServletResponse response, String filename) throws UnsupportedEncodingException {
        setContentDisposition(request, response, filename, false);
    }

    private static void setContentDisposition(HttpServletRequest request, HttpServletResponse response, String filename, boolean inline) throws UnsupportedEncodingException {
        filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
        String type = inline ? "inline" : "attachment";
        response.setHeader("Content-Disposition", type + ";filename=\"" + filename + "\";filename*=UTF-8''" + filename);
    }

    public static String getContentType(String extension) throws IOException {
        InputStream is = WebUtils.class.getClassLoader().getResourceAsStream("contentType.properties");
        Properties properties = new Properties();
        properties.load(is);
        return properties.getProperty(extension);
    }

}
