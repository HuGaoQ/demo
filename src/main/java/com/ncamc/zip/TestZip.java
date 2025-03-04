package com.ncamc.zip;

import com.ncamc.base.BaseController;
import com.ncamc.zip.utils.WebUtils;

import java.io.ByteArrayOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestZip extends BaseController {
    public static void main(String[] args) throws Exception {
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
