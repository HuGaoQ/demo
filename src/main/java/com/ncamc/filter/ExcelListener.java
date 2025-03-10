package com.ncamc.filter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EasyExcel 监听器
 * 用于解析excel数据
 */
@Slf4j
@Getter
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private final List<T> rows = new ArrayList<>();

    private final AtomicInteger sucTotal = new AtomicInteger();

    @Override
    public void invoke(T data, AnalysisContext context) {
        rows.add(data);
        sucTotal.getAndIncrement();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("数据解析完成，共解析到 {} 条数据。", sucTotal);
    }
}
