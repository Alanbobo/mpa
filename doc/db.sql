----------------------------------------------
-- Export file for user IFDS                --
-- Created by r25437 on 2018/8/16, 10:02:19 --
----------------------------------------------

spool dbexp.log

prompt
prompt Creating table MODULE_CONFIG
prompt ============================
prompt
create table IFDS.MODULE_CONFIG
(
  module_id             VARCHAR2(64) not null,
  module_name           VARCHAR2(32) not null,
  module_desc           VARCHAR2(128),
  module_config_content CLOB not null,
  dis_order             INTEGER not null,
  dis_begin_time        DATE not null,
  dis_end_time          DATE not null,
  update_time           DATE
);
comment on table IFDS.MODULE_CONFIG
  is '模版配置表，用于模版基础配置及预览使用';
comment on column IFDS.MODULE_CONFIG.module_id
  is '主键  GUID';
comment on column IFDS.MODULE_CONFIG.module_name
  is '模版名称';
comment on column IFDS.MODULE_CONFIG.module_desc
  is '模版描述';
comment on column IFDS.MODULE_CONFIG.module_config_content
  is '模版配置内容，用于前端展示';
comment on column IFDS.MODULE_CONFIG.dis_order
  is '模版配置内容，展示顺序（默认1000）';
comment on column IFDS.MODULE_CONFIG.dis_begin_time
  is '模版配置内容，展示生效时间';
comment on column IFDS.MODULE_CONFIG.dis_end_time
  is '模版配置内容，展示失效时间';
comment on column IFDS.MODULE_CONFIG.update_time
  is '更新时间';
create index IFDS.MODULE_CONFIG_IDX on IFDS.MODULE_CONFIG (MODULE_NAME, DIS_BEGIN_TIME, DIS_END_TIME);
alter table IFDS.MODULE_CONFIG
  add primary key (MODULE_ID);

prompt
prompt Creating table MODULE_RELEASE
prompt =============================
prompt
create table IFDS.MODULE_RELEASE
(
  module_id             VARCHAR2(64) not null,
  module_name           VARCHAR2(32) not null,
  module_desc           VARCHAR2(128),
  module_config_content CLOB not null,
  dis_order             INTEGER not null,
  dis_begin_time        DATE not null,
  dis_end_time          DATE not null,
  release_time          DATE
);
comment on table IFDS.MODULE_RELEASE
  is '模版发布表，模版发布后方可在大屏展示';
comment on column IFDS.MODULE_RELEASE.module_id
  is '主键  GUID';
comment on column IFDS.MODULE_RELEASE.module_name
  is '模版名称';
comment on column IFDS.MODULE_RELEASE.module_desc
  is '模版描述';
comment on column IFDS.MODULE_RELEASE.module_config_content
  is '模版配置内容，用于前端展示';
comment on column IFDS.MODULE_RELEASE.release_time
  is '更新时间';
create index IFDS.MODULE_RELEASE_IDX on IFDS.MODULE_RELEASE (MODULE_NAME, DIS_BEGIN_TIME, DIS_END_TIME);
alter table IFDS.MODULE_RELEASE
  add primary key (MODULE_ID);

prompt
prompt Creating table QRTZ_JOB_DETAILS
prompt ===============================
prompt
create table IFDS.QRTZ_JOB_DETAILS
(
  sched_name        VARCHAR2(120) not null,
  job_name          VARCHAR2(200) not null,
  job_group         VARCHAR2(200) not null,
  description       VARCHAR2(250),
  job_class_name    VARCHAR2(250) not null,
  is_durable        VARCHAR2(1) not null,
  is_nonconcurrent  VARCHAR2(1) not null,
  is_update_data    VARCHAR2(1) not null,
  requests_recovery VARCHAR2(1) not null,
  job_data          BLOB
);
create index IFDS.IDX_QRTZ_J_GRP on IFDS.QRTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);
create index IFDS.IDX_QRTZ_J_REQ_RECOVERY on IFDS.QRTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);
alter table IFDS.QRTZ_JOB_DETAILS
  add primary key (SCHED_NAME, JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_TRIGGERS
prompt ============================
prompt
create table IFDS.QRTZ_TRIGGERS
(
  sched_name     VARCHAR2(120) not null,
  trigger_name   VARCHAR2(200) not null,
  trigger_group  VARCHAR2(200) not null,
  job_name       VARCHAR2(200) not null,
  job_group      VARCHAR2(200) not null,
  description    VARCHAR2(250),
  next_fire_time INTEGER,
  prev_fire_time INTEGER,
  priority       INTEGER,
  trigger_state  VARCHAR2(16) not null,
  trigger_type   VARCHAR2(8) not null,
  start_time     INTEGER not null,
  end_time       INTEGER,
  calendar_name  VARCHAR2(200),
  misfire_instr  INTEGER,
  job_data       BLOB
);
create index IFDS.IDX_QRTZ_T_C on IFDS.QRTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME);
create index IFDS.IDX_QRTZ_T_G on IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IFDS.IDX_QRTZ_T_J on IFDS.QRTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IFDS.IDX_QRTZ_T_JG on IFDS.QRTZ_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IFDS.IDX_QRTZ_T_NEXT_FIRE_TIME on IFDS.QRTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME);
create index IFDS.IDX_QRTZ_T_NFT_MISFIRE on IFDS.QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);
create index IFDS.IDX_QRTZ_T_NFT_ST on IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);
create index IFDS.IDX_QRTZ_T_NFT_ST_MISFIRE on IFDS.QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);
create index IFDS.IDX_QRTZ_T_NFT_ST_MISFIRE_GRP on IFDS.QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);
create index IFDS.IDX_QRTZ_T_N_G_STATE on IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IFDS.IDX_QRTZ_T_N_STATE on IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IFDS.IDX_QRTZ_T_STATE on IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE);
alter table IFDS.QRTZ_TRIGGERS
  add primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_TRIGGERS
  add foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP)
  references IFDS.QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_BLOB_TRIGGERS
