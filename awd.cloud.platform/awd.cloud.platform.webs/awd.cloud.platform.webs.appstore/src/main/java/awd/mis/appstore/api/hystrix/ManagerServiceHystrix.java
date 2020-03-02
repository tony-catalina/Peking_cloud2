package awd.mis.appstore.api.hystrix;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.model.AppModel;
import awd.mis.appstore.model.ApplunboModel;
import awd.mis.appstore.model.InterfaceModel;
import awd.mis.appstore.model.InterfacebindingModel;
import awd.mis.appstore.model.JsappModel;
import awd.mis.appstore.model.MenusModel;
import awd.mis.appstore.model.PublishModel;
import awd.mis.appstore.model.SettingModel;
import awd.mis.appstore.model.UserinfoModel;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("managerService")
public class ManagerServiceHystrix implements FallbackFactory<ManagerService> {
    public static Logger logger = Logger.getLogger(ManagerService.class);

    @Override
    public ManagerService create(Throwable throwable) {

        return new ManagerService() {

            @Override
            public ResponseMessage<UserinfoModel> findByNameAndPass(String jsbh, String name, String pwd) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<UserinfoModel> getUserByid(String id) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<AppModel> getAppByid(String id) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<AppModel> querySingle(QueryParam params) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public ResponseMessage<String> saveApp(AppModel appModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> saveOrUpdateApp(AppModel appModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> updateApp(String id, AppModel appModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<AppModel>> listApp(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> save(PublishModel publish) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<PublishModel>> listPublish(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> updatePublish(String id, PublishModel publish) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<UserinfoModel>> listUsers(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> deleteUser(String id, UserinfoModel userinfoModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage addUser(UserinfoModel userinfoModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage updateUser(String id, UserinfoModel userinfoModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage delJsApp(String appcode, String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<JsappModel>> listJsApp(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<List<ApplunboModel>> getFourURL(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> addJsapp(JsappModel jsapp) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> insertLunbo(ApplunboModel applunboModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<AppModel>> getAppEntyListByJsbh(String pageIndex, String pageSize, String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getJsTypeByGroups(String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> setLunBoApp(String appcode, String username) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<InterfaceModel>> getInterface(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<InterfaceModel>> getInterfaceAndBdzt(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<InterfacebindingModel> getInterfacebinding(QueryParam params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> menusSave(String appcode, String menu, String fname, String name, String url,
                                                     String creator, Date createtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> settingInit(String aPPCODE, String progroup, String type, String key,
                                                       String pronamezh, String value, String creator) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<MenusModel>> getMenus(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<SettingModel>> getSetting(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> updateInterfaceBdByCode(String bdzt, String appcode, String[] interface_id) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public List<Integer> apiDshslQuery(String[] appcode) {
                return null;
            }

            @Override
            public ResponseMessage<String> updateInterfacebinding(String id, InterfacebindingModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }


            @Override
            public ResponseMessage<String> saveInterfacebinding(List<Map<String, Object>> list) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> deleteInterfaceBinding(String id) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<InterfaceModel>> getinterfaceByAppcode(String appcode, String bdzt, String start,
                                                                                      String end) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public List getApiTreeNode() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public List<Map<String, Object>> getTree() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> addMenuList(String appcode, List menuList, String creator) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> settingListInit(String appcode, List settingList, String creator) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

        };
    }
}
