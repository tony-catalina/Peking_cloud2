package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.kss.JGRYSYMonthDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-11-13 14:52
 * Description：<描述>
 */
@Service

public class Jls_JGRYSYMonthService {

    @Autowired
    private JGRYSYMonthDao jgrysyMonthDao;

    //全市被监管人员收押情况   拘留守月

    @UseDataSource("jls_ds")
    public ArrayList< Object> find_JGRYSY(){

       ArrayList<HashMap<String,Object>> list1 = jgrysyMonthDao.select_JGRYSYMonth();


        Calendar instance = Calendar.getInstance();
        Date date = new Date();
        instance.setTime(date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 8; i >= 0; i--) {
            instance.add(Calendar.MONTH, -i);
            HashMap<String, Object> map = new HashMap<>();
            boolean flag = false;

            for(int j =0;j<list1.size();j++){


                String  rq = list1.get(j).get("rq").toString();

                Date parse =null;
                try {
                    parse = format2.parse(rq);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (format.format(instance.getTime()).equals(format.format(parse))) {

                    map.put("name", format.format(instance.getTime()));

                    map.put("value", list1.get(j).get("sl"));

                    list.add(map);
                    flag = true;
                    break;

                }
            }

            if (!flag) {
                map.put("name", format.format(instance.getTime()));
                map.put("value", 0);
                list.add(map);
            }

            instance.clear();
            instance.setTime(date);
        }
        return  list;


    }

}
