/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.51-community : Database - ge
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ge` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ge`;

/*Table structure for table `tbcustomer` */

DROP TABLE IF EXISTS `tbcustomer`;

CREATE TABLE `tbcustomer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `customername` varchar(128) DEFAULT NULL COMMENT '客户名',
  `companyname` varchar(128) DEFAULT NULL COMMENT '客户公司名称',
  `companyaddress` varchar(256) DEFAULT NULL COMMENT '客户公司地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `tell` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真',
  `mail` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(4) NOT NULL COMMENT '状态：0无效，10申请，20审核通过，30审核驳回',
  `res01` varchar(50) DEFAULT NULL COMMENT '备用字段1',
  `res02` varchar(50) DEFAULT NULL COMMENT '备用字段2',
  `res03` varchar(50) DEFAULT NULL COMMENT '备用字段3',
  `res04` varchar(50) DEFAULT NULL COMMENT '备用字段4',
  `res05` varchar(50) DEFAULT NULL COMMENT '备用字段5',
  `res06` varchar(200) DEFAULT NULL COMMENT '备用字段6',
  `res07` varchar(200) DEFAULT NULL COMMENT '备用字段7',
  `res08` varchar(200) DEFAULT NULL COMMENT '备用字段8',
  `res09` varchar(200) DEFAULT NULL COMMENT '备用字段9',
  `res10` varchar(200) DEFAULT NULL COMMENT '备用字段10',
  `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `tbcustomer` */

/*Table structure for table `tbdoc` */

DROP TABLE IF EXISTS `tbdoc`;

CREATE TABLE `tbdoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资料ID',
  `docname` varchar(64) NOT NULL COMMENT '资料名，手动输入，和doctype组成逻辑主键',
  `filename` varchar(128) DEFAULT NULL COMMENT '上传文件名',
  `doctype` int(4) NOT NULL COMMENT '类型：10普通文档，20特殊文档，默认为10',
  `showtype` int(4) DEFAULT NULL COMMENT '类型：1为标准格式，2为特殊格式',
  `productcode` varchar(50) DEFAULT NULL COMMENT 'productcode',
  `subtitle` varchar(200) DEFAULT NULL COMMENT 'subtitle',
  `title1` varchar(200) DEFAULT NULL COMMENT '标题1',
  `content1` varchar(2000) DEFAULT NULL COMMENT '内容1',
  `title2` varchar(200) DEFAULT NULL COMMENT '标题2',
  `content2` varchar(2000) DEFAULT NULL COMMENT '内容2',
  `title3` varchar(200) DEFAULT NULL COMMENT '标题3',
  `content3` varchar(2000) DEFAULT NULL COMMENT '内容3',
  `title4` varchar(200) DEFAULT NULL COMMENT '标题4',
  `content4` varchar(2000) DEFAULT NULL COMMENT '内容4',
  `title5` varchar(200) DEFAULT NULL COMMENT '标题5',
  `content5` varchar(2000) DEFAULT NULL COMMENT '内容5',
  `picture1` varchar(20) DEFAULT NULL COMMENT 'PDF图片名1（初期显示用）',
  `picture2` varchar(20) DEFAULT NULL COMMENT 'PDF图片名2',
  `picture3` varchar(20) DEFAULT NULL COMMENT 'PDF图片名3',
  `picture4` varchar(20) DEFAULT NULL COMMENT 'PDF图片名4',
  `picture5` varchar(20) DEFAULT NULL COMMENT 'PDF图片名5',
  `picture6` varchar(20) DEFAULT NULL COMMENT 'PDF图片名6',
  `picture7` varchar(20) DEFAULT NULL COMMENT 'PDF图片名7',
  `picture8` varchar(20) DEFAULT NULL COMMENT 'PDF图片名8',
  `picture9` varchar(20) DEFAULT NULL COMMENT 'PDF图片名9',
  `picture10` varchar(20) DEFAULT NULL COMMENT 'PDF图片名10',
  `qrcode` varchar(100) DEFAULT NULL COMMENT '二维码图片名',
  `res01` varchar(500) DEFAULT NULL COMMENT '备用字段1',
  `res02` varchar(500) DEFAULT NULL COMMENT '备用字段2',
  `res03` varchar(500) DEFAULT NULL COMMENT '备用字段3',
  `res04` varchar(500) DEFAULT NULL COMMENT '备用字段4',
  `res05` varchar(500) DEFAULT NULL COMMENT '备用字段5',
  `res06` varchar(500) DEFAULT NULL COMMENT '备用字段6',
  `res07` varchar(500) DEFAULT NULL COMMENT '备用字段7',
  `res08` varchar(500) DEFAULT NULL COMMENT '备用字段8',
  `res09` varchar(500) DEFAULT NULL COMMENT '备用字段9',
  `res10` varchar(500) DEFAULT NULL COMMENT '备用字段10',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(4) NOT NULL COMMENT '状态：0无效，1有效',
  `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `tbdoc` */

