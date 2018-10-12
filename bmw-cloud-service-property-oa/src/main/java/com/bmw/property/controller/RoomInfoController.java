package com.bmw.property.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.RoomInfo;
import com.bmw.property.service.IRoomInfoService;
import com.bmw.property.warpper.RoomInfoWarpper;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/roomInfo")
public class RoomInfoController extends BaseController {

    private String PREFIX = "/property/roomInfo/";

	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
    @Autowired
    private IRoomInfoService roomInfoService;

    /**
     * 跳转到房屋管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "roomInfo.html";
    }

    /**
     * 跳转到添加房屋管理
     */
    @RequestMapping("/roomInfo_add")
    public String roomInfoAdd() {
        return PREFIX + "roomInfo_add.html";
    }

    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/roomInfo_update/{roomInfoId}")
    public String roomInfoUpdate(@PathVariable Integer roomInfoId, Model model) {
    	RoomInfo buildingInfo = roomInfoService.getdetail(roomInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "roomInfo_edit.html";
    }
    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/roomInfo_detail/{roomInfoId}")
    public String buildingInfoDetail(@PathVariable Integer roomInfoId, Model model) {
    	RoomInfo roomInfo = roomInfoService.getdetail(roomInfoId);
        model.addAttribute("item",roomInfo);
        LogObjectHolder.me().set(roomInfo);
        return PREFIX + "roomInfo_detail.html";
    }
    
    
    
    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/roomInfo_reader")
    public String roomInfoReader() {
        return PREFIX + "roomInfo_reader.html";
    }
    
    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/roomInfo_butler/{roomInfoId}")
    public String buildingInfoButler(@PathVariable Integer roomInfoId, Model model) {
    	RoomInfo roomInfo = roomInfoService.getdetail(roomInfoId);
        model.addAttribute("item",roomInfo);
        LogObjectHolder.me().set(roomInfo);
        return PREFIX + "roomInfo_butler.html";
    }
    
    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/roomInfo_batchButler")
    public String buildingInfoBatchButler( Model model) {
//    	RoomInfo roomInfo = roomInfoService.getdetail(roomInfoId);
//        model.addAttribute("item",roomInfo);
//        LogObjectHolder.me().set(roomInfo);
    	JSONArray users=roomInfoService.findUser(ShiroKit.getUser().geteId());
    	model.addAttribute("users", users);
        return PREFIX + "roomInfo_batchButler.html";
    }
    

    /**
     * 获取房屋管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String xqName , String lyName , String fjName) {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	return super.warpObject(new RoomInfoWarpper(roomInfoService.findList(xqName , lyName , fjName,eId.toString())));
    }
    
    
    @RequestMapping(value = "/selectData")
    @ResponseBody
    public Object selectData(Integer nbId,Integer id,Integer relevanceId,Integer costId) {
    	return roomInfoService.selectData(nbId,id,relevanceId,costId);
    }
    

    /**
     * 新增房屋管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RoomInfo roomInfo) {
        roomInfoService.add(roomInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除房屋管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer roomInfoId) {
        roomInfoService.del(roomInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改房屋管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RoomInfo roomInfo) {
        roomInfoService.update(roomInfo);
        return SUCCESS_TIP;
    }
    
    /**
     * 修改房屋管理
     */
    @RequestMapping(value = "/BatchButler")
    @ResponseBody
    public Object BatchButler(@RequestBody Map<String,Object> paramMap) {
    	paramMap.put("stewardName",StringUtils.trim(paramMap.get("stewardName").toString()));
        return roomInfoService.BatchButler(paramMap);
    }

    /**
     * 房屋管理详情
     */
    @RequestMapping(value = "/detail/{roomInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("roomInfoId") Integer roomInfoId) {
        return roomInfoService.getdetail(roomInfoId);
    }
    
    /**
     * 生成小区、楼宇、房屋的树
     * @author 张珵珺
     */
    @RequestMapping(value="/createNBTree/{level}/{nbId}")
    @ResponseBody
    public List<ZTreeNode> createNBTree(@PathVariable("level") Integer level,@PathVariable("nbId") Integer nbId) {
    	Integer eId =(Integer) super.getSession().getAttribute("eId");
        return roomInfoService.createNBTree(eId,level,nbId);                  
        }                                                                                                                                                                                                                                                                         
    /**
     * 查询楼宇列表
     */
    @RequestMapping(value = "/findBuilding/{roomInfoId}")
    @ResponseBody
    public Object findNeighbor(@PathVariable("roomInfoId") Integer roomInfoId) {
        return roomInfoService.findBuilding(roomInfoId);
    }
    
    /**
     * 查询楼宇列表
     */
    @RequestMapping(value = "/findBuildingData/{roomInfoId}")
    @ResponseBody
    public Object findNeighborData(@PathVariable("roomInfoId") Integer roomInfoId) {
        return roomInfoService.findBuildingData(roomInfoId);
    }
    
    /**
     * 查询管家列表
     */
    @RequestMapping(value = "/findUser")
    @ResponseBody
    public Object findUser() {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
        return roomInfoService.findUser(eId);
    }
    
    /**
     * Excel生成
     * @throws Exception 
     */      
    @RequestMapping(value = "/impExcel/{nId}")
    @ResponseBody
    public Object impExcel(@PathVariable("nId") String nId , HttpServletResponse response) throws Exception {
    	String[] data = nId.split(",");
    	JSONArray jsonArray = roomInfoService.findBuilding(Integer.parseInt(data[1]));
    	
    	int iSize = jsonArray.size();
    	String[] ly = new String[iSize] ;
    	for (int i = 0; i < iSize; i++) {
    	JSONObject jsonObj = jsonArray.getJSONObject(i);
    	ly[i]=jsonObj.get("bName").toString();
    	}
    	
    	
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
    	row.createCell(0).setCellValue("楼宇");
    	row.createCell(1).setCellValue("单元");
    	row.createCell(2).setCellValue("楼层");
    	row.createCell(3).setCellValue("房间号");
    	row.createCell(4).setCellValue("房屋类型");
    	row.createCell(5).setCellValue("入住状态");
    	row.createCell(6).setCellValue("建筑面积");
    	row.createCell(7).setCellValue("套内面积");
    	row.createCell(8).setCellValue("公摊面积");
    	row.createCell(9).setCellValue("房屋举架");
    	row.createCell(10).setCellValue("户型");
    	       //创建一个输入流
    	FileOutputStream fileOutputStream = new FileOutputStream("e:\\fwdr.xls");
    	 String[] fwlx = { "住宅", "公寓", "办公", "商铺", "厂房" , "仓库" , "酒店" , "别墅","其他" };
    	 String[] rzzt = { "未迁入", "已迁入", "已迁出" };
    	 sheet = setHSSFValidation(sheet, ly, 0, 500, 0, 0);
    	 sheet = setHSSFValidation(sheet, fwlx, 0, 500, 4, 4);
    	 sheet = setHSSFValidation(sheet, rzzt, 0, 500, 5, 5);
    	//写入
    	wb.write(fileOutputStream);
    	JSONObject json = new JSONObject();
    	json.put("address", "e:\\fwdr.xls");
    	
    	/**下载*/
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	wb.write(os);
    	byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
    	  response.reset();
          response.setContentType("application/vnd.ms-excel;charset=utf-8");
          response.setHeader("Content-Disposition", "attachment;filename="+ new String(("fwdr" + ".xls").getBytes(), "iso-8859-1"));
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
    	String[] data = nId.split(",");
    	String rcode = data[0] ;
    	nId=data[1];
    	String bId = "" ;
		roomInfoService.impExcel(file,nId,rcode,bId);
    	return json;
    }
    
	/**
	 * 生成树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryNBTree")
	@ResponseBody
	public List<ZTreeNode> queryNBTree() {
		// 小区活动id
		List<ZTreeNode> list = new ArrayList<>();
		list = roomInfoService.queryNBTree(ShiroKit.getUser().geteId());
		return list;
	}
    
    
}
