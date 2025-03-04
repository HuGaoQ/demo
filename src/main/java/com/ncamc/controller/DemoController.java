package com.ncamc.controller;

import com.ncamc.zip.utils.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/")
public class DemoController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @GetMapping
    public void demo() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CheckedOutputStream cos = new CheckedOutputStream(baos, new CRC32());
        ZipOutputStream zipOut = new ZipOutputStream(cos);

        String fileName = "空文件.pdf";
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        zipOut.write(new byte[0]);
        zipOut.closeEntry();
        zipOut.close();
        WebUtils.out(request, response, baos.toByteArray(), fileName, true);
    }
}
