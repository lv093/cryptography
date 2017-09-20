/*==============================================================*/
/* Table: pki_user                                    */
/*==============================================================*/

drop table if exists pki_user;

create table pki_user
(
  record_id            varchar(64) not null comment '记录标识唯一',
  user_email           varchar(64) comment '用户邮箱',
  password             varchar(64) comment '登录密码',
  user_name            varchar(64) comment '用户姓名，为邮箱前缀',
  user_tel             varchar(64) comment '用户电话',
  company_name         varchar(64) comment '公司名',
  company_tel          varchar(64) comment '公司电话',
  company_address      varchar(64) comment '公司地址',
  post_code            varchar(64) comment '公司邮编',
  company_id           varchar(64) comment '公司编号',
  pc_code              varchar(64) comment 'pc编号',
  other_id_name       varchar(64) comment '其他信息名',
  other_id_num         varchar(64) comment '其他信息内容'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 comment 'User用户数据存储';