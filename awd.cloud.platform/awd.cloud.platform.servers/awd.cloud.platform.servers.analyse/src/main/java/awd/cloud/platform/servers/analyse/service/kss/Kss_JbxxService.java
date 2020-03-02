package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author WS
 * @Date 2019-08-13 15:34
 */
@Service
public class Kss_JbxxService {

	@Autowired
	private JbxxDao jbxxDao;





	/**
	 * 收押入所业务动态
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryForSyrsYwdz(String jsbh, String startTime, String endTime) {
		return jbxxDao.queryForSyrsYwdz(jsbh, startTime, endTime);
	}

	/**
	 * 按性别查询统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryByXb(String startTime, String endTime, String jsbh) {
		// System.out.print("\33[32;4m--------------\33[30;0m");
		System.out.print("-----------ll---" + startTime + "---------" + endTime + "" + jsbh);
		return jbxxDao.queryByXb(startTime, endTime, jsbh);
	}

	/**
	 * 按文化程度查询统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryByWhcd(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryByWhcd(startTime, endTime, jsbh);
	}

	/**
	 * 查询户籍地统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryByHjd(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryByHjd(startTime, endTime, jsbh);
	}

	/**
	 * 按入所性质查询统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryByRsxz(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryByRsxz(startTime, endTime, jsbh);
	}

	/**
	 * 入所总人数统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryRszrs(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryRszrs(startTime, endTime, jsbh);
	}

	/**
	 * 在押总人数统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryZyzrs(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryZyzrs(startTime, endTime, jsbh);
	}

	/**
	 * 拒收总人数统计
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> queryJs(String startTime, String endTime, String jsbh) {
		return jbxxDao.queryJs(startTime, endTime, jsbh);
	}

	public List<Map<String, Object>> getTypeByJqCount(String jsbh, List<String> jslist) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getBahjCount(String jsbh, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getAjlbCount(String jsbh, String ajlbs) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getJyRyxx(String jsbh) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getTsLsThCount(String jsbh, Date time) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getTsLsThInfo(String jsbh) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取在押人员数量
	 * jsbh 为空时获取全部在押人员数量
	 *
	 * @param jsbh
	 * @return
	 */
	@UseDataSource("kss_ds")
	public Long getJbxxR8Count(String jsbh) {
		Map map = jbxxDao.getJbxxR8Count(jsbh);
		return (Long) map.get("zyrs");
	}

	/**
	 * 获取指定字段值的在押人员数量
	 * jsbh 为空时获取全部人员的分组统计的数量
	 * value 为空时根据值分组统计在押人员数量
	 *
	 * @param jsbh
	 * @param field
	 * @param value
	 * @return
	 */
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> getJbxxR8ByField(String jsbh, String field, String value) {
		List<Map<String, Object>> map = jbxxDao.getJbxxR8ByField(jsbh, field, value);
		return map;
	}

	/**
	 * 获取待办任务数量
	 * jsbh 为空获取全部待办数不为空是指定的数量
	 * flow 为空获取全部流程，不为空指定流程
	 * node 为空获取全部节点 不为空指定节点
	 *
	 * @param jsbh
	 * @param flow
	 * @param node
	 * @return
	 */
	public long queryTaskCount(String jsbh, String flow, String node) {
		long result = 0l;
		Set<String> allkey = null;
		if (StringUtils.isNullOrEmpty(jsbh)) {
			jsbh = "*";
		}
		if (StringUtils.isNullOrEmpty(flow)) {
			flow = "*";
		}
		if (StringUtils.isNullOrEmpty(node)) {
			node = "*";
		}
		allkey = CacheUtils.get().getKeys(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + flow + "_" + node);
		//System.err.println(allkey.size());

		for (String key : allkey) {
			result += Long.parseLong(CacheUtils.get().getKey(key).toString());
			System.out.println(key + ":" + CacheUtils.get().getKey(key));
		}
		return result;
	}

	//流水牌>基本信息
	@UseDataSource("kss_ds")
	public int getJbxxCountByField(String jsbh, String field, String value) {

		return jbxxDao.getJbxxCountByField(jsbh, field, value);
	}