prompt =================================
prompt
create table IFDS.QRTZ_BLOB_TRIGGERS
(
  sched_name    VARCHAR2(120) not null,
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  blob_data     BLOB
);
alter table IFDS.QRTZ_BLOB_TRIGGERS
  add primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_BLOB_TRIGGERS
  add foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_CALENDARS
prompt =============================
prompt
create table IFDS.QRTZ_CALENDARS
(
  sched_name    VARCHAR2(120) not null,
  calendar_name VARCHAR2(200) not null,
  calendar      BLOB not null
);
alter table IFDS.QRTZ_CALENDARS
  add primary key (SCHED_NAME, CALENDAR_NAME);

prompt
prompt Creating table QRTZ_CRON_TRIGGERS
prompt =================================
prompt
create table IFDS.QRTZ_CRON_TRIGGERS
(
  sched_name      VARCHAR2(120) not null,
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  cron_expression VARCHAR2(120) not null,
  time_zone_id    VARCHAR2(80)
);
alter table IFDS.QRTZ_CRON_TRIGGERS
  add primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_CRON_TRIGGERS
  add foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_FIRED_TRIGGERS
prompt ==================================
prompt
create table IFDS.QRTZ_FIRED_TRIGGERS
(
  sched_name        VARCHAR2(120) not null,
  entry_id          VARCHAR2(95) not null,
  trigger_name      VARCHAR2(200) not null,
  trigger_group     VARCHAR2(200) not null,
  instance_name     VARCHAR2(200) not null,
  fired_time        INTEGER not null,
  sched_time        INTEGER not null,
  priority          INTEGER not null,
  state             VARCHAR2(16) not null,
  job_name          VARCHAR2(200),
  job_group         VARCHAR2(200),
  is_nonconcurrent  VARCHAR2(1),
  requests_recovery VARCHAR2(1)
);
create index IFDS.IDX_QRTZ_FT_INST_JOB_REQ_RCVRY on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
create index IFDS.IDX_QRTZ_FT_JG on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IFDS.IDX_QRTZ_FT_J_G on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IFDS.IDX_QRTZ_FT_TG on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IFDS.IDX_QRTZ_FT_TRIG_INST_NAME on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
create index IFDS.IDX_QRTZ_FT_T_G on IFDS.QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_FIRED_TRIGGERS
  add primary key (SCHED_NAME, ENTRY_ID);

prompt
prompt Creating table QRTZ_LOCKS
prompt =========================
prompt
create table IFDS.QRTZ_LOCKS
(
  sched_name VARCHAR2(120) not null,
  lock_name  VARCHAR2(40) not null
);
alter table IFDS.QRTZ_LOCKS
  add primary key (SCHED_NAME, LOCK_NAME);