/*Table structure for table `tbresource` */

DROP TABLE IF EXISTS `tbresource`;

CREATE TABLE `tbresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL,
  `note` varchar(256) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL COMMENT '父节点ID',
  `restype` int(4) DEFAULT NULL COMMENT '资源类型：1为主节点，2为子节，3为其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `tbresource` */

insert  into `tbresource`(`id`,`url`,`note`,`status`,`createdate`,`sort`,`parentid`,`restype`) values (1,'系统功能','系统功能',1,'2016-02-08 00:00:00',1,-1,1),(10,'/doc/showUploadDocAction.action','首页',1,'2016-02-08 00:00:00',1,1,2),(11,'/doc/showDocManageAction.action','PDF资料管理',1,'2016-02-08 00:00:00',2,1,2),(12,'/customer/showCustomerManageAction.action','客户信息管理',1,'2016-02-08 00:00:00',3,1,2),(13,'/user/showUserManagePageAction.action','后台用户管理',0,'2016-02-08 00:00:00',4,1,2),(14,'/user/showUpdPasswordAction.action','修改密码',1,'2016-02-08 00:00:00',5,1,2);

/*Table structure for table `tbrole` */

DROP TABLE IF EXISTS `tbrole`;

CREATE TABLE `tbrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rolecode` varchar(32) DEFAULT NULL COMMENT '角色CODE',
  `rolename` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `createuser` varchar(32) DEFAULT NULL COMMENT '创建者ID',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者ID',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbrole` */

insert  into `tbrole`(`id`,`rolecode`,`rolename`,`note`,`status`,`createuser`,`createdate`,`updateuser`,`updatedate`) values (1,'admin','管理员','管理员',1,'admin','2016-02-08 00:00:00','admin','2016-02-08 00:00:00'),(2,'normal','普通用户','普通用户',1,'admin','2016-02-08 00:00:00','admin','2016-02-08 00:00:00');

/*Table structure for table `tbroleresource` */

DROP TABLE IF EXISTS `tbroleresource`;

CREATE TABLE `tbroleresource` (
  `roleid` int(11) DEFAULT NULL,
  `resourceid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbroleresource` */

insert  into `tbroleresource`(`roleid`,`resourceid`) values (1,1),(1,10),(1,11),(1,12),(1,13),(1,14),(2,1),(2,10),(2,11),(2,13),(2,14);

/*Table structure for table `tbuser` */

DROP TABLE IF EXISTS `tbuser`;

CREATE TABLE `tbuser` (
  `userid` varchar(32) NOT NULL COMMENT '用户登录ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(32) DEFAULT NULL COMMENT '登录密码，MD5加密',
  `rolecode` varchar(32) DEFAULT NULL COMMENT '角色CODE',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `createuser` varchar(32) DEFAULT NULL COMMENT '创建者ID',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者ID',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbuser` */

insert  into `tbuser`(`userid`,`username`,`password`,`rolecode`,`status`,`note`,`createuser`,`createdate`,`updateuser`,`updatedate`) values ('admin','管理员','b59c67bf196a4758191e42f76670ceba','admin',1,'admin','admin','2016-02-08 00:00:00','admin','2016-02-20 00:23:17');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
