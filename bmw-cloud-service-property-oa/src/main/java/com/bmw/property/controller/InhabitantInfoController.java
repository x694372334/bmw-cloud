package com.bmw.property.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.ApplyInfo;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.RoomInfo;
import com.bmw.property.service.IInhabitantInfoService;
import com.bmw.property.warpper.InhabitantInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 住户管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/inhabitantInfo")
public class InhabitantInfoController extends BaseController {

    private String PREFIX = "/property/inhabitantInfo/";

    @Autowired
    private IInhabitantInfoService inhabitantInfoService;
    

    /**
     * 跳转到住户管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "inhabitantInfo.html";
    }

    /**
     * 跳转到添加住户管理
     */
    @RequestMapping("/inhabitantInfo_add")
    public String inhabitantInfoAdd() {
    		return PREFIX + "inhabitantInfo_add.html";
    }

    /**
     * 跳转到修改住户管理
     */
    @RequestMapping("/inhabitantInfo_update/{inhabitantInfoId}")
    public String inhabitantInfoUpdate(@PathVariable Integer inhabitantInfoId, Model model) {
        InhabitantInfo inhabitantInfo = inhabitantInfoService.getdetail(inhabitantInfoId);
        model.addAttribute("item",inhabitantInfo);
        LogObjectHolder.me().set(inhabitantInfo);
        return PREFIX + "inhabitantInfo_edit.html";
    }
    
    
    /**
     * 跳转到修改住户管理
     */
    @RequestMapping("/inhabitantInfo_detail/{inhabitantInfoId}")
    public String buildingInfoDetail(@PathVariable Integer inhabitantInfoId, Model model) {
        InhabitantInfo inhabitantInfo = inhabitantInfoService.getdetail(inhabitantInfoId);
        model.addAttribute("item",inhabitantInfo);
        LogObjectHolder.me().set(inhabitantInfo);
        return PREFIX + "inhabitantInfo_detail.html";
    }
    
    /**
     * 跳转到修改住户管理
     */
    @RequestMapping("/inhabitantInfo_reader")
    public String buildingInfoReader() {
        return PREFIX + "inhabitantInfo_reader.html";
    }
    
	/**
	 * 跳转到事项审批页面
	 */
	@RequestMapping("/inhabitantInfo_discounts_approval/{id}/{taskId}")
	public String billDiscountsView(@PathVariable String taskId, @PathVariable String id ,Model model) {
		String[] businessId = id.split("\\.");
		InhabitantInfo applyInfo = inhabitantInfoService.getdetail(Integer.parseInt(businessId[1]));
		Map<String,String> map = inhabitantInfoService.findUserById(applyInfo.getiPhoneno());
		model.addAttribute("item", applyInfo);
		model.addAttribute("data", map);
		model.addAttribute("taskId", taskId);
		LogObjectHolder.me().set(applyInfo);
		LogObjectHolder.me().set(taskId);
		return PREFIX + "inhabitantInfo_flowable_approval.html";
	}
	
    /**
     * 跳转到修改审批申请单
     */
    @RequestMapping("/inhabitantInfo_approval/{inhabitantInfoId}")
    public String applyInfoApproval(@PathVariable Integer inhabitantInfoId, Model model) {
    	InhabitantInfo inhabitantInfo = inhabitantInfoService.getdetail(inhabitantInfoId);
        model.addAttribute("item",inhabitantInfo);
        LogObjectHolder.me().set(inhabitantInfo);
        return PREFIX + "inhabitantInfo_approval.html";
    }