prompt
prompt Creating table QRTZ_PAUSED_TRIGGER_GRPS
prompt =======================================
prompt
create table IFDS.QRTZ_PAUSED_TRIGGER_GRPS
(
  sched_name    VARCHAR2(120) not null,
  trigger_group VARCHAR2(200) not null
);
alter table IFDS.QRTZ_PAUSED_TRIGGER_GRPS
  add primary key (SCHED_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_SCHEDULER_STATE
prompt ===================================
prompt
create table IFDS.QRTZ_SCHEDULER_STATE
(
  sched_name        VARCHAR2(120) not null,
  instance_name     VARCHAR2(200) not null,
  last_checkin_time INTEGER not null,
  checkin_interval  INTEGER not null
);
alter table IFDS.QRTZ_SCHEDULER_STATE
  add primary key (SCHED_NAME, INSTANCE_NAME);

prompt
prompt Creating table QRTZ_SIMPLE_TRIGGERS
prompt ===================================
prompt
create table IFDS.QRTZ_SIMPLE_TRIGGERS
(
  sched_name      VARCHAR2(120) not null,
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  repeat_count    INTEGER not null,
  repeat_interval INTEGER not null,
  times_triggered INTEGER not null
);
alter table IFDS.QRTZ_SIMPLE_TRIGGERS
  add primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_SIMPLE_TRIGGERS
  add foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_SIMPROP_TRIGGERS
prompt ====================================
prompt
create table IFDS.QRTZ_SIMPROP_TRIGGERS
(
  sched_name    VARCHAR2(120) not null,
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  str_prop_1    VARCHAR2(512),
  str_prop_2    VARCHAR2(512),
  str_prop_3    VARCHAR2(512),
  int_prop_1    INTEGER,
  int_prop_2    INTEGER,
  long_prop_1   INTEGER,
  long_prop_2   INTEGER,
  dec_prop_1    NUMBER(13,4),
  dec_prop_2    NUMBER(13,4),
  bool_prop_1   VARCHAR2(1),
  bool_prop_2   VARCHAR2(1)
);
alter table IFDS.QRTZ_SIMPROP_TRIGGERS
  add primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table IFDS.QRTZ_SIMPROP_TRIGGERS
  add foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references IFDS.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table SCHEDULE_JOB
prompt ===========================
prompt
create table IFDS.SCHEDULE_JOB
(
  job_id          INTEGER not null,
  bean_name       VARCHAR2(200),
  method_name     VARCHAR2(100),
  params          VARCHAR2(2000),
  cron_expression VARCHAR2(100),
  status          INTEGER,
  remark          VARCHAR2(255),
  create_time     DATE
);
alter table IFDS.SCHEDULE_JOB
  add primary key (JOB_ID);

prompt
prompt Creating table SYS_CONFIG
prompt =========================
prompt
create table IFDS.SYS_CONFIG
(
  id     INTEGER not null,
  key    VARCHAR2(50),
  value  VARCHAR2(2000),
  status INTEGER default 1,
  remark VARCHAR2(500)
);
alter table IFDS.SYS_CONFIG
  add primary key (ID);

prompt
prompt Creating table SYS_LOG
prompt ======================
prompt
create table IFDS.SYS_LOG
(
  id          INTEGER not null,
  username    VARCHAR2(50),
  operation   VARCHAR2(50),
  method      VARCHAR2(200),
  params      VARCHAR2(4000),
  time        INTEGER not null,
  ip          VARCHAR2(64),
  create_date DATE
);
alter table IFDS.SYS_LOG
  add primary key (ID);

prompt
prompt Creating table SYS_MENU
prompt =======================
prompt
create table IFDS.SYS_MENU
(
  menu_id   INTEGER not null,
  parent_id INTEGER,
  name      VARCHAR2(50),
  url       VARCHAR2(200),
  perms     VARCHAR2(500),
  type      INTEGER,
  icon      VARCHAR2(50),
  order_num INTEGER
);
alter table IFDS.SYS_MENU
  add primary key (MENU_ID);

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table IFDS.SYS_ROLE
(
  role_id        INTEGER not null,
  role_name      VARCHAR2(100),
  remark         VARCHAR2(100),
  create_user_id INTEGER,
  create_time    DATE
);
alter table IFDS.SYS_ROLE
  add primary key (ROLE_ID);

prompt
prompt Creating table SYS_ROLE_MENU
prompt ============================
prompt
create table IFDS.SYS_ROLE_MENU
(
  id      INTEGER not null,
  role_id INTEGER,
  menu_id INTEGER
);
alter table IFDS.SYS_ROLE_MENU
  add primary key (ID);

prompt
prompt Creating table SYS_USER
prompt =======================
prompt
create table IFDS.SYS_USER
(
  user_id        INTEGER not null,
  username       VARCHAR2(50) not null,
  password       VARCHAR2(100),
  salt           VARCHAR2(20),
  email          VARCHAR2(100),
  mobile         VARCHAR2(100),
  status         INTEGER,
  create_user_id INTEGER,
  create_time    DATE
);
alter table IFDS.SYS_USER
  add primary key (USER_ID);

prompt
prompt Creating table SYS_USER_ROLE
prompt ============================
prompt
create table IFDS.SYS_USER_ROLE
(
  id      INTEGER not null,
  user_id INTEGER,
  role_id INTEGER
);
alter table IFDS.SYS_USER_ROLE
  add primary key (ID);

prompt
prompt Creating table SYS_USER_TOKEN
prompt =============================
prompt
create table IFDS.SYS_USER_TOKEN
(
  user_id     VARCHAR2(64) not null,
  token       VARCHAR2(100) not null,
  expire_time DATE,
  update_time DATE
);
alter table IFDS.SYS_USER_TOKEN
  add primary key (USER_ID);

prompt
prompt Creating table WEATHER_INFO
prompt ===========================
prompt
create table IFDS.WEATHER_INFO
(
  weather_id      VARCHAR2(64) not null,
  weather_type    VARCHAR2(32) not null,
  weather_name    VARCHAR2(64) not null,
  weather_desc    VARCHAR2(128) not null,
  weather_content CLOB,
  update_time     DATE
);
alter table IFDS.WEATHER_INFO
  add primary key (WEATHER_ID);


spool off
