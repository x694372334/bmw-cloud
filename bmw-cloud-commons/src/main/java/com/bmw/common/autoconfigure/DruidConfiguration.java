package com.bmw.common.autoconfigure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
 * Druid连接池配置
 * @author lyl
 * 2016年10月24日
 */
@Configuration
public class DruidConfiguration {
	
	
	@Value("${spring.datasource.driver-class-name}") 
	private String driver;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}") 
	private String username;
	
	@Value("${spring.datasource.password}") 
	private String password;
	
	
	@Value("${datasource.druid.initialsize}")
	private Integer druid_initialsize = 0;
	
	@Value("${datasource.druid.maxactive}")
	private Integer druid_maxactive = 20;
	
	@Value("${datasource.druid.minidle}")
	private Integer druid_minidle = 0;
	
	@Value("${datasource.druid.maxwait}")
	private Integer druid_maxwait = 30000;
	
	
	
    @Bean
    public ServletRegistrationBean druidServlet() {
    	
    	StatViewServlet statViewServlet = new StatViewServlet();
    	
    	ServletRegistrationBean servletRegistration = new ServletRegistrationBean(statViewServlet, "/druid/*");
    	Map<String,String> initParameters = new HashMap<String,String>();
    	// druid监控页  账号密码设置
    	initParameters.put("loginUsername", "bmw");
    	initParameters.put("loginPassword", "bmw!@#");
    	
    	servletRegistration.setInitParameters(initParameters);
    	return servletRegistration;
    }

    /**
     * 数据库源Bean
     * @author lyl
     * 2016年10月24日
     */
    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
    	// 数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver); // 驱动
        druidDataSource.setUrl(url); // 数据库连接地址
        druidDataSource.setUsername(username); // 数据库用户名
        druidDataSource.setPassword(password); // 数据库密码
        
        druidDataSource.setInitialSize(druid_initialsize);// 初始化连接大小
        druidDataSource.setMaxActive(druid_maxactive); // 连接池最大使用连接数量
        druidDataSource.setMinIdle(druid_minidle); // 连接池最小空闲
        druidDataSource.setMaxWait(druid_maxwait); // 获取连接最大等待时间
        
        // 打开PSCache，并且指定每个连接上PSCache的大小
        druidDataSource.setPoolPreparedStatements(false); 
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(33);
        
        druidDataSource.setValidationQuery("SELECT 1"); // 用来检测连接是否有效的sql
        druidDataSource.setTestOnBorrow(false); // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        druidDataSource.setTestOnReturn(false); // 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        druidDataSource.setTestWhileIdle(true); // 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
        
        druidDataSource.setTimeBetweenLogStatsMillis(60000); // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(1800000); // 配置一个连接在池中最小生存的时间，单位是毫秒
        
        
        // 当程序存在缺陷时，申请的连接忘记关闭，这时候，就存在连接泄漏
        // 配置removeAbandoned对性能会有一些影响，建议怀疑存在泄漏之后再打开。在上面的配置中，如果连接超过30分钟未关闭，就会被强行回收，并且日志记录连接申请时的调用堆栈。
        druidDataSource.setRemoveAbandoned(false); // 打开removeAbandoned功能 
        druidDataSource.setRemoveAbandonedTimeout(1800); // 1800秒，也就是30分钟
        druidDataSource.setLogAbandoned(true); // 关闭abanded连接时输出错误日志
        
        // 过滤器
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(this.getStatFilter()); // 监控
//        filters.add(this.getSlf4jLogFilter()); // 日志
        filters.add(this.getWallFilter()); // 防火墙
        
        druidDataSource.setProxyFilters(filters);
        
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        
        WebStatFilter webStatFilter = new WebStatFilter();
        
        filterRegistrationBean.setFilter(webStatFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
    
    
    /**
     * 监控过滤器
     * @author lyl
     * 2016年10月24日
     */
    public StatFilter getStatFilter(){
    	StatFilter sFilter = new StatFilter();
//    	sFilter.setSlowSqlMillis(2000); // 慢sql，毫秒时间 
    	sFilter.setLogSlowSql(false); // 慢sql日志
    	sFilter.setMergeSql(true); // sql合并优化处理
    	
    	return sFilter;
    }
    
    
    /**
     * 监控日志过滤器
     * @author lyl
     * 2016年10月24日
     */
    public Slf4jLogFilter getSlf4jLogFilter(){
    	Slf4jLogFilter slFilter =  new Slf4jLogFilter();
    	slFilter.setResultSetLogEnabled(false); 
    	slFilter.setStatementExecutableSqlLogEnable(false);
    	return slFilter;
    }
    
    
    
    
    /**
     * 防火墙过滤器
     * @author lyl
     * 2016年10月24日
     */
    public WallFilter getWallFilter(){
    	WallFilter wFilter = new WallFilter();
    	wFilter.setDbType("mysql");
    	wFilter.setConfig(this.getWallConfig());
    	
    	wFilter.setLogViolation(true); // 对被认为是攻击的SQL进行LOG.error输出
    	wFilter.setThrowException(true); // 对被认为是攻击的SQL抛出SQLExcepton
    	
    	return wFilter;
    }

    
    
    /**
     * 数据防火墙配置
     * @author lyl
     * 2016年10月24日
     */
    public WallConfig getWallConfig(){
    	WallConfig wConfig = new WallConfig();
    	wConfig.setDir("META-INF/druid/wall/mysql"); // 指定配置装载的目录
    	
    	// 拦截配置－语句 
    	wConfig.setTruncateAllow(false); // truncate语句是危险，缺省打开，若需要自行关闭
    	wConfig.setCreateTableAllow(false); // 是否允许创建表
    	wConfig.setAlterTableAllow(false); // 是否允许执行Alter Table语句
    	wConfig.setDropTableAllow(false); // 是否允许修改表
    	
    	// 其他拦截配置
    	wConfig.setStrictSyntaxCheck(true); // 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者
    	wConfig.setConditionOpBitwseAllow(true); // 查询条件中是否允许有"&"、"~"、"|"、"^"运算符。
    	wConfig.setMinusAllow(true); // 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句
    	wConfig.setIntersectAllow(true); // 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句
    	
//    	wConfig.setMetadataAllow(false); // 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息
    	
    	
    	return wConfig;
    }
    
    
    
    
    
    
}