package com.bmw.bill.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.common.utils.excel.ExportExcel;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillDiscountsVO;
import com.bmw.bill.model.BillVO;
import com.bmw.bill.service.IBillService;
import com.bmw.bill.warpper.BillWarpper;

/*import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;*/

/**
 * 账单明细控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 15:32:05
 */
@Controller
@RequestMapping("/bill")
public class BillController extends BaseController {

	private String PREFIX = "/property/bill/";

	@Autowired
	private IBillService billService;

	@Value("${modolePath}")
	private String modolePath;
	
	private final static String excel2003L =".xls";    //2003- 版本的excel  
	
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel 

	/**
	 * 跳转到账单明细首页
	 */
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("flag", "0");
		LogObjectHolder.me().set("0");
		return PREFIX + "bill.html";
	}

	/**
	 * 跳转账单查询
	 */
	@RequestMapping("/bill_billListAll")
	public String billListAll(Model model) {
		model.addAttribute("flag", "1");
		LogObjectHolder.me().set("1");
		return PREFIX + "bill.html";
	}

	/**
	 * 跳转到账单明细首页
	 */
	@RequestMapping("/bill_discounts")
	public String bill_discounts() {
		return PREFIX + "bill_discounts.html";
	}

	/**
	 * 跳转到添加账单明细
	 */
	@RequestMapping("/bill_add")
	public String billAdd() {
		return PREFIX + "bill_add.html";
	}

	/**
	 * 跳转到修改账单明细
	 */
	@RequestMapping("/bill_update/{billId}")
	public String billUpdate(@PathVariable Integer billId, Model model) {
		BillVO bill = billService.getdetail(billId);
		model.addAttribute("item", bill);
		LogObjectHolder.me().set(bill);
		return PREFIX + "bill_edit.html";
	}

	/**
	 * 跳转到收费页面
	 */
	@RequestMapping("/bill_charge/{ids}")
	public String billCharge(@PathVariable String ids, Model model) {
		model.addAttribute("ids", ids);
		LogObjectHolder.me().set(ids);
		return PREFIX + "bill_charge.html";
	}

	/**
	 * 跳转到收费查看页面
	 */
	@RequestMapping("/bill_charge_view/{ids}")
	public String billChargeView(@PathVariable String ids, Model model) {
		model.addAttribute("ids", ids);
		LogObjectHolder.me().set(ids);
		return PREFIX + "bill_charge_view.html";
	}

	/**
	 * 跳转到优惠申请详情
	 */
	@RequestMapping("/bill_discounts_view/{billId}/{discountsId}")
	public String billDiscountsView(@PathVariable Integer billId,@PathVariable Integer discountsId, Model model) {
		BillDiscountsVO bill = billService.getBillDiscountsVO(billId,discountsId);
		model.addAttribute("item", bill);
		LogObjectHolder.me().set(bill);
		return PREFIX + "bill_discounts_view.html";
	}
	
	/**
	 * 跳转到优惠审批页面
	 */
	@RequestMapping("/bill_discounts_approval/{id}/{taskId}")
	public String billDiscountsView(@PathVariable String taskId, @PathVariable String id ,Model model) {
		String[] businessId = id.split("-");
		BillDiscountsVO bill = billService.getBillDiscountsVO(Integer.parseInt(businessId[0]),Integer.parseInt(businessId[1]));
		model.addAttribute("item", bill);
		model.addAttribute("taskId", taskId);
		LogObjectHolder.me().set(bill);
		LogObjectHolder.me().set(taskId);
		return PREFIX + "bill_discounts_approval.html";
	}
	

	/**
	 * 跳转到申请优惠页面
	 */
	@RequestMapping("/bill_discounts/{billId}")
	public String billDiscounts(@PathVariable Integer billId, Model model) {
		BillVO bill = billService.getdetail(billId);
		model.addAttribute("item", bill);
		LogObjectHolder.me().set(bill);
		return PREFIX + "bill_discounts_edit.html";
	}

	/**
	 * 跳转Tab页
	 * 
	 */
	@RequestMapping("/bill_tab")
	public String billTab() {
		return PREFIX + "bill_tab.html";
	}

	/**
	 * 获取账单明细列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(Bill condition) {
		return new BillWarpper(billService.findList(condition)).warp();
	}

	/**
	 * 获取所有账单
	 */
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Object listAll(Bill condition) {
		return new BillWarpper(billService.findListAll(condition)).warp();
	}

	/**
	 * 获取账单明细列表
	 */
	@RequestMapping(value = "/list_discounts")
	@ResponseBody
	public Object listDiscounts(Bill condition) {
		
		return new BillWarpper(billService.billListDiscount(condition)).warp();
	}

	/**
	 * 根据id获取账单明细列表
	 */
	@RequestMapping(value = "/list/{ids}")
	@ResponseBody
	public Object list(String ids) {
		ids = ids.substring(0, ids.length() - 1);
		return billService.billListByIds(ids);
	}

	/**
	 * 提交缴费
	 */
	@RequestMapping(value = "/changeCommit")
	@ResponseBody
	public Object changeCommit(String ids, String payWay, String remark) {
		billService.changeCommit(ids, payWay, remark);
		return SUCCESS_TIP;
	}

	/**
	 * 提交优惠申请
	 */
	@RequestMapping(value = "/discountsCommit")
	@ResponseBody
	public Object discountsCommit(String billId, String reason, String discountsAmount, String latenessOffer) {
		billService.discountsCommit(billId, reason, discountsAmount, latenessOffer);
		return SUCCESS_TIP;
	}

	/**
	 * 新增账单明细
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Bill bill) {
		billService.add(bill);
		return SUCCESS_TIP;
	}

	/**
	 * 删除账单明细
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer billId) throws Exception {
		return billService.del(billId);
	}

	/**
	 * 修改账单明细
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Bill bill) {
		billService.update(bill);
		return SUCCESS_TIP;
	}

	/**
	 * 账单明细详情
	 */
	@RequestMapping(value = "/detail/{billId}")
	@ResponseBody
	public Object detail(@PathVariable("billId") Integer billId) {
		return billService.getdetail(billId);
	}
	
	/**
	 * 通过优惠申请
	 */
	@RequestMapping(value = "/discountsPass/{taskId}/{billId}")
	@ResponseBody
	public Object discountsPass(@PathVariable("billId") Integer billId,@PathVariable("taskId") String taskId) {
		billService.discountsPass(billId,taskId );
		return SUCCESS_TIP;
	}
	
	/**
	 * 终止优惠申请
	 */
	@RequestMapping(value = "/discountsEnd/{taskId}/{billId}")
	@ResponseBody
	public Object discountsEnd(@PathVariable("billId") Integer billId,@PathVariable("taskId") String taskId) {
		billService.discountsEnd(billId,taskId );
		return SUCCESS_TIP;
	}

	/**
	 * 导入账单
	 */
	@RequestMapping("/bill_leading")
	public String billLeading() {
		return PREFIX + "bill_leading.html";
	}

	/**
     * 导出收费账单
	 * @throws Exception 
     */
    @RequestMapping("/bill_export")
    public void billExport( Bill condition ,HttpServletResponse response) throws Exception {
    	try {
	    	JSONArray result = billService.findList(condition);
	    	String jsonStr = JSONObject.toJSONString(result);
	    	List<Bill> list = JSONObject.parseArray(jsonStr,  Bill.class);
	    	String fileName = "账单数据.xlsx";
//	 		new ExportExcel("账单数据", Bill.class,1, 1).setDataList(list).write(response, fileName).dispose();
    	} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
    }

    
    /**
     * 导出所有账单
     * @throws Exception 
     */
    @RequestMapping("/bill_exportAll")
    public void billExportAll( Bill condition ,HttpServletResponse response) throws Exception {
    	try {
	    	JSONArray result = billService.findListAll(condition);
	    	String jsonStr = JSONObject.toJSONString(result);
	    	List<Bill> list = JSONObject.parseArray(jsonStr,  Bill.class);
	    	String fileName = "账单数据.xlsx";
//	    	new ExportExcel("账单数据", Bill.class,1, 1).setDataList(list).write(response, fileName).dispose();
    	} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
    }

    
	/**
	 * 下载模板
	 */
	@RequestMapping(value = "/load/{path}")
	@ResponseBody
	public Object load(@PathVariable("path") String path) {
		path = "d:\\aa\\BugReport.txt";
		// OutputStream out = resp.getOutputStream();
		try {
			FileInputStream in = new FileInputStream(modolePath);
			FileOutputStream out = new FileOutputStream(path);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS_TIP;
	}

	
	/**
	 * 读取模板数据
	 * @throws Exception 
	 */
	@RequestMapping(value = "/readModel")
	@ResponseBody
	public Object readModel(@RequestParam("file") MultipartFile file,@RequestParam("neighborhoodsId") String neighborhoodsId) throws Exception {
		List<Bill> result = new ArrayList<Bill>();
		String fileName = file.getOriginalFilename();
		   //创建Excel工作薄  
		InputStream is = file.getInputStream();
		Workbook work =  getWorkbook(is, fileName) ;
        //Workbook work = new HSSFWorkbook(is); 
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  

        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  

            //遍历当前sheet中的所有行  
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {  
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){continue;}  

               /* for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) { */ 
                    //cell = row.getCell(y);  
                    if (null == getCellValue(row.getCell(5))
							|| "".equals(getCellValue(row.getCell(5))))
						break;
					Bill bill = new Bill();
					bill.setNeighborhoodsName(getCellValue(row.getCell(0)).toString());
					if (null != row.getCell(1)) {
						bill.setObjectNo(getCellValue(row.getCell(1)) + "-"
								+ getCellValue(row.getCell(2)) + "-"
								+ getCellValue(row.getCell(3)));
					} else {
						bill.setObjectNo(getCellValue(row.getCell(4)).toString());
					}
					bill.setOwnerName(getCellValue(row.getCell(5)).toString());
					bill.setOwnerPhone(getCellValue(row.getCell(6)).toString());
					String standardName = getCellValue(row.getCell(7)).toString();
					bill.setStandardName(standardName);
					if ("房屋".equals(standardName)) {
						bill.setStandardId(1);
					} else if ("车位".equals(standardName)) {
						bill.setStandardId(2);
					} else if ("广告位".equals(standardName)) {
						bill.setStandardId(3);
					} else if ("水表".equals(standardName)) {
						bill.setStandardId(4);
					} else if ("电表".equals(standardName)) {
						bill.setStandardId(5);
					}
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						bill.setCostStartTime(format.parse(getCellValue(row.getCell(8)).toString()));
						bill.setCostEndTime(format.parse(getCellValue(row.getCell(9)).toString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bill.setShouldTotal(toPrice(getCellValue(row.getCell(10)).toString()));
					bill.setDiscountAmount(toPrice(getCellValue(row.getCell(11)).toString()));
					bill.setOverdueFine(toPrice(getCellValue(row.getCell(12)).toString()));
					bill.setPaidTotal(toPrice(getCellValue(row.getCell(13)).toString()));
					bill.seteId(ShiroKit.getUser().geteId());
					bill.setIsRelevance(0);
					bill.setIsDelete(0);
					bill.setIsFee(1);
					String [] uuid  = UUID.randomUUID().toString().split("-");
					bill.setBillNo(uuid[0]);
					result.add(bill);
                    
                    
                /*}  */
            }  
        }
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(result);
        Integer resultCode = billService.upload(jsonArray.toString(),neighborhoodsId);
		if (200 != resultCode) {
			return new ErrorTip(resultCode, "操作失败");
		}
		return SUCCESS_TIP;
        //JSONArray jsonArray = (JSONArray) JSONArray.toJSON(result);
		// JSONObject json = (JSONObject) JSONObject.toJSON(result);
		//return jsonArray;
	
	}
	// 将Excel钱转化为数字
	private BigDecimal toPrice(String param) {
		String a = param.replace("$", "").replace(",", "");
		BigDecimal price = new BigDecimal(a);
		return price;
	}
	
	  /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  

    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    @SuppressWarnings("unused")
    public  Object getCellValue(Cell cell){  
        String strCell = "";  
         switch (cell.getCellType()) {  
             case XSSFCell.CELL_TYPE_STRING:  
                 strCell = cell.getStringCellValue();  
                 break;  
             case XSSFCell.CELL_TYPE_NUMERIC:  
                 if (XSSFDateUtil.isCellDateFormatted(cell)) {  
                     //  如果是date类型则 ，获取该cell的date值  
                     strCell = new SimpleDateFormat("yyyy-MM-dd").format(XSSFDateUtil.getJavaDate(cell.getNumericCellValue()));  
                 } else { // 纯数字  
                     strCell = String.valueOf(cell.getNumericCellValue());  
                     DecimalFormat df = new DecimalFormat("#.#########");
                     strCell=df.format(Double.valueOf(strCell));
                 }  
                     break;  
             case XSSFCell.CELL_TYPE_BOOLEAN:  
                 strCell = String.valueOf(cell.getBooleanCellValue());  
                 break;  
             case XSSFCell.CELL_TYPE_BLANK:  
                 strCell = "";  
                 break;  

             case XSSFCell.CELL_TYPE_FORMULA:
                     strCell = String.valueOf(cell.getNumericCellValue());  
                     DecimalFormat df = new DecimalFormat("#.#########");
                     strCell=df.format(Double.valueOf(strCell));
                 break;  

             default:  
                 strCell = "";  
                 break;  
         }  
         if (strCell.equals("") || strCell == null) {  
             return "";  
         }  
         if (cell == null) {  
             return "";  
         }  
         return strCell;  
    }  
    public static class XSSFDateUtil extends DateUtil {  
        protected static int absoluteDay(Calendar cal, boolean use1904windowing) {  
            return DateUtil.absoluteDay(cal, use1904windowing);  
        }  
        
    }
	/**
	 * 上传
	 */
	@RequestMapping(value = "/upload/{param}/{neighborhoodsId}")
	@ResponseBody
	public Object add(@PathVariable("param") String param,@PathVariable("neighborhoodsId") String neighborhoodsId) {
		System.out.println(neighborhoodsId);
		Integer result = billService.upload(param,neighborhoodsId);
		if (200 != result) {
			return new ErrorTip(result, "操作失败");
		}
		return SUCCESS_TIP;

	}

}
