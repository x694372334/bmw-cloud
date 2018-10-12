package com.bmw.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.ParkingInfo;
import com.bmw.property.service.IParkingInfoService;
import com.bmw.property.warpper.ParkingInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/parkingInfo")
public class ParkingInfoController extends BaseController {

    private String PREFIX = "/property/parkingInfo/";

    @Autowired
    private IParkingInfoService parkingInfoService;

    /**
     * 跳转到楼宇管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "parkingInfo.html";
    }

    /**
     * 跳转到添加楼宇管理
     */
    @RequestMapping("/parkingInfo_add")
    public String parkingInfoAdd() {
        return PREFIX + "parkingInfo_add.html";
    }

    /**
     * 跳转到修改楼宇管理
     */               
    @RequestMapping("/parkingInfo_update/{parkingInfoId}")
    public String parkingInfoUpdate(@PathVariable Integer parkingInfoId, Model model) {
    	ParkingInfo buildingInfo = parkingInfoService.getdetail(parkingInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "parkingInfo_edit.html";
    }
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/parkingInfo_detail/{parkingInfoId}")
    public String buildingInfoDetail(@PathVariable Integer parkingInfoId, Model model) {
    	ParkingInfo parkingInfo = parkingInfoService.getdetail(parkingInfoId);
        model.addAttribute("item",parkingInfo);
        LogObjectHolder.me().set(parkingInfo);
        return PREFIX + "parkingInfo_detail.html";
    }
    
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/parkingInfo_vehicle/{parkingInfoId}")
    public String vehicle(@PathVariable Integer parkingInfoId, Model model) {
    	ParkingInfo parkingInfo = parkingInfoService.getdetail(parkingInfoId);
        model.addAttribute("item",parkingInfo);
        LogObjectHolder.me().set(parkingInfo);
        return PREFIX + "parkingInfo_vehicle.html";
    }
    
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/parkingInfo_reader")
    public String reader() {
        return PREFIX + "parkingInfo_reader.html";
    }

    /**
     * 获取楼宇管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String pNum , String nName) {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	return super.warpObject(new ParkingInfoWarpper(parkingInfoService.findQuery(pNum , nName,eId.toString())));
    }

    /**
     * 新增楼宇管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ParkingInfo parkingInfo) {
        parkingInfoService.add(parkingInfo);
        return SUCCESS_TIP;
    }

    
    @RequestMapping(value = "/selectData")
    @ResponseBody
    public Object selectData(Integer nbId,Integer id,Integer relevanceId) {
    	return parkingInfoService.selectData(nbId,id,relevanceId);
    }
    
    /**
     * 删除楼宇管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer parkingInfoId) {
        parkingInfoService.del(parkingInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改楼宇管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ParkingInfo parkingInfo) {
        parkingInfoService.update(parkingInfo);
        return SUCCESS_TIP;
    }

    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/detail/{parkingInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("parkingInfoId") Integer parkingInfoId) {
        return parkingInfoService.getdetail(parkingInfoId);
    }
    
    /**
     * 查询住户列表
     */
    @RequestMapping(value = "/findInhabitant/{parkingInfoId}")
    @ResponseBody
    public Object findNeighbor(@PathVariable("parkingInfoId") Integer parkingInfoId) {
        return parkingInfoService.findInhabitant(parkingInfoId);
    }
    
    /**
     * 查询住户列表
     */
    @RequestMapping(value = "/findIVehicle/{parkingInfoId}")
    @ResponseBody
    public Object findIVehicle(@PathVariable("parkingInfoId") Integer parkingInfoId) {
        return parkingInfoService.findIVehicle(parkingInfoId);
    }
    /**
      * 方法名称 : createCWTree
      * 作者 : wangliqing  
      * 方法描述 : 创建车位Tree
      * 创建时间 : 2018年6月29日 下午2:07:43    
      * 返回类型 : List<ZTreeNode>    
     */
    @RequestMapping(value="/createCWTree/{nbId}")
    @ResponseBody
    public List<ZTreeNode> createCWTree(@PathVariable("nbId") Integer nbId) {
    	//TODO 后续从系统中取得eId，此处测试暂时放1
        return parkingInfoService.createCWTree(ShiroKit.getUser().geteId(),nbId);
    }
    
    
    /**
     * Excel生成
     * @throws Exception 
     */      
    @RequestMapping(value = "/impExcel/{rId}")
    @ResponseBody
    public Object impExcel(@PathVariable("rId") String rId , HttpServletResponse response) throws Exception {
    	JSONObject json = new JSONObject();
    	json.put("address", "e:\\cwdr.xls");
    	if(rId=="0") {
    		return json;
    	}
     	List<InhabitantInfo> inhabitant = parkingInfoService.findInhabitantInfo(Integer.parseInt(rId));
    	
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
    	row.createCell(0).setCellValue("车位号");
    	row.createCell(1).setCellValue("车位类型");
    	row.createCell(2).setCellValue("面积");
    	row.createCell(3).setCellValue("状态");
    	row.createCell(4).setCellValue("住户");
    	row.createCell(5).setCellValue("车牌号");
    	       //创建一个输入流
    	FileOutputStream fileOutputStream = new FileOutputStream("e:\\cwdr.xls");
    	
    	String[] cwzt = { "空闲", "已售", "已租" , "自用" };
    	String[] cwlx = { "租用", "出售"};
    	String[] zhxx = new String[inhabitant.size()];
//    	inhabitant.toArray(zhxx);
    	for(int i=0 , len = inhabitant.size();i<len;i++) {
    		zhxx[i]=inhabitant.get(i).getiName()+"|"+inhabitant.get(i).getiId();
    	}
    	 sheet = setHSSFValidation(sheet, cwzt, 0, 500, 3, 3);
    	 sheet = setHSSFValidation(sheet, cwlx, 0, 500, 1, 1);
    	 sheet = setHSSFValidation(sheet, zhxx, 0, 500, 4, 4);
    	//写入
    	wb.write(fileOutputStream);
    	
    	/**下载*/
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	wb.write(os);
    	byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
    	  response.reset();
          response.setContentType("application/vnd.ms-excel;charset=utf-8");
          response.setHeader("Content-Disposition", "attachment;filename="+ new String(("cwdr" + ".xls").getBytes(), "iso-8859-1"));
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
    
    /**
     * 上传图片
     */
    @RequestMapping(value = "/imageUpload")
    @ResponseBody
    public JSONObject imageUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,String nId ) {
    	JSONObject json=new JSONObject();
//    	String[] data = nId.split(",");
    	String rcode = "" ;
//    	nId=data[1];
    	String bId = "" ;
    	parkingInfoService.impExcel(file,nId,rcode,bId);
    	return json;
    }
    
    /**
     * 查询楼宇管理
     */
    @RequestMapping(value = "/findBuilding/{nId}")
    @ResponseBody
    public Object findBuilding(@PathVariable("nId") Integer nId) {
        return parkingInfoService.findBuilding(nId);
    }
    
    /**
     * 查询房屋管理
     */
    @RequestMapping(value = "/findRoomInfo/{bId}")
    @ResponseBody
    public Object findRoomInfo(@PathVariable("bId") Integer bId) {
        return parkingInfoService.findRoomInfo(bId);
    }
    
    /**
     * 查询住户管理
     */
    @RequestMapping(value = "/findInhabitantInfo/{rId}")
    @ResponseBody
    public Object findInhabitantInfo(@PathVariable("rId") Integer rId) {
        return parkingInfoService.findInhabitantInfo(rId);
    }

    /**
     * 判断是否已经关联了收费项
     */
    @RequestMapping(value = "/isAssociated/{parkingInfoId}")
    @ResponseBody
    public boolean isAssociated(@PathVariable("parkingInfoId") Integer parkingInfoId) {
        return parkingInfoService.isAssociated(parkingInfoId);
    }
    
}
