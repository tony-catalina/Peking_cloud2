// ------------------------------------------------------------------------------------------
// General apiDoc documentation blocks and old history blocks.
// ------------------------------------------------------------------------------------------
/**
 * @apiDefine g_base 基础数据
 */
/**
 * @apiDefine g_author 权限管理
 */
/**
 * @apiDefine g_logs 日志系统
 */
/**
 * @apiDefine g_kss 看守所
 */
/**
 * @apiDefine g_jls 拘留所
 */
/**
 * @apiDefine g_jds 戒毒所
 */
/**
 * @apiDefine g_sjs 收教所
 */
/**
 * @apiDefine g_akyy 安康医院
 */
// ------------------------------------------------------------------------------------------
// Current Success.
// ------------------------------------------------------------------------------------------


// ------------------------------------------------------------------------------------------
// Current Errors.
// ------------------------------------------------------------------------------------------
/**
 * @apiDefine QueryError
 * @apiVersion 0.4.0
 *
 * @apiError message 错误信息.
 * @apiError result 返回结果.
 * @apiError status 返回状态.
 * @apiError timestamp 时间戳.
 *
 * @apiErrorExample  Response (example):
 *     HTTP/1.1 500 Bad Request
 *     {
 *  	"message": "服务异常,查询失败",
 *  	"result": null,
 *  	"status": 500,
 *  	"timestamp": 1561373317020
 *     }
 */
/**
 * @apiDefine CreateError
 * @apiVersion 0.4.0
 *
 * @apiError message 错误信息.
 * @apiError result 返回结果.
 * @apiError status 返回状态.
 * @apiError timestamp 时间戳.
 *
 * @apiErrorExample  Response (example):
 *     HTTP/1.1 500 Bad Request
 *     {
 *       "message": "服务异常,保存失败",
 *  	 "result": null,
 *  	 "status": 500,
 *  	 "timestamp": 1561373317020
 *     }
 */
/**
 * @apiDefine UpdateError
 * @apiVersion 0.4.0
 *
 * @apiError message 错误信息.
 * @apiError result 返回结果.
 * @apiError status 返回状态.
 * @apiError timestamp 时间戳.
 *
 * @apiErrorExample  Response (example):
 *     HTTP/1.1 500 Bad Request
 *     {
 *       "message": "服务异常,更新失败",
 *  	 "result": null,
 *  	 "status": 500,
 *  	 "timestamp": 1561373317020
 *     }
 */

// ------------------------------------------------------------------------------------------
// Current Permissions.
// ------------------------------------------------------------------------------------------
/**
 * @apiDefine admin 管理员才能访问.
 * Optionally you can write here further Informations about the permission.
 *
 * An "apiDefinePermission"-block can have an "apiVersion", so you can attach the block to a specific version.
 *
 * @apiVersion 0.4.0
 */
/**
 * @apiDefine user 登录成功后才能访问.
 * Optionally you can write here further Informations about the permission.
 *
 * An "apiDefinePermission"-block can have an "apiVersion", so you can attach the block to a specific version.
 *
 * @apiVersion 0.4.0
 */
/**
 * @apiDefine jsbh 个别监所才能访问.
 * Optionally you can write here further Informations about the permission.
 *
 * An "apiDefinePermission"-block can have an "apiVersion", so you can attach the block to a specific version.
 *
 * @apiVersion 0.4.0
 */

// ------------------------------------------------------------------------------------------
// History.
// ------------------------------------------------------------------------------------------
/**
 * @apiDefine admin This title is visible in version 0.1.0 and 0.2.0
 * @apiVersion 0.1.0
 */