    /**
     * 获取住户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String xqName , String lyName , String fjName , String zhName , String phoneNo) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new InhabitantInfoWarpper(inhabitantInfoService.findQuery(xqName , lyName , fjName,zhName,phoneNo,eId.toString())));
    }

    /**
     * 新增住户管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InhabitantInfo inhabitantInfo) {
    	JSONArray list = inhabitantInfoService.findList(inhabitantInfo.getrId().toString());
    	boolean data = true;
    	  for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = (JSONObject) list.get(i);
            if(jsonObject.get("isHOwner").equals("1")) {
            	data = false;
            };
           }
    	  if(data) {
    		  inhabitantInfoService.add(inhabitantInfo);
    		  return SUCCESS_TIP;
    	  }else {
    		  return SUCCESS_TIP;
    	  }
    }

    /**
     * 删除住户管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer inhabitantInfoId) {
        inhabitantInfoService.del(inhabitantInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改住户管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InhabitantInfo inhabitantInfo) {
        inhabitantInfoService.update(inhabitantInfo);
        return SUCCESS_TIP;
    }

    /**
     * 住户管理详情
     */
    @RequestMapping(value = "/detail/{inhabitantInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("inhabitantInfoId") Integer inhabitantInfoId) {
        return inhabitantInfoService.getdetail(inhabitantInfoId);
    }
    
    /**
     * 查询住户列表
     */
    @RequestMapping(value = "/findRoomInfo")
    @ResponseBody
    public Object findNeighbor() {
        return inhabitantInfoService.findRoomInfo();
    }
    
    /**
     * 查询房屋列表
     */
    @RequestMapping(value = "/findRoomInfoExcel")
    @ResponseBody
    public Object findRoomInfo(String bId) {
        return inhabitantInfoService.findRoomInfoExcel(bId);
    }
    
    /**
     * Excel生成
     * @throws Exception 
     */      
    @RequestMapping(value = "/impExcel/{nId}/{bId}")
    @ResponseBody
    public Object impExcel(@PathVariable("nId") String nId , @PathVariable("bId") String bId  , HttpServletResponse response) throws Exception {
    	HSSFWorkbook wb = new HSSFWorkbook();

    	HSSFCellStyle setBorder = wb.createCellStyle();

    	setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
    	
    	HSSFFont font = wb.createFont();
    	font.setFontName("黑体");
    	font.setFontHeightInPoints((short) 16);//设置字体大小
    	HSSFFont font2 = wb.createFont();
    	font2.setFontName("仿宋_GB2312");
    	font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
    	font2.setFontHeightInPoints((short) 12);
    	setBorder.setFont(font);//选择需要用到的字体格式
    	
    	HSSFSheet sheet = wb.createSheet();
        //创建行
    	Row row = sheet.createRow(0);
    	    	 
    	//创建单元格
    	row.createCell(0).setCellValue("房屋");
    	row.createCell(1).setCellValue("姓名");
    	row.createCell(2).setCellValue("性别");
    	row.createCell(3).setCellValue("证件号码");
    	
    	row.createCell(4).setCellValue("手机");
    	row.createCell(5).setCellValue("身份");
    	row.createCell(6).setCellValue("是否房主");
    	row.createCell(7).setCellValue("入住状态");
    	row.createCell(8).setCellValue("爱好");
    	row.createCell(9).setCellValue("职业");
    	row.createCell(10).setCellValue("公司");
    	row.createCell(11).setCellValue("宠物");
    	
    	row.createCell(99).setCellValue(bId);
    	
		// 设置为文本格式，防止身份证号变成科学计数法    
    	HSSFCellStyle styleText = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		styleText.setDataFormat(format.getFormat("@"));
    	for (int s = 1; s < 501; s++) {
			Row rowID = sheet.createRow(s);
			Cell cellText = rowID.createCell(3);
			cellText.setCellStyle(styleText);
		}
    	
    	       //创建一个输入流
    	FileOutputStream fileOutputStream = new FileOutputStream("e:\\zhdr.xls");
    	
    	String[] zhsf = { "男", "女", "其他" };
    	String[] isHouse = { "是", "否"};
    	String[] rzzt = { "未迁入", "已迁入", "已迁出" };
    	String[] sf = { "业主本人", "业主家人", "业主亲属" , "租户" };
    	JSONArray jsonArray = inhabitantInfoService.findRoomInfoExcel(bId);
    	int iSize = jsonArray.size();
    	String[] roomRum = new String[iSize] ;
    	for (int i = 0; i < iSize; i++) {
    	JSONObject jsonObj = jsonArray.getJSONObject(i);
    	roomRum[i]=jsonObj.get("rRoomnum").toString();
    	}
    	
    	sheet = setHSSFValidation(sheet, roomRum, 1, 500, 0, 0);
    	sheet = setHSSFValidation(sheet, zhsf, 1, 500, 2, 2);
    	sheet = setHSSFValidation(sheet, sf, 1, 500, 5, 5);
    	sheet = setHSSFValidation(sheet, isHouse, 1, 500, 6, 6);
    	sheet = setHSSFValidation(sheet, rzzt, 1, 500, 7, 7);
    	 
    	//写入
    	wb.write(fileOutputStream);
    	JSONObject json = new JSONObject();
    	json.put("address", "e:\\zhdr.xls");
    	
    	/**下载*/
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	wb.write(os);
    	byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
    	  response.reset();
          response.setContentType("application/vnd.ms-excel;charset=utf-8");
          response.setHeader("Content-Disposition", "attachment;filename="+ new String(("zhrs" + ".xls").getBytes(), "iso-8859-1"));
          ServletOutputStream out = response.getOutputStream();
          BufferedInputStream bis = null;
          BufferedOutputStream bos = null;
          try {
              bis = new BufferedInputStream(is);
              bos = new BufferedOutputStream(out);
              byte[] buff = new byte[2048];
              int bytesRead;
              while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                  bos.write(buff, 0, bytesRead);
              }
          } catch (final Exception e) {
              throw e;
          } finally {
              if (bis != null)
                  bis.close();
              if (bos != null)
                  bos.close();
          }
    	