	//点名
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> getJbxxForDm() {

		return jbxxDao.getJbxxForDm();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> jianShiFuW(String jsbh, String jsh) {

		return jbxxDao.jianShiFuW(jsbh, jsh);
	}

	@UseDataSource("kss_ds")
	public int getJsBhrsByBhlx(String jsbh, String jsh, String field, String value) {
		return jbxxDao.getJsBhrsByBhlx(jsbh, jsh, field, value);
	}


    //下面上位机版本
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_hyzklh(String starttime,String endtime) {
		return jbxxDao.swj_hyzklh(starttime,endtime);
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_hyzkso(String starttime,String endtime) {
		return jbxxDao.swj_hyzkso(starttime,endtime);
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_jkzkslqx(String starttime,String endtime) {
		return jbxxDao.swj_jkzkslqx(starttime,endtime);
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_jkzkcj(String starttime,String endtime) {
		return jbxxDao.swj_jkzkcj(starttime,endtime);
	}
   //上面上位机版本

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> hyzklh() {
		return jbxxDao.hyzklh();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> hyzkso() {
		return jbxxDao.hyzkso();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> jkzkslqx() {
		return jbxxDao.jkzkslqx();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> jkzkcj() {
		return jbxxDao.jkzkcj();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> shseCount() {
		return jbxxDao.shseCount();
	}


	@UseDataSource("kss_ds")
	public List<Map<String, Object>> zdryCount() {
		return jbxxDao.zdryCount();
	}


	@UseDataSource("kss_ds")
	public List<Map<String, Object>> gjfxCount() {
		return jbxxDao.gjfxCount();
	}

	//超期羁押=上位机版本
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_cqjyCount(String starttime,String endtime,String zszt) {
		List<Map<String, Object>> maps = jbxxDao.swj_cqjyCount(starttime, endtime, zszt);
		if(!StringUtils.isNullOrEmpty(maps)) {
			for (Map<String, Object> key : maps) {
				Object dz = key.get("dz");
				if (!StringUtils.isNullOrEmpty(dz)) {
					key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
				} else {
					key.put("dzString", "");
				}

			}
		}
		return maps;
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> cqjyCount() {
		return jbxxDao.cqjyCount();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> alljbxxCount() {
		return jbxxDao.alljbxxCount();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> gyqxfxCount() {
		return jbxxDao.gyqxfxCount();
	}

	@UseDataSource("kss_ds")
	public List<AnalysisResultVO> swj_ajqkfxCount(String starttime,String endtime,String ay) {
		return jbxxDao.swjAjqkfx(starttime,endtime,ay);
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> ajqkfxCount() {
		return jbxxDao.ajqkfxCount();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> lsfxfxCount() {
		return jbxxDao.lsfxfxCount();
	}

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_lsfxfxCount(String starttime,String endtime) {

		List<Map<String,Object>> result = jbxxDao.swj_lsfxfxCount(starttime,endtime);
		for (Map<String,Object> map : result ){
			String dz = (String) map.get("dz");
			map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
		}
        return result;
    }

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> yzjbfxCount() {
		return jbxxDao.yzjbfxCount();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> swj_yzjbfxCount(String starttime,String endtime) {
		List<Map<String,Object>> result = jbxxDao.swj_yzjbfxCount(starttime,endtime);
		for (Map<String,Object> map : result ){
			String dz = (String) map.get("dz");
			map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
		}
		return result;
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> tswsfxCount() {
		return jbxxDao.tswsfxCount();
	}

	@UseDataSource("kss_ds")
	public List<Map<String, Object>> lslsfxCount() {
		return jbxxDao.lslsfxCount();
	}

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_lslsfxCount(String starttime,String endtime) {
		List<Map<String,Object>> result = jbxxDao.swj_lslsfxCount(starttime,endtime);
		for (Map<String,Object> map : result ){
			String dz = (String) map.get("dz");
			map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
		}
		return result;
    }

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_sjwsry(String starttime,String endtime) {
        List<Map<String,Object>> result = jbxxDao.swj_sjwsry(starttime,endtime);
        for (Map<String,Object> map : result ){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }

	@UseDataSource("kss_ds")
	public Map<String, Object> dpaqgl(String jsbh) {
		return jbxxDao.dpaqgl(jsbh);
	}

	@UseDataSource("kss_ds")
	public int getJbxxCountByFieldWithJs(String jsbh, String jsh, String field, String value) {
		return jbxxDao.getJbxxCountByFieldWithJs(jsbh, jsh, field, value);
	}

	@UseDataSource("kss_ds")
	public int getJbxxR8CountWithJs(String jsbh, String jsh) {
		return jbxxDao.getJbxxR8CountWithJs(jsbh, jsh);
	}

	@UseDataSource("kss_ds")
	public int queryZyzrsWithJs(String date, String date1, String jsbh, String jsh) {
		return jbxxDao.queryZyzrsWithJs(date, date1, jsbh, jsh);
	}


	//分所大屏在所人员
	@UseDataSource("kss_ds")
	public Map<String, Object> find_zsry(String jsbh) {

		return jbxxDao.select_zsry(jsbh);
	}

	//分所大屏在所人员信息
	@UseDataSource("kss_ds")
	public ArrayList<Map<String, Object>> find_zsryxx(String bahj, String rybh, String jsbh,String rsrq,  String crjbj, String jsh) {

		return jbxxDao.select_zsryxx(bahj, rybh, jsbh,rsrq,  crjbj, jsh);
	}

	//医疗情况==上位机版本
	@UseDataSource("kss_ds")
	public ArrayList<Map<String,Object>> swj_ylgl(String starttime,String endtime) {

		ArrayList<Map<String, Object>> maps = jbxxDao.swj_ylgl(starttime, endtime);
		if(!StringUtils.isNullOrEmpty(maps)) {
			for (Map<String, Object> key : maps) {
				Object dz = key.get("dz");
				if (!StringUtils.isNullOrEmpty(dz)) {
					key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
				} else {
					key.put("dzString", "");
				}

			}
		}
         return   maps;
	}


	//分所大屏医疗管理
	@UseDataSource("kss_ds")
	public HashMap<String, Object> find_ylgl(String jsbh) {


		try {
			Map<String, Object> map = jbxxDao.select_ylgl(jsbh);
			ArrayList<Object> list = new ArrayList<>();

			for (String key : map.keySet()) {
				HashMap<String, Object> map1 = new HashMap<>();

				if (key.equals("重病号")) {
					map1.put("name", "重病号");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("所内就医")) {

					map1.put("name", "所内就医");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("公安医院住院")) {
					map1.put("name", "公安医院住院");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("其他住院")) {
					map1.put("name", "其他住院");
					map1.put("msg", map.get(key));
					list.add(map1);
				}

			}


			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("name", "医疗管理");
			map2.put("list", list);

			HashMap<String, Object> map3 = new HashMap<>();
			map3.put("b2", map2);
			map3.put("name", "重大一、二、三级风险对象详情");


			return map3;
		} catch (Exception e) {
			HashMap<String, Object> map3 = new HashMap<>();
			map3.put("b2", "");
			map3.put("name", "重大一、二、三级风险对象详情");
			return map3;
		}

	}


	//kss分所大屏提讯会见
	@UseDataSource("kss_ds")
	public Map<String, Object> find_txhj(String jsbh) {


		try {
			Map<String, Object> map = jbxxDao.select_txhj(jsbh);
			ArrayList<Object> list = new ArrayList<>();

			for (String key : map.keySet()) {
				Map<String, Object> map1 = new HashMap<>();
				if (key.equals("提讯")) {
					map1.put("name", "提讯");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("家属会见")) {
					map1.put("name", "家属会见");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("律师会见")) {
					map1.put("name", "律师会见");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
			}
			Map map1 = new HashMap<String, Object>();
			map1.put("name", "提讯会见");
			map1.put("list", list);

			Map map2 = new HashMap<String, Object>();
			map2.put("b4", map1);
			map2.put("name", "重大一、二、三级风险对象详情");
			return map2;
		} catch (Exception e) {
			Map map2 = new HashMap<String, Object>();
			map2.put("b4", "");
			map2.put("name", "重大一、二、三级风险对象详情");
			return map2;
		}
	}


	//KSS分所大屏关押期限
	@UseDataSource("kss_ds")
	public HashMap<String, Object> find_gyqx(String jsbh) {

		try {
			HashMap<String, Object> map = jbxxDao.select_gyqx(jsbh);
			ArrayList<Object> list = new ArrayList<>();

			for (String key : map.keySet()) {
				HashMap<String, Object> map1 = new HashMap<>();
				if (key.equals("三日内到期")) {
					map1.put("name", "三日内到期");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
				if (key.equals("超期")) {
					map1.put("name", "超期");
					map1.put("msg", map.get(key));
					list.add(map1);
				}
			}

			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("name", "关押期限");
			map1.put("list", list);
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("b5", map1);
			map2.put("name", "重大一、二、三级风险对象详情");

			return map2;
		} catch (Exception e) {
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("b5", "");
			map2.put("name", "重大一、二、三级风险对象详情");
			return map2;
		}

	}


	//kss分所大屏各监区抱病情况趋势图
	@UseDataSource("kss_ds")
	public List<Object> find_jqbb(String jsbh) {
		//	获取当前时间
		Date date = new Date();
		//	要返回的icon
		String ico = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAKCAYAAABrGwT5AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkVDRENCNkY2QUVCNzExRTk5NzBGRkY2NEM0RDdCOUUwIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkVDRENCNkY3QUVCNzExRTk5NzBGRkY2NEM0RDdCOUUwIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RUNEQ0I2RjRBRUI3MTFFOTk3MEZGRjY0QzREN0I5RTAiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RUNEQ0I2RjVBRUI3MTFFOTk3MEZGRjY0QzREN0I5RTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7kAizbAAAAhElEQVR42pyQPQ5AQBCFx0bpOOsAej+n0GloKWntXdCrZR2HmjfJqERhXvI1M+/LZja4yookGahBDCJ65wAejGDhgZFFDyaQfIgkc97P0qcQpKClf+H+xi83pEvNslXK1pA+Acu7UvYsO6XsjHz98FPk/vzc3IECrOD8EE7Z59KnW4ABAFIJFmavJoVZAAAAAElFTkSuQmCC";
		//	返回结果
		List<Object> result = new ArrayList<Object>();

		//	根据jsbh查询 所有jqh和对应名称
		ArrayList<HashMap<String, Object>> jqh_list = jbxxDao.select_jqbbmc(jsbh);

		if (jqh_list.size() > 0) {
			//	遍历所有jsh
			for(HashMap<String,Object> jqh_map : jqh_list) {
				if (jqh_list != null) {

					//	创建jqh所有信息的集合
					Map<String,Object> jqh_result = new HashMap<String,Object>();
					//	获取jqh
					Object jqh = jqh_map.get("jqh");
					//	获取jqh名称
					String jqh_name = jqh_map.get("mc") != null && !"".equals(jqh_map.get("mc").toString().trim()) ? jqh_map.get("mc").toString() : "暂无对应名称";

					if (jqh != null && !"".equals(jqh.toString().trim())) {
						//	根据jqh 查询月份和对应数量
						ArrayList<HashMap<String, Object>> month_list = jbxxDao.select_jqbbDY(jsbh, jqh.toString() + "%");

						//	创建存储所有月份集合
						List<Object> all_month = new ArrayList<Object>();
						//	获取当前时间
						Calendar instance = Calendar.getInstance();
						instance.setTime(date);
						SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");

						for (int i = 6; i >= 0; i--) {
							//	判断是否添加了数据
							boolean add_flag = false;
							//	创建要添加的时间集合
							Map<String,Object> add_map = new HashMap<String,Object>();
							//	推算时间日期
							instance.add(instance.MONTH, -i);
							//	得到推算时间格式日期
							String compare_date = format.format(instance.getTime());

							if (month_list.size() > 0) {
								for (HashMap<String,Object> month_map : month_list) {
									//	获取月份
									String month = month_map.get("月份").toString();
									//	获取数量
									String count = month_map.get("数量").toString();

									if (month.equals(compare_date)) {
										add_map.put("name", month);
										add_map.put("value", count);
										add_flag = true;
										break;
									}
								}
							}

							//	判断是否添加了月份数据
							if (!add_flag) {
								add_map.put("name", compare_date);
								add_map.put("value", 0);
							}

							all_month.add(add_map);
							//	清空当前时间
							instance.clear();
							instance.setTime(date);
						}

					//	添加jqh 所有数据
					jqh_result.put("name", jqh_name);
					jqh_result.put("icon", ico);
					jqh_result.put("list", all_month);

					}
					result.add(jqh_result);
				}

			}
		} else {
			Map<String,Object> temp_map = new HashMap<String,Object>();
			temp_map.put("name", "暂无监区名称");
			temp_map.put("ico", ico);
			temp_map.put("list", "");
			result.add(temp_map);
		}

		return result;
	}



	// kss分所大屏在岗民警 协警--
	@UseDataSource("kss_ds")
	public Map<String, Object> find_zbld(String jsbh) {


		ArrayList<Map<String, Object>> map = jbxxDao.select_zbld(jsbh);
		String s = "";
		HashMap<String, Object> map1 = new HashMap<>();
		HashMap<String, Object> map2 = new HashMap<>();
		if (map.size() == 0) {
			s = "暂无值班领导";
		} else {
			for (int i = 0; i < map.size(); i++) {
				String sld = map.get(i).get("sld").toString();
				if (null != sld && !"".equals(sld)) {
					s = s.concat(sld + "、");
				}

			}
			if (s.lastIndexOf("、") != -1) {
				s = s.substring(0, s.lastIndexOf("、"));

			}
		}

		map1.put("lname", "值班领导");
		map1.put("lvalue", s);



		ArrayList<Map<String, Object>> maps = jbxxDao.select_zbmj(jsbh);
		String k = "暂无值班民警";
		String l = "暂无值班辅警";

		if(!StringUtils.isNullOrEmpty(maps)){
		    for(Map<String, Object> key:maps){
		        if(key!=null){
                    Object mjrs = key.get("在岗民警人数");
                    Object fjrs = key.get("在岗辅警人数");
                    if(null!=mjrs && !"".equals(mjrs.toString())){
                        k=mjrs.toString();
                    }
                    if(null!=fjrs && !"".equals(fjrs.toString())){
                        l=fjrs.toString();
                    }
                }
            }
        }
        map1.put("rname", "在岗民警人数");
        map1.put("rvalue", k);
        map2.put("lname", "在岗辅警人数");
        map2.put("lvalue", l);



		//姓名做参 查手机号
		String sjhAll = "";
		if (map.size() == 0) {
			sjhAll = "暂无";
		} else {
			for (int q = 0; q < map.size(); q++) {
				ArrayList<Map<String, Object>> sjh = jbxxDao.select_sjh(jsbh, map.get(q).get("sld").toString());

				if (sjh.size() != 0) {
					for (int f = 0; f < sjh.size(); f++) {
						String sjh1 = sjh.get(f).get("sjh").toString().trim();

						if (sjh1 != null && !"".equals(sjh1)) {
							sjhAll = sjhAll.concat(sjh1 + "、");

						}
					}

				}
			}

			if (sjhAll.lastIndexOf("、") != -1) {
				sjhAll = sjhAll.substring(0, sjhAll.lastIndexOf("、"));
			}
		}

		map2.put("rname", "值班电话");
		map2.put("rvalue", sjhAll);

		ArrayList<Object> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		HashMap<String, Object> map3 = new HashMap<>();
		map3.put("ct", list);
		return map3;

	}



	//分所大屏重点关注人员
	@UseDataSource("kss_ds")
	public ArrayList<Object> find_zdgz(String jsbh) {

		ArrayList<HashMap<String, Object>> list = jbxxDao.select_zdgz(jsbh);
		ArrayList<Object> list1 = new ArrayList<>();


		if(list.size()>0) {
			int i =-1;
			for (HashMap<String, Object> zdgz : list) {
				String xm ="暂无姓名";
				String jshm ="暂无监室号";
				String img ="暂无图片";
				String rybh1 ="暂无人员编号";
				if (zdgz != null){
					i++;

					Object xm1 = zdgz.get("xm");
				if(xm1!=null && !"".equals(xm1.toString())){
					xm=xm1.toString();
				}
					Object jsh = zdgz.get("jsh");
				if(jsh!=null && !"".equals(jsh.toString())){
					jshm=jsh.toString();
				}
					Object rybh = zdgz.get("rybh");
				if(rybh!=null && !"".equals(rybh)){
					rybh1=rybh.toString();
					ArrayList<HashMap<String, Object>> list2 = jbxxDao.select_zdgzImg(rybh.toString());//根据rybh  查照片

					if(list2.size()>0){
						for(HashMap<String, Object> imgUrl:list2){
							if(imgUrl!=null){
								Object img1 = imgUrl.get("img");
								if(img1!=null && !"".equals(img1)){
									img=img1.toString();

								}

							}
						}
					}

				}

				}
				HashMap<String, Object> map10 = new HashMap<>();
				map10.put("id", i);
				map10.put("type", i);
				map10.put("name",xm);
				map10.put("area", "一级风险");
				map10.put("number",jshm);
				map10.put("img",img);
				map10.put("rybh", rybh1);
				list1.add(map10);
			}
		}else{
			HashMap<String, Object> map10 = new HashMap<>();
			map10.put("id", 0);
			map10.put("type", 0);
			map10.put("name","暂无姓名");
			map10.put("area", "暂无风险等级");
			map10.put("number","暂无监室号");
			map10.put("img","暂无图片");
			map10.put("rybh", "暂无人员编号");
			list1.add(map10);
		}


		return list1;
	}

	//分所大屏重点关注人员信息
	@UseDataSource("kss_ds")
	public HashMap<String, Object> find_zdgzry(String jsbh,String rybh) {

		ArrayList<Object> list1 = new ArrayList<>();
		ArrayList<Object> list = new ArrayList<>();
		HashMap<String, Object> map9 = new HashMap<>();

        //kss分所大屏重点关注人员信息
		ArrayList<HashMap<String, Object>> map = jbxxDao.select_zdgzry(jsbh,rybh);

		for (int i = 0; i < map.size(); i++) {


			HashMap<String, Object> map1 = new HashMap<>();
			if (map.get(i).keySet().contains("姓名")) {
				map1.put("item1", ( map.get(i).get("姓名") != "") ? map.get(i).get("姓名") : "暂无姓名");

			}else{map1.put("item1", "暂无姓名");}
			if (map.get(i).keySet().contains("监室号")) {
				map1.put("item2", ( map.get(i).get("监室号") != "") ? map.get(i).get("监室号") : "暂无监室号");
			}else{map1.put("item2", "暂无监室号");}

			if (map.get(i).keySet().contains("风险等级")) {

				map1.put("item3", "一级风险");
			}
			if (map.get(i).keySet().contains("列控原因")) {
				try {
					map1.put("item4", ( !map.get(i).get("列控原因").toString().equals(""))? map.get(i).get("列控原因") : "无患病记录");

				} catch (Exception e) {
					map1.put("item4", "无患病记录");
				}
			}else{map1.put("item4","无患病记录");}

			if (map.get(i).keySet().contains("管控措施")) {
				try {
					map1.put("item5", ( !map.get(i).get("管控措施").equals("")) ? map.get(i).get("管控措施") : "无医院治疗措施");
				} catch (Exception e) {
					map1.put("item5", "无医院治疗措施");
				}
			}else{map1.put("item5", "无医院治疗措施");}
			if (map.get(i).keySet().contains("管控民警")) {
				try {
					map1.put("item6", ( !map.get(i).get("管控民警").equals("")) ? map.get(i).get("管控民警") : "暂无管控民警");
				} catch (Exception e) {
					map1.put("item6", "暂无管控民警");
				}
			}else{map1.put("item6", "暂无管控民警");}
			map1.put("item7", map.get(i).get("rybh"));


			map1.put("xl", 1);
			list.add(map1);                     // AA  data里面


			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("name", "序列");
			map2.put("series", "xl");
			HashMap<String, Object> map3 = new HashMap<>();
			map3.put("name", "姓名");
			map3.put("series", "item1");
			HashMap<String, Object> map4 = new HashMap<>();
			map4.put("name", "监室号");
			map4.put("series", "item2");
			HashMap<String, Object> map5 = new HashMap<>();
			map5.put("name", "风险等级");
			map5.put("series", "item3");
			HashMap<String, Object> map6 = new HashMap<>();
			map6.put("name", "列控原因");
			map6.put("series", "item4");
			HashMap<String, Object> map7 = new HashMap<>();
			map7.put("name", "管控措施");
			map7.put("series", "item5");
			HashMap<String, Object> map8 = new HashMap<>();
			map8.put("name", "管控民警");
			map8.put("series", "item6");


			//ArrayList<Object> list1 = new ArrayList<>(); //AA  titles 里面的
			list1.add(map2);
			list1.add(map3);
			list1.add(map4);
			list1.add(map5);
			list1.add(map6);
			list1.add(map7);
			list1.add(map8);


			map9.put("titles", list1);
			map9.put("data", list);

		}


		return map9;


	}


	//kss大屏监区滑动菜单===下面卡牌各监区数据
	@UseDataSource("kss_ds")
	public ArrayList<Object> find_hdsj(String jsbh) {

		ArrayList<HashMap<String, Object>> map2 = jbxxDao.select_jqbbmc(jsbh);//名称和监区号
		ArrayList<Object> list = new ArrayList<>();
		if(map2.size()>0){
			for(HashMap<String, Object> mc_jqh:map2){
               if(mc_jqh!=null){
				   Object jqh = mc_jqh.get("jqh");
				   if(jqh!=null && !"".equals(jqh.toString())){
					   ArrayList<HashMap<String, Object>> list2 = jbxxDao.select_hdsjDY(jsbh, jqh.toString()+"%");//kss大屏监区滑动菜单===下面卡牌各监区数据 占比
					   for(HashMap<String, Object> zb:list2) {
                         if(zb!=null&& !"".equals(zb.toString())) {
							 HashMap<String, Object> map = new HashMap<>();
							 map.put("name", mc_jqh.get("mc")); //百分比放入

							 map.put("val0", zb.get("val0"));

							 map.put("val01", zb.get("val01"));
							 map.put("val1", zb.get("val1"));
							 map.put("val2", zb.get("val2"));
							 map.put("val3", zb.get("val3"));
							 map.put("val4", zb.get("val4"));
							 map.put("val11", zb.get("val11"));

							 if (zb.get("all").toString().equals("0")) {
								 map.put("val21", 0);
								 map.put("val31", 0);
								 map.put("val41", 0);
							 } else {
								 map.put("val21", String.format("%.2f", (float) Integer.parseInt(zb.get("val2").toString()) / (float) Integer.parseInt(zb.get("all").toString()) * 100));
								 map.put("val31", String.format("%.2f", (float) Integer.parseInt(zb.get("val3").toString()) / (float) Integer.parseInt(zb.get("all").toString()) * 100));
								 map.put("val41", String.format("%.2f", (float) Integer.parseInt(zb.get("val4").toString()) / (float) Integer.parseInt(zb.get("all").toString()) * 100));
							 }
							 map.put("jsh", zb.get("jsh"));
							 list.add(map);

						 }
					   }
				   }
			   }
			}
		}else{
			HashMap<String, Object> map = new HashMap<>();
			map.put("name", "暂无监区名称");
			map.put("val0", "暂无数据");

			map.put("val01", "暂无数据");
			map.put("val1", "暂无数据");
			map.put("val2", "暂无数据");
			map.put("val3", "暂无数据");
			map.put("val4", "暂无数据");
			map.put("val11", "暂无数据");
			map.put("val21", "暂无数据");
			map.put("val31", "暂无数据");
			map.put("val41", "暂无数据");
			list.add(map);
		}

		return list;






	}


	//诉讼情况分类与犯罪类型分析
	@UseDataSource("kss_ds")
	public ArrayList<Object> find_ssqq(String jsbh) {

		ArrayList<HashMap<String, Object>> list = jbxxDao.select_bfb(jsbh);//百分比数据
		ArrayList<Object> list1 = new ArrayList<>();
		if (list.size() > 0) {
			for (HashMap<String, Object> bfb : list) {
				HashMap<String, Object> map = new HashMap<>();
				String mc = "暂无办案环节";
				String sl = "0";
				String code = "0";
				if (bfb != null) {
					Object mc1 = bfb.get("mc");
					if (mc1 != null && !"".equals(mc1.toString())) {
						mc = mc1.toString();
					}
					Object sl1 = bfb.get("sl");
					if (sl1 != null && !"".equals(sl1.toString())) {
						sl = sl1.toString();
					}
					Object code1 = bfb.get("code");
					if (code1 != null && !"".equals(code1.toString())) {
						code = code1.toString();
					}

				}
				map.put("name", mc);
				map.put("value", sl);
				map.put("code", code);
				list1.add(map);
			}
			return list1;
		} else {
			HashMap<Object, Object> map = new HashMap<>();
			map.put("name", "暂无办案环节");
			map.put("value", "0");
			map.put("code", "0");
			list1.add(map);
			return list1;
		}
	}




    //监区违规趋势图
	@UseDataSource("kss_ds")
	public ArrayList<Object> find_jqwg(String jsbh) {


		//大屏kss查看监区号和监区名称
		ArrayList<HashMap<String, Object>> list = jbxxDao.select_jsh(jsbh);
		ArrayList<Object> list2 = new ArrayList<>();
        if(list.size()>0){
        	for(HashMap<String, Object> jqh_jqmc:list){
				HashMap<String, Object> map = new HashMap<>();
        		String jqh = "";
				String jqmc = "暂无监区名称";
				String sl = "0";
        		if(jqh_jqmc!=null){
					Object jqmc1 = jqh_jqmc.get("jqmc");
					if(jqmc1!=null&& !"".equals(jqmc1.toString())){
						jqmc=jqmc1.toString();
					}

					Object jqh1 = jqh_jqmc.get("jqh");
					if(jqh1!=null && !"".equals(jqh1.toString())){
						jqh=jqh1.toString();
						ArrayList<HashMap<String, Object>> list1 = jbxxDao.select_jqwgry(jsbh, jqh + "%");//大屏kss监区违规情况趋势图==监区数量
						if(list1.size()>0){
							sl = list1.size()+"";
						}
					}
				}
				map.put("name",jqmc);
				map.put("value",sl);
				map.put("jqh", jqh);
				list2.add(map);
			}
		}else{
			HashMap<String, Object> map = new HashMap<>();
			map.put("name","暂无监区名称");
			map.put("value",0);
			map.put("jqh", "");
			list2.add(map);
		}

		return list2;

	}


	//监区违规趋势图人员信息
	@UseDataSource("kss_ds")
	public HashMap<String, Object> find_jqwgryxx(String jsbh,String jqh) {


		ArrayList<Object> list2 = new ArrayList<>();
		HashMap<String, Object> map2 = new LinkedHashMap<>();
		map2.put("name","序列");
		map2.put("series","xl");
		HashMap<String, Object> map3 = new LinkedHashMap<>();
		map3.put("name","姓名");
		map3.put("series","item1");
		HashMap<String, Object> map4 = new LinkedHashMap<>();
		map4.put("name","违规事件");
		map4.put("series","item2");
		HashMap<String, Object> map5 = new LinkedHashMap<>();
		map5.put("name","违规类型");
		map5.put("series","item3");
		HashMap<String, Object> map6 = new LinkedHashMap<>();
		map6.put("name","处理结果");
		map6.put("series","item4");
		HashMap<String, Object> map7 = new LinkedHashMap<>();
		map7.put("name","监室号");
		map7.put("series","item5");
		HashMap<String, Object> map8 = new LinkedHashMap<>();
		map8.put("name","主管、协管民警");
		map8.put("series","item6");
		list2.add(map2);
		list2.add(map3);list2.add(map4);list2.add(map5);
		list2.add(map6);list2.add(map7);list2.add(map8);//titles

		try {
			ArrayList<HashMap<String, Object>> list = jbxxDao.select_jqwgry(jsbh, jqh);//人员信息
			ArrayList<Object> list3 = new ArrayList<>();
			int i =0;
			if(list!=null){
				for(HashMap<String, Object> key:list){
					HashMap<String, Object> map = new HashMap<>();

					String xm ="暂无姓名";String wgsj ="暂无违规事件";String wglx ="暂无违规类型";
					String cljg ="暂无处理结果";String jsh ="暂无监室号";
					String zg ="暂无主管民警";String xg ="暂无协管民警";String rybh="暂无人员编号";
					if(key!=null && !"".equals(key)){
						Object xm1 = key.get("姓名");
						Object wgsj1 = key.get("违规事件");
						Object wglx1 = key.get("违规类型");
						Object cljg1 = key.get("处理结果");
						Object jsh1 = key.get("监室号");
						Object iteam71 = key.get("iteam7");//rybh

						if(xm1!=null&&!"".equals(xm1.toString())){
							xm=xm1.toString();
						}
						if(wgsj1!=null&&!"".equals(wgsj1.toString())){
							wgsj=wgsj1.toString();
						}
						if(wglx1!=null&&!"".equals(wglx1.toString())){
							wglx=wglx1.toString();
						}
						if(cljg1!=null&&!"".equals(cljg1.toString())){
							cljg=cljg1.toString();
						}
						if(iteam71!=null&&!"".equals(iteam71.toString())){
							rybh=iteam71.toString();
						}
						if(jsh1!=null&&!"".equals(jsh1.toString())){
							jsh=jsh1.toString();
							ArrayList<HashMap<String, Object>> zgxg = jbxxDao.select_jqwgry_zgxg(jsbh, jsh);//监区违规情况监视图==详细人员数据==主管民警和协管民警
							if(zgxg.size()>0){
								for(HashMap<String, Object> mj:zgxg){
									if(mj!=null&&!"".equals(mj)){
										Object zg1 = mj.get("zg");//主管民警
										Object xg1 = mj.get("xg");//协管民警
										if(zg1!=null &&!"".equals(zg1.toString())){
											zg=zg1.toString();
										}
										if(xg1!=null &&!"".equals(xg1.toString())){
											xg=xg1.toString();
										}

									}
								}
							}
						}

					}

					map.put("xl",++i);map.put("item1",xm);map.put("item2",wgsj);
					map.put("item3",wglx);map.put("item4",cljg);map.put("item5",jsh);
					map.put("item6",zg+"、"+xg);map.put("item7",rybh);
					list3.add(map);  //data
				}
			}else{
				HashMap<String, Object> map = new HashMap<>();
				map.put("xl",1);map.put("item1","暂无姓名");map.put("item2","暂无违规事件");
				map.put("item3","暂无违规类型");map.put("item4","暂无处理结果");
				map.put("item5","暂无监室号");map.put("item6","暂无主管和协管民警");
				map.put("item7","暂无人员编号");
				list3.add(map);  //data
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("data",list3);
			map.put("titles",list2);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, Object> map = new HashMap<>();
			map.put("data","");
			map.put("titles","");
			return map;
		}
	}


    //查看违规情况=上位机版本
    @UseDataSource("kss_ds")
    public  ArrayList<HashMap<String,Object>> swj_wgqk(String starttime,String endtime){

		ArrayList<HashMap<String, Object>> maps = jbxxDao.swj_wgqk(starttime, endtime);
		if(!StringUtils.isNullOrEmpty(maps)) {
			for (Map<String, Object> key : maps) {
				Object dz = key.get("dz");
				if (!StringUtils.isNullOrEmpty(dz)) {
					key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
				} else {
					key.put("dzString", "");
				}

			}
		}
		return maps;

    }



	//大屏kss监区违规程度
	@UseDataSource("kss_ds")
	public LinkedHashMap<String, Object> select_wgcd(String jsbh) {

		ArrayList<HashMap<String, Object>> list = jbxxDao.select_wgcd(jsbh);
		ArrayList<Object> list1 = new ArrayList<>();
		if(list.size()>0){
			for(HashMap<String, Object> key:list){
				String all="0";String qw="0";String zd="0";String yz="0";
					Object all0 = key.get("all");
					Object qw1 = key.get("qw");
					Object zd1 = key.get("zd");
					Object yz1 = key.get("yz");
				if(key!=null && !"".equals(key)){
					if(all0!=null&&!"".equals(all0.toString())){
						all=all0.toString();
					}
					if(qw1!=null&&!"".equals(qw1.toString())){
						qw=qw1.toString();
					}
					if(zd1!=null&&!"".equals(zd1.toString())){
						zd=zd1.toString();
					}
					if(yz1!=null&&!"".equals(yz1.toString())){
						yz=yz1.toString();
					}


				}
			if(!all.equals("0")) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("id", 0);
				map.put("type", 0);
				map.put("rule", "轻");
				map.put("name", "轻微违规情况");
				map.put("value", qw);
				map.put("deci", String.format("%.2f", (float) Integer.parseInt(qw) / (float) Integer.parseInt(all) * 100));

				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", 1);
				map1.put("type", 1);
				map1.put("rule", "中");
				map1.put("name", "一般违规情况");
				map1.put("value", zd);
				map1.put("deci", String.format("%.2f", (float) Integer.parseInt(zd) / (float) Integer.parseInt(all) * 100));

				HashMap<String, Object> map2 = new HashMap<>();
				map2.put("id", 2);
				map2.put("type", 2);
				map2.put("rule", "重");
				map2.put("name", "严重违规情况");
				map2.put("value", yz);
				map2.put("deci", String.format("%.2f", (float)Integer.parseInt(yz) / (float) Integer.parseInt(all) * 100));


				list1.add(map);list1.add(map1);list1.add(map2);
				//return   list1;
			}else{
				HashMap<String, Object> map = new HashMap<>();
				map.put("id", 0);
				map.put("type", 0);
				map.put("rule", "轻");
				map.put("name", "轻微违规情况");
				map.put("value", qw);
				map.put("deci", "0");

				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", 1);
				map1.put("type", 1);
				map1.put("rule", "中");
				map1.put("name", "一般违规情况");
				map1.put("value", zd);
				map1.put("deci", "0");

				HashMap<String, Object> map2 = new HashMap<>();
				map2.put("id", 2);
				map2.put("type", 2);
				map2.put("rule", "重");
				map2.put("name", "严重违规情况");
				map2.put("value", yz);
				map2.put("deci", "0");


				list1.add(map);list1.add(map1);list1.add(map2);
			}
			}
		}else{
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", 0);
			map.put("type", 0);
			map.put("rule", "轻");
			map.put("name", "轻微违规情况");
			map.put("value", 0);
			map.put("deci", 0);

			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", 1);
			map1.put("type", 1);
			map1.put("rule", "中");
			map1.put("name", "一般违规情况");
			map1.put("value", 0);
			map1.put("deci", 0);
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("id", 2);
			map2.put("type", 2);
			map2.put("rule", "重");
			map2.put("name", "严重违规情况");
			map2.put("value", 0);
			map2.put("deci", 0);

			list1.add(map);list1.add(map1);list1.add(map2);  //title

		}



          //2
		ArrayList<Object> list3 = new ArrayList<>();  //text
		ArrayList<Object> list2 = new ArrayList<>();

		ArrayList<HashMap<String, Object>> wgcd_ld = jbxxDao.select_wgcd_ld(jsbh );//查看违规程度=里面联动下面的数据
		int all_sl=0;
		if(wgcd_ld.size()>0){
			for(HashMap<String, Object> key1:wgcd_ld){
				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				String SL="0"; String wgqk="";//违规情况
				if(key1!=null && !"".equals(key1)){
					Object sl = key1.get("sl");
					Object wgqk1 = key1.get("wgqk");
					if(sl!=null && !"".equals(sl.toString())){
						SL=sl.toString();
						all_sl+=Integer.parseInt(SL);//总数
					}
					if(wgqk1!=null && !"".equals(wgqk1.toString())){
						wgqk=wgqk1.toString();
					}

					if(wgqk.equals("0011")){

						map.put("name","轻微违规");
					}
					if(wgqk.equals("0012")){

						map.put("name","中等违规");
					}
					if(wgqk.equals("0014")||wgqk.equals("0013")){

					map.put("name","严重违规");
					}

					map.put("value",SL);
					map.put("wgqk",wgqk);
					list2.add(map);


				}
			}
		}
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<>();
		map2.put("list",list2);
		//map2.put("name","件");
		//map2.put("min",all_sl);
		list3.add(map2);




		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("title",list1);
		map.put("text",list3);
		return map;

	}





	//大屏kss查看违规程度=里面联动下面的数据=数据详查rybh
	@UseDataSource("kss_ds")
	public ArrayList<HashMap<String,Object>> select_wgcd_rybh(String jsbh,String wgqk) {
		return  jbxxDao.select_wgcd_ld_rybh(jsbh,wgqk);
	}

	 //大屏在押人员风险因素
	 @UseDataSource("kss_ds")
	 public Map<String, Object> dpzyryFxys(String jsbh,String rybh) {
		Map<String, Object> allmap = new LinkedHashMap<String, Object>();
		try {
			Map<String, Object> fxysmap = jbxxDao.dpzyryFxys(jsbh, rybh);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for (String key : fxysmap.keySet()) {
				Map<String, Object> keymap = new LinkedHashMap<String, Object>();
				keymap.put("name", key);
				keymap.put("type", fxysmap.get(key));
				list.add(keymap);
			}
			allmap.put("data", list);
			allmap.put("code", 200);
			allmap.put("msg", "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			allmap.put("data", "");
			allmap.put("code", 200);
			allmap.put("msg", "查询失败");
		}
		return allmap;

	}

	//根据入所日期统计入所人数
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> jbxx_rqcxR(String starttime, String endtime) {
		return jbxxDao.rqcxR(starttime, endtime);
	}

    //入所情况查询=上位机版本
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> swj_rsqk(String starttime, String endtime) {
        return jbxxDao.swj_rsqk(starttime, endtime);
    }

}

