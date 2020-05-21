package com.demo.rest.controller;

import com.demo.rest.common.util.ExcelUtil;
import com.demo.rest.model.TestDateVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author littlenew
 * @date 2020/5/18 4:36 PM
 **/
@Controller
@RequestMapping(value = "/report")
public class ReportFormController {
    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<TestDateVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestDateVO testDateVO = new TestDateVO();
            testDateVO.setStuName("孩子" + i);
            testDateVO.setStuAge(1 + i);
            testDateVO.setStuSex(i % 2 == 0 ? (byte) 1 : (byte) 2);
            testDateVO.setStuClassName("1班");
            testDateVO.setStuSchoolName("xxx学校");
            list.add(testDateVO);
        }

        //excel标题
        String[] title = {"名称", "性别", "年龄", "学校", "班级"};

        //excel文件名
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "学生信息表";
        String[][] content = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            TestDateVO obj = list.get(i);
            content[i][0] = obj.getStuName();
            content[i][1] = obj.getStuSex().toString();
            content[i][2] = obj.getStuAge().toString();
            content[i][3] = obj.getStuSchoolName();
            content[i][4] = obj.getStuClassName();
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送响应流方法

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