        return json;
    }
    
    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     * 
     * @param sheet
     *            要设置的sheet.
     * @param textlist
     *            下拉框显示的内容
     * @param firstRow
     *            开始行
     * @param endRow
     *            结束行
     * @param firstCol
     *            开始列
     * @param endCol
     *            结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
            String[] textlist, int firstRow, int endRow, int firstCol,
            int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }
    
    
    /********************************工作流*/
    
    /**
     * 添加流程审批认证
     *
     * @param ea_user    用户Id
     * @param money     
     * @param descption 描述
     */
    @RequestMapping(value = "/flowabled_add")
    @ResponseBody
    public Object addExpense(InhabitantInfo inhabitantInfo) {
    	inhabitantInfoService.flowabled_add(inhabitantInfo);
        return SUCCESS_TIP;
    }
   
    
    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @RequestMapping(value = "/flowabled_apply")
    @ResponseBody
    public Object apply(String taskId , String iId , String user_id) {
    	inhabitantInfoService.flowabled_apply(taskId , iId , user_id);
       return SUCCESS_TIP;
    }
    
    /**
     * 拒绝
     */
    @ResponseBody
    @RequestMapping(value = "/flowabled_reject")
    public Object reject(String taskId , String iId) {
    	inhabitantInfoService.flowabled_reject(taskId , iId);
        return SUCCESS_TIP;
    }
    
    /**
     * 上传图片
     */
    @RequestMapping(value = "/imageUpload")
    @ResponseBody
    public JSONObject imageUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,String nId , String bId ) {
    	JSONObject json=new JSONObject();
    	String rcode = "" ;
    	inhabitantInfoService.impExcel(file,nId,rcode,bId);
    	return json;
    }
    
    
    /**
     * 住户管理详情
     */
    @RequestMapping(value = "/findByAllInha/{inhabitantInfoId}")
    @ResponseBody
    public Object findByAllInha(@PathVariable("inhabitantInfoId") Integer inhabitantInfoId) {
        return inhabitantInfoService.findByAllInha(inhabitantInfoId);
    }
    
    
}
