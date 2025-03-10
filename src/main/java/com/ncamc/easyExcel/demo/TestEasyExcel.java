package com.ncamc.easyExcel.demo;

import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ncamc.easyExcel.entity.User;
import com.ncamc.filter.ExcelListener;
import com.ncamc.zip.utils.WebUtils;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) throws IOException {
        // 读取Excel数据
        readeExcel();
        // 写入Excel数据并下载
        writeDownload();
    }

    private static void writeDownload() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriter writer = EasyExcel.write(outputStream, User.class).build();
        WriteSheet sheet = EasyExcel.writerSheet("客户信息").build();
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setName("张三" + i);
            user.setAge(i);
            list.add(user);
        }
        writer.write(list, sheet);
        writer.finish();
        byte[] bytes = outputStream.toByteArray();
        outputStream.flush();
        WebUtils.out(request, response, bytes, "账号清单.xlsx", true);
    }

    private static void readeExcel() {
        ClassPathResource resource = new ClassPathResource("user.xlsx");
        InputStream inputStream = resource.getStream();
        ExcelListener<User> listener = new ExcelListener<>();
        ExcelReader reader = EasyExcel.read(inputStream, User.class, listener).excelType(ExcelTypeEnum.XLSX).build();
        reader.readAll();
        reader.finish();
        List<User> rows = listener.getRows();
        rows.forEach(System.out::println);
    }
}
